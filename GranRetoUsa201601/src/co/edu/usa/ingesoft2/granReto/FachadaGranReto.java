package co.edu.usa.ingesoft2.granReto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
import java.util.Stack;

public class FachadaGranReto implements IFachadaGranReto{
	
	//ALMACENA NOTACION POSFIJA----------------------------------
	LinkedList<String> expresion;

	//CONSTRUCTOR VACIO------------------------------------------
	public FachadaGranReto() {
		expresion = new LinkedList<String>();
	}

	//CARGA Y VALIDACION DE DATOS--------------------------------
	public void cargarArchivo(String rutaArchivo) throws GranRetoException {
		try {
			//COLA PARA NOTACION POSFIJA TEMPORAL-----------------
			LinkedList<String> datos = new LinkedList<String>();
			
			//LECTURA DE ARCHIVO----------------------------------
			BufferedReader b = new BufferedReader(new FileReader(rutaArchivo));
			String cadena;
			
			//ALMACENA RESULTADO DE VALIDACION--------------------
			boolean validacion=true;
			
			//RECORRER ARCHIVO------------------------------------
			while((cadena = b.readLine())!=null) {
				//GUARDA VALOR INICIAL----------------------------
				String linea=cadena;
				//LINEA NUEVA SIN ESPACIOS------------------------
				cadena=quitarEspacios(cadena);

				//VALIDACION CARACTERES---------------------------
				boolean valCaracteres = validarCaracteres(cadena);
				
				//REEMPLAZAR COMAS POR PUNTOS---------------------
				cadena=remplazarComas(cadena);
				
				//VALIDACION LINEA--------------------------------
				boolean valLinea = validarLinea(cadena);
				
				//VALIDACION DE ESPACIOS JUNTO A COMAS------------
				boolean valEspaciosComas = validarEspaciosComas(linea);

				//RESULTADO VALIDACIONES--------------------------
				validacion=(valCaracteres && valLinea && valEspaciosComas);
				
				//AGREGAR LINEA O CANCELAR CARGAR ARCHIVO---------
		    	if(validacion) datos.add(cadena);
		    	else break;
		    }
		    b.close();
		    
		    //REMPLAZAMIENTO DE DATOS Y ELIMINACION DE SALTOS DE LINEA
		    boolean nulo=true;
		    if(datos.size()==0){
		    	nulo=false;
		    }
		    if(validacion && nulo) {
		    	expresion=datos;
		    	quitarSaltosDeLinea();
		    	//System.out.println("Se reemplazo la expresion");
		    }//else throw new GranRetoException("Los datos Ingresados no son validos! (2)");
		} catch (IOException e) {
			//throw new GranRetoException("El Archivo No Existe! (1)");
		} 
	}
	
	//CALCULO DE EXPRESION POSFIJA------------
	public String calcular() {
		try {
			
			Stack<String> aux = new Stack<String>();
			Stack<String> pila = new Stack<String>();
			int cantDatos = expresion.size();
			for (int i=0;i<cantDatos; i++) {
				aux.add(expresion.pop());
				expresion.add(aux.peek());
			}
			for (int i=0;i<cantDatos; i++) {
				pila.add(aux.pop());
			}
			aux.removeAllElements();
			
			//INICIO CALCULO----------------------------------
			
			while(!pila.isEmpty()){
				String valor = pila.pop();
				if(validarNumero(valor)){
					aux.add(valor);
				}else{
					char operacion= valor.charAt(0);
					double valor1=Double.parseDouble(aux.pop());
					double valor2=Double.parseDouble(aux.pop());
					double resultado=0;
					switch (operacion) {
						case '+':resultado=valor2+valor1;break;
						case '-':resultado=valor2-valor1;break;
						case '*':resultado=valor2*valor1;break;
						case '/':resultado=valor2/valor1;break;
						case '^':resultado=Math.pow(valor2, valor1);break;
						default:;break;
					}
					//System.out.println(valor1+""+operacion+""+valor2);
					aux.add(resultado+"");
				}
			}
			if(aux.size()>1){
				return "ERROR";
			}else{
				return imprimirResultado(Double.parseDouble(aux.pop()));
			}
		} catch (Exception e) {
			return "ERROR";
		}	
	}
	
	public String quitarEspacios(String cadena){
		String[] datos=cadena.split(" ");
		String retorno="";
		for (int i = 0; i < datos.length; i++) {
			retorno+=datos[i];
		}
		datos=retorno.split("\t");
		retorno="";
		for (int i = 0; i < datos.length; i++) {
			retorno+=datos[i];
		}
		return retorno;
	}
	
	public boolean validarCaracteres(String linea){
		char[] entradasValidas={'0','1','2','3','4','5','6','7','8','9',',','E','e','+','-','*','/','^'};
		char[] datos=linea.toCharArray();
		for (int i = 0; i < datos.length; i++) {
			boolean val=false;
			for (int j = 0; j < entradasValidas.length; j++)
				if(datos[i]==entradasValidas[j])val=true;
			if(!val)return false;
		}
		return true;
	}
	
	public boolean validarLinea(String linea){
		char[] datos=linea.toCharArray();
		if(datos.length>1){
			boolean num=validarNumero(linea);
			if(num){
				char[] cad=linea.toCharArray();
				for (int i = 0; i < datos.length; i++) {
					if(cad[i]=='+'){
						return false;
					}
				}
				return true;
			}else{
				return num;
			}
		}
		else return true;	
	}
	
	public boolean validarEspaciosComas(String linea){
		String[] existComa = linea.split(",");
		char[] aux1=linea.toCharArray();
		if(existComa.length==2){
			for (int i = 0; i < existComa.length; i++) {
				char[] caracteres = existComa[i].toCharArray();
				if(aux1[0]!=','){
					if(i==0 && (caracteres[caracteres.length-1]==' ' || caracteres[caracteres.length-1]=='\t')){
						return false;
					}
				}
				
				if(i==1 && (caracteres[0]==' ' || caracteres[0]=='\t')){
					return false;
				}
			}
			return true;
		}else{
			return true;
		}
	}
			
	public boolean validarNumero(String linea){
		try {
			Double.parseDouble(linea);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public String remplazarComas(String cadena){
		cadena=cadena.replace(',','.');
		return cadena;
	}
	
	public void quitarSaltosDeLinea(){
		int puntero=0;
		while(true){
			String dato = expresion.get(puntero);
			String[] cad = dato.split(" ");
			if(cad.length==0 || dato.equals("") || dato.equals(null)){
				expresion.remove(puntero);
			}else{
				puntero++;
			}
			if(puntero>=expresion.size()){
				break;
			}
		}
	}
	
	public void imprimirExpresionPosfija(){
		System.out.println(expresion);
	}
	
	public double Redondear(double numero,int digitos){
	      String val = numero+"";
	      BigDecimal big = new BigDecimal(val);
	      big = big.setScale(digitos, RoundingMode.HALF_UP);
	      return Double.parseDouble(big+"");
	}

	public String imprimirResultado(double resultado){
		resultado=Redondear(resultado, 10);
		DecimalFormatSymbols formatoDePuntos= new DecimalFormatSymbols();
		formatoDePuntos.setDecimalSeparator('.');
		DecimalFormat num = new DecimalFormat("#,###.0000000000");
		String entrada= num.format(resultado)+"";
		char[] aux=entrada.toCharArray();
		if(aux[0]==','){
			entrada="0"+entrada;
		}
		String salida="";
		int cont=0;
		String[] entrada2= entrada.split(",");
		String[] entrada3= entrada2[0].split("");
		for(int i=entrada3.length-1; i>=0; i--){
			if(!entrada3[i].equals(".")){
				if(!entrada3[i].equalsIgnoreCase("-")){
					cont=cont+1;
				}
				salida=entrada3[i]+salida;
				
				if(cont==3 && i!=0 && !entrada3[i-1].equals("-")){
					salida="."+salida;
				}
				else if(cont==6 && i!=0){
					salida="'"+salida;
				}
			}
		}
		String numero0[]=entrada2[1].split("");
		int cont2=0;
		for(int j=0; j<numero0.length; j++)
		{
			if(numero0[j].equals("0")){
				cont2=cont2+1;
			}
		}
		if(cont2!=numero0.length){
			if(salida.equalsIgnoreCase("-")) salida+="0";
			salida=quitarCeros(salida+","+entrada2[1]);
		}
		return salida;
	}
	
	public String quitarCeros(String salida){
		char[] salidaFinal=salida.toCharArray();
		int cont=0;
		String finalFinalNoDaMas="";
		if(salidaFinal.length<=1){
			finalFinalNoDaMas=salida;
		}else{
			for(int i=salidaFinal.length-1; i>=0; i--){
				if(salidaFinal[i]!='0'){
					break;
				}
				else
				{
					cont=cont+1;
				}
			}
			
			for(int j=0; j<salidaFinal.length-cont; j++){
				finalFinalNoDaMas+=salidaFinal[j];
			}
		}
		return finalFinalNoDaMas;
	}
}

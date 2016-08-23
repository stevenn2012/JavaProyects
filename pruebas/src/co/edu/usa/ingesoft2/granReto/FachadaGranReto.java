package co.edu.usa.ingesoft2.granReto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class FachadaGranReto implements IFachadaGranReto {

	ArrayList<String> datosArchivo;
	
	String[] operadores = {"+","-","*","/","^"};

	public FachadaGranReto() {
		datosArchivo = new ArrayList<String>();
	}

	public void cargarArchivo(String rutaArchivo) throws GranRetoException {
		try {
			FileReader fr = new FileReader(rutaArchivo);
			BufferedReader br = new BufferedReader(fr);
			String datos;
			datosArchivo.clear();
			while ((datos = br.readLine()) != null) {
				String sinEspacios = datos.replaceAll("\\s","");
				sinEspacios = convertirNumero(sinEspacios);
				if(!(sinEspacios.trim().length()==0)){
					datosArchivo.add(sinEspacios);
				}				
			}
			//System.out.println(datosArchivo);
		} catch (IOException ex) {
			throw new GranRetoException("Problema en la lectura del archivo");
		}
	}

	public String calcular() {
		if(datosArchivo.isEmpty())
		{
			return "ERROR";
		}
		Stack<String> entrada = new Stack<String>();
		Stack<String> resultado = new Stack<String>();
		String solucion;
		if(!esNumero(datosArchivo.get(0)) || !esSigno(datosArchivo.get(datosArchivo.size()-1)))
		{
			if(esNumero(datosArchivo.get(0)) && datosArchivo.get(0).equals(datosArchivo.get(datosArchivo.size()-1))){}
			else
			{
				return "ERROR";
			}
		}
		for(int i = datosArchivo.size()-1; i>=0;i--)
		{
			if(datosArchivo.get(i).charAt(0)=='+' && datosArchivo.get(i).length()>1)
			{
				return "ERROR";
			}
			if(datosArchivo.get(i).equals("malo"))
			{
				return "ERROR";
			}
			entrada.push(datosArchivo.get(i));
		}
		while(!entrada.isEmpty()){
			if(esSigno(entrada.peek()) && resultado.size()>1){
				resultado.push(operar(entrada.pop(),resultado.pop(),resultado.pop())+"");
			}
			else if(!esSigno(entrada.peek())){
				resultado.push(entrada.pop());
			}
			else{
				return "ERROR";
			}
		}
		if(resultado.size()>1)
		{
			return "ERROR";
		}
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(10);
		solucion = df.format(Double.parseDouble(resultado.peek()));
		//System.out.println(solucion);
		return transformar(solucion);
		
	}
	
	public boolean esNumero(String dato){
		try{
			Double.parseDouble(dato);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean esSigno(String dato){
		if(dato.length()==1)
		{
			for(int i=0;i<operadores.length;i++)
			{
				if(dato.compareTo(operadores[i])==0)
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			return false;
		}
		
	}
	
	public String convertirNumero(String dato){
		String convertido;
		if(dato.indexOf('.') !=-1){
			return "malo";
		}
		convertido = dato.replace(",",".");
		return convertido;		
	}
	
	public double operar(String operador,String n2, String n1)
	{
		double num1 = Double.parseDouble(n1);
		double num2 = Double.parseDouble(n2);
		if(operador.equals("+")){
			return num1+num2;
		}
		else if(operador.equals("-")){
			return num1-num2;
		}
		else if(operador.equals("*")){
			return num1*num2;
		}
		else if(operador.equals("/")){
			return num1/num2;
		}
		else if(operador.equals("^")){
			return Math.pow(num1, num2);
		}
		return 0;
	}
	
	public String transformar(String solucion)
	{
		String resultado ="";
		ArrayList<String> formato = new ArrayList<String>();
		String[] separado = solucion.split(",");
		int contador = 0;
		for(int i = separado[0].length()-1; i>=0 ;i--){
			if(separado[0].charAt(i)=='-'){
				formato.add(String.valueOf(separado[0].charAt(i)));
				continue;
			}
			formato.add(String.valueOf(separado[0].charAt(i)));
			if(contador+1==3 && contador!=separado[0].length()-1)
			{
				formato.add(".");
				contador++;
				continue;
			}
			if(contador>0 && (contador+1)%3 == 0 && contador!=separado[0].length()-1)
			{
				formato.add("'");
			}
			contador++;
		}
		int tamano = formato.size();
		for(int i = tamano-1; i>=0;i--){
			resultado = resultado+formato.remove(i);
		}
		if(separado.length>1)
		{
			resultado = resultado+","+separado[1];
		}
		if (resultado.charAt(0) == ',')
		{
			resultado = "0"+resultado;
			resultado =resultado.replace(",",".");
			resultado = redondear(resultado);
			resultado =resultado.replace(".", ",");
		}
		if(resultado.charAt(0)=='-' && resultado.charAt(1)==',')
		{
			resultado = resultado.replace("-", "-0");
		}				
		return resultado;
	}
	
	public String redondear(String dato)
	{
		BigDecimal bd = new BigDecimal(dato);
		bd = bd.setScale(10, BigDecimal.ROUND_HALF_UP);
		return String.valueOf(bd);		
	}

}

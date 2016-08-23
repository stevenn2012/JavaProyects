package Logic;

/*************************************************************************/
/* Versión: 1                                                            */
/* Autor: Steven Puerto                                                  */
/* Fecha: 15/05/2016 dd/mm/aaa 											 */
/* Descripción: Programa para contar palabras, parrafos, lineas, letras, */ 
/* vocales y consonantes de un texto  									 */
/*************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;

public class Conteo {
	static int palabras=0, letras=0, parrafos=0, vocales=0, consonantes=0, lineas=0;
	static boolean valParrafo=false;
	
	public static void reiniciarValores(){
		palabras=0;
		letras=0;
		parrafos=0;
		vocales=0;
		consonantes=0;
		lineas=0;
		valParrafo=false;
	}
	
	public static int[] contar(String ruta) {
		try {
			reiniciarValores();
			String cadena;
		    BufferedReader b = new BufferedReader(new FileReader(ruta));
		    while((cadena = b.readLine())!=null) {
		    	validarLetraCadena(cadena);
		    	validarPalabra(cadena);
		    	validarParrafo(cadena);
		    	lineas++;
		    }
		    if(valParrafo) parrafos++;
		    b.close();
		} catch (Exception e) {
			System.out.println("Error(1)...");
			e.printStackTrace();
			System.exit(0);
		}
		int[] ret = {palabras,letras,parrafos,vocales,consonantes,lineas};
		return ret;
	}
	
	public static void validarPalabra(String cadena){
		cadena=cadena.toLowerCase();
		String[] subCadenas=cadena.split(" ");
		for (int i = 0; i < subCadenas.length; i++) {
			char[] caracts=subCadenas[i].toCharArray();
			boolean val=true;
			for (int j = 1; j < caracts.length-1; j++) 
				if(!validarLetra(2,caracts[j])) val=false;
			if(val==true) palabras++;
		}
	}
	
	public static boolean validarParrafo(String cadena){
		String[] cad = cadena.split(" ");
		if(valParrafo){
			if(cad.length==0 || cadena.equals("") ||cadena.equals(null))	parrafos++;
			valParrafo=false;
		}else{
			if(cad.length!=0 || !cadena.equals("") || !cadena.equals(null)){
				String[] subCad = cadena.split(" ");
				char[] caracts = subCad[subCad.length-1].toCharArray();
				if(caracts.length>0){
					if(caracts[caracts.length-1]=='.'){
						valParrafo=true;
					}
				}
			}
		}
		return false;
	}
	
	public static void validarLetraCadena(String cadena){
		cadena=cadena.toLowerCase();
    	char[] caracts=cadena.toCharArray();
    	for (int i = 0; i < cadena.length(); i++) validarLetra(1,caracts[i]);
	}
	
	public static boolean validarLetra(int uso, char caract){
		if((caract>=97 && caract<=122) || caract==241){
			if(uso==1)letras++;
			if(caract==97 || caract==101 || caract==105 || caract==111 || caract==117){
				if(uso==1)vocales++;
			}else if(uso==1)consonantes++;
			return true;
		}
		if((caract>=192 && caract<=197)||(caract>=200 && caract<=207)||(caract>=210 && caract<=214)||(caract>=217 && caract<=220)||(caract>=224 && caract<=229)||(caract>=232 && caract<=239)||(caract>=242 && caract<=246)||(caract>=249 && caract<=252)){
			if(uso==1){
				vocales++;
				letras++;
			}
			return true;
		}
		return false;
	}
}

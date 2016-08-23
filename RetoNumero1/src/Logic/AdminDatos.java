package Logic;

/**************************************************************/
/* Versión: 1                                                 */
/* Autor: Steven Puerto                                       */
/* Fecha: 05/05/2016 	mm/aaaa                               */
/* Descripción: Calcula todos los datos requeridos y obtiene  */
/* 				los datos del archivo txt					  */					 
/**************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;

import Datos.Array;

public class AdminDatos {
	
	static Array arreglo = new Array();
	
	public static void leerArchivo(String ruta){ //lee el archivo indicado en la clase Reto1 y almacena los datos en un objeto de la clase Array
		try {
			arreglo.clear();
			String cadena;
		     BufferedReader b = new BufferedReader(new FileReader(ruta));
		     while((cadena = b.readLine())!=null) {
		    	 arreglo.add(Double.parseDouble(cadena));
		     }
		     b.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	public static double calcularPromedio(){ //calcula el promedio
		double suma=0;
		for (int i = 0; i < arreglo.size(); i++) {
			suma+=arreglo.get(i);
		}
		return (suma/arreglo.size());
	}

	public static double calcularDesviacion(){// calcula la desviacion estandar
		double xavg=calcularPromedio();
		double suma=0;
		for (int i = 0; i < arreglo.size(); i++) {
			suma+=Math.pow((arreglo.get(i)-xavg), 2);
		}
		return Math.sqrt((suma)/(arreglo.size()-1));
	}
}

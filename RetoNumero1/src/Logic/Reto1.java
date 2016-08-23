package Logic;

/**************************************************************/
/* Versión: 1                                                 */
/* Autor: Steven Puerto                                       */
/* Fecha: 05/05/2016 	mm/aaaa                               */
/* Descripción: Programa para calcular Promedio y desviacion  */
/* 				standard de los numeros obtenidos desde un 	  */
/* 				archivo con extencion txt					  */					 
/**************************************************************/

//clase principal ejecuta metodos de AdminDatos 
public class Reto1{

	public static void main(String[] args) { //metodo principal 
		double[] datos = obtenerDatos("datos.txt"); //empieza la ejecucion de el programa y obtiene los datos requeridos basado en los datos del txt especificado el cual contiene los datos de la tabla 1
		double[] datos2 = obtenerDatos("datos2.txt");//empieza la ejecucion de el programa y obtiene los datos requeridos basado en los datos del txt especificado el cual contiene los datos de la tabla 2 
		
		System.out.println("\t\tPromedio\tdesviacion");
		System.out.println("Tabla 1 \t "+datos[0]+"\t\t "+datos[1]);
		System.out.println("Tabla 2 \t "+datos2[0]+"\t\t "+datos2[1]);
	}
	
	public static double[] obtenerDatos(String ruta){ //se encarga de llamar metodos de AdminDatos para obtener los calculos requeridos
		AdminDatos.leerArchivo(ruta);
		double[] datos ={redondearCifras(AdminDatos.calcularPromedio()),redondearCifras(AdminDatos.calcularDesviacion())};
		return datos;
	}
	
	public static double redondearCifras(double numero){ //redondea las cifras a 2 decimales
		return Math.rint(numero*100)/100;
	}
}

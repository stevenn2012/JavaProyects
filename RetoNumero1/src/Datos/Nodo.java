package Datos;

/**************************************************************/
/* Versión: 1                                                 */
/* Autor: Steven Puerto                                       */
/* Fecha: 05/05/2016 	mm/aaaa                               */
/* Descripción: Programa para calcular Promedio y desviacion  */
/* 				standard de los numeros obtenidos desde un 	  */
/* 				archivo con extencion txt					  */					 
/**************************************************************/

// Descripción: almacena los datos en variables double
public class Nodo {

	double dato;
	Nodo Siguiente;
	
	public Nodo(double dato, Nodo siguiente) { //constructos del nodo
		this.dato = dato;
		Siguiente = siguiente;
	}

	public double getDato() { //obtiene el dato en el nodo
		return dato;
	}

	public void setDato(double dato) { //edita el dato en el nodo
		this.dato = dato;
	}
}

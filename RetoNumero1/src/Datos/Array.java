package Datos;

/**************************************************************/
/* Versión: 1                                                 */
/* Autor: Steven Puerto                                       */
/* Fecha: 05/05/2016 	mm/aaaa                               */
/* Descripción: Programa para calcular Promedio y desviacion  */
/* 				standard de los numeros obtenidos desde un 	  */
/* 				archivo con extencion txt					  */					 
/**************************************************************/

//Descripción: encargado de la manipulacion de los nodos
public class Array {

	Nodo datos;
	int size=0; //Cantidad de datos almacenados
	
	public Array() { //constructor de la clase
		this.datos=new Nodo(-1, null);
	}
	
	public int size(){ //retorna la cantidad del elementos almacenados
		return size;
	}

	public boolean clear(){ //limpia la lista de nodos
		try {
			datos = null;
			datos = new Nodo(-1, null);
			size=0;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean add(double dato){ //agrega un nuevo nodo con su respectivo dato a la lista
		try {
			Nodo actual = datos;
			for (int i = 0; i < size; i++) {
				actual = actual.Siguiente;
			}
			actual.setDato(dato);
			actual.Siguiente = new Nodo(-1, null);
			size++;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isEmpty(){ //indica si la lista esta vacia
		if(size>0) return false;
		else return true;
	}
	
	public double get(int puntero){ //obtiene el dato en la posicion indicada
		try {
			Nodo actual = datos;
			for (int i = 0; i < puntero; i++) {
				actual = actual.Siguiente;
			}
			return actual.getDato();
		} catch (Exception e) {
			return -1;
		}		 
	}
	
	public boolean set(int puntero, int dato){ //edita el dato de la posicion indicada
		try {
			Nodo actual = datos;
			for (int i = 0; i < puntero; i++) {
				actual = actual.Siguiente;
			}
			actual.setDato(dato);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

package prueba;

import java.util.HashMap;

public class main {

	public static void main(String[] args) {
		
	}
	
	public static void prueba1(){
		HashMap<String, cantante> cantantes = new HashMap<String, cantante>();
		persona persona = new persona();
		animal animal = new animal ();
		cantantes.put("persona", persona);
		cantantes.put("animal", animal);		
		System.out.println(cantantes.get("animal").cantar());
	}
	
	public static void prueba2(){
		
	}
}

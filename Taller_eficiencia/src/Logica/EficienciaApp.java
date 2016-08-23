package Logica;

import java.io.IOException;
import java.util.ArrayList;

import Datos.Persona;

public class EficienciaApp {
	
	private static ArrayList<Persona> personas = new ArrayList<Persona>(); 
	
	public static void main(String[] args) {
		try {
			int op=0;
			do {
				System.out.println("1. Ingresar Persona.");
				System.out.println("2. Calcular eficiencia de una persona.");
				System.out.println("3. ver eficiencia de todas las personas.");
				System.out.println("4. Ver datos de todas las personas.");
				System.out.println("5. Salir");	
				op=Leer.datoInt();
				switch (op) {
					case 1:ingresarPersona();break;
					case 2:calcularEficiencia();break;
					case 3:verEficiencia();	break;
					case 4:verDatos();break;
					case 5:System.out.println("Ud ha salido");break;
					default:System.out.println("Opcion no implementada");break;
				}
			} while (op!=5);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ingresarPersona(){
		//							Nombres					Ho.In		Ho.End		Distancia
		personas.add(new Persona("Lenin_Sanchez",       	4, 30, 		6, 50, 		18.3));
		personas.add(new Persona("Juan_David",          	5, 30, 		6, 40, 		7.6));
		personas.add(new Persona("Jose_Paredes",        	5, 30, 		6, 30, 		21));
		personas.add(new Persona("Nicolas_gomez",       	6, 30, 		7, 00, 		1));
		personas.add(new Persona("Andrea_Ruiz",         	5, 40, 		6, 40, 		7.8));
		personas.add(new Persona("Cristian_peralta",    	4, 40, 		6, 50, 		19.1));
		personas.add(new Persona("Andrea_Tejada",       	6, 10, 		7, 00, 		0.7));
		personas.add(new Persona("Jorge_Arenas",        	5, 30, 		6, 50, 		10.1));
		personas.add(new Persona("Alejandra_Castillo",  	5, 00, 		6, 40, 		10.5));
		personas.add(new Persona("Diego_Barragan",      	5, 30, 		6, 50, 		7.7));
		personas.add(new Persona("Brian_Sterling",      	5, 00, 		6, 40, 		4.4));
		personas.add(new Persona("Paola_Vargas",        	5, 00, 		7, 00, 		7.7));
		personas.add(new Persona("Alejandro_Rodriguez", 	5, 30, 		6, 30, 		10.3));
		personas.add(new Persona("Camilo_Suarez",       	4, 50, 		6, 50, 		25.9));
		personas.add(new Persona("Sebastian_Avila",     	5, 30, 		7, 00, 		10.8));
		personas.add(new Persona("Luis_moreno",         	5, 14, 		6, 50,		9.3));
		personas.add(new Persona("Nicolas_Chicuazuque", 	4, 00, 		6, 55,		18.7));
	}
	
	public static void calcularEficiencia(){
		for(int i=0; i<personas.size(); i++){
			if(personas.get(i).getEficiencia()==-1){
				int tiempo=personas.get(i).horaEnd - personas.get(i).horaIn;
				tiempo*=60;
				tiempo+=(personas.get(i).minutosEnd - personas.get(i).minutosIn);
				personas.get(i).setEficiencia((personas.get(i).getDistancia()*1000)/tiempo);
			}
		}
	}
	
	public static void verEficiencia(){
		
	}
	
	public static void verDatos(){
		
	}
}

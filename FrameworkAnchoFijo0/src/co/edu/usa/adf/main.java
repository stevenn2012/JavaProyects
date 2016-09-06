package co.edu.usa.adf;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class main {

	public static void main(String[] args) {
		System.out.println("*******ArrayList*********");
		infoClass("java.util.ArrayList");
		
		System.out.println("*******String*********");
		infoClass("java.lang.String");
		
		System.out.println("*******Math*********");
		infoClass("java.lang.Math");
		
		System.out.println("*******Integer*********");
		infoClass("java.lang.Integer");
		
		System.out.println("*******Phone*********");
		infoClass("co.edu.usa.adf.PhoneContact");
	
	}
	
	public static void infoClass(String classRute){
		try {
			//Class<?> miClase = Class.forName("co.edu.usa.adf.PhoneContact");
			Class<?> miClase = Class.forName(classRute);
			
			System.out.println("Nombre: "+miClase.getName());
			System.out.println("Modifiers: "+Modifier.toString(miClase.getModifiers()));
			System.out.println("Parametros Tipo: "+Arrays.toString(miClase.getTypeParameters()));
			System.out.println("Interfaces: "+Arrays.toString(miClase.getInterfaces()));
			 
			String camino="";
			Class<?> miClaseherencia = Class.forName(classRute);
			String hereda;
			//!(hereda=miClaseherencia.getSuperclass().getName()).equalsIgnoreCase("")
			
			while(true){
				hereda=miClaseherencia.getSuperclass().getName();
				camino+=hereda;
				if(hereda.equalsIgnoreCase("java.lang.Object")){
					break;
				}else{
					camino+=", ";
				}
				miClaseherencia = Class.forName(hereda);
			}
			
			System.out.println("Camino Herencia: "+camino);
			
			System.out.println("Anotaciones: "+Arrays.toString(miClase.getAnnotations()));
			System.out.println("Atributos: "+Arrays.toString(miClase.getDeclaredFields()));
			System.out.println("Metodos: "+Arrays.toString(miClase.getMethods()));
			System.out.println("Constructores: "+Arrays.toString(miClase.getConstructors()));
			System.out.println();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

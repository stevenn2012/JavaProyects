package co.edu.usa.adf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;

import Fanfid.franfid;

public class inicio {

	public static void main(String[] args) {
		try {
			tomateContacto();
			tomateEventos();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void tomateContacto() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ParseException{
		System.out.println("Leyendo Descriptor...\n");
		franfid prueba = new franfid("co.edu.usa.adf.Contacto");
		
		System.out.println("Leyendo Datos...");
		ArrayList<Object> dats = prueba.leerArchivo("Datos/Archivos/Contactos.txt");
		System.out.println(dats+"\n");
		
		System.out.println("Guardando Datos...");
		prueba.escribirArchivo("datos.txt", dats);
		
		System.out.println("-----------------------------------------------");
	}
	
	public static void tomateEventos() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ParseException{
		System.out.println("Leyendo Descriptor...\n");
		franfid prueba = new franfid("co.edu.usa.adf.Evento");
		
		System.out.println("Leyendo Datos...");
		ArrayList<Object> dats = prueba.leerArchivo("Datos/Archivos/Eventos.txt");
		System.out.println(dats+"\n");
		
		System.out.println("Guardando Datos...");
		prueba.escribirArchivo("datosE.txt", dats);
		System.out.println("-----------------------------------------------");
	}
}
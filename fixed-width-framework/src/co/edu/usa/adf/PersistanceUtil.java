package co.edu.usa.adf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersistanceUtil {

	public static ArrayList<Object> readFile(String descriptor, String datos) throws NumberFormatException, Exception {
		
		ArrayList<Integer> widths = new ArrayList<Integer>();
		ArrayList<String> tipos = new ArrayList<String>();
		ArrayList<String> nombres = new ArrayList<String>();
		String clase="";
		boolean val=true;
		BufferedReader leerDescriptor= new BufferedReader(new FileReader(descriptor));
			for (int i = 0; i < ArchivoSize(descriptor); i++) {
				String cadena = leerDescriptor.readLine();
				
				if(cadena.indexOf("Class") > (-1) && val){
					clase = cadena.split(" ")[2];
					val=false;
				}
				if(cadena.indexOf("Attrib") > (-1)){
					tipos.add(cadena.split(" ")[2]);
					nombres.add(cadena.split(" ")[3]);
					widths.add(Integer.parseInt((cadena.split(" ")[4])));
				}
				if(cadena.indexOf("/Class") > (-1)){
					break;
				}
			}
		leerDescriptor.close();	
		return leerDatos(datos, widths, tipos, nombres, clase);
	}

	private static ArrayList<Object> leerDatos(String datos, ArrayList<Integer> widths, ArrayList<String> tipos,	ArrayList<String> nombres, String clase) throws Exception{
		
		ArrayList<Object> objetos = new ArrayList<Object>();
		
		BufferedReader leer= new BufferedReader(new FileReader(datos));
		for (int i = 0; i < ArchivoSize(datos); i++) {
			String cadena = leer.readLine();
			
			List<Object> chunks= new ArrayList<Object>();
			
			int pos=0;
			
			for(int j=0; j< widths.size(); j++){
				chunks.add(cadena.substring(pos, pos+widths.get(j)));
				pos+=widths.get(j);
			}
			
			Class<?> cls = Class.forName(clase);
			Object inst =cls.newInstance();
			
			for (int j = 0; j <chunks.size(); j++) {
				Class[] tipo = {verClass(tipos.get(j))};
				Method m = cls.getMethod(buscarMetodo(cls, nombres.get(j), tipo), tipo);
				cast(m, inst, tipos.get(j), chunks.get(j));
			}
			objetos.add(inst);
		}
		leer.close();
		
		return objetos;
	}
	
	public static void cast(Method m, Object inst, String tipo, Object dato) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		if(tipo.equalsIgnoreCase("boolean")){
			m.invoke(inst, Boolean.parseBoolean((dato+"").trim()));
		}
		
		if(tipo.equalsIgnoreCase("int")){
			m.invoke(inst, Integer.parseInt((dato+"").trim()));
		}
		
		if(tipo.equalsIgnoreCase("integer")){
			m.invoke(inst, Integer.parseInt((dato+"").trim()));
		}
		
		if(tipo.equalsIgnoreCase("long")){
			m.invoke(inst, Long.parseLong((dato+"").trim()));
		}
		
		if(tipo.equalsIgnoreCase("char")){
			m.invoke(inst, (dato+"").trim());
		}
		
		if(tipo.equalsIgnoreCase("string")){
			m.invoke(inst, (dato+"").trim());
		}
		
		if(tipo.equalsIgnoreCase("double")){
			m.invoke(inst, Double.parseDouble((dato+"").trim()));
		}
		
		if(tipo.equalsIgnoreCase("float")){
			m.invoke(inst, Float.parseFloat((dato+"").trim()));
		}
	}
	
	public static Class verClass(String tipo){
		Class<?> cls = null;
		if(tipo.equalsIgnoreCase("boolean")){
			cls = boolean.class;
		}
		
		if(tipo.equalsIgnoreCase("int")){
			cls = int.class;
		}
		
		if(tipo.equalsIgnoreCase("integer")){
			cls = java.lang.Integer.class;
		}
		
		if(tipo.equalsIgnoreCase("long")){
			cls = long.class;
		}
		
		if(tipo.equalsIgnoreCase("char")){
			cls = char.class;
		}
		
		if(tipo.equalsIgnoreCase("string")){
			cls = java.lang.String.class;
		}
		
		if(tipo.equalsIgnoreCase("double")){
			cls = double.class;
		}
		
		if(tipo.equalsIgnoreCase("float")){
			cls = float.class;
		}
		return cls;
	}

	public static int ArchivoSize(String ruta) throws Exception{
		int size=0;
		BufferedReader b = new BufferedReader(new FileReader(ruta));
		String cadena="";
		while((cadena=b.readLine())!=null){
	            size++;
		}
        b.close();
		return size;
	}
	
	public static void writeFile(String ruta, List<String> datos, int[] widths) throws IOException{
		BufferedWriter escribir= new BufferedWriter(new FileWriter(ruta));	
		//guardar datos en disco
		
	}
	
	public static String buscarMetodo(Class<?> cls, String nombreAtributo, Class<?>[] tipos){
		try {
			String nombre=(nombreAtributo.charAt(0)+"").toUpperCase();
			for (int i = 1; i < nombreAtributo.length(); i++)nombre+=nombreAtributo.charAt(i);	
			String methodName = "set"+nombre;
			
			Method metodos[] = cls.getMethods();
			
			
			for (int i = 0; i < metodos.length; i++) {
				if(metodos[i].getName().equalsIgnoreCase("set"+nombre)){
					return methodName;
				}else if(Arrays.toString(metodos[i].getParameterTypes()).equals(Arrays.toString(tipos)) && metodos[i].getName().indexOf(nombre)>(-1)){
					methodName= metodos[i].getName();
				}
			}
			return methodName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

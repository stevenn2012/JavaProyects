package co.edu.usa.adf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersistanceUtil {

	public static List<String> readFile(String ruta) throws IOException {
		List<String> records= new ArrayList<String>();
		BufferedReader leer= new BufferedReader(new FileReader(ruta));
			String cadena="";
			while((cadena=leer.readLine())!=null){
				records.add(cadena);
				
			}
		leer.close();
		return records;
	}
	
	public static void writeFile(String ruta) throws IOException{
			
	}
}

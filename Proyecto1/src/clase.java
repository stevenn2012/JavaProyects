import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class clase {

	static String ji="for(int i=0;i<10;i++){ for}";
	public static void main(String[] args) {
		
	}
	
	private static void buscarMetodos(){
		try {
			FileInputStream stream= new FileInputStream("C://Users//USUARIO//Desktop//JUANDA//SISTEMAS//Sistemas III//JUANITO//OrdenamientoYBusqueda//src//Datos//OrdenamientoMerge.java");
			DataInputStream entrada = new DataInputStream(stream);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String linea;
			while((linea = buffer.readLine())!=null)
			{
				if(linea.indexOf("public")>=0){
					if(){
						
					}
				}else if(linea.indexOf("private")>=0){
					
				}else if(linea.indexOf("protected")>=0){
					
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void validar(String linea) {
		
	}
}

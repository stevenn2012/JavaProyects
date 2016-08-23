package co.edu.usa.ingesoft2.granReto;

import java.io.File;

public class Main {

	static String carpeta="C:\\Users\\Steven\\workspace\\GranRetoUsa201601\\GranRetoUsa201601\\Pruebas/";
	static String archivos="prueba_";
	static String extension =".txt";
	static FachadaGranReto nueva;
	
	public static void main(String[] args) throws GranRetoException {
		nueva = new FachadaGranReto();
		for(int i=0;i<=totalArchivos(new File(carpeta))-1;i++){
			casos(i);
		}
	}
	
	public static void casos(int i){
		try {
			String archivo=archivos+i+extension;
			System.out.println(archivo+" ------------------------");
			nueva.cargarArchivo(carpeta+archivo);
			//System.out.print("Expresion: ");
			//nueva.imprimirExpresionPosfija();
			System.out.println(nueva.calcular());
			System.out.println("\n");
		} catch (GranRetoException e) {
			System.out.println("Error(main) "+e);
			System.out.println("\n");
		}
	}
	
	public static int totalArchivos(File directorio){
		int total = 0;
		String[] arrArchivos = directorio.list();
		total += arrArchivos.length;
		File tmpFile;
		for(int i=0; i<arrArchivos.length; ++i){
			tmpFile = new File(directorio.getPath() + "/" +arrArchivos[i]);
		    if(tmpFile.isDirectory()){
		    	total += totalArchivos(tmpFile);
		    }
		}
		return total;
	}
}

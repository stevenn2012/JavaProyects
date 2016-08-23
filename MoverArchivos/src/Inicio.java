import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Inicio {

	static String directorioRaiz = "J:\\Desorden\\cel";
	static String[] extensiones={"odt","gif","png","jpg","mp4","docx","pdf","pptx","xlsx","txt","doc","exe","zip","rar","msi","ppt","m","psd","webm","bat","mp3","jpeg"};
	static String[] directorios={
			"D:\\Descargas\\Datos\\Documentos\\Word",
			"D:\\Descargas\\Datos\\Imagenes\\Imagenes Gif",
			"D:\\Descargas\\Datos\\Imagenes",
			"D:\\Descargas\\Datos\\Imagenes",
			"D:\\Descargas\\Datos\\Videos",
			"D:\\Descargas\\Datos\\Documentos\\Word",
			"D:\\Descargas\\Datos\\Documentos\\Pdf",
			"D:\\Descargas\\Datos\\Documentos\\Powerpoint",
			"D:\\Descargas\\Datos\\Documentos\\Excel",
			"D:\\Descargas\\Datos\\Documentos\\Txt",
			"D:\\Descargas\\Datos\\Documentos\\Word",
			"D:\\Descargas\\Datos\\Programas",
			"D:\\Descargas\\Datos\\Documentos\\ZipRar",
			"D:\\Descargas\\Datos\\Documentos\\ZipRar",
			"D:\\Descargas\\Datos\\Programas",
			"D:\\Descargas\\Datos\\Documentos\\Powerpoint",
			"D:\\Descargas\\Datos\\Documentos\\Matlab",
			"D:\\Descargas\\Datos\\Imagenes\\psd",
			"D:\\Descargas\\Datos\\Videos\\webm",
			"D:\\Descargas\\Datos\\Documentos\\bat",
			"D:\\Descargas\\Datos\\Musica",
			"D:\\Descargas\\Datos\\Imagenes"
	};		
	
	public static void main(String[] args) {
		
		File f = new File(directorioRaiz);
		
		if (f.exists()){ // Directorio existe 
			//------------------------------------------------
			File[] ficheros = f.listFiles();
			for (int i=0;i<ficheros.length;i++){
				//Seleccion de Archivo------------------------
				String archivo=ficheros[i].getName();
				System.out.println("Nombre de archivo: "+archivo);
				//--------------------------------------------
				
				int index = archivo.lastIndexOf(".");
				if (index != -1) {
					String extension=archivo.substring(index + 1);
					System.out.println("\tExtension: "+extension);
					moverArchivo(archivo, extension);
				}else{
					System.out.println("\tEl archivo no tiene Extension.");
				}
				
			}
			System.out.println("Cantidad de archivos: "+ficheros.length);
			//------------------------------------------------
		}else { //Directorio no existe 
			System.out.println("No se encontro el directorio.");
		}		
	}
	
	public static boolean moverArchivo(String archivo, String extension){
		for (int i = 0; i < extensiones.length; i++) {
			if(extension.equalsIgnoreCase(extensiones[i])){
				try {
					copyFile_Java7((directorioRaiz+"\\"+archivo), (directorios[i]+"\\"+archivo),extension);
					File fichero = new File(directorioRaiz+"\\"+archivo);
					fichero.delete();
				} catch (IOException e) {
					System.out.println("Error copiando archivo.");
				}
				System.out.println("Se movio el archivo al directorio: "+directorios[i]);
				return true;
			}
		}
		System.out.println("No se pudo mover el archivo.");
		return false;
	}
	
	public static void copyFile_Java7(String origen, String destino, String extension) throws IOException {
		while(true){
			File f = new File(destino);
			if(f.exists()) {
				destino+="copia."+extension;
			}else{
				break;
			}
		}
        Path FROM = Paths.get(origen);
        Path TO = Paths.get(destino);
        //sobreescribir el fichero de destino, si existe, y copiar
        //los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES}; 
        Files.copy(FROM, TO, options);
    }
}


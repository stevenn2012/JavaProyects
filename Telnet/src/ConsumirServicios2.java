import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.commons.net.telnet.TelnetClient;
import examples.util.IOUtil;

public final class ConsumirServicios2{

	static String ip="190.81.10.4";
	static int puerto=8080;
	static String rutaSalida="SalidaTelnet.xml";
	
	public static void main(String[] args) {
		ArrayList<String> Comands = new ArrayList<String>();
		Comands.add("GET /OrquestaWS/OrquestaWS?Tester \r\n");
		Comands.add(" ");
		telnet(ip, puerto, Comands, rutaSalida);
	}

	//METODO PRINCIPAL PARA CONEXION CON TELNET
    public static final void telnet(String ip, int puerto,ArrayList<String> Comand, String rutaSalida){
        try{
        	System.out.println("Consumir Servicio:");
        	
        	String rutaComando = "comands";//RUTA ARCHIVO PARA GUARDAR COMANDO
        	crearArchivoComandos(Comand,rutaComando);
        	
        	System.out.println("Estableciendo Conexion con el servidor...");
            TelnetClient telnet = new TelnetClient();
        	telnet.connect(ip, puerto); //Conexion con el servidor por Telnet
        	System.out.println("Se establecio conexion con el servidor!");
        	
        	System.out.println("Leyendo archivo....");
        	InputStream in = new BufferedInputStream(new FileInputStream(new File(rutaComando))); //archivo con comando rest   
        	OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(rutaSalida))); //archivo de salida
        	
            System.out.println("Ejecutando comando y guardando en Archivo de salida....");
            IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(),in, out);// Ejecucucion de comandos por Telnet
            System.out.println("Comando ejecutado: "+Comand.toString());
            
            System.out.println("Fin de la ejecucion!");
            telnet.disconnect();
    		arreglarXML(rutaSalida);
    		in.close();
    		
    		/*
    		File fichero = new File(rutaComando);
    		System.out.println();
			if (fichero.delete())
			   System.out.println("El fichero "+rutaComando+" ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero "+rutaComando+" no puede ser borrado");
            //System.exit(0); // terminar ejecucion
			*/
        }catch (IOException e){
        	System.out.println("Error(2) error en conexion telnet....");
            e.printStackTrace();
            System.exit(1);
        }
    }


	
    //CREAR ARCHIVO CON VARIOS LOS COMANDOS A ENVIAR
	private static void crearArchivoComandos(ArrayList<String> comand, String rutaComando) {	
		try {
			limpiarArchivoComandos(rutaComando);
			System.out.println("Creando Archivo de comandos....");
			File archivo=new File(rutaComando);
			FileWriter escribir=new FileWriter(archivo,true);
			System.out.println("Guardando Comandos....");
			for (int i = 0; i < comand.size(); i++) {
				System.out.println("Guardando comando: "+comand.get(i)+"...");
				escribir.write(comand.get(i)+"\r\n");
			}
			escribir.close();
		} catch (IOException e) {
			System.out.println("Error(7) Error creando archivo de comandos...");
			e.printStackTrace();
		}	
	}
	
	//LIMPIA EL ARCHIVO DE COMANDOS
	private static void limpiarArchivoComandos(String rutaComando){
		try {
			System.out.println("Limpiando Archivo "+rutaComando+"....");
			BufferedWriter bw = new BufferedWriter(new FileWriter(rutaComando));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			System.out.println("Error(4) Error limpiando archivo "+rutaComando+"...");
			e.printStackTrace();
		}
	}
	
	//MOSTRAR EL ARCHIVO DE SALIDA
	private static void VerArchivoSalida(String rutaSalida) {
		try {
			System.out.println();
			System.out.println("Archivo de salida:");
			String cadena;
		    BufferedReader b = new BufferedReader(new FileReader(rutaSalida));
		    int i=0;
		    while((cadena = b.readLine())!=null) {
		        System.out.println(cadena);
		        i++;
		    }
		    if(i==0) System.out.println("El archivo de salida vacio");
		    b.close();
		} catch (Exception e) {
			System.out.println("Error(5)Error mostrando archivo de salida...");
			e.printStackTrace();
		}
	}
	
	public static void arreglarXML(String rutaSalida){
		try {
			System.out.println("Arreglando XML....");
			String rutaAux="ficheroAux.xml";
			//creando un archivo Auxiliar-------------------------------------
			CrearArchivoAux(rutaSalida, rutaAux);
		    //limpiando archivo-----------------------------------------------
			limpiarArchivoComandos(rutaSalida);
			//separando etiquetas---------------------------------------------
			separarEtiquetas(rutaSalida, rutaAux);
			//borrando archivo aux---------------------------------------------
			File fichero = new File(rutaAux);
			if (fichero.delete())
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero no puede ser borrado");
			
			VerArchivoSalida(rutaSalida);
		} catch (Exception e) {
			System.out.println("Error(6)Error Arreglando archivo de salida...");
			e.printStackTrace();
		}
	}
	
	public static void CrearArchivoAux(String rutaSalida, String rutaAux){
		try {
			System.out.println("Creando Archivo Auxiliar....");
			//PASAR DATOS A FICHERO AUX--------------------------------------
			BufferedReader b = new BufferedReader(new FileReader(rutaSalida));
			File ficheroAux=new File(rutaAux);
			BufferedWriter aux= new BufferedWriter(new FileWriter(ficheroAux));
			
			String cadena;
		    while((cadena = b.readLine())!=null) {
		    	aux.write(cadena+"\r\n");
		    }
			aux.close();
			b.close();
		} catch (Exception e) {
			System.out.println("Error(8)Error Creando Archivo Aux...");
			e.printStackTrace();
		}
	}
	
	public static void separarEtiquetas(String rutaSalida, String rutaAux){
		try {
			System.out.println("Separando Etiquetas");
			BufferedReader aux2 = new BufferedReader(new FileReader(rutaAux));
			
			File fichero=new File(rutaSalida);
			BufferedWriter linea= new BufferedWriter(new FileWriter(fichero));
			
			String cad;
		    while((cad = aux2.readLine())!=null) {
		    	String[] datos = cad.split("><");
		    	for (int i = 0; i < datos.length; i++) {
		    		String comand=datos[i];
		    		if(i!=0) comand="<"+comand;
		    		if(i!=datos.length-1) comand+=">";
		    		linea.write(comand+"\r\n");
				}
		    }
			aux2.close();
			linea.close();
		} catch (Exception e) {
			System.out.println("Error(9)Error separando etiquetas...");
			e.printStackTrace();
		}
	}
}


/*
//METODOS PARA CONSUMIR TIENDA -----------------------------------------------------------------------
	//CONSUMIR ACEPTAR CANCION
	public static void ConsumirAceptarCancion(String nombre, String artista, String album, String genero){
		ArrayList<String> Comands = new ArrayList<String>();
			Comands.add("GET /TiendaV4/rest/ServicioREST/aceptarCancion HTTP/1.1");
			Comands.add("Host: "+ip);
			Comands.add("Content-Type: application/x-www-form-urlencoded");
			Comands.add("Content-Length: "+(nombre.length()+7));
			Comands.add("");
			Comands.add("nombre="+nombre);
			//Comands.add("");
			Comands.add("Content-Length: "+(artista.length()+8));
			Comands.add("");
			Comands.add("artista="+artista);
			//Comands.add("");
			Comands.add("Content-Length: "+(album.length()+6));
			Comands.add("");
			Comands.add("album="+album);
			//Comands.add("");
			Comands.add("Content-Length: "+(genero.length()+7));
			Comands.add("");
			Comands.add("genero="+genero);
			
			ConsumirServicio(ip, puerto, Comands, rutaSalida);
	}
	
	//CONSUMIR GENERAR REPORTE
	public static void ConsumirGenerarReporte(String fechaInicio, String fechaFin){
		ArrayList<String> Comands = new ArrayList<String>();
			Comands.add("GET /TiendaV4/rest/ServicioREST/generarReporte HTTP/1.1");
			Comands.add("Host: "+ip);
			Comands.add("Content-Type: application/x-www-form-urlencoded");
			Comands.add("Content-Length: "+(fechaInicio.length()+12));
			Comands.add("");
			Comands.add("fechaInicio="+fechaInicio);
			Comands.add("");
			Comands.add("Content-Length: "+(fechaFin.length()+9));
			Comands.add("");
			Comands.add("fechaFin="+fechaFin);
			Comands.add("");
		ConsumirServicio(ip, puerto, Comands, rutaSalida);
	}
	//------------------------------------------------------------------------------------------------------------
	*/
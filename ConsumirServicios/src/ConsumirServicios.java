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

public final class ConsumirServicios{

	static String ip = "190.81.10.2";
	static int puerto = 8080;
	static String rutaSalida = "Salida.xml";//RUTA DEL ARCHIVO DE SALIDA				
	
	//EJEMPLOS DE CONSUMO DE SERVICIOS TIENDA
	public static void main(String[] args) {
		
		
		String nombreCancion="hn";
		String nombreArtista="Stven";
		String nombreAlbum="mi";
		String generoCancion="rock666";
		//ConsumirAceptarCancion(nombreCancion, nombreArtista, nombreAlbum, generoCancion);//EJEMPLO CONSUMO SERVICIO aceptarCancion TIENDA
		
		String fechaInicio="1994/04/23";
		String fechaFinal="2020/04/26";
		ConsumirGenerarReporte(fechaInicio,fechaFinal);//EJEMPLO CONSUMO SERVICIO generarReporte TIENDA
	}
	
	//METODOS PARA CONSUMIR TIENDA -----------------------------------------------------------------------
	//CONSUMIR ACEPTAR CANCION
	public static String ConsumirAceptarCancion(String nombre, String artista, String album, String genero){
		System.out.println("Generando comando para aceptarCancion...");
		String comand1 = "POST /TiendaV4/rest/ServicioREST/aceptarCancion?"+
						"nombre="+nombre+"&"+
						"artista="+artista+"&"+
						"album="+album+"&"+
						"genero="+genero;
		//Comando--------------------------------------------------------------------------------------
		ArrayList<String> Comand= new ArrayList<String>();
		Comand.add(comand1);
		ConsumirServicio(ip, puerto, Comand, rutaSalida); //METODO INICIAL PARA LA COMUNICACION CON TELNET
		return comand1;
	}
	
	//CONSUMIR GENERAR REPORTE
	public static String ConsumirGenerarReporte(String fechaInicio, String fechaFin){
		System.out.println("Generando comando para generarReporte...");
		String comand1 = "GET /TiendaV4/rest/ServicioREST/generarReporte?"+
						"fechaInicio="+fechaInicio+"&"+
						"fechaFin="+fechaFin;
		
		//Comando--------------------------------------------------------------------------------------
		ArrayList<String> Comand= new ArrayList<String>();
		Comand.add(comand1);
		ConsumirServicio(ip, puerto, Comand, rutaSalida); //METODO INICIAL PARA LA COMUNICACION CON TELNET
		return comand1;
	}
	//------------------------------------------------------------------------------------------------------------
	
	//METODO PRINCIPAL PARA CONEXION CON TELNET
    public static final void ConsumirServicio(String ip, int puerto,ArrayList<String> Comand, String rutaSalida){
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
    		File fichero = new File(rutaComando);
    		System.out.println();
			if (fichero.delete())
			   System.out.println("El fichero "+rutaComando+" ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero "+rutaComando+" no puede ser borrado");
            //System.exit(0); // terminar ejecucion
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
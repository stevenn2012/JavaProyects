package intent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.telnet.TelnetClient;
import examples.util.IOUtil;

public final class ConsumirServicios{

	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int puerto = 8080;
		
		//EJEMPLO EJECUCION METODO aceptarCancion TIENDA
		String Comand = "POST /TiendaV4/rest/ServicioREST/aceptarCancion?"+
						"nombre=nombre123456789"+"&"+
						"artista=artista2"+"&"+
						"album=album2"+"&"
						+"genero=genero2";
		
		//EJEMPLO EJECUCION METODO generarReporte TIENDA
		/*String Comand = "GET /TiendaV4/rest/ServicioREST/generarReporte?"+
				"fechaInicio=2015-08-26"+"&"+
				"fechaFin=2016-10-11";
		*/
		
		String rutaComando = "Archivo1.txt";
		String rutaSalida = "Archivo2.txt";
		
		ConsumirServicio(ip, puerto, Comand, rutaComando, rutaSalida);	
	}
	
    public static final void ConsumirServicio(String ip, int puerto,String Comand,String rutaComando,String rutaSalida){
        try{
        	System.out.println("Consumir Servicio:");
        	crearArchivoComandos(Comand,rutaComando);
        	
            TelnetClient telnet = new TelnetClient();
        	telnet.connect(ip, puerto); //Conexion con el servidor por Telnet
        	
        	System.out.println("Leyendo archivo....");
        	InputStream in = new BufferedInputStream(new FileInputStream(new File(rutaComando))); //archivo con comando rest   
        	File archivo=new File(rutaSalida);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(rutaSalida))); //archivo de salida
        	
            System.out.println("Ejecutando comando y guardando en Archivo de salida....");
            IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(),in, out);// Ejecucucion de comandos por Telnet
        	
            System.out.println("Fin de la ejecucion!");
            telnet.disconnect();
            System.exit(0);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

	private static void crearArchivoComandos(String comand, String rutaComando) {	
		try {
			limpiarArchivoComandos(rutaComando);
			System.out.println("Creando Archivo de comandos....");
			File archivo=new File(rutaComando);
			FileWriter escribir=new FileWriter(archivo,true);
			System.out.println("Guardando Comandos....");
			escribir.write(comand+"\n");
			escribir.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private static void limpiarArchivoComandos(String rutaComando){
		try {
			System.out.println("Limpiando Archivo de comandos....");
			BufferedWriter bw = new BufferedWriter(new FileWriter(rutaComando));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


/*
   public static final void main(String[] args){
    try{
        TelnetClient telnet = new TelnetClient();
    	telnet.connect("127.0.0.1", 8080); //Conexion con el servidor por Telnet
    	
    	System.out.println("Leyendo archivo....");
    	InputStream in = new BufferedInputStream(new FileInputStream(new File("D:\\Descargas\\Archivo1.txt"))); //archivo con comando rest       
        OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:\\Descargas\\Archivo2.txt"))); //archivo de salida
    	System.out.println("Ejecutando comando....");
        IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(),in, out);// Ejecucucion de comandos por Telnet
    	System.out.println("Comando Ejecutado");
        telnet.disconnect();
        System.exit(0);
    }catch (IOException e){
        e.printStackTrace();
        System.exit(1);
    }
}
*/

//This class requires the IOUtil support class!

//BufferedReader (new InputStreamReader (System.in)).readLine()
//InputStream arg0, OutputStream arg1, InputStream arg2, OutputStream arg3
//IOUtil.readWrite(telnet.getInputStream(), telnet.getOutputStream(),System.in, System.out);

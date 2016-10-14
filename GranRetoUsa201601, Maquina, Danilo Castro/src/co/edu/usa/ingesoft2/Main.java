package co.edu.usa.ingesoft2;

import java.io.File;
import java.io.IOException;

import co.edu.usa.ingesoft2.granReto.FachadaGranReto;


public class Main
{   
   public static void main( String[] args ){
      new Main().ejecutar();
   }

   public void ejecutar(){
      int pruebasHechas = 0;
      int exitos = 0;
      String nombreCarpeta = "guiones-reto";
      File carpeta = new File( nombreCarpeta );
      LectorGuiones lector = new LectorGuiones();
      
      File[] archivos = carpeta.listFiles();
      System.out.println( "Archivos: " + archivos.length );
      
      try {
         for ( File arch : archivos ) {
            Sesion sesion = lector.obtenerSesion( arch.getAbsolutePath() );
            sesion.setSalida( System.out );
            sesion.setFachadaGranReto( new FachadaGranReto() );
            pruebasHechas += sesion.puntosPosibles();
            exitos += sesion.ejecutar();
         }
      }
      catch ( IOException e ) {
         e.printStackTrace();
      }
      
      System.out.println( "Resultado: " + exitos + "/" + pruebasHechas + " = " 
         + ( Double.valueOf( exitos ) / Double.valueOf( pruebasHechas ) ) );
   }
   
}

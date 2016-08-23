/**
 * 
 */
package co.edu.usa.ingesoft2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Danilo
 *
 */
public class LectorGuiones
{

   public Sesion obtenerSesion( String absolutePath ) throws IOException
   {
      Sesion sesion = new Sesion();
      BufferedReader reader = new BufferedReader( new FileReader( new File( absolutePath ) ) );
      String linea = null;
      
      while ( ( linea = reader.readLine() ) != null ) {
         linea = linea.trim();
         if ( !linea.isEmpty() ) {
            String[] campos = linea.split( ";" );
            sesion.addInfoArchivo( campos[0], campos[1], campos[2] );
         }
      }
      
      reader.close();
      return sesion;
   }

}

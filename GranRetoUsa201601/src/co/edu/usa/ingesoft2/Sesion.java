/**
 * 
 */
package co.edu.usa.ingesoft2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import co.edu.usa.ingesoft2.granReto.GranRetoException;
import co.edu.usa.ingesoft2.granReto.IFachadaGranReto;

/**
 * @author Danilo
 * 
 */
public class Sesion
{
   private List<InfoArchivo> archivosEntrada = new ArrayList<InfoArchivo>();
   private PrintStream salida = null;
   private IFachadaGranReto fachada = null;
   
   class InfoArchivo
   {
      String nombreArchivo, resultado, valor;

      public InfoArchivo( String nombreArchivo, String resultado, String valor )
      {
         super();
         this.nombreArchivo = nombreArchivo;
         this.resultado = resultado;
         this.valor = valor;
      }
      
   }
   
   public void setSalida( PrintStream out )
   {
      this.salida = out;
   }

   public void setFachadaGranReto( IFachadaGranReto fachadaGranReto )
   {
      this.fachada = fachadaGranReto;
   }

   public int puntosPosibles()
   {
      return 2 * archivosEntrada.size();
   }

   public int ejecutar()
   {
      int pruebasExitosas = 0;
      
      for ( InfoArchivo x: archivosEntrada ) {
         salida.println( "--------" );
         pruebasExitosas += probarCargarArchivo( x );
         pruebasExitosas += probarEvaluar( x );
      }
      
      return pruebasExitosas;
   }

   private int probarCargarArchivo( InfoArchivo x )
   {
      int pruebasExitosas = 0;
      
      try {
         fachada.cargarArchivo( x.nombreArchivo );
         if ( x.resultado.equals( "OK" ) ) {
            pruebasExitosas++;
         }
      }
      catch ( GranRetoException e ) {
         if ( x.resultado.equals( "KO" ) ) {
            pruebasExitosas++;
         }
      }
      catch ( Exception e ) {
         
      }
      salida.println( "Carga: " + ( ( pruebasExitosas == 0 ) ? "in" : "" ) + "correcta." );
      return pruebasExitosas;
   }

   private int probarEvaluar( InfoArchivo x )
   {
      int pruebasExitosas = 0;
      String resultado = null;
      
      try {
         resultado = fachada.calcular();
         if ( x.valor.equals( resultado ) ) {
            pruebasExitosas++;
         }
      }
      catch ( Exception e ) {

      }
      salida.println( "Evaluación: " + ( ( pruebasExitosas == 0 ) ? "in" : "" ) + "correcta." );
      salida.println( "Esperado: \"" + x.valor + "\" Retornado: \"" + resultado + "\"" );
      return pruebasExitosas;
   }

   public void addInfoArchivo( String nombreArchivo, String resultado, String valor )
   {
      this.archivosEntrada.add( new InfoArchivo( nombreArchivo, resultado, valor ) );
   }


}

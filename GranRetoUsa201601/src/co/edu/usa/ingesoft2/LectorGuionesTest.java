/**
 * 
 */
package co.edu.usa.ingesoft2;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Danilo
 *
 */
public class LectorGuionesTest
{
   private LectorGuiones lector = null;
   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      lector = new LectorGuiones();
   }

   /**
    * @throws java.lang.Exception
    */
   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testDeberiaLeerCorrectamenteArchivoCorrecto()
   {
      Sesion sesion;
      
      try {
         sesion = lector.obtenerSesion( "guiones-prueba/guionPrueba.txt" );
         assertNotNull( sesion );
         assertEquals( 8, sesion.puntosPosibles() );
      }
      catch ( IOException e ) {
         fail();
      }
      
   }

   @Test
   public void testDeberiaLeerBienAPesarDeEspaciosInternos()
   {
      Sesion sesion;
      
      try {
         sesion = lector.obtenerSesion( "guiones-prueba/guionPrueba2.txt" );
         assertNotNull( sesion );
         assertEquals( 8, sesion.puntosPosibles() );
      }
      catch ( IOException e ) {
         e.printStackTrace();
         fail();
      }
      
   }
}

/**
 * 
 */
package co.edu.usa.ingesoft2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.edu.usa.ingesoft2.granReto.GranRetoException;
import co.edu.usa.ingesoft2.granReto.IFachadaGranReto;

/**
 * @author Danilo
 *
 */
public class SesionTest
{
   private Sesion sesion = null;
   
   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      sesion = new Sesion();
      sesion.setSalida( System.out );
   }

   /**
    * @throws java.lang.Exception
    */
   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testDeberiaReportarDosConCargaCorrectaYEvalCorrecta()
   {
      IFachadaGranReto fachada = Mockito.mock( IFachadaGranReto.class );
      Mockito.when( fachada.calcular() ).thenReturn( "45'345.723,97" );
      
      sesion.setFachadaGranReto( fachada );
      sesion.addInfoArchivo( "bien1.txt", "OK", "45'345.723,97" ); 
      assertEquals( 2, sesion.ejecutar() );
   }
   
   @Test
   public void testDeberiaReportarUnaconCargaCorrectaYEvalIncorrecta()
   {
      IFachadaGranReto fachada = Mockito.mock( IFachadaGranReto.class );
      Mockito.when( fachada.calcular() ).thenReturn( "45'345.723,96" );
      
      sesion.setFachadaGranReto( fachada );
      sesion.addInfoArchivo( "bien1.txt", "OK", "45'345.723,97" ); 
      assertEquals( 1, sesion.ejecutar() );
   }

   @Test
   public void testDeberiaReportarUnoSiExcepcionEnEval()
   {
      IFachadaGranReto fachada = Mockito.mock( IFachadaGranReto.class );
      Mockito.when( fachada.calcular() ).thenThrow( new RuntimeException() );
      
      sesion.setFachadaGranReto( fachada );
      sesion.addInfoArchivo( "bien1.txt", "OK", "45'345.723,97" ); 
      assertEquals( 1, sesion.ejecutar() );
   }
   
   @Test
   public void testDeberiaReportarDosSiArchivoIncorrectoYVacio()
   {
      try {
         IFachadaGranReto fachada = Mockito.mock( IFachadaGranReto.class );
         Mockito.doThrow( GranRetoException.class ).when( fachada ).cargarArchivo( "error.txt" );
         Mockito.when( fachada.calcular() ).thenReturn( "ERROR" );
         
         sesion.setFachadaGranReto( fachada );
         sesion.addInfoArchivo( "error.txt", "KO", "ERROR" ); 
         assertEquals( 2, sesion.ejecutar() );
      }
      catch ( GranRetoException e ) {
         fail();
      }
   }
   
   @Test
   public void testDeberiaReportarUnoSiExcepcionRaraYVacio()
   {
      try {
         IFachadaGranReto fachada = Mockito.mock( IFachadaGranReto.class );
         Mockito.doThrow( RuntimeException.class ).when( fachada ).cargarArchivo( "error.txt" );
         Mockito.when( fachada.calcular() ).thenReturn( "ERROR" );
         
         sesion.setFachadaGranReto( fachada );
         sesion.addInfoArchivo( "error.txt", "KO", "ERROR" ); 
         assertEquals( 1, sesion.ejecutar() );
      }
      catch ( GranRetoException e ) {
         fail();
      }
   }

   @Test
   public void testDeberiaReportarUnoSiCargaCorrectaYNull()
   {
      IFachadaGranReto fachada = Mockito.mock( IFachadaGranReto.class );
      Mockito.when( fachada.calcular() ).thenReturn( null );
      
      sesion.setFachadaGranReto( fachada );
      sesion.addInfoArchivo( "bien.txt", "OK", "872343" ); 
      assertEquals( 1, sesion.ejecutar() );
   }

}

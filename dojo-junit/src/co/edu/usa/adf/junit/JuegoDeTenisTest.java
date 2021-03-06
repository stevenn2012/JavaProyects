package co.edu.usa.adf.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JuegoDeTenisTest {

	JuegoDeTenis juego;
	
	@Before
	public void setUp() throws Exception {
		juego = new JuegoDeTenis("Nati", "Danilo");
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void deberiaCantarSirveNatiSiJuegoCeroCero() {
		String respuesta=juego.cantar();
		assertEquals("Sirve Nati", respuesta);
	}
	
	@Test
	public void deberiaCantarSirveDaniloSiJuegoCeroCeroYVaPrimeroDanilo() {
		juego = new JuegoDeTenis("Danilo", "Nati");
		String respuesta=juego.cantar();
		assertEquals("Sirve Danilo", respuesta);
	}
	
	@Test
	public void deberiaCantarQuinceCeroSiPuntoServidor() {
		try{
		juego.puntoServidor();
		String respuesta=juego.cantar();
		assertEquals("Quince - Cero", respuesta);
	} catch (JuegoTerminadoException e) {
		assertEquals("Juego ya terminado", e.getMessage());
	}
	}
	
	@Test
	public void deberiaCantarQuinceCeroSiPuntoReceptor() {
		juego.puntoReceptor();
		String respuesta=juego.cantar();
		assertEquals("Cero - Quince", respuesta);
	}
	
	@Test
	public void deberiaCantarTreintaCeroSiDosPuntoServidor() {
		try{
		juego.puntoServidor();
		juego.puntoServidor();
		String respuesta=juego.cantar();
		assertEquals("Treinta - Cero", respuesta);
	} catch (JuegoTerminadoException e) {
		assertEquals("Juego ya terminado", e.getMessage());
	}
	}
	
	@Test
	public void deberiaCantarTreintaCeroSiTresPuntoServidor() {
		try{
		juego.puntoServidor();
		juego.puntoServidor();
		juego.puntoServidor();
		String respuesta=juego.cantar();
		assertEquals("Cuarenta - Cero", respuesta);
		} catch (JuegoTerminadoException e) {
			assertEquals("Juego ya terminado", e.getMessage());
		}
	}
	
	@Test
	public void deberiaBotarExcepcionSiJuegoTerminado() {
		try {
			juego.puntoServidor();
			juego.puntoServidor();
			juego.puntoServidor();
			juego.puntoServidor();
			juego.puntoServidor();
			fail();
		} catch (JuegoTerminadoException e) {
			assertEquals("Juego Ya Terminado", e.getMessage());
		}catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void deberiaCantarTreintaQuince() {
		try {
			juego.puntoReceptor();
			juego.puntoServidor();
			juego.puntoServidor();
			assertEquals("Treinta - Quince", juego.cantar());
		} catch (JuegoTerminadoException e) {
			assertEquals("Juego Ya Terminado", e.getMessage());
		}catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void deberiaCantarQuinceIguales() {
		try {
			juego.puntoServidor();
			juego.puntoReceptor();
			assertEquals("Quince Iguales", juego.cantar());
		} catch (JuegoTerminadoException e) {
			fail();
		}catch(Exception e){
			fail();
		}
	}
}

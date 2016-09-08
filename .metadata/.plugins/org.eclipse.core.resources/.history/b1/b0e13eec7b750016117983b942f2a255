package co.edu.usa.adf.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.usa.adf.Framework_Ancho_Fijo_Anotaciones.franfia;
import co.edu.usa.adf.tomates.Contacto;
import co.edu.usa.adf.tomates.Evento;

public class Pruebas_fanfia {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void pruebaValidarObjeto() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
		try {
			franfia prueba = new franfia("Datos/Descriptores/Descriptor_Contacto.txt");
			prueba.add(new Contacto("Steven", "(6)666666666666666666666666666666", "6666@g", "666asg", 6666666, 10223666));
			String d = prueba.getDato(0).toString();
			assertEquals("Contacto [nombre=Steven, celular=(6)66666666666666666, email=6666@g, direccion=666asg, edad=666, cedula=10223666]", d);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

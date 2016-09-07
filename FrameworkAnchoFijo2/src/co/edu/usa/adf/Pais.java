package co.edu.usa.adf;

import Fanfia.FixedWidthField;

public class Pais {
	
	@FixedWidthField(posicion=0, width=14) private String nombre;
	@FixedWidthField(posicion=1, width=14) private String capital;
	
	public Pais(){
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}

	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", capital=" + capital + "]";
	}

}

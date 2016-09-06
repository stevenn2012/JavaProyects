package co.edu.usa.adf;

import java.util.Date;

import Fanfid.FixedWidthField;

public class Evento {

	private @FixedWidthField(width=30) String nombre;
	private @FixedWidthField(width=10) Date fecha;
	private @FixedWidthField(width=30) String descripcion;
	
	public Evento() {
	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Eventos [nombre=" + nombre + ", fecha=" + fecha + ", descripcion=" + descripcion + "]";
	}
}
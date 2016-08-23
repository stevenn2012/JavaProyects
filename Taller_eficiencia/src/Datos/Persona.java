package Datos;

public class Persona {

	public String nombre;
	
	public int horaIn;
	public int minutosIn;
	
	public int horaEnd;
	public int minutosEnd;
	
	public double distancia; //kilometros

	public double eficiencia; // metros/min
	
	public Persona(String nombre, int horaIn, int minutosIn, int horaEnd, int minutosEnd, double distancia) {
		this.nombre = nombre;
		this.horaIn = horaIn;
		this.minutosIn = minutosIn;
		this.horaEnd = horaEnd;
		this.minutosEnd = minutosEnd;
		this.distancia = distancia;
		this.eficiencia=-1;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoraIn() {
		return horaIn;
	}

	public void setHoraIn(int horaIn) {
		this.horaIn = horaIn;
	}

	public int getMinutosIn() {
		return minutosIn;
	}

	public void setMinutosIn(int minutosIn) {
		this.minutosIn = minutosIn;
	}

	public int getHoraEnd() {
		return horaEnd;
	}

	public void setHoraEnd(int horaEnd) {
		this.horaEnd = horaEnd;
	}

	public int getMinutosEnd() {
		return minutosEnd;
	}

	public void setMinutosEnd(int minutosEnd) {
		this.minutosEnd = minutosEnd;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public double getEficiencia() {
		return eficiencia;
	}

	public void setEficiencia(double eficiencia) {
		this.eficiencia = eficiencia;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", horaIn=" + horaIn + ", minutosIn=" + minutosIn + ", horaEnd=" + horaEnd
				+ ", minutosEnd=" + minutosEnd + ", distancia=" + distancia + ", eficiencia=" + eficiencia + "]";
	}
}

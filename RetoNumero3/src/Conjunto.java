import java.util.ArrayList;

public class Conjunto {

	private ArrayList<Integer> numeros = new ArrayList<Integer>();
	private String nombre;
	
	public Conjunto(ArrayList<Integer> numeros, String nombre) {
		this.numeros = numeros;
		this.nombre = nombre;
	}

	public ArrayList<Integer> getNumeros() {
		return numeros;
	}

	public int getNumero(int i) {
		return numeros.get(i);
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Nombre: "+nombre+" Elementos: {"+numeros+ "}";
	}
}

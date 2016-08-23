import java.io.IOException;

public class histograma {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("Cantidad de histogramas: ");
		int histogramas=leer.datoInt();
		int[] numHist= new int[histogramas];
		System.out.println("Ud ingreso "+histogramas+" como cantidad de histogramas.");
		for (int i = 0; i < histogramas; i++) {
			System.out.println("Ingrese numero de barras del histograma "+(i+1));
			for (int j = 0; j < args.length; j++) {
				System.out.println("Ingrese numero "+j);
				String numero=leer.dato();
			}
		}
	}
}

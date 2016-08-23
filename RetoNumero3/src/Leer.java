import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leer {
	public static String dato() throws IOException{
		return new BufferedReader (new InputStreamReader (System.in)).readLine();
	}
	public static int datoInt () throws NumberFormatException, IOException{
	  return Integer.parseInt(dato());
	}
}
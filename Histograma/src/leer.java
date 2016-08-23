import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class leer {
	public static String dato() throws IOException{
		return new BufferedReader (new InputStreamReader (System.in)).readLine();
	}
	public static int datoInt () throws NumberFormatException, IOException{
	  return Integer.parseInt(dato());
	}
    public static long datoLong () throws NumberFormatException, IOException{
	  return Long.parseLong (dato());
	}
    public static float datoFloat() throws  Exception{
    	return Float.parseFloat(dato());
    }
    public static double datoDouble() throws Exception{
    	return Float.parseFloat(dato());
    }
}
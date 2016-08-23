import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Comparacion{    
	
	public static void main(String[] args) {
		boolean bloqueado = bloquear("ListaNoPermitidos.txt", "Regeton", "Reggaeton", 2);
		System.out.println(bloqueado);
	}
	
	//retorna true o false para saber si bloquear o no cancion
	public static boolean bloquear(String ruta, String palabraIn, String palabraBlock, int cantiDif) {
		//ruta , palabra ingresada como genero, diferencias
		HashMap<String, nodo> palabras = comparar(ruta, palabraIn, cantiDif);
		if(palabras.get(palabraBlock)==null){
			return false;
		}else{
			System.out.println(palabras.get(palabraBlock).palabra+" "+palabras.get(palabraBlock).gradoSimilitud);
			return true;
		}
	}
	
	//retorna las palabras que tienen similitud con otra, dependiendo de un rango de diferencia y leyendo un archivo de palabras
	public static HashMap<String, nodo> comparar(String ruta,String palabra, int RangoDiferencia){
		try {
			int numeroPalabras = CantidadLineas(ruta);
			
			BufferedReader b = new BufferedReader(new FileReader(ruta));
			
			String base = b.readLine(); 
			String comparador = base; 
			boolean existe = false;
			
			HashMap<String, nodo> palabrasSimilares= new HashMap<String, nodo>();
			
			for (int i = 0; i < numeroPalabras; i++) {
				if(comparador.charAt(0) == '<'){
					base = RecortarString(comparador, 1, comparador.length());
					comparador = base;
					existe = false;
				}
				
				int diferencia=computeLevenshteinDistance(palabra, comparador);
				nodo minod = new nodo(comparador, diferencia);
				if(existe){
					if(diferencia<palabrasSimilares.get(base).gradoSimilitud){
						
						palabrasSimilares.put(base, minod);
					}
				}else{
					if(diferencia<=RangoDiferencia){
						palabrasSimilares.put(base, minod);
						existe = true;
					}
				}
				comparador = b.readLine();
			}	
			b.close();
			return palabrasSimilares;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//retorna la cantidad de lineas que tiene un archivo
	public static int CantidadLineas(String ruta){
	    try {
	    	int cantidad=0;
	    	String cadena;
			BufferedReader b = new BufferedReader(new FileReader(ruta));
		    while((cadena = b.readLine())!=null) {
		        cantidad++;
		    }
			b.close();
			return cantidad;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//recorta un string desde un rango hasta otro rango
	public static String RecortarString(String palabra,int inicio, int fin){
		char[] caracteres = palabra.toCharArray();
		String resultado="";
		for (int i = inicio; i < fin; i++) {
			resultado+=caracteres[i];
		}
		return resultado;
	}
	
	//calcula las diferencias entre dos palabras
    public static int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {      
        int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];        
                                                                                 
        for (int i = 0; i <= lhs.length(); i++)                                 
            distance[i][0] = i;                                                  
        for (int j = 1; j <= rhs.length(); j++)                                 
            distance[0][j] = j;                                                  
                                                                                 
        for (int i = 1; i <= lhs.length(); i++)                                 
            for (int j = 1; j <= rhs.length(); j++)                             
                distance[i][j] = minimum(                                        
                        distance[i - 1][j] + 1,                                  
                        distance[i][j - 1] + 1,                                  
                        distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));
                                                                                 
        return distance[lhs.length()][rhs.length()];                           
    }  
    
    //metodo usado en computeLevenshteinDistance
    private static int minimum(int a, int b, int c) {                            
        return Math.min(Math.min(a, b), c);                                      
    }                                                                            
      
    
}


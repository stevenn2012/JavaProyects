
public class main {

	static String datos[] = new String[10];
	static String palabra = "hola";
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			datos[i]=palabra+"/"+i;
			palabra=palabra+((char)(65+i));
		}
		
		for (int i = 0; i < datos.length; i++) {
			char[] dato = datos[i].toCharArray();
			
			String palab="";
			String num="";
			
			int aux=1;
			
			for (int j = 0; j < dato.length; j++) {
				if(dato[j]=='/'){
					j++;
					aux++;
				}
				if(aux==1){
					palab+=dato[j];
				}else if(aux==2){
					num+=dato[j];
				}
			}
			
			int numero=Integer.parseInt(num);
			System.out.println("la palabra para la posicion "+i+" fue: "+palab);
			System.out.println("El numero para la posicion "+i+" fue: "+numero);
		}
	}
}

import java.util.ArrayList;
import java.util.Collections;

public class AdminConjuntos {

	static ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>();
	
	public static void crearConjunto() {
		try {
			String nombre="";
			while(true){
				System.out.println("Ingrese el nombre del conjunto:");
				nombre=Leer.dato();
				boolean val=true;
				for (int i=0;i<conjuntos.size(); i++) {
					if(conjuntos.get(i).getNombre().equals(nombre)){
						val=false;
						break;
					}
				}	
				if(val){
					break;
				}else{
					System.out.println("El nombre ya existe");
				}
			}
			System.out.println("Ingrese el conjunto(debe ser ingresado entre '{' y '}' y cada elemento del conjunto separado por comas ',' ):");
			System.out.println("Ejemplo: {1,2,3}");
			String conjunto=Leer.dato();
			if(validarConjunto(conjunto)){
				ArrayList<Integer> nuevo = convertirConjunto(conjunto);
				Collections.sort(nuevo);
				conjuntos.add(new Conjunto(nuevo, nombre));
				System.out.println("Se creo el conjunto: ");
				mostrarConjunto(nombre);
			}else{
				System.out.println("El Conjunto ingresado no es valido");
			}
		} catch (Exception e) {
			System.out.println("Error(3).....");
			e.printStackTrace();
			Inicio.main(null);
		}
	}

	public static boolean validarConjunto(String conjunto){
		char[] caracteres = conjunto.toCharArray();
		if(caracteres[0]=='{' && caracteres[caracteres.length-1]=='}'){
			String[] elementos= conjunto.split(",");
			for(int i=0; i<elementos.length;i++) {
				char[] elements=elementos[i].toCharArray();
				for (int j=0; j<elements.length;j++) {
					if((i!=0 || j!=0) && ((i!=elementos.length-1) || (j!=(elements.length-1)))){
						if(elements[j]>57 || elements[j]<48){
							return false;
						}
					}
				}
			}
		}else{
			return false;
		}
		return true;
	}
	
	public static ArrayList<Integer> convertirConjunto(String conjunto){
		try {
			char[] caracts=conjunto.toCharArray();
			String elements="";
			for (int i=1; i<(caracts.length-1); i++) {
				elements+=caracts[i];
			}
			String[] elementos = elements.split(",");
			ArrayList<Integer> conj = new ArrayList<Integer>();
			for (int i=0;i<elementos.length;i++) {
				conj.add(Integer.parseInt(elementos[i]));
			}
			return conj;
		} catch (Exception e) {
			System.out.println("Error(4)...");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void construirUnion() {
		try {
			System.out.println("Construir Union:");
			System.out.println("Seleccione los numeros de los conjuntos a operar separados por la letra U (mayuscula):");
			mostrarConjuntos();
			String operacion=Leer.dato();
			String[] conjuntos1=operacion.split("U");
			
			if(conjuntos1.length!=2){
				System.out.println("la operacion no es valida...");
			}else{
				if(validarNumerosConjunto(conjuntos1)){
					boolean val=true;
					String nombre = "("+conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNombre()+"U"+conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNombre()+")";
					for (int i=0;i<conjuntos.size(); i++) {
						if(conjuntos.get(i).getNombre().equals(nombre)){
							val=false;
							break;
						}
					}	
					if(val){
						ArrayList<Integer> conjunto1 = conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNumeros();
						ArrayList<Integer> conjunto2 = conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNumeros();
						ArrayList<Integer> union = new ArrayList<Integer>();
						for (int i = 0; i < conjunto1.size(); i++) {
							union.add(conjunto1.get(i));
						}
						System.out.println("realizando operacion...");
						//
						for (int i = 0; i < conjunto2.size(); i++) {
							boolean validar = true;
							for (int j = 0; j < conjunto1.size(); j++) {
								if(conjunto1.get(j)==conjunto2.get(i)){
									validar=false;
								}
							}
							if(validar){
								union.add(conjunto2.get(i));
							}
						}
						Collections.sort(union);
						conjuntos.add(new Conjunto(union, nombre));
						mostrarConjunto(nombre);
						//
					}else{
						mostrarConjunto(nombre);
					}
					
				}else{
					System.out.println("Los datos ingresados no son validos");
				}
			}
		} catch (Exception e) {
			System.out.println("Error(5)....");
			e.printStackTrace();
			Inicio.main(null);
		}
	}
	
	public static boolean validarNumerosConjunto(String[] conjuntos1){
		for(int i=0; i<conjuntos1.length;i++) {
			char[] elements=conjuntos1[i].toCharArray();
			for (int j=0; j<elements.length;j++) {
				if((i!=0 || j!=0) && ((i!=conjuntos1.length-1) || (j!=(elements.length-1)))){
					if(elements[j]>57 || elements[j]<48){
						return false;
					}
				}
			}
		}
		int numero1 = Integer.parseInt(conjuntos1[0])-1;
		int numero2 = Integer.parseInt(conjuntos1[1])-1;
		if(numero1>conjuntos.size() || numero2>conjuntos.size() || numero1<0 || numero2<0){
			return false;
		}
		return true;	
	}

	public static void construirInterseccion() {
		try {
			System.out.println("Construir Interseccion:");
			System.out.println("Seleccione los numeros de los conjuntos a operar separados por la letra I (mayuscula):");
			mostrarConjuntos();
			String operacion=Leer.dato();
			String[] conjuntos1=operacion.split("I");
			
			if(conjuntos1.length!=2){
				System.out.println("la operacion no es valida...");
			}else{
				if(validarNumerosConjunto(conjuntos1)){
					boolean val=true;
					String nombre = "("+conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNombre()+"I"+conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNombre()+")";
					for (int i=0;i<conjuntos.size(); i++) {
						if(conjuntos.get(i).getNombre().equals(nombre)){
							val=false;
							break;
						}
					}	
					if(val){
						ArrayList<Integer> conjunto1 = conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNumeros();
						ArrayList<Integer> conjunto2 = conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNumeros();
						ArrayList<Integer> interseccion = new ArrayList<Integer>();

						System.out.println("realizando operacion...");
						//
						for (int i = 0; i < conjunto2.size(); i++) {
							boolean validar = false;
							for (int j = 0; j < conjunto1.size(); j++) {
								if(conjunto1.get(j)==conjunto2.get(i)){
									validar=true;
								}
							}
							if(validar){
								interseccion.add(conjunto2.get(i));
							}
						}
						Collections.sort(interseccion);
						conjuntos.add(new Conjunto(interseccion, nombre));
						mostrarConjunto(nombre);
						//
					}else{
						mostrarConjunto(nombre);
					}
					
				}else{
					System.out.println("Los datos ingresados no son validos");
				}
			}
		} catch (Exception e) {
			System.out.println("Error(5)....");
			e.printStackTrace();
			Inicio.main(null);
		}
	}

	public static void construirDiferencia() {
		try {
			System.out.println("Construir Diferencia:");
			System.out.println("Seleccione los numeros de los conjuntos a operar separados por la letra D (mayuscula):");
			mostrarConjuntos();
			String operacion=Leer.dato();
			String[] conjuntos1=operacion.split("D");
			
			if(conjuntos1.length!=2){
				System.out.println("la operacion no es valida...");
			}else{
				if(validarNumerosConjunto(conjuntos1)){
					boolean val=true;
					String nombre = "("+conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNombre()+"D"+conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNombre()+")";
					for (int i=0;i<conjuntos.size(); i++) {
						if(conjuntos.get(i).getNombre().equals(nombre)){
							val=false;
							break;
						}
					}	
					if(val){
						ArrayList<Integer> conjunto1 = conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNumeros();
						ArrayList<Integer> conjunto2 = conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNumeros();
						ArrayList<Integer> diferencia = new ArrayList<Integer>();

						System.out.println("realizando operacion...");
						//
						for (int i = 0; i < conjunto1.size(); i++) {
							boolean validar = true;
							for (int j = 0; j < conjunto2.size(); j++) {
								if(conjunto2.get(j)==conjunto1.get(i)){
									validar=false;
								}
							}
							if(validar){
								diferencia.add(conjunto1.get(i));
							}
						}
						Collections.sort(diferencia);
						conjuntos.add(new Conjunto(diferencia, nombre));
						mostrarConjunto(nombre);
						//
					}else{
						mostrarConjunto(nombre);
					}
					
				}else{
					System.out.println("Los datos ingresados no son validos");
				}
			}
		} catch (Exception e) {
			System.out.println("Error(5)....");
			e.printStackTrace();
			Inicio.main(null);
		}
	}

	public static void construirDiferenciaSimetrica() {
		try {
			System.out.println("Construir Diferencia Simetrica:");
			System.out.println("Seleccione los numeros de los conjuntos a operar separados por la letra S (mayuscula):");
			mostrarConjuntos();
			String operacion=Leer.dato();
			String[] conjuntos1=operacion.split("S");
			
			if(conjuntos1.length!=2){
				System.out.println("la operacion no es valida...");
			}else{
				if(validarNumerosConjunto(conjuntos1)){
					boolean val=true;
					String nombre = "("+conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNombre()+"S"+conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNombre()+")";
					for (int i=0;i<conjuntos.size(); i++) {
						if(conjuntos.get(i).getNombre().equals(nombre)){
							val=false;
							break;
						}
					}	
					if(val){
						ArrayList<Integer> conjunto1 = conjuntos.get(Integer.parseInt(conjuntos1[0])-1).getNumeros();
						ArrayList<Integer> conjunto2 = conjuntos.get(Integer.parseInt(conjuntos1[1])-1).getNumeros();
						ArrayList<Integer> simetrica = new ArrayList<Integer>();
						System.out.println("realizando operacion...");
						//
						for (int i = 0; i < conjunto1.size(); i++) {
							boolean validacion=true;
							for (int j = 0; j < conjunto2.size(); j++) {
								if(conjunto1.get(i)==conjunto2.get(j)) validacion = false;
							}
							if(validacion) simetrica.add(conjunto1.get(i));
						}
						for (int i = 0; i < conjunto2.size(); i++) {
							boolean validacion=true;
							for (int j = 0; j < conjunto1.size(); j++) {
								if(conjunto2.get(i)==conjunto1.get(j)) validacion = false;
							}
							if(validacion) simetrica.add(conjunto2.get(i));
						}
						Collections.sort(simetrica);
						conjuntos.add(new Conjunto(simetrica, nombre));
						mostrarConjunto(nombre);
						//
					}else{
						mostrarConjunto(nombre);
					}
					
				}else{
					System.out.println("Los datos ingresados no son validos");
				}
			}
		} catch (Exception e) {
			System.out.println("Error(5)....");
			e.printStackTrace();
			Inicio.main(null);
		}
	}
	
	public static void mostrarConjuntos(){
		for (int i=0;i<conjuntos.size();i++) {
			System.out.println((i+1)+". Datos del Conjunto: "+conjuntos.get(i).toString());
		}
		System.out.println();
	}
	
	public static void mostrarConjunto(String nombre){
		for (int i=0;i<conjuntos.size();i++) {
			if(conjuntos.get(i).getNombre().equals(nombre)){
				System.out.println(conjuntos.get(i).toString());
			}
		}
		System.out.println();
	}

}

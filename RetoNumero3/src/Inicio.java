
public class Inicio {

	public static void main(String[] args) {
		try {
			int op=0;
			do{
				System.out.println("1. Crear Conjunto\n2. Operar Conjuntos\n3. Salir");
				op=Leer.datoInt();
				switch (op) {
					case 1:AdminConjuntos.crearConjunto();break;
					case 2:operarConjuntos();break;
					case 3:System.out.println("Fin de la ejecucion.");System.exit(0);break;
					default:break;
				}
			}while(op!=3);
		} catch (Exception e) {
			System.out.println("Error(1)...");
			e.printStackTrace();
			main(null);
		}
	}
	
	public static void operarConjuntos(){
		try {
			int op=0;
			do{
				System.out.println("1. Union\n2. Interseccion\n3. Diferencia\n4. Diferencia Simetrica\n5. Volver al menu principal");
				op=Leer.datoInt();
				switch (op) {
					case 1:AdminConjuntos.construirUnion();break;
					case 2:AdminConjuntos.construirInterseccion();break;
					case 3:AdminConjuntos.construirDiferencia();break;
					case 4:AdminConjuntos.construirDiferenciaSimetrica();break;
					case 5:System.out.println("-------");;break;
					default:break;
				}
			}while(op!=5);
		} catch (Exception e) {
			System.out.println("Error(2)....");
			e.printStackTrace();
			operarConjuntos();
		}
	}
}

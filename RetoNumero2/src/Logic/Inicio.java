package Logic;

/*************************************************************************/
/* Versión: 1                                                            */
/* Autor: Steven Puerto                                                  */
/* Fecha: 15/05/2016 dd/mm/aaa 											 */
/* Descripción: Programa para contar palabras, parrafos, lineas, letras, */ 
/* vocales y consonantes de un texto  									 */
/*************************************************************************/

public class Inicio {

	public static void main(String[] args) {
		String ruta ="texto3.txt";
		int[] resultado = Conteo.contar(ruta);// palabras, letras, parrafos, vocales, consonantes, lineas
		System.out.println("El Archivo "+ruta+" Contiene\n"+resultado[0]+" Palabras\n"+resultado[1]+" Letras\n"+resultado[2]+" Parrafos\n"+
							resultado[3]+" Vocales\n"+resultado[4]+" Consonantes\n"+resultado[5]+" Lineas\n");
	}
}

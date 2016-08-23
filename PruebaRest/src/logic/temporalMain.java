package logic;

public class temporalMain {

	private static tienda ti = new tienda("localhost",8080,"/TiendaV4/rest/ServicioREST");
	
	public static void main(String[] args) {
		consumirAgregarCancion();
		consumirGenerarReporte();
	}
	
	public static void consumirGenerarReporte(){
		//String[] datos = {fechaInicio, fechaFinal};
		String[] datos = {"01-01-01","02-02-02"};
		ti.consumirServicio(2, datos);
	}
	
	public static void consumirAgregarCancion(){
		//String[] datos = {nombre, artista, album, genero};
		String[] datos = {"minombre","yomismo","notengo","masculino"};
		ti.consumirServicio(1, datos);
	}
}

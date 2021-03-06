package co.edu.usa.adf.junit;

public class JuegoDeTenis {

	private String elQueSirve;
	private String mensaje=null;
	
	public JuegoDeTenis(String elQueSirve, String elQueRecibe) {
		this.elQueSirve=elQueSirve;
	}
	
	public String cantar() {
		if(mensaje == null){
			return "Sirve "+elQueSirve;
		}
		return mensaje;
	}

	public void puntoServidor() throws JuegoTerminadoException {
		if(mensaje==null){
			mensaje="Quince - Cero";
		}else{
			if(mensaje.equals("Quince - Cero")){
				mensaje = "Treinta - Cero";
			}else{
				if(mensaje.equals("Treinta - Cero")){
					mensaje = "Cuarenta - Cero";
				}else{
					if(mensaje.equals("Cuarenta - Cero")){
						throw new JuegoTerminadoException("Juego Ya Terminado");
					}
				}
			}
		}
		if(mensaje.equals("Cero - Quince")){
			mensaje = "Quince Iguales";
		}else{
			if(mensaje.equals("Quince Iguales")){
				mensaje = "Treinta - Quince";
			}
		}
	}

	public void puntoReceptor() {
		if(mensaje == null){
			mensaje="Cero - Quince";
		}
		
		if(mensaje.equals("Quince - Cero")){
			mensaje = "Quince Iguales";
		}else{
			if(mensaje.equals("Quince Iguales")){
				mensaje = "Quince - Treinta";
			}
		}
	}
}

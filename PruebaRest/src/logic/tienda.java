package logic;

public class tienda implements servicios{
	
	private String ip;
	private int puerto;
	private String ruta;
	
	public tienda(String ip, int puerto, String ruta) {
		this.ip=ip;
		this.puerto=puerto;
		this.ruta=ruta;
	}
	
	
	@SuppressWarnings("null")
	@Override
	public Object consumirServicio(int numeroServicio, Object[] datos) {
		switch (numeroServicio) {
			case 1:return aceptarCancion(datos);
			case 2:generarReporte(datos);break;
			default:System.out.println("El servicio no existe!(1)--------------");;break;
		}
		return null;
	}

	//-aceptarCancion(nombre,artista,álbum,genero)->Void---------------------------------
	private boolean aceptarCancion(Object[] datos) {
		try {
			//datos[0] --- nombre
			//datos[1] --- artista
			//datos[2] --- album
			//datos[3] --- genero
			String[] atributos={"nombre","artista","album","genero"};
			String comando="http://"+ip+":"+puerto+ruta+"/aceptarCancion?";
			for (int i = 0; i < atributos.length; i++) {
				if(i!=0) comando+="&";
				comando+=atributos[i]+"="+datos[i];
			}
			System.out.println(comando);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//-generarReporte(fechaInicio, fechaFinal)-> nombreCancion, fechaMercado, nombreArtista, genero, álbum, cantidadVendida.
	private void generarReporte(Object[] datos) {
		//datos[0] --- fechaInicio
		//datos[1] --- fechaFinal
		String[] atributos={"fechaInicio","fechaFinal"};
		String comando="http://"+ip+":"+puerto+ruta+"/generarReporte?";
		for (int i = 0; i < atributos.length; i++) {
			if(i!=0) comando+="&";
			comando+=atributos[i]+"="+datos[i];
		}
		System.out.println(comando);
	}

	private void enviarComando(String comando){
		
	}
	
	//metodos para datos de coneccion------------------------------------
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

}

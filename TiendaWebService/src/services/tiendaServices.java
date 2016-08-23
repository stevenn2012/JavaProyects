package services;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tiendaServices")
public class tiendaServices {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/prueba")
	public String aceptarCancion(String nombre){
		return "hola mundo! o.O 2000";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/aceptarCancion")
	public String aceptarCancion(String nombre, String artista, String álbum, String genero){
		return "hola mundo! o.O";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/generarReporte")
	public String generarReporte(String fechaInicio, String fechaFinal){
		return "hololo";
	}
}

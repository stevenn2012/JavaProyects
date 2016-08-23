import java.net.*;
import java.io.*;
 
public class consumirServicio {
	
	public static void main(String argv[]) throws InterruptedException {	
		try {  
			
			System.out.println("Conectando...");
			
			//String comand1="cmd /C start telnet 127.0.0.1 8080";
			//String comand2="Telnet /C GET /TiendaV4/rest/ServicioREST/aceptarCancion?nombre=prueba55513&artista=pruebita13&album=13&genero=rock";
			//String[] comand={comand1,comand2};
			String comand="cmd /C telnet 127.0.0.1 8080";
			Process p = Runtime.getRuntime().exec(comand);
			System.out.println("Se ha conectado con exito!");	
			
			System.out.println("Consumiendo servicio...");
			comand="cmd /C>Telnet GET /TiendaV4/rest/ServicioREST/aceptarCancion?nombre=prueba55513&artista=pruebita13&album=13&genero=rock";
			p = Runtime.getRuntime().exec(comand);
			
			
			//"cmd /C telnet 127.0.0.1 8080",
			//"cmd /C GET /TiendaV4/rest/ServicioREST/aceptarCancion?nombre=prueba55513&artista=pruebita13&album=13&genero=rock"
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        
			String line = reader.readLine();
		        
		    while (line != null) {
		        System.out.println(line);
		        line = reader.readLine();
		    }
		        
		    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		    String Error;
		    
		    while ((Error = stdError.readLine()) != null) {
		      	System.out.println(Error);
		    }
		    
		    while ((Error = stdInput.readLine()) != null) {
		      	System.out.println(Error);
		    }
        } catch (IOException e) {  
            e.printStackTrace();  
        }
	 }
}

/*try {
Socket socket = new Socket("127.0.0.1",8080); 
DataOutputStream out = new DataOutputStream(socket.getOutputStream());
String mensaje1="telnet 127.0.0.1 8080";
String mensaje2="GET /TiendaV4/rest/ServicioREST/aceptarCancion?nombre=prueba55513&artista=pruebita13&album=13&genero=rock";
out.writeUTF(mensaje1);		
out.writeUTF(mensaje2);		
socket.close();
}catch (Exception e) {
System.err.println(e.getMessage());
System.exit(1);
}*/
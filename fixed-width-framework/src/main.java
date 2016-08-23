
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.swing.internal.plaf.metal.resources.metal_ja;

import co.edu.usa.adf.Contacto;
import co.edu.usa.adf.PersistanceUtil;

public class main {

	public static void main(String[] args) {
		try {
			/*Class<?> cls = Class.forName("co.edu.usa.adf.Contacto");
			Object inst =cls.newInstance();
			
			Method m = cls.getMethod("setNombre", String.class);
			m.invoke(inst, "Steven");
			
			m = cls.getMethod("setCelular", String.class);
			m.invoke(inst, "(+57)3015436823");
			
			m = cls.getMethod("setEmail", String.class);
			m.invoke(inst, "2012Stevenn@gmail.com");
			
			m = cls.getMethod("setDireccion", String.class);
			m.invoke(inst, "Cra 78a n� 58p - 09 sur");
			
			m = cls.getMethod("setEdad", int.class);
			m.invoke(inst, 21);
			
			m = cls.getMethod("setCedula", int.class);
			m.invoke(inst, 1022395936);
			
			System.out.println(inst);
		
			System.out.println("------------------------------------");
			
			Class<?>[] tipos = {String.class};
			System.out.println(buscarMetodo(cls, "nombr", tipos));
			*/
			
			ArrayList<Object> contactos = PersistanceUtil.readFile("Descriptor_contacto.xml", "Contactos.txt");
			
			for (int i = 0; i < contactos.size(); i++) {
				System.out.println(contactos.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static String buscarMetodo(Class<?> cls, String nombreAtributo, Class<?>[] tipos){
		try {
			String nombre=(nombreAtributo.charAt(0)+"").toUpperCase();
			for (int i = 1; i < nombreAtributo.length(); i++)nombre+=nombreAtributo.charAt(i);	
			String methodName = "set"+nombre;
			
			Method metodos[] = cls.getMethods();
			
			
			for (int i = 0; i < metodos.length; i++) {
				if(metodos[i].getName().equalsIgnoreCase("set"+nombre)){
					return methodName;
				}else if(Arrays.toString(metodos[i].getParameterTypes()).equals(Arrays.toString(tipos)) && metodos[i].getName().indexOf(nombre)>(-1)){
					methodName= metodos[i].getName();
				}
			}
			return methodName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void infoClass(String classRute){
		try {
			//Class<?> miClase = Class.forName("co.edu.usa.adf.PhoneContact");
			Class<?> miClase = Class.forName(classRute);
			
			System.out.println("Nombre: "+miClase.getName());
			System.out.println("Modifiers: "+Modifier.toString(miClase.getModifiers()));
			System.out.println("Parametros Tipo: "+Arrays.toString(miClase.getTypeParameters()));
			System.out.println("Interfaces: "+Arrays.toString(miClase.getInterfaces()));
			
			String camino="";
			Class<?> miClaseherencia = Class.forName(classRute);
			String hereda;
			
			while(true){
				hereda=miClaseherencia.getSuperclass().getName();
				camino+=hereda;
				if(hereda.equalsIgnoreCase("java.lang.Object")){
					break;
				}else{
					camino+=", ";
				}
				miClaseherencia = Class.forName(hereda);
			}
			
			System.out.println("Camino Herencia: "+camino);
			
			System.out.println("Anotaciones: "+Arrays.toString(miClase.getAnnotations()));
			System.out.println("Atributos: "+Arrays.toString(miClase.getDeclaredFields()));
			System.out.println("Metodos: "+Arrays.toString(miClase.getMethods()));
			System.out.println("Constructores: "+Arrays.toString(miClase.getConstructors()));
			System.out.println();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

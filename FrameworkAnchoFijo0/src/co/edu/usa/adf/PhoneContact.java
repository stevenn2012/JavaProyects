package co.edu.usa.adf;

import java.util.Iterator;
import java.util.List;

public class PhoneContact extends AbstractPersistable{
	
	private String nombre;
	private String direccion;
	private String numeroTelefono;
	private int[] widths = new int[]{20, 50, 15};
	
	static{
		try {
			List<String> records= PersistanceUtil.readFile("contacts.txt");
			for(String record: records){
				new PhoneContact().init(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public PhoneContact(){
		
	}
		
	@Override
	protected void set(List<Object> chunks) {
		Iterator<Object> it= chunks.iterator();
		nombre= (String)it.next();
		direccion= (String)it.next();
		numeroTelefono= (String)it.next();
	}
	
	@Override
	protected int[] getWidths() {
		return widths;
	}
	
	public void setWidths(int[] widths) {
		this.widths = widths;
	}

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getNumeroTelefono() {
		return numeroTelefono;
	}



	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

}

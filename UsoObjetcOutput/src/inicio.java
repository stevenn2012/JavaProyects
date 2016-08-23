import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class inicio {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Integer> list= new ArrayList<Integer>();
			list.add(12);
			list.add(15);
			list.add(4);
			list.add(32);
			
		numeros num = new numeros(list);
		
		ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream("media.txt"));
	    	salida.writeObject(num);
	    salida.close();
	    
	    ObjectInputStream entrada=new ObjectInputStream(new FileInputStream("media.txt"));
		    numeros obj1=(numeros)entrada.readObject();
		    ArrayList<Integer> list2 = obj1.getList();
		entrada.close();
	    
	    for (int i = 0; i < list2.size(); i++) {
			System.out.println(list2.get(i));
		}
	}
}

import java.io.Serializable;
import java.util.ArrayList;

public class numeros implements Serializable{
	ArrayList<Integer> list= new ArrayList<Integer>();

	public numeros(ArrayList<Integer> list) {
		this.list = list;
	}

	public ArrayList<Integer> getList() {
		return list;
	}

	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}
}

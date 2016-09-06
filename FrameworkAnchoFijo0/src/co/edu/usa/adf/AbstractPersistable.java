package co.edu.usa.adf;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPersistable {

	public void init(String initialData){
		int[] widths= getWidths();
		List<Object> chunks= new ArrayList<>();
		
		for(int i=0; i< widths.length; i++){
			chunks.add(initialData.substring(0, widths[i]));
			initialData= initialData.substring(widths[i]+1);
		}
		
		set(chunks);
	}
	
	protected abstract void set(List<Object> chunks);
	
	protected abstract int[] getWidths();

}

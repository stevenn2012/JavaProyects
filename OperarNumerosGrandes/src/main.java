
public class main {

	public static void main(String[] args) {
		System.out.println(suma("123456","98765"));
	}

	public static String suma(String num1, String num2){
		String result="";
		char[] numb1= num1.toCharArray();
		char[] numb2= num2.toCharArray();
		
		int n=1;
		int menor=numb1.length;
		
		if(numb2.length<menor){
			menor=numb2.length;
			n=2;
		}
		
		char acc='0';
		for(int i=(menor-1);i>=0;i--){
			char[] res=opsum(numb1[i],numb2[i],acc);
			acc = ((res.length>1) ? res[0] : 0);
			if(acc==0) result= res[0]+result; else result= res[1]+result; 
		}
		if(acc!=0){
			result=acc+result;
		}
		
		int k=0;
		if(numb1.length!=numb2.length){
			while(acc!=0){
				if(numb1.length!=numb2.length){
					char[] res;
					if(n==1){
						res = opsum(numb1[menor-k],'0',acc); 
					}else{
						res = opsum('0',numb2[menor-k],acc);
					}
					acc = ((res.length>1) ? res[0] : 0);
					if(acc==0) result= res[0]+result; else result= res[1]+result;
					k++;
				}
			}
		}
		
		if(n==1){
			for(int i=numb1.length-(menor+k)-1;i>=0;i--){	
				result=numb1[i]+result;
			}
		}else{
			for(int i=numb2.length-(menor+k)-1;i>=0;i--){	
				result=numb2[i]+result;
			}
		}
		
		return result;
	}
	
	public static char[] opsum(char num1, char num2,char acc){
		return (""+(Integer.parseInt(num1+"")+Integer.parseInt(num2+"")+Integer.parseInt(acc+""))).toCharArray();
	}
}

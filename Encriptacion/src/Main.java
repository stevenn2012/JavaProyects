
public class Main {

	public static void main(String[] args) {
		String caracter="ñ";
		byte[] dato=caracter.getBytes();
		for (int i = -127; i <= 127; i++) {
			byte bytes=(byte)i;
			System.out.println("Byte: "+i+" Caracter: "+(char) bytes);
		}
	}
}
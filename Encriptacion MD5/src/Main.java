import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(getMD5("Steven Puerto"));
    }

	public static String getMD5(String input) {
        try {
            String hashtext = new BigInteger(1, MessageDigest.getInstance("MD5").digest(input.getBytes())).toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
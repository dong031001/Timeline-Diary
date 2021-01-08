import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

public class User {

    private Date creationDate;
    private int userID;
    private String username;
    private String passwordChar;

    public User(String username, String password) throws NoSuchAlgorithmException {

        this.username = username;
        this.passwordChar = charize(password);
        this.userID = Main.getNewUserID();
        this.creationDate = Date.from(Instant.now());

    }

    private static String charize(String text) throws NoSuchAlgorithmException {
        MessageDigest charizer = MessageDigest.getInstance("MD5");
        byte[] inputByteArray = text.getBytes();
        charizer.update(inputByteArray);
        byte[] resultByteArray = charizer.digest();
        return byteArrayToHex(resultByteArray);
    }

    public boolean isRightPassword(String inputPassword) throws NoSuchAlgorithmException {
        return charize(inputPassword).equals(this.passwordChar);
    }

    public static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        char[] resultCharArray =new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        return new String(resultCharArray);
    }

    public String serialize(){
        System.out.println(IOHandler.getGson().toJson(this));
        return IOHandler.getGson().toJson(this);
    }

    public static User deserialize(String json){
        return IOHandler.getGson().fromJson(json, User.class);
    }

    public int getUserID() {
        return userID;
    }
}

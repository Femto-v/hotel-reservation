import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordUtils {
    public static String readPasswordFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine(); // Assuming the password is in the first line
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if an error occurs
        }
    }
}

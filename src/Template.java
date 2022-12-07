import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Template {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./example.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class D01 {
    public static void main(String[] args) {
        File input = new File("./D01.txt");
        int topOne = 0;
        int topTwo = 0;
        int topThree = 0;
        try {
            FileReader fileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int calories = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.equals("")) {
                    if(calories>topOne) {
                        topTwo = topOne;
                        topOne = calories;
                    } else if(calories>topTwo) {
                        topThree = topTwo;
                        topTwo = calories;
                    } else if(calories>topThree) {
                        topThree = calories;
                    }
                    calories = 0;
                } else {
                    calories += Integer.parseInt(line);
                }
            }
            bufferedReader.close();
            fileReader.close();
            System.out.println(topOne);
            System.out.println(topOne+topTwo+topThree);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

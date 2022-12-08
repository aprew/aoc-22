import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class D03 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./D03.txt"));
            String line;
            int sum = 0;
            while ((line = bufferedReader.readLine()) != null) {
                int middle = (line.length()/2);
                String first = line.substring(0,middle);
                String second = line.substring(middle);
                char item = findSharedItem(first, second);
                sum += getValue(item);
            }
            System.out.println(sum);
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader("./D03.txt"));
            sum = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String second = bufferedReader.readLine();
                String third = bufferedReader.readLine();
                sum += getValue(findBadge(line, second, third));
            }
            System.out.println(sum);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static char findSharedItem(String first, String second) {
        for(char c1 : first.toCharArray()) {
            for(char c2 : second.toCharArray()) {
                if(c1==c2) return c1;
            }
        }
        return 0;
    }

    static int getValue(char c) {
        if(Character.isUpperCase(c)) {
            return (int)c-38;
        } else {
            return (int)c-96;
        }
    }

    static char findBadge(String first, String second, String third) {
        for(char c1 : first.toCharArray()) {
            for(char c2 : second.toCharArray()) {
                if(c1==c2) {
                    for(char c3 : third.toCharArray()) {
                        if(c3 == c1) return c3;
                    }
                }
            }
        }
        return 0;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class D04 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./D04.txt"));
            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                String first = strings[0];
                String second = strings[1];
                if (rangeContains(getRange(first),getRange(second))) {
                    count++;
                }
            }
            System.out.println(count);
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader("./D04.txt"));
            count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                String first = strings[0];
                String second = strings[1];
                if (rangeOverlaps(getRange(first),getRange(second))) {
                    count++;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[] getRange(String input) {
        int[] range = new int[2];
        String[] strings = input.split("-");
        range[0] = Integer.parseInt(strings[0]);
        range[1] = Integer.parseInt(strings[1]);
        return range;
    }

    static boolean rangeContains(int[] first, int[] second) {
        if(first[0] <= second[0] && first[1] >= second[1]) {
            return true;
        } else return second[0] <= first[0] && second[1] >= first[1];
    }

    static boolean rangeOverlaps(int[] first, int[] second) {
        return first[1] >= second[0] && second[1] >= first[0];
    }
}

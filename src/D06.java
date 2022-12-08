import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class D06 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./D06.txt"));
            String line;
            HashMap<Character, Integer> charCount = new HashMap<>();
            LinkedList<Character> charHistory = new LinkedList<>();
            final int MARKER = 14;
            while ((line = bufferedReader.readLine()) != null) {
                int count = 0;
                charCount.clear();
                charHistory.clear();
                for(char c : line.toCharArray()) {
                    count++;
                    charHistory.add(c);
                    if(!charCount.containsKey(c)) {
                        charCount.put(c,1);
                    } else {
                        charCount.replace(c,charCount.get(c)+1);
                    }
                    if(charCount.size()==MARKER) {
                        int sum=0;
                        for(Integer i : charCount.values()) {
                            sum+=i;
                        }
                        if(sum==MARKER) {
                            System.out.println(count);
                            break;
                        }
                    }
                    if(charHistory.size()==MARKER) {
                        char charToRemove = charHistory.poll();
                        if(charCount.get(charToRemove) > 1) {
                            charCount.replace(charToRemove,charCount.get(charToRemove)-1);
                        } else {
                            charCount.remove(charToRemove);
                        }
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

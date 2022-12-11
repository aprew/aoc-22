import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class D10 {
    public static void main(String[] args) {
        int cycle = 0;
        int x = 1;
        int sum=0;
        String output = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./D10.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(" ");
                cycle++;
                output = draw(output,cycle, x);
                if((cycle-20)%40==0) {
                    sum+=x*cycle;
                }
                switch (strings[0]) {
                    case "noop" -> {}
                    case "addx" -> {
                        cycle++;
                        if((cycle-20)%40==0) {
                            sum+=x*cycle;
                        }
                        output = draw(output, cycle, x);
                        x += Integer.parseInt(strings[1]);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("sum = " + sum);
        System.out.println(output.substring(0,40));
        System.out.println(output.substring(40,80));
        System.out.println(output.substring(80,120));
        System.out.println(output.substring(120,160));
        System.out.println(output.substring(160,200));
        System.out.println(output.substring(200));
    }

    private static String draw(String output,int cycle, int x) {
        int position = (cycle%40)-1;
        if(x==position||x-1==position||x+1==position) {
            output += "#";
        } else {
            output += ".";
        }
        return output;
    }
}

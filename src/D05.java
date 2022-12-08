import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

public class D05 {
    private static final Vector<Stack<Character>> stackVector = new Vector<>();
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./D05.txt"));
            String line;
            boolean stacksMade = false;
            boolean cratesParsed = false;
            Vector<Stack<Character>> intialStackVector = new Vector<>();
            while (!cratesParsed) {
                line = bufferedReader.readLine();
                String[] crates;
                crates = line.split("(\\] \\[|\\[|   \\[|\\]  |\\])|    |  \\n");
                if (crates.length==1) {
                    cratesParsed = true;
                } else {
                    if (!stacksMade) {
                        for (int i = 0; i < crates.length-1; i++) {
                            intialStackVector.add(new Stack<>());
                            stackVector.add(new Stack<>());
                        }
                        stacksMade = true;
                    }
                    for (int i = 0; i < crates.length; i++) {
                        if (!crates[i].isBlank()) {
                            intialStackVector.elementAt(i - 1).push(crates[i].charAt(0));
                        }
                    }
                }
            }
            for (int i=0; i < intialStackVector.size(); i++) {
                int initialSize = intialStackVector.elementAt(i).size();
                for (int j=0; j < initialSize;j++) {
                    stackVector.elementAt(i).push(intialStackVector.elementAt(i).pop());
                }
            }
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] instructions;
                instructions = line.split("move | from | to ");
                newMoveFromTo(Integer.parseInt(instructions[1]),Integer.parseInt(instructions[2]),Integer.parseInt(instructions[3]));
            }
            for (Stack<Character> stack : stackVector) {
                System.out.print(stack.pop());
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void moveFromTo(int quantity, int source, int target) {
        for (int i=0; i < quantity; i++) {
            stackVector.elementAt(target-1).push(stackVector.elementAt(source-1).pop());
        }
    }

    static void newMoveFromTo(int quantity, int source, int target) {
        Vector<Stack<Character>> teacup = new Vector<>();
        for (int i = 0; i < stackVector.size(); i++) {
            teacup.add(new Stack<>());
        }
        for (int i=0; i < quantity; i++) {
            teacup.elementAt(target-1).push(stackVector.elementAt(source-1).pop());
        }
        for (int i=0; i < quantity; i++) {
            stackVector.elementAt(target-1).push(teacup.elementAt(target-1).pop());
        }
    }
}

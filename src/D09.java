import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class D09 {
    public static void main(String[] args) {
        final int SIZE = 1000;
        final int MID = SIZE / 2;
        final int KNOTS = 10;
        boolean[][] visited = new boolean[SIZE][SIZE];
        LinkedList<Integer[]> knots = new LinkedList<>();
        for(int i=0;i<KNOTS;i++) {
            knots.add(new Integer[]{MID, MID});
        }
        visited[MID][MID] = true;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./D09.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(" ");
                int move = Integer.parseInt(strings[1]);
                switch (strings[0].charAt(0)) {
                    case 'U' -> {
                        for (int i = 0; i < move; i++) {
                            knots.peek()[1]--;
                            for (int j = 1; j < knots.size(); j++) {
                                checkMove(knots.get(j - 1), knots.get(j));
                            }
                            visited[knots.peekLast()[0]][knots.peekLast()[1]] = true;
                        }
                    }
                    case 'R' -> {
                        for (int i = 0; i < move; i++) {
                            knots.peek()[0]++;
                            for (int j = 1; j < knots.size(); j++) {
                                checkMove(knots.get(j - 1), knots.get(j));
                            }
                            visited[knots.peekLast()[0]][knots.peekLast()[1]] = true;
                        }
                    }
                    case 'L' -> {
                        for (int i = 0; i < move; i++) {
                            knots.peek()[0]--;
                            for (int j = 1; j < knots.size(); j++) {
                                checkMove(knots.get(j - 1), knots.get(j));
                            }
                            visited[knots.peekLast()[0]][knots.peekLast()[1]] = true;
                        }
                    }
                    case 'D' -> {
                        for (int i = 0; i < move; i++) {
                            knots.peek()[1]++;
                            for (int j = 1; j < knots.size(); j++) {
                                checkMove(knots.get(j - 1), knots.get(j));
                            }
                            visited[knots.peekLast()[0]][knots.peekLast()[1]] = true;
                        }
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int count=0;
        for (boolean[] bools : visited) {
            for (boolean bool : bools) {
                if(bool) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void checkMove(Integer[] head, Integer[] tail) {
        int diffX = head[0]-tail[0];
        int diffY = head[1]-tail[1];
        switch (diffX) {
            case 2 -> {
                tail[0]++;
                switch (diffY) {
                    case 1 -> tail[1]++;
                    case -1 -> tail[1]--;
                }
            }
            case -2 -> {
                tail[0]--;
                switch (diffY) {
                    case 1 -> tail[1]++;
                    case -1 -> tail[1]--;
                }
            }
        }
        switch (diffY) {
            case 2 -> {
                tail[1]++;
                switch (diffX) {
                    case 1 -> tail[0]++;
                    case -1 -> tail[0]--;
                }
            }
            case -2 -> {
                tail[1]--;
                switch (diffX) {
                    case 1 -> tail[0]++;
                    case -1 -> tail[0]--;
                }
            }
        }
    }
}

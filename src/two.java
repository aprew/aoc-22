import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class two {

    public static void main(String[] args) {
        File input = new File("./two.txt");
        partOne(input);
        partTwo(input);
    }

    private static void partOne(File input) {
        try {
            FileReader fileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int score = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] rps = line.split(" ");
                switch (rps[0]) {
                    case "A" -> {
                        switch (rps[1]) {
                            case "X" -> score += 4;
                            case "Y" -> score += 8;
                            case "Z" -> score += 3;
                        }
                    }
                    case "B" -> {
                        switch (rps[1]) {
                            case "X" -> score += 1;
                            case "Y" -> score += 5;
                            case "Z" -> score += 9;
                        }
                    }
                    case "C" -> {
                        switch (rps[1]) {
                            case "X" -> score += 7;
                            case "Y" -> score += 2;
                            case "Z" -> score += 6;
                        }
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
            System.out.println(score);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void partTwo(File input) {
        try {
            FileReader fileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int score = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] rps = line.split(" ");
                switch (rps[0]) {
                    case "A" -> {
                        switch (rps[1]) {
                            case "X" -> score += 3;
                            case "Y" -> score += 4;
                            case "Z" -> score += 8;
                        }
                    }
                    case "B" -> {
                        switch (rps[1]) {
                            case "X" -> score += 1;
                            case "Y" -> score += 5;
                            case "Z" -> score += 9;
                        }
                    }
                    case "C" -> {
                        switch (rps[1]) {
                            case "X" -> score += 2;
                            case "Y" -> score += 6;
                            case "Z" -> score += 7;
                        }
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
            System.out.println(score);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }

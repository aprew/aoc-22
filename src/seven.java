import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class seven {
    private static int GLOBAL_SUM = 0;
    private static int GLOBAL_TARGET = 0;
    private static int GLOBAL_BEST_SIZE = 0;
    public static void main(String[] args) {
        TreeNode<HashMap<String, Integer>> root = new TreeNode<>(new HashMap<>(), "root");
        TreeNode<HashMap<String, Integer>> current  = root;
        boolean list = false;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./seven.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(" ");
                if(line.charAt(0)=='$') {
                    switch (strings[1]) {
                        case "ls" -> list = true;
                        case "cd" -> {
                            list = false;
                            if (strings[2].equals("/")) {
                                current = root;
                            } else if (strings[2].equals("..")) {
                                current = current.getParent();
                            } else {
                                ArrayList<TreeNode<HashMap<String, Integer>>> children = current.getChildren();
                                for (TreeNode<HashMap<String, Integer>> child : children) {
                                    if (strings[2].equals(child.getId())) {
                                        current = child;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if(list) {
                        if (strings[0].equals("dir")) {
                            current.addChild(new TreeNode<>(new HashMap<>(), strings[1]));
                        } else {
                            current.getData().put(strings[1],Integer.parseInt(strings[0]));
                        }
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GLOBAL_BEST_SIZE = sumAllChildren(root);
        System.out.println("sum of smaller than 100000: " + GLOBAL_SUM);
        GLOBAL_TARGET = 30000000 - (70000000 - GLOBAL_BEST_SIZE);
        System.out.println("total size: "+sumAllChildren(root));
        System.out.println("smallest that meets target: " + GLOBAL_BEST_SIZE);
    }

    private static int sumSizes(HashMap<String, Integer> input) {
        int sum = 0;
        for(int size : input.values()) {
            sum += size;
        }
        return sum;
    }

    private static int sumAllChildren(TreeNode<HashMap<String, Integer>> root) {
        int sum = 0;
        for(TreeNode<HashMap<String, Integer>> child : root.getChildren()) {
            sum += sumAllChildren(child);
        }
        int current = sum+sumSizes(root.getData());
        if(current<=100000) {
            GLOBAL_SUM += sum+sumSizes(root.getData());
        }
        if(current>GLOBAL_TARGET&&current<GLOBAL_BEST_SIZE) {
            GLOBAL_BEST_SIZE = current;
        }
        return sum+sumSizes(root.getData());
    }
}
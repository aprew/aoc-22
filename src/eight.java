import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class eight {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./eight.txt"));
            String line;
            ArrayList<ArrayList<Integer>> trees = new ArrayList<>();
            ArrayList<ArrayList<Boolean>> treeCounted = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                ArrayList<Integer> rowList = new ArrayList<>();
                ArrayList<Boolean> rowCount = new ArrayList<>();
                for(char c : line.toCharArray()) {
                    rowList.add(Character.getNumericValue(c));
                    rowCount.add(false);
                }
                trees.add(rowList);
                treeCounted.add(rowCount);
            }
            System.out.println(lookUp(trees, treeCounted)+
            lookDown(trees, treeCounted)+
            lookLeft(trees, treeCounted)+
            lookRight(trees, treeCounted));
            int highScore = 0;
            for(int x=0;x<trees.get(0).size();x++) {
                for(int y=0;y<trees.size();y++) {
                    int score = lookUpFrom(trees,x,y)*lookLeftFrom(trees,x,y)*lookDownFrom(trees,x,y)*lookRightFrom(trees,x,y);
                    if(score > highScore) {
                        highScore = score;
                    }
                }
            }
            System.out.println(highScore);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int lookUp(ArrayList<ArrayList<Integer>> trees, ArrayList<ArrayList<Boolean>> treeCounted) {
        int visible=0;
        for(int i=0;i<trees.get(0).size();i++) {
            int tallest=-1;
            for(int j=trees.size()-1;j>0;j--) {
                int tree = trees.get(j).get(i);
                if(tree>tallest) {
                    tallest = tree;
                    if(!treeCounted.get(j).get(i)) {
                        treeCounted.get(j).set(i, true);
                        visible++;
                    }
                    if (tallest==9) {
                        break;
                    }
                }
            }
        }
        return visible;
    }
    private static int lookUpFrom(ArrayList<ArrayList<Integer>> trees, int x, int y) {
        int visible=0;
        int current=trees.get(y).get(x);
        int tallest = -1;
        y--;
        for(int j=y;j>=0;j--) {
            int tree = trees.get(j).get(x);
            if(tree>=current||tallest>=current) {
                visible++;
                break;
            }
            if(tree>=tallest) {
                tallest = tree;
            }
            visible++;
        }
        return visible;
    }

    private static int lookDown(ArrayList<ArrayList<Integer>> trees, ArrayList<ArrayList<Boolean>> treeCounted) {
        int visible=0;
        for(int i=0;i<trees.get(0).size();i++) {
            int tallest=-1;
            for(int j=0;j<trees.size()-1;j++) {
                int tree = trees.get(j).get(i);
                if(tree>tallest) {
                    tallest = tree;
                    if(!treeCounted.get(j).get(i)) {
                        treeCounted.get(j).set(i, true);
                        visible++;
                    }
                    if (tallest==9) {
                        break;
                    }
                }
            }
        }
        return visible;
    }

    private static int lookDownFrom(ArrayList<ArrayList<Integer>> trees,int x,int y) {
        int visible=0;
        int current=trees.get(y).get(x);
        int tallest = -1;
        y++;
        for(int j=y;j<trees.size();j++) {
            int tree = trees.get(j).get(x);
            if(tree>=current||tallest>=current) {
                visible++;
                break;
            }
            if(tree>=tallest) {
                tallest = tree;
            }
            visible++;
        }
        return visible;
    }

    private static int lookLeft(ArrayList<ArrayList<Integer>> trees, ArrayList<ArrayList<Boolean>> treeCounted) {
        int visible=0;
        for(int j=1;j<trees.size()-1;j++) {
            int tallest=-1;
            for(int i=trees.get(0).size()-1;i>0;i--) {
                int tree = trees.get(j).get(i);
                if(tree>tallest) {
                    tallest = tree;
                    if(!treeCounted.get(j).get(i)) {
                        treeCounted.get(j).set(i, true);
                        visible++;
                    }
                    if (tallest==9) {
                        break;
                    }
                }
            }
        }
        return visible;
    }

    private static int lookLeftFrom(ArrayList<ArrayList<Integer>> trees, int x,int y) {
        int visible=0;
        int current=trees.get(y).get(x);
        int tallest = -1;
        x--;
        for(int j=x;j>=0;j--) {
            int tree = trees.get(y).get(j);
            if(tree>=current||tallest>=current) {
                visible++;
                break;
            }
            if(tree>=tallest) {
                tallest = tree;
            }
            visible++;
        }
        return visible;
    }
    private static int lookRight(ArrayList<ArrayList<Integer>> trees, ArrayList<ArrayList<Boolean>> treeCounted) {
        int visible=0;
        for(int j=1;j<trees.size()-1;j++) {
            int tallest=-1;
            for(int i=0;i<trees.get(0).size();i++) {
                int tree = trees.get(j).get(i);
                if(tree>tallest) {
                    tallest = tree;
                    if(!treeCounted.get(j).get(i)) {
                        treeCounted.get(j).set(i, true);
                        visible++;
                    }
                    if (tallest==9) {
                        break;
                    }
                }
            }
        }
        return visible;
    }

    private static int lookRightFrom(ArrayList<ArrayList<Integer>> trees, int x, int y) {
        int visible=0;
        int current=trees.get(y).get(x);
        int tallest = -1;
        x++;
        for(int i=x;i<trees.size();i++) {
            int tree = trees.get(y).get(i);
            if(tree>=current||tallest>=current) {
                visible++;
                break;
            }
            if(tree>=tallest) {
                tallest = tree;
            }
            visible++;
        }
        return visible;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class D12 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> heights = new ArrayList<>();
        UnweightedNode start = null;
        UnweightedNode end = null;
        ArrayList<UnweightedNode> nodes = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./D12.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                ArrayList<Integer> temp = new ArrayList<>();
                for(char c : line.toCharArray()) {
                    if(c=='S') temp.add(0);
                    else if (c=='E') temp.add(27);
                    else temp.add(c -  'a' + 1);
                    nodes.add(new UnweightedNode(c+":"+heights.size()+","+(temp.size()-1)));
                    if(c=='S')
                        start=nodes.get((heights.size() * line.length())+temp.size()-1);
                    else if (c=='E') end=nodes.get((heights.size()*line.length())+temp.size()-1);
                }
                heights.add(temp);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(heights);
        int index=0;
        int yIndex=0;
        start.distance=0;
        for(int y=0;y<heights.size();y++) {
            for(int x=0;x<heights.get(y).size();x++) {
                int current = heights.get(y).get(x);
                UnweightedNode curNode = nodes.get(index);
                if(y-1>=0) {
                    if(heights.get(y-1).get(x)<=current+1) {
                        curNode.addNeighbour(nodes.get((yIndex-1)*heights.get(y).size()+x));
                    }
                }
                if(y+1<heights.size()) {
                    if(heights.get(y+1).get(x)<=current+1) {
                        curNode.addNeighbour(nodes.get((yIndex+1)*heights.get(y).size()+x));
                    }
                }
                if(x-1>=0) {
                    if(heights.get(y).get(x-1)<=current+1) {
                        curNode.addNeighbour(nodes.get((yIndex*heights.get(y).size())+x-1));
                    }
                }
                if(x+1<heights.get(y).size()) {
                    if(heights.get(y).get(x+1)<=current+1) {
                        curNode.addNeighbour(nodes.get((yIndex*heights.get(y).size())+x+1));
                    }
                }
                index++;
                if(index%heights.get(y).size()==0) {
                    yIndex++;
                }
            }
        }
        bfs(start, end);
    }
    static void bfs(UnweightedNode start, UnweightedNode end) {
        LinkedList<UnweightedNode> queue = new LinkedList<>();
        start.visited = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            UnweightedNode curNode = queue.poll();
            if(curNode==end) {
                queue.clear();
                break;
            }
            for (UnweightedNode node : curNode.neighbours) {
                if(!node.visited) {
                    node.visited = true;
                    node.previous=curNode;
                    queue.add(node);
                }
            }
        }
        trace(end);
    }

    static void trace(UnweightedNode end) {
        UnweightedNode node = end;
        LinkedList<UnweightedNode> route = new LinkedList<>();
        while(node != null) {
            System.out.println(node);
            route.add(node);
            node=node.previous;
        }
        System.out.println(route.size());
    }
}

class UnweightedNode {
    String id;
    LinkedList<UnweightedNode> neighbours;
    boolean visited = false;
    UnweightedNode previous = null;
    int distance = 999999;
    UnweightedNode(String id) {
        this.id = id;
        this.neighbours = new LinkedList<>();
    }

    void addNeighbour(UnweightedNode node) {
        this.neighbours.add(node);
    }

    @Override
    public String toString() {
        return "UnweightedNode{" +
                "id='" + id + '\'' +
                '}';
    }
}
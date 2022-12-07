import java.util.ArrayList;

public class TreeNode<T> {
    private String id;
    private T data;
    private TreeNode<T> parent = null;
    private final ArrayList<TreeNode<T>> children = new ArrayList<>();

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, String id) {
        this.data = data;
        this.id = id;
    }

    public void addChild(TreeNode<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public ArrayList<TreeNode<T>> getChildren() {
        return children;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public String getId() {
        return id;
    }
    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }
}

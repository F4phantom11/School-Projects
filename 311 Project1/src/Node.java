import java.util.Random;

public class Node {
    private Node parent,left,right;
    private Interval interval;
    private int imax=0,prioity,height;
    private Random rand =  new Random();

    Node (Interval i){
        interval = i;
        prioity = rand.nextInt(100);
    }

    public int getImax() {
        return imax;
    }

    public void setImax(int imax) {

        this.imax = imax;
    }

    public int getPrioity() {
        return prioity;
    }

    public Interval getInterval() {
        return interval;
    }

    public Node getLeft() {
        return left;
    }

    public Node getParent() {
        return parent;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

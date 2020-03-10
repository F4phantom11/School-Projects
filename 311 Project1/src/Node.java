public class Node {
    private Node parent,left,right;
    private Interval interval;
    private int imax,prioity,height;
    Node (Interval i){
interval = i;
prioity = 5;
    }

    public int getImax() {
        return imax;
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
}

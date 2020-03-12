public class IntervalTreap {
    private Node root;
    private int size,height;

    IntervalTreap(){

    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    public Node getRoot() {
        return root;
    }
    void intervalInsert(Node z){
        //todo
    }
    void intervalDelete(Node z){
     //todo
    }
    Node intervalSearch(Interval i){
        //todo
        return new Node(new Interval(0,1));
    }
    int max = 0;
    public void settingImax(Node z){
        max = 0;
        if(z == null){
            return;
        }
        settingImax(z.getLeft());
  settingImax(z.getRight());
        if(z.getInterval().getHigh()>max) {
            max = z.getInterval().getHigh();
        }
        z.setImax(max);
    }
}

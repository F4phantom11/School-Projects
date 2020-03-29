public class IntervalTreap {
    private Node root;
    private int size=0,height;

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

    public void setRoot(Node root) {
        this.root = root;
    }

    void intervalInsert(Node z){
        //todo

        z.setImax(z.getInterval().getHigh());
        Node y = null;
        Node x = root;
        while(x!=null) {
            y = x;
            if (z.getInterval().getLow() < x.getInterval().getLow()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

            z.setParent(y);
            if(y==null) {
                setRoot(z);
            }
            else if(z.getInterval().getLow()<y.getInterval().getLow()){

                y.setLeft(z);
            }
            else {
                y.setRight(z);
            }
           if(y!=null) {
                while (z.getParent()!=null &&z.getParent().getPriority() > z.getPriority()) {
                    if (z.getParent().getLeft() == z) {

                     z=   rotateRight(z.getParent());
                    } else {
                       z= rotateLeft(z.getParent());
                    }
                }
            }


    }
    void intervalDelete(Node z){
     //todo
    }
    Node intervalSearch(Interval i){
        Node x = root;
        while (x!=null && ((i.getLow()>x.getInterval().getLow())&&i.getLow()>x.getInterval().getHigh())){
            if(x.getLeft()!=null&&x.getLeft().getImax()>= i.getLow()){
                x=x.getLeft();
            }
            else{
                x= x.getRight();
            }

        }
        return x;
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
    public void Height(){

    }
    private Node rotateRight(Node subRoot){
        Node R = subRoot.getLeft();
        Node Y = subRoot.getLeft().getRight();
        if(subRoot==getRoot()){
            setRoot(R);
        }
        R.setRight(subRoot);
        R.setParent(subRoot.getParent());
        subRoot.setParent(R);
        subRoot.setLeft(Y);
        return R;
    }
    private Node rotateLeft(Node subRoot){

        Node L = subRoot.getRight();
        Node X = subRoot.getRight().getLeft();
        L.setLeft(subRoot);
        subRoot.setRight(X);
        return L;
    }
}

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
                while (z.getParent()!=null && z.getParent().getPriority() > z.getPriority()) {
                    if (z.getParent().getLeft() == z) {

                     z=   rotateRight(z.getParent());
                    } else {
                       z= rotateLeft(z.getParent());
                    }
                }
            }

        settingImax(getRoot());
    }
    void intervalDelete(Node z){
     //todo
    }
    Node intervalSearch(Interval i){
        Node x = root;
        int count=0;
        System.out.println(doesOverlap(i,x.getInterval()));
        while (x!=null&&!doesOverlap(i,x.getInterval())){
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
        subRoot.setLeft(R.getRight());
        if(R.getRight()!=null){
            R.getRight().setParent(subRoot);
        }
        R.setParent(subRoot.getParent());
        if(subRoot.getParent()==null){
            setRoot(R);
        }
        else if(subRoot==subRoot.getParent().getRight()){
            subRoot.getParent().setRight(R);
        }
        else{
            subRoot.getParent().setLeft(R);
        }
        R.setRight(subRoot);
        subRoot.setParent(R);
        return R;
    }
    private Node rotateLeft(Node subRoot){
Node L = subRoot.getRight();
subRoot.setRight(L.getLeft());
if(L.getLeft()!=null){
    L.getLeft().setParent(subRoot);
}
L.setParent(subRoot.getParent());
    if(subRoot.getParent()==null){
        setRoot(L);
    }
    else if(subRoot==subRoot.getParent().getLeft()){
        subRoot.getParent().setLeft(L);
    }
    else{
        subRoot.getParent().setRight(L);
    }
    L.setLeft(subRoot);
    subRoot.setParent(L);
        return L;
    }
    /*
       Node L = subRoot.getRight();
        Node X = subRoot.getRight().getLeft();
        if(subRoot==getRoot()){
            setRoot(L);
        }
        if(subRoot.getParent()!=null&&subRoot.getParent().getRight()==subRoot) {
            subRoot.getParent().setRight(L);
        }
        else if(subRoot.getParent()!=null&&subRoot.getParent().getLeft()==subRoot) {
            subRoot.getParent().setLeft(L);
        }
        L.setLeft(subRoot);
        L.setParent(subRoot.getParent());
        subRoot.setParent(L);
        subRoot.setRight(X);
     */
    private boolean doesOverlap(Interval test, Interval in ){
        return ((test.getHigh() >= in.getLow()) && test.getHigh() <= in.getHigh()) || ((test.getLow() >= in.getLow()) && test.getLow() <= in.getHigh()||(test.getLow()<=in.getHigh()&&test.getHigh()>=in.getHigh()));
    }
}

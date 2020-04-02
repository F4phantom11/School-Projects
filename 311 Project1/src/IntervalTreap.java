public class IntervalTreap {
    private Node root;
    private int size, height, max = 0;

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
int countH=0;
        z.setImax(z.getInterval().getHigh());
        Node y = null;
        Node x = root;
        while(x!=null) {
            y = x;
            if (z.getInterval().getLow() < x.getInterval().getLow()) {
                x.setImax(Math.max(x.getImax(),z.getInterval().getHigh()));
                if(x.getLeft()==null){
                   // heightRec(x);
                }

                x = x.getLeft();
            } else {
                x.setImax(Math.max(x.getImax(),z.getInterval().getHigh()));

                if(x.getRight()==null){
                   // heightRec(x);
                }
                x = x.getRight();

            }
        }

            z.setParent(y);
            if(y==null) {
                setRoot(z);
                z.setHeight(0);
            }
            else if(z.getInterval().getLow()<y.getInterval().getLow()){

                y.setLeft(z);
            }
            else {
                y.setRight(z);
            }
        heightRec(z);
           if(y!=null) {
                while (z.getParent()!=null && z.getParent().getPriority() > z.getPriority()) {
                    if (z.getParent().getLeft() == z) {

                     z=   rotateRight(z.getParent());

                     if(z.getRight()==null&&z.getLeft()==null){
                         z.setImax(z.getInterval().getHigh());
                         heightRec(z);
                     }
                     else if(z.getRight()==null&&z.getLeft()!=null){
                       z.setImax(Math.max(z.getInterval().getHigh(),z.getLeft().getImax()));
                         heightRec(z.getLeft());
                     }
                     else if(z.getRight()!=null&&z.getLeft()==null){
                         z.setImax(Math.max(z.getInterval().getHigh(),z.getRight().getImax()));
                         heightRec(z.getRight());
                     }
                     else{
                         int max= Math.max(z.getInterval().getHigh(),z.getRight().getImax());
                         z.setImax(Math.max(max,z.getLeft().getImax()));
                         heightRec(z.getRight());
                     }

//----------------------------------------------------------------------------------------------------------------------------
                        if(z.getRight()!=null){
                            Node rightZ = z.getRight();
                       if(rightZ.getRight()==null&&rightZ.getLeft()==null){
                            rightZ.setImax(rightZ.getInterval().getHigh());
                        }
                        else if(rightZ.getRight()==null&&rightZ.getLeft()!=null){
                            rightZ.setImax(Math.max(rightZ.getInterval().getHigh(),rightZ.getLeft().getImax()));
                        }
                        else if(rightZ.getRight()!=null&&rightZ.getLeft()==null){
                            rightZ.setImax(Math.max(rightZ.getInterval().getHigh(),rightZ.getRight().getImax()));
                        }
                        else{
                            int max= Math.max(rightZ.getInterval().getHigh(),rightZ.getRight().getImax());
                            rightZ.setImax(Math.max(max,rightZ.getLeft().getImax()));
                        }
                        }
                    } else {
                       z= rotateLeft(z.getParent());

                        if(z.getRight()==null&&z.getLeft()==null){
                            z.setImax(z.getInterval().getHigh());
                            heightRec(z);
                        }
                        else if(z.getRight()==null&&z.getLeft()!=null){
                            z.setImax(Math.max(z.getInterval().getHigh(),z.getLeft().getImax()));
                            heightRec(z.getLeft());
                        }
                        else if(z.getRight()!=null&&z.getLeft()==null){
                            z.setImax(Math.max(z.getInterval().getHigh(),z.getRight().getImax()));
                            heightRec(z.getRight());
                        }
                        else {
                            int max = Math.max(z.getInterval().getHigh(), z.getRight().getImax());
                            z.setImax(Math.max(max, z.getLeft().getImax()));
                            heightRec(z.getLeft());
                        }



                        if(z.getLeft()!=null){
                            Node rightZ = z.getLeft();
                            if(rightZ.getRight()==null&&rightZ.getLeft()==null){
                                rightZ.setImax(rightZ.getInterval().getHigh());
                            }
                            else if(rightZ.getRight()==null&&rightZ.getLeft()!=null){
                                rightZ.setImax(Math.max(rightZ.getInterval().getHigh(),rightZ.getLeft().getImax()));
                            }
                            else if(rightZ.getRight()!=null&&rightZ.getLeft()==null){
                                rightZ.setImax(Math.max(rightZ.getInterval().getHigh(),rightZ.getRight().getImax()));
                            }
                            else{
                                int max= Math.max(rightZ.getInterval().getHigh(),rightZ.getRight().getImax());
                                rightZ.setImax(Math.max(max,rightZ.getLeft().getImax()));
                            }
                        }
                    }
                }
            }

       // settingImax(getRoot());

    }

    void intervalDelete(Node z){
     //todo
    }
    Node intervalSearch(Interval i){
        Node x = root;
        int count=0;
      //  System.out.println(doesOverlap(i,x.getInterval()));
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
    private void heightRec(Node parent){
        if(parent==null){
            return;
        }
        if(parent == getRoot() && parent.getRight()==null&&parent.getLeft()==null){
            parent.setHeight(1);
        }
        else if(parent.getRight()==null&&parent.getLeft()==null){
parent.setHeight(0);
        }
        else if(parent.getLeft()==null){
            parent.setHeight(parent.getRight().getHeight()+1);
        }
        else if(parent.getRight()==null){
            parent.setHeight(parent.getLeft().getHeight()+1);
        }
        else{
            parent.setHeight(Math.max(parent.getLeft().getHeight(),parent.getRight().getHeight())+1);
        }

        heightRec(parent.getParent());
    }
}

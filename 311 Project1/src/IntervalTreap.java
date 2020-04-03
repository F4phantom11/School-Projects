import static java.lang.Integer.MIN_VALUE;

public class IntervalTreap {
    private Node root;
    private int size, height;

    IntervalTreap() {

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

    void intervalInsert(Node z) {
        //todo
        int countH = 0;
        z.setImax(z.getInterval().getHigh());
        Node y = null;
        Node x = root;
        while (x != null) {
            y = x;
            if (z.getInterval().getLow() < x.getInterval().getLow()) {
                x.setImax(Math.max(x.getImax(), z.getInterval().getHigh()));
                if (x.getLeft() == null) {
                    // heightRec(x);
                }

                x = x.getLeft();
            } else {
                x.setImax(Math.max(x.getImax(), z.getInterval().getHigh()));

                if (x.getRight() == null) {
                    // heightRec(x);
                }
                x = x.getRight();

            }
        }

        z.setParent(y);
        if (y == null) {
            setRoot(z);
            z.setHeight(0);
        } else if (z.getInterval().getLow() < y.getInterval().getLow()) {

            y.setLeft(z);
        } else {
            y.setRight(z);
        }
        heightRec(z);
        if (y != null) {
            while (z.getParent() != null && z.getParent().getPriority() > z.getPriority()) {
                if (z.getParent().getLeft() == z) {

                    z = rotateRight(z.getParent());

                    if (z.getRight() == null && z.getLeft() == null) {
                        z.setImax(z.getInterval().getHigh());
                        heightRec(z);
                    } else if (z.getRight() == null && z.getLeft() != null) {
                        z.setImax(Math.max(z.getInterval().getHigh(), z.getLeft().getImax()));
                        heightRec(z.getLeft());
                    } else if (z.getRight() != null && z.getLeft() == null) {
                        z.setImax(Math.max(z.getInterval().getHigh(), z.getRight().getImax()));
                        heightRec(z.getRight());
                    } else {
                        int max = Math.max(z.getInterval().getHigh(), z.getRight().getImax());
                        z.setImax(Math.max(max, z.getLeft().getImax()));
                        heightRec(z.getRight());
                    }

//----------------------------------------------------------------------------------------------------------------------------
                    if (z.getRight() != null) {
                        Node rightZ = z.getRight();
                        if (rightZ.getRight() == null && rightZ.getLeft() == null) {
                            rightZ.setImax(rightZ.getInterval().getHigh());
                        } else if (rightZ.getRight() == null && rightZ.getLeft() != null) {
                            rightZ.setImax(Math.max(rightZ.getInterval().getHigh(), rightZ.getLeft().getImax()));
                        } else if (rightZ.getRight() != null && rightZ.getLeft() == null) {
                            rightZ.setImax(Math.max(rightZ.getInterval().getHigh(), rightZ.getRight().getImax()));
                        } else {
                            int max = Math.max(rightZ.getInterval().getHigh(), rightZ.getRight().getImax());
                            rightZ.setImax(Math.max(max, rightZ.getLeft().getImax()));
                        }
                    }
                } else {
                    z = rotateLeft(z.getParent());

                    if (z.getRight() == null && z.getLeft() == null) {
                        z.setImax(z.getInterval().getHigh());
                        heightRec(z);
                    } else if (z.getRight() == null && z.getLeft() != null) {
                        z.setImax(Math.max(z.getInterval().getHigh(), z.getLeft().getImax()));
                        heightRec(z.getLeft());
                    } else if (z.getRight() != null && z.getLeft() == null) {
                        z.setImax(Math.max(z.getInterval().getHigh(), z.getRight().getImax()));
                        heightRec(z.getRight());
                    } else {
                        int max = Math.max(z.getInterval().getHigh(), z.getRight().getImax());
                        z.setImax(Math.max(max, z.getLeft().getImax()));
                        heightRec(z.getLeft());
                    }


                    if (z.getLeft() != null) {
                        Node rightZ = z.getLeft();
                        if (rightZ.getRight() == null && rightZ.getLeft() == null) {
                            rightZ.setImax(rightZ.getInterval().getHigh());
                        } else if (rightZ.getRight() == null && rightZ.getLeft() != null) {
                            rightZ.setImax(Math.max(rightZ.getInterval().getHigh(), rightZ.getLeft().getImax()));
                        } else if (rightZ.getRight() != null && rightZ.getLeft() == null) {
                            rightZ.setImax(Math.max(rightZ.getInterval().getHigh(), rightZ.getRight().getImax()));
                        } else {
                            int max = Math.max(rightZ.getInterval().getHigh(), rightZ.getRight().getImax());
                            rightZ.setImax(Math.max(max, rightZ.getLeft().getImax()));
                        }
                    }
                }
            }
        }

        // settingImax(getRoot());

    }

    void intervalDelete(Node z) { // TODO : Update height for nodes getting moved
        boolean isLeftChild = z.isLeftChild();
        Node parent = z.getParent();

        // First phase of deletion
        if (z.getLeft() == null) {
            // CASE 1 : Left child is empty
            if(z.getRight() != null) {
                z.getRight().setParent(z.getParent());
            } else {
                z.getParent().setRight(null);
            }
            z = z.getRight();
        } else if (z.getLeft() != null && z.getRight() == null) {
            // CASE 2 : Right child is empty
            z.getLeft().setParent(z.getParent());
            z = z.getLeft();
        } else {
            // CASE 3 : Two children exist
            Node y = Successor(z);
            if (z.getLeft() != null && z.getRight() == y) { // Case A
                parent = z.getParent();
                z = y;
                z.setParent(parent);
            } else if (z.getLeft() != null && z.getRight() != y) { // Case B
                Node w = y;
                // Replace y by its right child
                y.getRight().setParent(y.getParent());
                y = y.getRight();

                // Make y the root of the right subtree
                z.getRight().setParent(w);
                w.setRight(z.getRight());
                w.setParent(z.getParent());

                // Replace z by w
                z = w;
            }

        }

        // Update parent node to reflect change of child
        if(z != null && z.getParent() != null) {
            if (isLeftChild) {
                z.getParent().setLeft(z);
            } else {
                z.getParent().setRight(z);
            }
        }

        // Second phase of deletion, fixing priority and Imax fields
        if(z != null && z.getParent() != null) {
            while (z.getParent().getPriority() > z.getPriority()) {
                if (z.getParent().getLeft() == z) { // If z is a left child of its parent
                    rotateRight(z.getParent());
                    updateImax(z.getRight());
                } else { // If not a left child, then must be a right child
                    rotateLeft(z.getParent());
                    updateImax(z.getLeft());
                }
                updateImax(z);
            }
        } else if(parent != null){
            updateImax(parent);
        } else {
            updateImax(z);
        }

    }

    Node intervalSearch(Interval i) {
        Node x = root;
        int count = 0;
        //  System.out.println(doesOverlap(i,x.getInterval()));
        while (x != null && !doesOverlap(i, x.getInterval())) {
            if (x.getLeft() != null && x.getLeft().getImax() >= i.getLow()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }

        }

        return x;
    }

    public void updateImax(Node subRoot){
        int max = subRoot.getInterval().getHigh();
        if(subRoot.getLeft() != null){
            if(subRoot.getLeft().getImax() > max){
                max = subRoot.getLeft().getImax();
            }
        }
        if(subRoot.getRight() != null){
            if(subRoot.getRight().getImax() > max){
                max = subRoot.getRight().getImax();
            }
        }

        subRoot.setImax(max);
    }

    public void updateHeight(Node subRoot){ // TODO : Make correct or use heightRec() function for height
        if(subRoot.getLeft() != null){
            subRoot.setHeight(subRoot.getLeft().getHeight() + 1);
        } else if (subRoot.getRight() != null){
            subRoot.setHeight(subRoot.getRight().getHeight() + 1);
        } else {
            subRoot.setHeight(0);
        }
    }

    public void settingImax(Node z) {
        int max = 0;
        if (z == null) {
            return;
        }

        settingImax(z.getLeft());
        settingImax(z.getRight());

        if (z.getInterval().getHigh() > max) {
            max = z.getInterval().getHigh();
        }

        z.setImax(max);
    }

    public void Height() {

    }

    private Node rotateRight(Node subRoot) {
        Node R = subRoot.getLeft();
        subRoot.setLeft(R.getRight());
        if (R.getRight() != null) {
            R.getRight().setParent(subRoot);
        }
        R.setParent(subRoot.getParent());
        if (subRoot.getParent() == null) {
            setRoot(R);
        } else if (subRoot == subRoot.getParent().getRight()) {
            subRoot.getParent().setRight(R);
        } else {
            subRoot.getParent().setLeft(R);
        }
        R.setRight(subRoot);
        subRoot.setParent(R);
        return R;
    }

    private Node rotateLeft(Node subRoot) {
        Node L = subRoot.getRight();
        subRoot.setRight(L.getLeft());
        if (L.getLeft() != null) {
            L.getLeft().setParent(subRoot);
        }
        L.setParent(subRoot.getParent());
        if (subRoot.getParent() == null) {
            setRoot(L);
        } else if (subRoot == subRoot.getParent().getLeft()) {
            subRoot.getParent().setLeft(L);
        } else {
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
    private boolean doesOverlap(Interval test, Interval in) {
        return ((test.getHigh() >= in.getLow()) && test.getHigh() <= in.getHigh()) || ((test.getLow() >= in.getLow()) && test.getLow() <= in.getHigh() || (test.getLow() <= in.getHigh() && test.getHigh() >= in.getHigh()));
    }

    private void heightRec(Node parent) {
        if (parent == null) {
            return;
        }
        if (parent == getRoot() && parent.getRight() == null && parent.getLeft() == null) {
            parent.setHeight(1);
        } else if (parent.getRight() == null && parent.getLeft() == null) {
            parent.setHeight(0);
        } else if (parent.getLeft() == null) {
            parent.setHeight(parent.getRight().getHeight() + 1);
        } else if (parent.getRight() == null) {
            parent.setHeight(parent.getLeft().getHeight() + 1);
        } else {
            parent.setHeight(Math.max(parent.getLeft().getHeight(), parent.getRight().getHeight()) + 1);
        }

        heightRec(parent.getParent());
    }

    private Node Minimum(Node z) {
        Node node = z;

        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    private Node Maximum(Node z) {
        Node node = z;

        while (node.getRight() != null) {
            node = node.getRight();
        }

        return node;
    }

    private Node Successor(Node z) {
        Node x = z;

        if (x.getRight() != null) {
            return Minimum(x.getRight());
        }

        Node y = x.getParent();
        while (y != null && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }

        return y;
    }

}

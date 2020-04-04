import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class Testing {
    Node root;
    Node node1R;
    Node node2R;
    Node node3R;
    Node node4R;
    Node node1L;
    Node node2L;
    Node node3L;
    Node node4L;
    Node node5L;
    IntervalTreap treap;
    ArrayList<Interval> TP;

    @Before
    public void setUp() {
        root = new Node(new Interval(16, 21));
        node1R = new Node(new Interval(25, 30));
        node2R = new Node(new Interval(26, 26));
        node3R = new Node(new Interval(17, 19));
        node4R = new Node(new Interval(19, 20));
        node1L = new Node(new Interval(8, 9));
        node2L = new Node(new Interval(5, 8));
        node3L = new Node(new Interval(0, 3));
        node4L = new Node(new Interval(6, 10));
        node5L = new Node(new Interval(15, 23));
        root.setPriority(8);
        node1R.setPriority(10);
        node2R.setPriority(12);
        node3R.setPriority(13);
        node4R.setPriority(17);
        node1L.setPriority(12);
        node2L.setPriority(17);
        node3L.setPriority(21);
        node4L.setPriority(20);
        node5L.setPriority(16);
        TP = new ArrayList<Interval>();
      /*  root.setLeft(node1L);
        root.setRight(node1R);
        node1R.setRight(node2R);
        node1R.setLeft(node3R);
        node3R.setRight(node4R);
        node1L.setLeft(node2L);
        node1L.setRight(node5L);
        node2L.setLeft(node3L);
        node2L.setRight(node4L);*/
        treap = new IntervalTreap();
        treap.intervalInsert(root);
        treap.intervalInsert(node1R);
        treap.intervalInsert(node1L);
        treap.intervalInsert(node2L);
        treap.intervalInsert(node2R);
        treap.intervalInsert(node3L);
        treap.intervalInsert(node3R);
        treap.intervalInsert(node4L);
        treap.intervalInsert(node4R);
        treap.intervalInsert(node5L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void makenewInterval() {
        Interval i = new Interval(1, 2);
        Assert.assertEquals(i.getLow(), 1);
        Assert.assertEquals(i.getHigh(), 2);
    }

    @Test
    public void makeNewNode() {
        Interval i = new Interval(1, 2);
        Assert.assertEquals(i.getLow(), 1);
        Assert.assertEquals(i.getHigh(), 2);
        Node node = new Node(i);
        Assert.assertEquals(node.getInterval().getLow(), new Interval(1, 2).getLow());


    }

    @Test
    public void addNodePrelim() {
        Interval i = new Interval(1, 2);
        Node node = new Node(i);
        Node node1 = new Node(new Interval(5, 6));
        node.setLeft(node1);
        Assert.assertEquals(node.getLeft(), node1);
    }

    @Test
    public void setImax() {
        Interval i = new Interval(1, 2);
        Node node = new Node(i);
        Node node1 = new Node(new Interval(5, 6));
        node.setRight(node1);
        IntervalTreap treap = new IntervalTreap();
        treap.settingImax(node);
        Assert.assertEquals(6, node.getImax());
    }

    @Test
    public void fullTreap() {


        //treap.settingImax(root);

        Assert.assertEquals(30, root.getImax());
//        treap.settingImax(node1L);
        Assert.assertEquals(23, node1L.getImax());
        Assert.assertEquals(10, node2L.getImax());
        Assert.assertEquals(3, node3L.getImax());
        Assert.assertEquals(10, node4L.getImax());
        Assert.assertEquals(23, node5L.getImax());
        // treap.settingImax(node2R);
        Assert.assertEquals(30, node1R.getImax());
        Assert.assertEquals(26, node2R.getImax());
        //treap.settingImax(node3R);
        Assert.assertEquals(20, node3R.getImax());
        Assert.assertEquals(20, node4R.getImax());
        //treap.settingImax(node2L);


    }

    @Test
    public void searching() {
        //treap.settingImax(root);
        //  treap.setRoot(root);

        Node re = treap.intervalSearch(new Interval(22, 25));
        Assert.assertEquals(15, re.getInterval().getLow());
        Assert.assertEquals(23, re.getInterval().getHigh());
        re = treap.intervalSearch(new Interval(24, 27));
        Assert.assertEquals(25, re.getInterval().getLow());
        Assert.assertEquals(30, re.getInterval().getHigh());
        re = treap.intervalSearch(new Interval(18, 18));
        Assert.assertEquals(16, re.getInterval().getLow());
        Assert.assertEquals(21, re.getInterval().getHigh());
        re = treap.intervalSearch(new Interval(50, 52));
        Assert.assertNull(re);
    }

    @Test
    public void insertRoot() {
        IntervalTreap intervalTreap = new IntervalTreap();
        Node node = new Node(new Interval(5, 9));
        intervalTreap.intervalInsert(node);
        Assert.assertEquals(node, intervalTreap.getRoot());
    }

    @Test
    public void insertChildRotatingHeight() {

        IntervalTreap intervalTreap = new IntervalTreap();
        Node node = new Node(new Interval(16, 21));
        Node node2 = new Node(new Interval(25, 30));
        Node node3 = new Node(new Interval(5, 8));
        Node node4 = new Node(new Interval(15, 23));
        //  Node node4 = new Node(new Interval(26,26));
        // Node node5 = new Node(new Interval(19,20));


        Node node6 = new Node(new Interval(8, 9));
        //   Node node7 = new Node(new Interval(15,23));
        node.setPriority(8);
        node2.setPriority(10);
        node3.setPriority(9);
        node4.setPriority(16);
        //  node5.setPriority(17);

        node6.setPriority(12);
        // node7.setPriority(16);
        intervalTreap.intervalInsert(node);
        intervalTreap.intervalInsert(node2);
        intervalTreap.intervalInsert(node6);
        intervalTreap.intervalInsert(node4);
        intervalTreap.intervalInsert(node3);
    }

    @Test
    public void insertChildPriority() {
        IntervalTreap intervalTreap = new IntervalTreap();
        Node node = new Node(new Interval(16, 21));
        Node node2 = new Node(new Interval(25, 30));
        Node node3 = new Node(new Interval(17, 19));
        Node node4 = new Node(new Interval(26, 26));
        Node node5 = new Node(new Interval(19, 20));
        Node node6 = new Node(new Interval(8, 9));
        Node node7 = new Node(new Interval(15, 23));
        node.setPriority(8);
        node2.setPriority(10);
        node3.setPriority(13);
        node4.setPriority(11);
        node5.setPriority(17);
        node6.setPriority(12);
        node7.setPriority(16);
        intervalTreap.intervalInsert(node);

        intervalTreap.intervalInsert(node2);
        Assert.assertEquals(0, node2.getHeight());
        Assert.assertEquals(1, node.getHeight());
        intervalTreap.intervalInsert(node3);
        Assert.assertEquals(2, node.getHeight());
        Assert.assertEquals(1, node2.getHeight());

        Assert.assertEquals(0, node3.getHeight());

        intervalTreap.intervalInsert(node4);
        Assert.assertEquals(2, node.getHeight());
        Assert.assertEquals(1, node2.getHeight());

        Assert.assertEquals(0, node3.getHeight());
        Assert.assertEquals(0, node4.getHeight());
        intervalTreap.intervalInsert(node5);
        Assert.assertEquals(3, node.getHeight());
        Assert.assertEquals(2, node2.getHeight());
        Assert.assertEquals(1, node3.getHeight());
        Assert.assertEquals(0, node4.getHeight());
        Assert.assertEquals(0, node5.getHeight());
        intervalTreap.intervalInsert(node6);
        Assert.assertEquals(3, node.getHeight());
        Assert.assertEquals(2, node2.getHeight());
        Assert.assertEquals(1, node3.getHeight());
        Assert.assertEquals(0, node4.getHeight());
        Assert.assertEquals(0, node5.getHeight());
        Assert.assertEquals(0, node6.getHeight());
        intervalTreap.intervalInsert(node7);
        Assert.assertEquals(3, node.getHeight());
        Assert.assertEquals(2, node2.getHeight());
        Assert.assertEquals(1, node3.getHeight());
        Assert.assertEquals(0, node4.getHeight());
        Assert.assertEquals(0, node5.getHeight());
        Assert.assertEquals(1, node6.getHeight());
        Assert.assertEquals(0, node7.getHeight());
        ArrayList<Node> inOrder = new ArrayList<Node>();
        inOrder(intervalTreap.getRoot(), inOrder);
        for (Node value : inOrder) {
            System.out.println(value.getPriority());
        }
        //  Assert.assertEquals(node4, node3.getLeft());
        //Assert.assertEquals(node5, node2.getRight());
    }

    public void inOrder(Node node, ArrayList<Node> array) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), array);
        array.add(node);

        //As you visit each node, check for the heap property.
        if (node.getParent() != null && node.getPriority() < node.getParent().getPriority()) {
            fail("failed treap's min-heap property!");
        }

        inOrder(node.getRight(), array);
    }

    @Test
    public void heighttest() {
        Assert.assertEquals(3, root.getHeight());
        Assert.assertEquals(2, node1L.getHeight());
        Assert.assertEquals(1, node2L.getHeight());
        Assert.assertEquals(0, node3L.getHeight());
        Assert.assertEquals(0, node4L.getHeight());
        Assert.assertEquals(0, node5L.getHeight());
        Assert.assertEquals(2, node1R.getHeight());
        Assert.assertEquals(0, node2R.getHeight());
        Assert.assertEquals(1, node3R.getHeight());
        Assert.assertEquals(0, node4R.getHeight());
        Node node = new Node(new Interval(7, 25));
        node.setPriority(9);
        treap.intervalInsert(node);
        Assert.assertEquals(3, root.getHeight());
        Assert.assertEquals(1, node1L.getHeight());
        Assert.assertEquals(1, node2L.getHeight());
        Assert.assertEquals(0, node3L.getHeight());
        Assert.assertEquals(0, node4L.getHeight());
        Assert.assertEquals(0, node5L.getHeight());
        Assert.assertEquals(2, node1R.getHeight());
        Assert.assertEquals(0, node2R.getHeight());
        Assert.assertEquals(1, node3R.getHeight());
        Assert.assertEquals(0, node4R.getHeight());
        Assert.assertEquals(2, node.getHeight());
        node = new Node(new Interval(27, 40));
        node.setPriority(11);
        treap.intervalInsert(node);
        Assert.assertEquals(3, root.getHeight());
        Assert.assertEquals(1, node1L.getHeight());
        Assert.assertEquals(1, node2L.getHeight());
        Assert.assertEquals(0, node3L.getHeight());
        Assert.assertEquals(0, node4L.getHeight());
        Assert.assertEquals(0, node5L.getHeight());
        Assert.assertEquals(2, node1R.getHeight());
        Assert.assertEquals(0, node2R.getHeight());
        Assert.assertEquals(1, node3R.getHeight());
        Assert.assertEquals(0, node4R.getHeight());
        Assert.assertEquals(1, node.getHeight());
        Assert.assertEquals(40, root.getImax());
        Assert.assertEquals(40, root.getRight().getImax());
        Assert.assertEquals(20, root.getRight().getLeft().getImax());
    }

    @Test
    public void IntervalDeleteTest(){
        Node tmpNode;

        treap.intervalDelete(node3R);
        tmpNode = root.getRight().getLeft();
        Assert.assertEquals(19, tmpNode.getInterval().getLow());
        Assert.assertEquals(20, tmpNode.getInterval().getHigh());
        Assert.assertEquals(3, root.getHeight());
        Assert.assertEquals(1, root.getRight().getHeight());
        Assert.assertEquals(0, tmpNode.getHeight());

        treap.intervalDelete(node5L);
        tmpNode = root.getLeft();
        Assert.assertEquals(10, tmpNode.getImax());
        Assert.assertEquals(node1L, tmpNode);
        Assert.assertEquals(2, tmpNode.getHeight());

        treap.intervalDelete(node1L);
        tmpNode = root.getLeft();
        Assert.assertEquals(5, tmpNode.getInterval().getLow());
        Assert.assertEquals(1, tmpNode.getHeight());
        Assert.assertEquals(0, tmpNode.getLeft().getHeight());
        Assert.assertEquals(0, tmpNode.getRight().getHeight());
    }

}

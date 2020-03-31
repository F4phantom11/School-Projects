import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class Testing {
    Node root;
    Node node1R ;
    Node node2R;
    Node node3R ;
    Node node4R ;
    Node node1L;
    Node node2L ;
    Node node3L ;
    Node node4L ;
    Node node5L;
    IntervalTreap treap;
    ArrayList<Interval> TP;
    @Before
    public void setUp() {
         root = new Node(new Interval(16,21));
         node1R = new Node(new Interval(25,30));
         node2R = new Node(new Interval(26,26));
         node3R = new Node(new Interval(17,19));
         node4R = new Node(new Interval(19,20));
         node1L = new Node(new Interval(8,9));
         node2L = new Node(new Interval(5,8));
         node3L = new Node(new Interval(0,3));
         node4L = new Node(new Interval(6,10));
         node5L = new Node(new Interval(15,23));
        TP = new ArrayList<Interval>();
        root.setLeft(node1L);
        root.setRight(node1R);
        node1R.setRight(node2R);
        node1R.setLeft(node3R);
        node3R.setRight(node4R);
        node1L.setLeft(node2L);
        node1L.setRight(node5L);
        node2L.setLeft(node3L);
        node2L.setRight(node4L);
        treap = new IntervalTreap();
    }

    @After
    public void tearDown() {}
@Test
public void makenewInterval(){
        Interval i = new Interval(1,2);
        Assert.assertEquals(i.getLow(),1);
        Assert.assertEquals(i.getHigh(),2);
    }
    @Test
    public void makeNewNode() {
        Interval i = new Interval(1,2);
        Assert.assertEquals(i.getLow(),1);
        Assert.assertEquals(i.getHigh(),2);
        Node node = new Node(i);
        Assert.assertEquals(node.getInterval().getLow(),new Interval(1,2).getLow());


    }
    @Test
    public void addNodePrelim(){
        Interval i = new Interval(1,2);
        Node node = new Node(i);
        Node node1 = new Node(new Interval(5,6));
        node.setLeft(node1);
        Assert.assertEquals(node.getLeft(),node1);
    }
    @Test
    public void setImax(){
        Interval i = new Interval(1,2);
        Node node = new Node(i);
        Node node1 = new Node(new Interval(5,6));
        node.setRight(node1);
        IntervalTreap treap = new IntervalTreap();
        treap.settingImax(node);
        Assert.assertEquals(6,node.getImax());
    }
    @Test
    public void fullTreap(){



       treap.settingImax(root);

       Assert.assertEquals(30,root.getImax());
//        treap.settingImax(node1L);
        Assert.assertEquals(23,node1L.getImax());
        Assert.assertEquals(10,node2L.getImax());
        Assert.assertEquals(3,node3L.getImax());
        Assert.assertEquals(10,node4L.getImax());
        Assert.assertEquals(23,node5L.getImax());
       // treap.settingImax(node2R);
        Assert.assertEquals(30,node1R.getImax());
        Assert.assertEquals(26,node2R.getImax());
        //treap.settingImax(node3R);
        Assert.assertEquals(20,node3R.getImax());
        Assert.assertEquals(20,node4R.getImax());
        //treap.settingImax(node2L);



    }
    @Test
    public void searching(){
        treap.settingImax(root);
        treap.setRoot(root);

        Node re = treap.intervalSearch(new Interval(22,25));
        Assert.assertEquals(15,re.getInterval().getLow());
        Assert.assertEquals(23,re.getInterval().getHigh());
         re = treap.intervalSearch(new Interval(24,27));
        Assert.assertEquals(25,re.getInterval().getLow());
        Assert.assertEquals(30,re.getInterval().getHigh());
         re = treap.intervalSearch(new Interval(18,18));
        Assert.assertEquals(16,re.getInterval().getLow());
        Assert.assertEquals(21,re.getInterval().getHigh());
        re = treap.intervalSearch(new Interval(50,52));
        Assert.assertNull(re);
    }
    @Test
    public void insertRoot(){
        IntervalTreap intervalTreap = new IntervalTreap();
        Node node = new Node(new Interval(5,9));
        intervalTreap.intervalInsert(node);
        Assert.assertEquals(node, intervalTreap.getRoot());
    }
  /*  @Test
    public void insertChild(){
        IntervalTreap intervalTreap = new IntervalTreap();
        Node node = new Node(new Interval(5,9));
        Node node2 = new Node(new Interval(1,9));
        Node node3 = new Node(new Interval(10,20));
        Node node4 = new Node(new Interval(6,9));
        Node node5 = new Node(new Interval(4,5));

        intervalTreap.intervalInsert(node);
        intervalTreap.intervalInsert(node2);
        intervalTreap.intervalInsert(node3);
        intervalTreap.intervalInsert(node4);
        intervalTreap.intervalInsert(node5);
        Assert.assertEquals(node, intervalTreap.getRoot());
        Assert.assertEquals(node2, node.getLeft());
        Assert.assertEquals(node3, node.getRight());
        Assert.assertEquals(node4, node3.getLeft());
        Assert.assertEquals(node5, node2.getRight());
    }
*/
    @Test
    public void insertChildPriorit(){
        IntervalTreap intervalTreap = new IntervalTreap();
        Node node = new Node(new Interval(5,9));
        Node node2 = new Node(new Interval(6,9));
        Node node3 = new Node(new Interval(10,20));
        Node node4 = new Node(new Interval(6,9));
        Node node5 = new Node(new Interval(4,5));
node.setPriority(20);
node2.setPriority(10);
node3.setPriority(30);
node4.setPriority(2);
        intervalTreap.intervalInsert(node);
      intervalTreap.intervalInsert(node2);
       intervalTreap.intervalInsert(node3);
        intervalTreap.intervalInsert(node4);
        //intervalTreap.intervalInsert(node5);
     //   Assert.assertEquals(node2, intervalTreap.getRoot());
      // Assert.assertEquals(node, node2.getRight());
      //Assert.assertEquals(node3, node.getRight());
        ArrayList<Node> inOrder = new ArrayList<Node>();
        inOrder(intervalTreap.getRoot(), inOrder);
        for (Node value : inOrder) {
            System.out.println(value.getPriority());
        }
      //  Assert.assertEquals(node4, node3.getLeft());
        //Assert.assertEquals(node5, node2.getRight());
    }
    public void inOrder(Node node, ArrayList<Node> array){
        if(node == null){
            return;
        }
        inOrder(node.getLeft(), array);
        array.add(node);

        //As you visit each node, check for the heap property.
        if (node.getParent()!=null && node.getPriority() < node.getParent().getPriority()) {
            fail("failed treap's min-heap property!");
        }

        inOrder(node.getRight(), array);
    }


}

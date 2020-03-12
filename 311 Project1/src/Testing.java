import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Testing {

    @Before
    public void setUp() {}

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
        Node root = new Node(new Interval(16,21));
        Node node1R = new Node(new Interval(25,30));
        Node node2R = new Node(new Interval(26,26));
        Node node3R = new Node(new Interval(17,19));
        Node node4R = new Node(new Interval(19,20));
        Node node1L = new Node(new Interval(8,9));
        Node node2L = new Node(new Interval(5,8));
        Node node3L = new Node(new Interval(0,3));
        Node node4L = new Node(new Interval(6,10));
        Node node5L = new Node(new Interval(15,23));
        root.setLeft(node1L);
        root.setRight(node1R);
        node1R.setRight(node2R);
        node1R.setLeft(node3R);
        node3R.setRight(node4R);
        node1L.setLeft(node2L);
        node1L.setRight(node5L);
        node2L.setLeft(node3L);
        node2L.setRight(node4L);

        IntervalTreap treap = new IntervalTreap();
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

}

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
        Assert.assertEquals(node.getPrioity(),5);

    }
    @Test
    public void addNodePrelim(){
        Interval i = new Interval(1,2);
        Node node = new Node(i);
        Node node1 = new Node(new Interval(5,6));
        node.setLeft(node1);
        Assert.assertEquals(node.getLeft(),node1);
    }

}

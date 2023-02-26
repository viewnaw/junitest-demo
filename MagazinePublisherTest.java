import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MagazinePublisherTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private MagazinePublisher publisher;
    private MagazineSubscriber s1;
    private MagazineSubscriber s2;
    
    @Before
    public void setUp() {
        publisher = new MagazinePublisher();
        s1 = new MagazineSubscriber("John", new ArrayList<>());
        s1.addInterest("Fashion");
        s1.addInterest("Sports");
        s2 = new MagazineSubscriber("Jane", new ArrayList<>());
        s2.addInterest("Fashion");
        s2.addInterest("Technology");
        publisher.addSubscriber(s1, "Fashion");
        publisher.addSubscriber(s1, "Sports");
        publisher.addSubscriber(s2, "Fashion");
        publisher.addSubscriber(s2, "Technology");
        System.setOut(new PrintStream(output));
    }
    
    @Test
    public void testPublish() {
        publisher.publish("Fashion");
        assertEquals("Publisher has published a new issue of Fashion\nJohn received the new issue of Fashion\nJane received the new issue of Fashion\n", output.toString());
        output.reset();
        publisher.publish("Sports");
        assertEquals("Publisher has published a new issue of Sports\nJohn received the new issue of Sports\n", output.toString());
        output.reset();
        publisher.publish("Technology");
        assertEquals("Publisher has published a new issue of Technology\nJane received the new issue of Technology\n", output.toString());
    }
    
    @Test
    public void testRemoveSubscriber() {
        publisher.removeSubscriber(s1, "Sports");
        publisher.publish("Sports");
        assertEquals("", output.toString());
    }
}

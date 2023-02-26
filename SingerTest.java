import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SingerTest {
    private List<String> songTitles;
    private Singer singer;
    private List<Follower> followers;
    
    @Before
    public void setUp() {
        songTitles = new ArrayList<>();
        songTitles.add("Song 1");
        songTitles.add("Song 2");
        singer = new Singer("John Doe", songTitles);
        followers = new ArrayList<>();
        Follower follower1 = new Follower("Follower 1");
        followers.add(follower1);
        singer.addObserver(follower1);
    }
    
    @Test
    public void testUpdate() {
        List<String> listAssertResult = new ArrayList<>();
        listAssertResult.add("Follower 1 received notification: John Doe has started singing Song 1");
        singer.notifyObservers("Song 1");
        List<String> notifications = new ArrayList<>();
        for (Follower follower : followers) {
            notifications.addAll(follower.getNotifications());
            follower.clearNotifications();
        }
        assertEquals(listAssertResult, notifications);
    }
    
    @Test
    public void testAddObserver() {
        Follower follower3 = new Follower("Follower 3");
        followers.add(follower3);
        singer.addObserver(follower3);
        List<String> listAssertResult = new ArrayList<>();
        listAssertResult.add("Follower 1 received notification: John Doe has started singing Song 1");
        singer.notifyObservers("Song 1");
        List<String> notifications = new ArrayList<>();
        for (Follower follower : followers) {
            notifications.addAll(follower.getNotifications());
            follower.clearNotifications();
        }
        assertEquals(listAssertResult, notifications);
    }
    
    @Test
    public void testRemoveObserver() {
        singer.removeObserver(followers.get(0));
        List<String> listAssertResult = new ArrayList<>();
        listAssertResult.add("Follower 2 received notification: John Doe has started singing Song 1");
        singer.notifyObservers("Song 1");
        List<String> notifications = new ArrayList<>();
        for (Follower follower : followers) {
            notifications.addAll(follower.getNotifications());
            follower.clearNotifications();
        }
        assertEquals(listAssertResult, notifications);
    }
}

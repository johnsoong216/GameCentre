package fall2018.csc2017.Games;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionTest {
    Session session;

    private void setUp() {
        session = Session.getCurrentUser();
    }


    @Test
    public void testUsername() {
        setUp();
        session.setUsername("username");
        assertEquals("username", session.getUsername());
    }

    @Test
    public void testScore() {
        setUp();
        session.setScore(100);
        assertEquals(100, session.getScore());
    }
}

package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {
    private Stack<Integer> stack = new Stack<>();

    private void setUp() {
        for(int i = 0; i < 10; i ++) {
            stack.push(i);
        }
    }

    private void setUp15() {
        for(int i = 0; i < 15; i ++) {
            stack.push(i);
        }
    }

    @Test
    public void testPush() {
        stack.push(1);
        assertEquals(1, stack.size());
    }

    @Test
    public void testPop() {
        stack.push(1);
        assertEquals(1, (int)stack.pop());
    }

    @Test
    public void testPopLastTwo() {
        setUp();
        Stack<Integer> temp = new Stack<>();
        for(int i = 2; i < 10; i ++) {
            temp.push(i);
        }
        Stack<Integer> temp1 = stack.poplasttwo(7);
        assertEquals(temp.size(), temp1.size());
        for(int i = 0; i < 8; i ++) {
            assertEquals(temp.pop(), temp1.pop());
        }
    }

    @Test
    public void testPopLastFive() {
        setUp15();
        Stack<Integer> temp = new Stack<>();
        for(int i = 5; i < 15; i ++) {
            temp.push(i);
        }
        Stack<Integer> temp1 = stack.poplastfive(7);
        assertEquals(temp.size(), temp1.size());
        for(int i = 0; i < 10; i ++) {
            assertEquals(temp.pop(), temp1.pop());
        }
    }

    @Test
    public void testToString() {
        setUp();
        assertEquals("9 8 7 6 5 4 3 2 1 0 ", stack.toString());
    }
}

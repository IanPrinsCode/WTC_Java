package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testGetAxis() {
        Position pos = new Position(70, -50);
        assertEquals(70, pos.getX());
        assertEquals(-50, pos.getY());
    }

    @Test
    public void comparePosition() {
        assertEquals(new Position(90, 45), new Position(90, 45));
        assertNotEquals(new Position(90, 67), new Position(-45, 67));
        assertNotEquals(new Position(90, 67), new Position(90, -45));
        assertNotEquals(new Position(90, 67), new Position(-45, -45));
    }

    @Test
    public void insideBlock() {
        Position topLeft = new Position(-40, 40);
        Position bottomRight = new Position(40,-40);
        assertTrue((new Position(0,20)).isIn(topLeft, bottomRight), "should be inside");
        assertFalse((new Position(0,60)).isIn(topLeft, bottomRight), "should be beyond top boundary");
        assertFalse((new Position(0,-60)).isIn(topLeft, bottomRight), "should be beyond bottom boundary");
        assertFalse((new Position(60,20)).isIn(topLeft, bottomRight), "should be beyond right boundary");
        assertFalse((new Position(-60,-20)).isIn(topLeft, bottomRight), "should be beyond left boundary");
    }
}

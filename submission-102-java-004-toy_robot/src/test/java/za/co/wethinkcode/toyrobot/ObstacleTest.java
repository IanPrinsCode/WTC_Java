package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {
    Obstacle obstacle = new SquareObstacle(50,-100);

    @Test
    void testObstacleDimensions() {
        assertEquals(50, this.obstacle.getBottomLeftX());
        assertEquals(-100, this.obstacle.getBottomLeftY());
        assertEquals(5, this.obstacle.getSize());
    }

    @Test
    void testBlockPosition(){
        assertTrue(this.obstacle.blocksPosition(new Position(50,-100)));
        assertTrue(this.obstacle.blocksPosition(new Position(54,-100)));
        assertTrue(this.obstacle.blocksPosition(new Position(50,-96)));
        assertFalse(this.obstacle.blocksPosition(new Position(49,-95)));
        assertFalse(this.obstacle.blocksPosition(new Position(55,-94)));
    }

    @Test
    void testBlockPath(){
        assertTrue(this.obstacle.blocksPath(new Position(50,0), new Position(50,-150)));
        assertTrue(this.obstacle.blocksPath(new Position(52,-150), new Position(52, 105)));
        assertTrue(this.obstacle.blocksPath(new Position(0,-98), new Position(70, -98)));
        assertFalse(this.obstacle.blocksPath(new Position(49,-180), new Position(49, 0)));
        assertFalse(this.obstacle.blocksPath(new Position(40,-95), new Position(60, -95)));
    }
}
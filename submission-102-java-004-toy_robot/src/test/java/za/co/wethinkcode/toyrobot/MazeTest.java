package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.maze.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeTest {

    @Test
    void emptyMaze() {
        Maze maze = new EmptyMaze();
        assertEquals(0, maze.getObstacles().size());
    }

    @Test
    void simpleMaze() {
        Maze maze = new SimpleMaze();
        assertEquals(1, maze.getObstacles().size());
        assertEquals(1, maze.getObstacles().get(0).getBottomLeftX());
        assertEquals(1, maze.getObstacles().get(0).getBottomLeftY());
    }

    @Test
    void randomMaze() {
        Maze maze = new RandomMaze();
        assertTrue(maze.getObstacles().size() <= 20);
        assertTrue(maze.getObstacles().size() >= 0);
    }

    @Test
    void designedMaze() {
        Maze maze = new DesignedMaze();
        assertTrue(maze.getObstacles().size() > 0);
    }
}

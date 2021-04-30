package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class SimpleMaze extends AbstractMaze{
    /**
     * Constructor
     */
    public SimpleMaze() {
        this.addObstacle(new SquareObstacle( 1,1));
    }
}

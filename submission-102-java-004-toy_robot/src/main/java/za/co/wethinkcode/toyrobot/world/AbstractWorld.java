package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.List;

public abstract class AbstractWorld implements IWorld{
    private final Position TOP_LEFT = new Position(-100,200);
    private final Position BOTTOM_RIGHT = new Position(100,-200);
    public static final Position CENTRE = new Position(0,0);

    public boolean blocked;

    public Position position;
    public Direction currentDirection;
    public List<Obstacle> obstacles;
    private final Maze maze;

    /**
     * Constructor
     * @param maze maze object to use in world
     */
    public AbstractWorld(Maze maze) {
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.maze = maze;
        this.obstacles = maze.getObstacles();
    }


    /**
     * @return Current position on grid
     */
    public Position getPosition() {
        return this.position;
    }


    /**
     * @return Current facing direction on grid
     */
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }


    /**
     * Check if the new position you are moving to is a valid move.
     * @param position the position to check
     * @return boolean to say if it is valid or not
     */
    public boolean isNewPositionAllowed(Position position) {
        this.blocked = false;

        if (maze.blocksPath(this.position, position)) {
            this.blocked = true;
            return false;
        }
        else return position.isIn(TOP_LEFT, BOTTOM_RIGHT);
    }


    /**
     * @return boolean to say if you are at an edge of the grid or not
     */
    public boolean isAtEdge() {
        return position.getX() == 100 || position.getX() == -100 ||
                position.getY() == 200 || position.getY() == -200;
    }


    /**
     * Reset position, direction and the map for a new game
     */
    public void reset() {
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.obstacles.clear();
    }


    /**
     * @return List of obstacles that the current map consists of
     */
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }
}

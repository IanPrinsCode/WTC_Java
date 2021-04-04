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

    public AbstractWorld(Maze maze) {
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.maze = maze;
        this.obstacles = maze.getObstacles();
    }


    public Position getPosition() {
        return this.position;
    }


    public Direction getCurrentDirection() {
        return this.currentDirection;
    }


    public boolean isNewPositionAllowed(Position position) {
        this.blocked = false;

        if (maze.blocksPath(this.position, position)) {
            this.blocked = true;
            return false;
        }
        else return position.isIn(TOP_LEFT, BOTTOM_RIGHT);
    }


    public boolean isAtEdge() {
        return position.getX() == 100 || position.getX() == -100 ||
                position.getY() == 200 || position.getY() == -200;
    }


    public void reset() {
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
        this.obstacles.clear();
    }


    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }
}

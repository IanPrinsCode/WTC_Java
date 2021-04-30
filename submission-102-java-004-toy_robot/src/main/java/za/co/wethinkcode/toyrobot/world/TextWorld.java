package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

public class TextWorld extends AbstractWorld{
    /**
     * Constructor
     * @param maze maze object to use in world
     */
    public TextWorld(Maze maze) {
        super(maze);
        showObstacles();
    }


    /**
     * Update position when a movement command is triggered.
     * @param nrSteps steps to move in current direction
     * @return Status of movement as enum
     */
    public UpdateResponse updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (Direction.LEFT.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }
        else if (Direction.DOWN.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (!isNewPositionAllowed(newPosition)) {
            if (this.blocked) {
                return UpdateResponse.FAILED_OBSTRUCTED;
            }
            else {
                return UpdateResponse.FAILED_OUTSIDE_WORLD;
            }
        }
        this.position = newPosition;
        return UpdateResponse.SUCCESS;
    }


    /**
     * Turns turtle to face a new direction.
     * @param turnRight if true, then turn 90 degrees to the right, else turn left.
     */
    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            switch (getCurrentDirection()) {
                case UP:
                    this.currentDirection = Direction.RIGHT;
                    break;
                case RIGHT:
                    this.currentDirection = Direction.DOWN;
                    break;
                case DOWN:
                    this.currentDirection = Direction.LEFT;
                    break;
                case LEFT:
                    this.currentDirection = Direction.UP;
            }
        } else {
            switch (getCurrentDirection()) {
                case UP:
                    this.currentDirection = Direction.LEFT;
                    break;
                case LEFT:
                    this.currentDirection = Direction.DOWN;
                    break;
                case DOWN:
                    this.currentDirection = Direction.RIGHT;
                    break;
                case RIGHT:
                    this.currentDirection = Direction.UP;
            }
        }
    }


    /**
     * Prints out a list of all obstacles and their coordinates in the terminal.
     */
    public void showObstacles() {
        if (!obstacles.isEmpty()) {
            Play.display("There are some obstacles:");
            for (Obstacle obstacle : obstacles) {
                Play.display("- At position " + obstacle.getBottomLeftX() + ", " + obstacle.getBottomLeftY() +
                        " (to " + (obstacle.getBottomLeftX()+5) + ", " + (obstacle.getBottomLeftY()+5) + ")");
            }
        }
    }
}


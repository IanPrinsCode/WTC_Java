package za.co.wethinkcode.toyrobot.world;

import org.turtle.Turtle;
import org.turtle.StdDraw;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.awt.*;

public class TurtleWorld extends AbstractWorld{
    public Turtle turtle = new Turtle(0, 0 , 90);

    public TurtleWorld(Maze maze) {
        super(maze);
        this.obstacles = maze.getObstacles();

        StdDraw.setScale(-300, 300);
        drawConstraint();
        showObstacles();

        turtle.setPosition(0, 0);
        turtle.setAngle(90);
        turtle.setSize(0.008);
        turtle.show();
    }


    public void drawConstraint() {
        turtle.setColor(Color.cyan);
        turtle.setPosition(-100, -200);
        turtle.setAngle(90);
        turtle.forward(400);
        turtle.setAngle(0);
        turtle.forward(200);
        turtle.setAngle(-90);
        turtle.forward(400);
        turtle.setAngle(180);
        turtle.forward(200);
    }


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
        turtle.forward(nrSteps);
        return UpdateResponse.SUCCESS;
    }


    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            switch (getCurrentDirection()) {
                case UP:
                    this.currentDirection = Direction.RIGHT;
                    turtle.setAngle(0);
                    break;
                case RIGHT:
                    this.currentDirection = Direction.DOWN;
                    turtle.setAngle(-90);
                    break;
                case DOWN:
                    this.currentDirection = Direction.LEFT;
                    turtle.setAngle(180);
                    break;
                case LEFT:
                    this.currentDirection = Direction.UP;
                    turtle.setAngle(90);
            }
        } else {
            switch (getCurrentDirection()) {
                case UP:
                    this.currentDirection = Direction.LEFT;
                    turtle.setAngle(180);
                    break;
                case LEFT:
                    this.currentDirection = Direction.DOWN;
                    turtle.setAngle(-90);
                    break;
                case DOWN:
                    this.currentDirection = Direction.RIGHT;
                    turtle.setAngle(0);
                    break;
                case RIGHT:
                    this.currentDirection = Direction.UP;
                    turtle.setAngle(90);
            }
        }
    }


    public void drawObstacle(Obstacle obstacle) {
        int xAxis = obstacle.getBottomLeftX();
        int yAxis = obstacle.getBottomLeftY();

        turtle.setColor(Color.red);
        turtle.setPosition(xAxis, yAxis);
        turtle.setAngle(90);
        turtle.forward(5);
        turtle.setAngle(0);
        turtle.forward(5);
        turtle.setAngle(-90);
        turtle.forward(5);
        turtle.setAngle(180);
        turtle.forward(5);
    }


    public void showObstacles() {
        for (Obstacle obstacle : this.obstacles) {
            drawObstacle(obstacle);
        }
    }
}

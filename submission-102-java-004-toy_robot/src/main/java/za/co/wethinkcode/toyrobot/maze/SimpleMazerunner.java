package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class SimpleMazerunner implements MazeRunner{
    private IWorld.Direction currentDirection;
    private Position position;
    private Robot target;
    private List<Obstacle> obstacles;
    private int steps;
    private String edge;

    /**
     * Does mazerun to specified edge.
     * @param target the instance of Robot to use to run the maze
     * @param edgeDirection the edge to try and reach, one of Direction.UP, RIGHT, DOWN, or LEFT
     * @return boolean to continue program
     */
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {
        this.steps = 0;
        this.target = target;
        this.position = target.world.getPosition();
        this.currentDirection = target.world.getCurrentDirection();
        this.obstacles = this.target.world.obstacles;

        switch (edgeDirection) {
            case LEFT:
                this.edge = "left";
                break;
            case RIGHT:
                this.edge = "right";
                break;
            case UP:
                this.edge = "top";
                break;
            case DOWN:
                this.edge = "bottom";
        }

        while (true) {
            List<Integer> currentPosition = constructKey(this.position.getX(), this.position.getY());

            if (edgeDirection == IWorld.Direction.UP && currentPosition.get(1).equals(200))
                break;
            else if (edgeDirection == IWorld.Direction.DOWN && currentPosition.get(1).equals(-200))
                break;
            else if (edgeDirection == IWorld.Direction.LEFT && currentPosition.get(0).equals(-100))
                break;
            else if (edgeDirection == IWorld.Direction.RIGHT && currentPosition.get(0).equals(100))
                break;

            List<Integer> blockInFront = getFrontCoordinate();
            doMove(blockInFront);
        }

        target.setStatus("I am at the " + this.edge + " edge. (Cost: " + getMazeRunCost() + " steps)");

        return true;
    }


    /**
     * Gets coordinate in front of turle based off of currentDirection and currentPosition.
     * @return coordinate in front
     */
    public List<Integer> getFrontCoordinate() {
        switch (this.currentDirection) {
            case UP:
                return constructKey(this.position.getX(), this.position.getY()+1);
            case RIGHT:
                return constructKey(this.position.getX()+1, this.position.getY());
            case DOWN:
                return constructKey(this.position.getX(), this.position.getY()-1);
            case LEFT:
                return constructKey(this.position.getX()-1, this.position.getY());
        }
        return constructKey(this.position.getX(), this.position.getY());
    }


    /**
     * Does a movement depending on if the coordinate in front is blocked or not.
     * @param blockInFront coordinate in front of turtle
     */
    public void doMove(List<Integer> blockInFront) {
        if (!isBlocked(blockInFront.get(0), blockInFront.get(1))) {
            doForward();
        } else {
            doLeft();
        }
    }


    /**
     * Takes two values and makes a list of integers to use as a coordinate.
     * @param x x value of coordinate as an integer
     * @param y y value of coordinate as an integer
     * @return coordinate list
     */
    public List<Integer> constructKey(int x, int y) {
        Position position = new Position(x, y);
        List<Integer> elements = new ArrayList<>();
        elements.add(position.getX());
        elements.add(position.getY());
        return elements;
    }


    /**
     * Checks if a coordinate is blocked on the map.
     * @param x x value of coordinate as an integer
     * @param y y value of coordinate as an integer
     * @return boolean to say if it is blocked or not
     */
    public boolean isBlocked(int x, int y) {
        for (Obstacle obs : this.obstacles) {
            int xPos = obs.getBottomLeftX();
            int yPos = obs.getBottomLeftY();
            for (int i = xPos; i <= xPos + 4; i++) {
                for (int j = yPos; j <= yPos + 4; j++) {
                    if (x == i && y == j) {
                        return true;
                    }
                }
            }
        }
        if (x > 100 || x < -100) {
            return true;
        } else return y > 200 || y < -200;
    }


    /**
     * Triggers a forward movement and updates position.
     */
    public void doForward() {
        Command command = Command.create("forward 1");
        this.target.handleCommand(command);
        System.out.println(this.target.toString());
        this.position = target.world.getPosition();
        this.steps++;
    }


    /**
     * Triggers a left turn and updates currentDirection.
     */
    public void doLeft() {
        Command command = Command.create("left");
        this.target.handleCommand(command);
        System.out.println(this.target.toString());
        this.currentDirection = target.world.getCurrentDirection();
        this.steps++;
    }


    /**
     * @return The amount of moves made to escape the maze.
     */
    public int getMazeRunCost() {
        return this.steps;
    }
}

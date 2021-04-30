package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle {
    private int bottomLeftX;
    private int bottomLeftY;

    /**
     * Constructor
     * @param x x value of bottomLeft coordinate
     * @param y y value of bottomLeft coordinate
     */
    public SquareObstacle(int x, int y) {
        this.bottomLeftX = x;
        this.bottomLeftY = y;
    }


    /**
     * @return x value of bottomLeft coordinate
     */
    public int getBottomLeftX() {
        return this.bottomLeftX;
    }


    /**
     * @return y value of bottomLeft coordinate
     */
    public int getBottomLeftY() {
        return this.bottomLeftY;
    }


    /**
     * @return size of obstacle
     */
    public int getSize() {
        return 5;
    }


    /**
     * Checks if position is blocked.
     * @param position the position to check
     * @return boolean saying if it is blocked or not
     */
    public boolean blocksPosition(Position position) {
        for (int i = bottomLeftX; i <= bottomLeftX + 4; i++) {
            for (int j = bottomLeftY; j <= bottomLeftY + 4; j++) {
                if (position.getX() == i && position.getY() == j) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Check if a path you are following for a movement, is blocked or not.
     * @param a first position
     * @param b second position
     * @return
     */
    public boolean blocksPath(Position a, Position b) {
        int minY;
        int maxY;
        int minX;
        int maxX;

        if (a.getX() > b.getX()) {
            minX = b.getX();
            maxX = a.getX();
        } else {
            maxX = b.getX();
            minX = a.getX();
        }
        if (a.getY() > b.getY()) {
            minY = b.getY();
            maxY = a.getY();
        } else {
            maxY = b.getY();
            minY = a.getY();
        }

        if (a.getX() == b.getX()) {
            for (int i = minY; i <= maxY; i++) {
                if (blocksPosition(new Position(a.getX(), i))) {
                    return true;
                }
            }
        } else if (a.getY() == b.getY()) {
            for (int i = minX; i <= maxX; i++) {
                if (blocksPosition(new Position(i, a.getY()))) {
                    return true;
                }
            }
        }
        return false;
    }
}

package za.co.wethinkcode.toyrobot;

public class Position {
    private final int x;
    private final int y;

    /**
     * Constructor
     * @param x x value of coordinate
     * @param y y value of coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * @return x value of coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * @return y value of coordinate
     */
    public int getY() { return y; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }


    /**
     * Checks if the position is in a specified area
     * @param topLeft topLeft corner of area
     * @param bottomRight bottomRight corner of area
     * @return boolean to say if it is in specified area or not
     */
    public boolean isIn(Position topLeft, Position bottomRight) {
        boolean withinTop = this.y <= topLeft.getY();
        boolean withinBottom = this.y >= bottomRight.getY();
        boolean withinLeft = this.x >= topLeft.getX();
        boolean withinRight = this.x <= bottomRight.getX();
        return withinTop && withinBottom && withinLeft && withinRight;
    }
}

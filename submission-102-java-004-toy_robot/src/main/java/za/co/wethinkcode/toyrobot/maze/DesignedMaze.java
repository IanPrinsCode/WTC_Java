package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class DesignedMaze extends AbstractMaze{
    public DesignedMaze () {
        drawHorisontal(new Position(-80, 160), new Position(80, 160));
        drawHorisontal(new Position(-80, -160), new Position(80, -160));
        drawHorisontal(new Position(-40, 80), new Position(40, 80));
        drawHorisontal(new Position(-40, -80), new Position(40, -80));

        drawVertical(new Position(-60, -120), new Position(-60, 120));
        drawVertical(new Position(60, -120), new Position(60, 120));
        drawVertical(new Position(-20, -40), new Position(-20, 40));
        drawVertical(new Position(20, -40), new Position(20, 40));
    }


    public void drawHorisontal(Position a, Position b) {
        int from;
        int to;

        if (a.getX() < b.getX()) {
            from = a.getX();
            to = b.getX();
        }
        else {
            from = b.getX();
            to = a.getX();
        }

        for (int i = from; i <= to; i++) {
            this.addObstacle(new SquareObstacle(i, a.getY()));
        }
    }


    public void drawVertical(Position a, Position b) {
        int from;
        int to;

        if (a.getY() < b.getY()) {
            from = a.getY();
            to = b.getY();
        }
        else {
            from = b.getY();
            to = a.getY();
        }

        for (int i = from; i <= to; i++) {
            this.addObstacle(new SquareObstacle(a.getX(), i));
        }
    }
}

package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMaze implements Maze{
    private List<Obstacle> obstacles = new ArrayList<>();

    @Override
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }


    @Override
    public boolean blocksPath(Position a, Position b) {
        for (Obstacle obs : this.getObstacles()) {
            if (obs.blocksPath(a, b)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Adds an obstacles to obstacle list.
     * @param obstacle obstacle to add
     */
    public void addObstacle(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }
}

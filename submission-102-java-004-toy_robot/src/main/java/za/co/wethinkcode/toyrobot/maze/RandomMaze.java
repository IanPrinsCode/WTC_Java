package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.Random;

public class RandomMaze extends AbstractMaze{
    public RandomMaze() {
        Random random = new Random();
        boolean counterX;
        boolean counterY;
        int multiplierX = 1;
        int multiplierY = 1;

        for (int i = 0; i < random.nextInt(20); i++) {
            counterX = random.nextBoolean();
            counterY = random.nextBoolean();

            if (counterX) {
                multiplierX = -1;
            }
            if (counterY) {
                multiplierY = -1;
            }
            this.addObstacle(new SquareObstacle(random.nextInt(100) * multiplierX, random.nextInt(200) * multiplierY));
        }
    }
}

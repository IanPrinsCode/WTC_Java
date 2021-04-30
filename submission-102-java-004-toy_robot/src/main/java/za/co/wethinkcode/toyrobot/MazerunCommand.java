package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.SimpleMazerunner;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class MazerunCommand extends Command{
    /**
     * Constructor
     * @param argument which side to solve the maze towards
     */
    public MazerunCommand(String argument) { super("mazerun", argument); }


    /**
     * Constructor
     */
    public MazerunCommand() { super("mazerun"); }


    @Override
    public boolean execute(Robot target) {
        String argument = getArgument();
        SimpleMazerunner runner = new SimpleMazerunner();

        try {
            switch (argument) {
                case "left":
                    runner.mazeRun(target, IWorld.Direction.LEFT);
                    break;
                case "right":
                    runner.mazeRun(target, IWorld.Direction.RIGHT);
                    break;
                case "bottom":
                    runner.mazeRun(target, IWorld.Direction.DOWN);
                    break;
                default:
                    runner.mazeRun(target, IWorld.Direction.UP);
                    break;
            }
        } catch (Exception e) {
            runner.mazeRun(target, IWorld.Direction.UP);
        }
        return true;
    }
}

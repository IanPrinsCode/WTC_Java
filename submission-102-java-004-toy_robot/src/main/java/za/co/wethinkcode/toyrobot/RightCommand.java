package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        target.world.updateDirection(true);
        target.setStatus("Turned right.");
        return true;
    }


    /**
     * Constructor
     */
    public RightCommand() {super("right");}
}


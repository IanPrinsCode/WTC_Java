package za.co.wethinkcode.toyrobot;

public class LeftCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        target.world.updateDirection(false);
        target.setStatus("Turned left.");
        return true;
    }


    /**
     * Constructor
     */
    public LeftCommand() {super("left");}
}

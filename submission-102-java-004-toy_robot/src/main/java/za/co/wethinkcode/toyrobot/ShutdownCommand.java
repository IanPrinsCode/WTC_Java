package za.co.wethinkcode.toyrobot;

public class ShutdownCommand extends Command {
    /**
     * Constructor
     */
    public ShutdownCommand() {
        super("off");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("Shutting down...");
        History.clearHistory();
        return false;
    }
}

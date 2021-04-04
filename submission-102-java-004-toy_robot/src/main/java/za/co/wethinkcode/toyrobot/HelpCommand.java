package za.co.wethinkcode.toyrobot;

public class HelpCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g. 'BACK 10'\n" +
                "LEFT - turn left\n" +
                "RIGHT - turn right\n" +
                "SPRINT - does a sprint\n" +
                "REPLAY - replay all movement commands\n" +
                "MAZERUN - solves maze to reach a border");
        return true;
    }


    public HelpCommand() {
        super("help");
    }
}

package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class ForwardCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());

        if (target.world.updatePosition(nrSteps).equals(IWorld.UpdateResponse.SUCCESS)){
            target.setStatus("Moved forward by "+nrSteps+" steps.");
        }
        else if (target.world.updatePosition(nrSteps).equals(IWorld.UpdateResponse.FAILED_OBSTRUCTED)) {
            target.setStatus("Sorry, there is an obstacle in the way.");
        }
        else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }


    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}


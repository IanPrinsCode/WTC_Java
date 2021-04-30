package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class SprintCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        int testSteps = 0;

        for (int i = nrSteps; i > 0; i--) {
            testSteps += i;
        }

        if (target.world.updatePosition(testSteps).equals(IWorld.UpdateResponse.SUCCESS)){
            target.world.updatePosition(-testSteps);
            for (int i = nrSteps; i > 0; i--) {
                target.world.updatePosition(i);
                target.setStatus("Moved forward by "+i+" steps.");
                if (i > 1) { Play.display(target.toString()); }
            }
        }
        else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }


    /**
     * Constructor
     * @param argument how many times to sprint
     */
    public SprintCommand(String argument) {
        super("sprint", argument);
    }
}

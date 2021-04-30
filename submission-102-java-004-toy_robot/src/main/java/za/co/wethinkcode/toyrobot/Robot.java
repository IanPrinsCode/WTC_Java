package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

public class Robot {
    public final AbstractWorld world = getWorld();
    private String status;
    private String name;
    public History history;

    /**
     * Constructor
     * @param name name of robot
     */
    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.history = new History();
    }


    /**
     * @return world object associated with current robot
     */
    public AbstractWorld getWorld() {
        if(Play.worldType.equalsIgnoreCase("turtle")){
            return new TurtleWorld(Play.currentMaze);
        }
        else
        {
            return new TextWorld(Play.currentMaze);
        }
    }


    /**
     * @return message to print out
     */
    public String getStatus() {
        return this.status;
    }


    /**
     * Executes specified command
     * @param command specified command
     * @return boolean
     */
    public boolean handleCommand(Command command) {
        return command.execute(this);
    }


    @Override
    public String toString() {
       return "[" + this.world.getPosition().getX() + "," + this.world.getPosition().getY() + "] "
               + this.name + "> " + this.status;
    }


    /**
     * Sets new status.
     * @param status new status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return name of robot
     */
    public String getName() {
        return name;
    }
}
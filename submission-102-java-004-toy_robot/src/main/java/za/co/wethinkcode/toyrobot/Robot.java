package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

public class Robot {
    public final AbstractWorld world = getWorld();
    private String status;
    private String name;
    public History history;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.history = new History();
    }


    public AbstractWorld getWorld() {
        if(Play.worldType.equalsIgnoreCase("turtle")){
            return new TurtleWorld(Play.currentMaze);
        }
        else
        {
            return new TextWorld(Play.currentMaze);
        }
    }


    public String getStatus() {
        return this.status;
    }


    public boolean handleCommand(Command command) {
        return command.execute(this);
    }


    @Override
    public String toString() {
       return "[" + this.world.getPosition().getX() + "," + this.world.getPosition().getY() + "] "
               + this.name + "> " + this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }
}
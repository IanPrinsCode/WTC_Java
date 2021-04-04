package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void helpCommand() {
        Command help = Command.create("help");
        assertEquals("help", help.getName());
    }

    @Test
    void shutdownCommand() {
        Command shutdown = Command.create("shutdown");                                                  //<2>
        assertEquals("off", shutdown.getName());
    }

    @Test
    void forwardCommand() {
        Command forward = Command.create("forward 10");                                                 //<1>
        assertEquals("forward", forward.getName());
        assertEquals("10", forward.getArgument());
    }

    @Test
    void backCommand() {
        Command back = Command.create("back 20");                                                  //<2>
        assertEquals("back", back.getName());
        assertEquals("20", back.getArgument());
    }

    @Test
    void leftCommand() {
        Command left = Command.create("left");                                                  //<2>
        assertEquals("left", left.getName());
    }

    @Test
    void rightCommand() {
        Command right = Command.create("right");                                                  //<2>
        assertEquals("right", right.getName());
    }

    @Test
    void replayCommand() {
        Command replay = Command.create("replay");                                                  //<2>
        assertEquals("replay", replay.getName());
    }

    @Test
    void mazerunCommand() {
        Command mazerun = Command.create("mazerun left");                                                  //<2>
        assertEquals("mazerun", mazerun.getName());
        assertEquals("left", mazerun.getArgument());
    }

    @Test
    void createInvalidCommand() {
        try {
            Command forward = Command.create("say hello");                                              //<4>
            fail("Should have thrown an exception");                                                    //<5>
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());                             //<6>
        }
    }
}

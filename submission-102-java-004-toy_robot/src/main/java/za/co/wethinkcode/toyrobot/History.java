package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History {
    private static List<String> history = new ArrayList<>();
    private static final List<String> movementCommands = new ArrayList<>(Arrays.asList(
            "forward",
            "back",
            "right",
            "left",
            "sprint"));


    /**
     * Clears history list.
     */
    public static void clearHistory() {
        history.clear();
    }


    /**
     * Adds a command to the history list
     * @param command command to add
     */
    public void append(String command) {
        String[] commandArgs = command.split(" ");

        if (movementCommands.contains(commandArgs[0])){
            history.add(command);
        }
    }


    /**
     * Removes a command from history list.
     * @param command command to remove
     */
    public void remove(String command) {
        history.remove(command);
    }


    /**
     * @return history list
     */
    public static List<String> getHistory() {
        return history;
    }
}

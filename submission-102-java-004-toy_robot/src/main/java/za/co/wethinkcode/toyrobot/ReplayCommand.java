package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplayCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        String argument = getArgument();

        switch (testReplayArg(argument, target)) {
            case "reversedRange":
                reversedRangeReplay(argument, target);
                break;
            case "reversed":
                reversedReplay(target);
                break;
            case "range":
                rangeReplay(argument, target);
                break;
            case "number":
                numberReplay(argument, target);
                break;
            case "normal":
                normalReplay(target);
        }
        return true;
    }


    private static void reversedRangeReplay(String argument, Robot target) {
        String[] args = argument.split(" ");
        String range = args[1];
        String[] params = range.split("-");
        List<String> historyList = History.getHistory();
        List<String> newList = new ArrayList<>();
        int startIndex;

        if (!range.contains("-")) {
            if (Integer.parseInt(params[0]) > historyList.size()) {
                startIndex = 0;
            }
            else {
                startIndex = historyList.size() - Integer.parseInt(range);
            }

            for (int i = startIndex; i < historyList.size(); i++) {
                newList.add(historyList.get(i));
            }
            Collections.reverse(newList);

            for (String instruction : newList) {
                Command command = Command.create(instruction);
                target.handleCommand(command);
                System.out.println(target.toString());
            }
        }
        else {

            int endIndex = historyList.size() - Integer.parseInt(params[1]);

            if (Integer.parseInt(params[0]) > historyList.size()) {
                startIndex = 0;
            }
            else {
                startIndex = historyList.size() - Integer.parseInt(params[0]);
            }

            for (int i = startIndex; i < endIndex; i++) {
                newList.add(historyList.get(i));
            }
            Collections.reverse(newList);

            for (String instruction : newList) {
                Command command = Command.create(instruction);
                target.handleCommand(command);
                System.out.println(target.toString());
            }
        }
        target.setStatus("replayed " + newList.size() + " commands.");
    }


    private static String testReplayArg(String argument, Robot target) {
        if (argument.contains(" ")) {
            return "reversedRange";
        }

        else if (argument.equals("")) {
            return "normal";
        }

        else if (argument.equals("reversed")) {
            return "reversed";
        }

        else if (argument.contains("-")) {
            String[] params = argument.split("-");
            ArrayList<Integer> testList = new ArrayList<Integer>();
            for (String param : params) {
                try {
                    int num = Integer.parseInt(param);
                    testList.add(num);
                } catch (Exception e) {
                    target.setStatus("Sorry, I did not understand 'replay " + argument + "'.");
                    break;
                }
            }
            if (testList.size() > 1) {
                if (testList.get(0) > testList.get(1)) {
                    return "range";
                }
            }
        }

        else
            try {
                Integer.parseInt(argument);
                return "number";
            }
            catch (Exception e) {
                target.setStatus("Sorry, I did not understand 'replay " + argument + "'.");
            }

        return "";
    }


    private static void reversedReplay(Robot target) {
        List<String> historyList = History.getHistory();
        Collections.reverse(historyList);

        for (String instruction : historyList) {
            Command command = Command.create(instruction);
            target.handleCommand(command);
            System.out.println(target.toString());
        }
        target.setStatus("replayed " + historyList.size() + " commands.");
    }


    private static void rangeReplay(String argument, Robot target) {
        List<String> historyList = History.getHistory();
        List<String> newList = new ArrayList<>();
        String[] params = argument.split("-");
        int startIndex;
        int endIndex = historyList.size() - Integer.parseInt(params[1]);

        if (Integer.parseInt(params[0]) > historyList.size()) {
            startIndex = 0;
        }
        else {
            startIndex = historyList.size() - Integer.parseInt(params[0]);
        }

        for (int i = startIndex; i < endIndex; i++) {
            newList.add(historyList.get(i));
        }
        for (String instruction : newList) {
            Command command = Command.create(instruction);
            target.handleCommand(command);
            System.out.println(target.toString());
        }
        target.setStatus("replayed " + newList.size() + " commands.");
    }


    private static void numberReplay(String argument, Robot target) {
        List<String> historyList = History.getHistory();
        List<String> newList = new ArrayList<>();
        int amount = Integer.parseInt(argument);

        if (amount > historyList.size()) {
            normalReplay(target);
            target.setStatus("replayed " + historyList.size() + " commands.");
        }
        else {
            for (int i = (historyList.size()-amount); i < historyList.size(); i++) {
                newList.add(historyList.get(i));
            }
            for (String instruction : newList) {
                Command command = Command.create(instruction);
                target.handleCommand(command);
                System.out.println(target.toString());
            }
            target.setStatus("replayed " + argument + " commands.");
        }
    }


    private static void normalReplay(Robot target) {
        List<String> historyList = History.getHistory();

        for (String instruction : historyList) {
            Command command = Command.create(instruction);
            target.handleCommand(command);
            System.out.println(target.toString());
        }
        target.setStatus("replayed " + historyList.size() + " commands.");
    }


    public ReplayCommand() {super("replay");}


    public ReplayCommand(String argument) {
        super("replay", argument);
    }
}

package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.*;

import java.util.Scanner;

public class Play {
    static Scanner scanner;
    public static String worldType;
    public static String mazeType;
    public static Maze currentMaze;

    /**
     * Main game loop
     * @param args arguments to run program with
     */
    public static void main(String[] args) {
        try {
            worldType = args[0];
        }
        catch (Exception e) {
            worldType = "text";
        }

        try {
            mazeType = args[1];

            switch (mazeType){
                case "EmptyMaze":
                    currentMaze = new EmptyMaze();
                    break;
                case "SimpleMaze":
                    currentMaze = new SimpleMaze();
                    break;
                case "RandomMaze":
                    currentMaze = new RandomMaze();
                    break;
                case "DesignedMaze":
                    currentMaze = new DesignedMaze();
            }
        }
        catch (Exception e) {
            currentMaze = new RandomMaze();
        }

        scanner = new Scanner(System.in);
        Robot robot;

        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        System.out.println("Hello Kiddo!");

        System.out.println(robot.toString());

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
                robot.history.append(instruction);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
                robot.history.remove(instruction);
            }
            System.out.println(robot);
        } while (shouldContinue);
    }


    /**
     * Takes input from user
     * @param prompt Message to display in terminal before receiving input from user
     * @return received input
     */
    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }


    /**
     * Print a message to console.
     * @param output message to print
     */
    public static void display(String output) {
        System.out.println(output);
    }
}

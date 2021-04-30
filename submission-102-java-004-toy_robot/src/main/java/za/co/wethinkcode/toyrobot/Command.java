package za.co.wethinkcode.toyrobot;

public abstract class Command {
    private final String name;
    private String argument;

    /**
     * Trigger the functionality of specified command.
     * @param target Robot object to apply command to
     * @return boolean to continue program
     */
    public abstract boolean execute(Robot target);


    /**
     * Constructor
     * @param name Type of command
     */
    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }


    /**
     * Constructor
     * @param name Type of command
     * @param argument Command arguments
     */
    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }


    /**
     * @return type of command
     */
    public String getName() {                                                                           //<2>
        return name;
    }


    /**
     * @return arguments for command
     */
    public String getArgument() {
        return this.argument;
    }


    /**
     * Creates a command object based off of the instruction input from the user.
     * @param instruction Instruction input from user. Specifies which command object to create.
     * @return Command object
     */
    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "back":
                return new BackCommand(args[1]);
            case "right":
                return new RightCommand();
            case "left":
                return new LeftCommand();
            case "sprint":
                return new SprintCommand(args[1]);
            case "replay":
                if (args.length == 3) {
                    return new ReplayCommand(args[1] + " " + args[2]);
                }
                try {
                    return new ReplayCommand(args[1]);
                } catch (Exception e) {
                    return new ReplayCommand();
                }
            case "mazerun":
                try {
                    if (args[1].equals("top")) {
                        return new MazerunCommand(args[1]);
                    }
                    else if (args[1].equals("bottom")) {
                        return new MazerunCommand(args[1]);
                    }
                    else if (args[1].equals("left")) {
                        return new MazerunCommand(args[1]);
                    }
                    else if (args[1].equals("right")) {
                        return new MazerunCommand(args[1]);
                    }
                } catch (Exception e) {
                    return new MazerunCommand();
                }
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}


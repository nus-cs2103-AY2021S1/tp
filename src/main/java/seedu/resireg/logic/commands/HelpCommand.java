package seedu.resireg.logic.commands;

import static seedu.resireg.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Map;

import seedu.resireg.logic.CommandMapper;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;

/**
 * Displays help message for a command.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Gets help for a command or ResiReg in general.",
            "When used with the command name (eg. help exit), it displays the help message for that command."
                    + " When used without any arguments, it displays a summary of all the commands.");

    private static final CommandMapper commandMapper = new CommandMapper();

    private String input;

    /**
     * Creates a HelpCommand to display help for the specified command. If no command is given, a summary of all
     * the commands available is shown.
     *
     * @param input Command word of the command to display help for.
     */
    public HelpCommand(String input) {
        this.input = input.strip();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Map<String, Help> commandWordToHelp = commandMapper.getCommandWordToHelpMap();

        if (input.isBlank()) {
            // print summary of commands in alphabetical order, followed by full description of help command
            StringBuilder sb = new StringBuilder();
            sb.append("Commands available:\n");
            commandWordToHelp.keySet().stream().sorted().forEachOrdered((commandWord) -> {
                if (commandWord.equals(HelpCommand.COMMAND_WORD)) {
                    return;
                }
                sb.append(commandWordToHelp.get(commandWord).getSummary());
                sb.append("\n");
            });
            sb.append("\n");
            sb.append(HELP.getFullMessage());
            sb.append("\n\n");
            sb.append("You can also check out our user guide at: "
                    + "https://ay2021s1-cs2103-t16-3.github.io/tp/UserGuide.html");
            return new CommandResult(sb.toString());
        } else if (commandWordToHelp.containsKey(input)) { // print full help message for specific command
            return new CommandResult(commandWordToHelp.get(input).getFullMessage());
        } else {
            throw new CommandException(input + ": " + MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

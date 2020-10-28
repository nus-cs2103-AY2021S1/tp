package seedu.resireg.logic.commands;

import java.util.Map;
import java.util.stream.Collectors;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.AppMode;
import seedu.resireg.model.Model;
import seedu.resireg.model.alias.AliasUtils;
import seedu.resireg.storage.Storage;

/**
 * Displays help message for a command.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Gets help for a command or ResiReg in general.",
            "When used with the command name (eg. help exit), it displays the help message for that command."
                    + " When used without any arguments, it displays a summary of all the commands.");

    public static final String MESSAGE_UNKNOWN_COMMAND = "Cannot show help for %s: " + Messages.MESSAGE_UNKNOWN_COMMAND;

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
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {

        if (input.isBlank()) {
            return new CommandResult(getGeneralHelpMessage(model.getAppMode()));
        }

        Map<String, String> aliasMap = AliasUtils.makeAliasToCommandWordMap(model.getCommandWordAliases());
        input = aliasMap.getOrDefault(input, input);
        Map<String, CommandWordEnum> commandWordEnumMap = CommandWordEnum.getCommandWordMap(model.getAppMode());
        if (commandWordEnumMap.containsKey(input)) {
            return new CommandResult(commandWordEnumMap.get(input).getHelp().getFullMessage());
        }

        throw new CommandException(String.format(MESSAGE_UNKNOWN_COMMAND, input));
    }

    static String getGeneralHelpMessage(AppMode appMode) {
        return "Commands Available:\n"

                // summary for commands available (excluding help command), in alphabetical order
                + CommandWordEnum.getCommandWordMap(appMode).entrySet().stream()
                        .filter(entry -> !entry.getKey().equals(COMMAND_WORD))
                        .sorted(Map.Entry.comparingByKey())
                        .map(entry -> entry.getValue().getHelp().getSummary())
                        .collect(Collectors.joining("\n"))

                // full help message for help command
                + "\n\n" + HELP.getFullMessage() + "\n\n"

                // user guide
                + "You can also refer to our user guide at: https://ay2021s1-cs2103-t16-3.github.io/tp/UserGuide.html";
    }
}

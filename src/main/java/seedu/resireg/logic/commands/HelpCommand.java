package seedu.resireg.logic.commands;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
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

    // weird getter system (rather than making these final static constants)
    // is necessary due to cyclic dependency between CommandWordEnum and HelpCommand
    // TODO explain this better
    private static Map<String, Help> commandWordToHelpMap;
    private static String generalHelpMessage;

    private String inputCommandWord;

    /**
     * Creates a HelpCommand to display help for the specified command. If no command is given, a summary of all
     * the commands available is shown.
     *
     * @param inputCommandWord Command word of the command to display help for.
     */
    public HelpCommand(String inputCommandWord) {
        this.inputCommandWord = inputCommandWord.strip();
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        Map<String, String> aliasMap = AliasUtils.makeAliasToCommandWordMap(model.getCommandWordAliases());
        inputCommandWord = aliasMap.getOrDefault(inputCommandWord, inputCommandWord);

        if (inputCommandWord.isBlank()) {
            return new CommandResult(getGeneralHelpMessage());
        }

        if (getCommandWordToHelpMap().containsKey(inputCommandWord)) {
            return new CommandResult(getCommandWordToHelpMap().get(inputCommandWord).getFullMessage());
        }

        throw new CommandException(String.format(MESSAGE_UNKNOWN_COMMAND, inputCommandWord));
    }

    private static Map<String, Help> getCommandWordToHelpMap() {
        if (commandWordToHelpMap == null) {
            commandWordToHelpMap = Arrays.stream(CommandWordEnum.values())
                    .collect(Collectors.toMap(CommandWordEnum::getCommandWord, CommandWordEnum::getHelp));
        }
        return commandWordToHelpMap;
    }

    static String getGeneralHelpMessage() {
        if (generalHelpMessage == null) {
            generalHelpMessage = "Commands Available:\n"
                    // summary for commands available (excluding help command), in alphabetical order
                    + getCommandWordToHelpMap().entrySet().stream()
                    .filter(entry -> !entry.getKey().equals(COMMAND_WORD))
                    .sorted(Map.Entry.comparingByKey())
                    .map(entry -> entry.getValue().getSummary())
                    .collect(Collectors.joining("\n"))

                    // full help message for help command
                    + "\n\n" + HELP.getFullMessage() + "\n\n"

                    // user guide
                    + "You can also refer to our user guide at: "
                    + "https://ay2021s1-cs2103-t16-3.github.io/tp/UserGuide.html";
        }
        return generalHelpMessage;
    }
}

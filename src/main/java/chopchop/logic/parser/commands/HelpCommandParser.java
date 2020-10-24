// HelpCommandParser.java

package chopchop.logic.parser.commands;

import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;

import chopchop.logic.parser.CommandArguments;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.HelpCommand;

public class HelpCommandParser {

    /**
     * Parses a 'help' command.
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a HelpCommand, if the input was valid.
     */
    public static Result<? extends Command> parseHelpCommand(CommandArguments args) {
        assert args.getCommand().equals(Strings.COMMAND_HELP);

        // for now, instead of erroring on arguments, we just let it pass through.
        // we might want to display command-specific help in the future.
        return Result.of(new HelpCommand());
    }
}

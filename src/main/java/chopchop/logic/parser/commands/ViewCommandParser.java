package chopchop.logic.parser.commands;

import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

import java.util.ArrayList;
import java.util.Optional;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.MakeRecipeCommand;
import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;
import chopchop.logic.parser.ItemReference;
import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;

public class ViewCommandParser {
    private static final String commandName = Strings.COMMAND_VIEW;

    /**
     * Parses a 'view' command. Syntax:
     * {@code view recipe REF}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a ViewCommand , if the input was valid.
     */
    public static Result<? extends Command> parseViewCommand(CommandArguments args) {
        if (!args.getCommand().equals(commandName)) {
            return Result.error("invalid command '%s' (expected '%s')", args.getCommand(), commandName);
        }


    }
}

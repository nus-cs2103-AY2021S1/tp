package chopchop.logic.parser.commands;

import static chopchop.logic.parser.commands.CommonParser.getCommandTarget;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.ViewCommand;
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

        return getCommandTarget(args)
                .then(target -> {
                    if (target.snd().isEmpty()) {
                        return Result.error("'%s' command requires at least one search term\n%s",
                                commandName, ViewCommand.MESSAGE_USAGE);
                    }
                    return Result.of(new ViewCommand(ItemReference.parse(target.snd()).getValue()));
                });
    }
}

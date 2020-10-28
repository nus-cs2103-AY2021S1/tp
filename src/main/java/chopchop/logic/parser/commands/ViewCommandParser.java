package chopchop.logic.parser.commands;

import static chopchop.logic.parser.commands.CommonParser.ensureCommandName;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.ViewCommand;
import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;
import chopchop.logic.parser.ItemReference;
import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;

import java.util.ArrayList;
import java.util.Optional;

public class ViewCommandParser {

    /**
     * Parses a 'view' command. Syntax:
     * {@code view recipe REF}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a ViewCommand , if the input was valid.
     */
    public static Result<? extends Command> parseViewCommand(CommandArguments args) {
        ensureCommandName(args, Strings.COMMAND_VIEW);

        // we expect no named arguments
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, new ArrayList<>())).isPresent()) {
            return Result.error("'view' command doesn't support '%s'", foo.get());
        }

        var name = args.getRemaining();
        if (name.isEmpty()) {
            return Result.error("Recipe name cannot be empty");
        } else {
            return ItemReference.parse(name).map(ViewCommand::new);
        }
    }
}

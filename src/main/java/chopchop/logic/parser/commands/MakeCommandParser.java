package chopchop.logic.parser.commands;

import static chopchop.logic.parser.commands.CommonParser.ensureCommandName;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

import java.util.List;
import java.util.Optional;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.MakeRecipeCommand;
import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;
import chopchop.logic.parser.ItemReference;
import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;

public class MakeCommandParser {

    /**
     * Parses a 'make' command. Syntax:
     * {@code make recipe REF}
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a MakeCommand, if the input was valid.
     */
    public static Result<? extends Command> parseMakeCommand(CommandArguments args) {
        ensureCommandName(args, Strings.COMMAND_MAKE);

        // we expect no named arguments
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of())).isPresent()) {
            return Result.error("'make' command doesn't support '%s'", foo.get());
        }

        var name = args.getRemaining();
        if (name.isEmpty()) {
            return Result.error("Recipe name cannot be empty");
        } else {
            return ItemReference.parse(name).map(MakeRecipeCommand::new);
        }
    }
}

// HelpCommandParser.java

package chopchop.logic.parser.commands;

import java.util.List;
import java.util.Optional;

import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;
import chopchop.commons.util.StringView;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.HelpCommand;

import static chopchop.logic.parser.commands.CommonParser.ensureCommandName;
import static chopchop.logic.parser.commands.CommonParser.getFirstUnknownArgument;

public class HelpCommandParser {

    /**
     * Parses a 'help' command.
     *
     * @param args the parsed command arguments from the {@code CommandParser}.
     * @return     a HelpCommand, if the input was valid.
     */
    public static Result<? extends Command> parseHelpCommand(CommandArguments args) {
        ensureCommandName(args, Strings.COMMAND_HELP);

        // we expect no named arguments
        Optional<ArgName> foo;
        if ((foo = getFirstUnknownArgument(args, List.of())).isPresent()) {
            return Result.error("'help' command doesn't support '%s'", foo.get());
        }

        // this is a little different from normal commands and their targets, so just
        // parse this manually. since this is the HELP command, we should be a little
        // more forgiving of mistakes.
        var words = new StringView(args.getRemaining()).words();

        Optional<String> cmd = words.size() > 0 ? Optional.of(words.get(0)) : Optional.empty();
        //todo: I change this to handle 3 keywords
        Optional<String> tgt = words.size() > 1 ? Optional.of(String.join(" ", words.subList(1, words.size())))
            : Optional.empty();

        // for now, instead of erroring on arguments, we just let it pass through.
        // we might want to display command-specific help in the future.
        return Result.of(new HelpCommand(cmd, tgt));
    }
}

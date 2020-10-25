// CommandParser.java

package chopchop.logic.parser;

import java.util.List;
import java.util.ArrayList;

import chopchop.logic.commands.RedoCommand;
import chopchop.logic.commands.UndoCommand;
import chopchop.commons.util.Pair;
import chopchop.commons.util.Result;
import chopchop.commons.util.Strings;
import chopchop.commons.util.StringView;

import chopchop.logic.commands.Command;
import chopchop.logic.commands.QuitCommand;

import static chopchop.logic.parser.commands.AddCommandParser.parseAddCommand;
import static chopchop.logic.parser.commands.EditCommandParser.parseEditCommand;
import static chopchop.logic.parser.commands.HelpCommandParser.parseHelpCommand;
import static chopchop.logic.parser.commands.ListCommandParser.parseListCommand;
import static chopchop.logic.parser.commands.FilterCommandParser.parseFilterCommand;
import static chopchop.logic.parser.commands.FindCommandParser.parseFindCommand;
import static chopchop.logic.parser.commands.DeleteCommandParser.parseDeleteCommand;
import static chopchop.logic.parser.commands.MakeCommandParser.parseMakeCommand;
import static chopchop.logic.parser.commands.ViewCommandParser.parseViewCommand;

public class CommandParser {

    private Result<List<Pair<ArgName, String>>> parseNamedArguments(StringView input) {

        var ret = new ArrayList<Pair<ArgName, String>>();
        while (input.size() > 0) {

            if (input.find('/') != 0) {
                break;
            }

            var pair = splitUntilNextSlash(input.drop(1));
            var self = new StringView(pair.fst());
            input = pair.snd();

            {

                var argValue = new StringView("");
                var argName = self.bisect(' ', argValue);

                if (argName.isEmpty()) {
                    return Result.error("expected argument name after '/'");
                }

                System.err.printf("arg = %s, val = %s\n", argName, argValue);
                ret.add(Pair.of(new ArgName(argName.trim().toString()), argValue.trim().toString()));
            }

            if (input.isEmpty()) {
                break;
            }



            /*

            // TODO: this won't handle things like slashes in dates. ideally we want to
            // split based on " /" (ie. there must be a leading space before the slash),
            // but that requires changing StringView::bisect. later.
            var currentArg = input.drop(1).bisect('/', input);

            {
                var argName = new StringView("");
                var argValue = new StringView("");

                currentArg.bisect(argName, ' ', argValue);
                if (argName.isEmpty()) {
                    return Result.error("expected argument name after '/'");
                }

                ret.add(Pair.of(new ArgName(argName.trim().toString()), argValue.trim().toString()));
            }

            if (input.isEmpty()) {
                break;
            }

            input = input.undrop(1);
            */
        }

        return Result.of(ret);
    }

    /**
     * Parse an input string into its constituent components, including the name of the command, its target,
     * and its arguments. See the documentation for {@link chopchop.logic.parser.CommandArguments} for the recognised
     * components of a given input string.
     *
     * @param input the input string to parse
     * @return      the parsed components, iff parsing succeeded; an empty optional otherwise.
     */
    public Result<CommandArguments> parseArgs(String input) {

        var sv = new StringView(input);

        var x = new StringView("");
        var xs = new StringView("");

        var command = sv.bisect(' ', xs).toString().strip();

        var p = splitUntilNextSlash(xs);
        var theRest = p.fst();

        return this.parseNamedArguments(p.snd())
            .map(args -> new CommandArguments(command, theRest, args));
    }


    /**
     * Parse a user input into a {@code Command}, or an error message if parsing failed.
     *
     * @param input the input string to parse
     * @return      the parsed command on success; an error message otherwise.
     */
    public Result<Command> parse(String input) {

        return this.parseArgs(input)
            .then(args -> {
                switch (args.getCommand()) {

                case Strings.COMMAND_ADD:       return parseAddCommand(args);
                case Strings.COMMAND_EDIT:      return parseEditCommand(args);
                case Strings.COMMAND_HELP:      return parseHelpCommand(args);
                case Strings.COMMAND_FIND:      return parseFindCommand(args);
                case Strings.COMMAND_LIST:      return parseListCommand(args);
                case Strings.COMMAND_DELETE:    return parseDeleteCommand(args);
                case Strings.COMMAND_MAKE:      return parseMakeCommand(args);
                case Strings.COMMAND_VIEW:      return parseViewCommand(args);
                case Strings.COMMAND_FILTER:    return parseFilterCommand(args);
                case Strings.COMMAND_UNDO:      return Result.of(new UndoCommand());
                case Strings.COMMAND_REDO:      return Result.of(new RedoCommand());
                case Strings.COMMAND_QUIT:      return Result.of(new QuitCommand());

                default:
                    return Result.error("unknown command '%s'", args.getCommand());
                }
            });
    }



    private Pair<String, StringView> splitUntilNextSlash(StringView input) {

        int i = 0;
        var sb = new StringBuilder();

        for (; i < input.size(); i++) {
            if (i + 1 < input.size() && input.at(i) == '\\' && input.at(i + 1) == '/') {
                i += 1;
                sb.append("/");
            } else if (input.at(i) == '/') {
                break;
            } else {
                sb.append(input.at(i));
            }
        }

        return Pair.of(sb.toString().strip(), input.drop(i));
    }
}

// CommandParser.java

package chopchop.parser;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import chopchop.util.Pair;
import chopchop.util.Result;
import chopchop.util.StringView;
import chopchop.logic.commands.Command;

import static chopchop.parser.commands.AddCommandParser.parseAddCommand;

public class CommandParser {

    private Result<List<Pair<String, String>>> parseNamedArguments(StringView input) {

        var ret = new ArrayList<Pair<String, String>>();
        while (input.size() > 0) {

            if (input.find('/') != 0) {
                break;
            }

            // TODO: this won't handle things like slashes in dates. ideally we want to
            // split based on " /" (ie. there must be a leading space before the slash),
            // but that requires changing StringView::bisect. later.
            var currentArg = input.drop(1).bisect('/', input);

            {
                var argName = new StringView("");
                var argValue = new StringView("");

                currentArg.bisect(argName, ' ', argValue);
                if (argName.isEmpty()) {
                    return Result.error("argument name cannot be empty");
                }

                ret.add(Pair.of(argName.trim().toString(), argValue.trim().toString()));
            }

            if (input.isEmpty()) {
                break;
            }

            input = input.undrop(1);
        }

        return Result.of(ret);
    }

    /**
     * Parse an input string into its constituent components, including the name of the command, its target,
     * and its arguments. See the documentation for {@link chopchop.parser.CommandArguments} for the recognised
     * components of a given input string.
     *
     * @param input the input string to parse
     * @return      the parsed components, iff parsing succeeded; an empty optional otherwise.
     */
    private Result<CommandArguments> parseArgs(String input) {

        var sv = new StringView(input);

        var x = new StringView("");
        var xs = new StringView("");

        sv.bisect(x, ' ', xs);

        /*
            add recipe NAME [/ingredient INGREDIENT [/qty QTY1]...]... [/step STEP]...
            delete recipe NAME
            find recipe KEYWORDS [MORE_KEYWORDS]
            list recipes
            make RECIPE_NAME [/qty TIMES]

            add ingredient NAME [/qty QUANTITY] [/expiry DATE]
            use ingredient NAME [/qty QUANTITY]
            find ingredient KEYWORDS [MORE_KEYWORDS]
            list ingredients
        */

        var command = x.toString().strip();
        xs.bisect(x, ' ', xs);

        var target = Optional.of(x)
            .filter(s -> !s.isEmpty())
            .map(StringView::toString)
            .map(String::strip)
            .orElse(null);

        xs.bisect(x, '/', xs);
        var theRest = x.toString().strip();

        if (!xs.isEmpty()) {
            xs = xs.undrop(1);
            assert xs.at(0) == '/';
        }

        return this.parseNamedArguments(xs)
            .map(args -> new CommandArguments(command, target, theRest, args));
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

                case "add": return parseAddCommand(args);


                default:
                    return Result.error("unknown command '%s'", args.getCommand());
                }
            });
    }
}

// CommandParser.java

package chopchop.parser;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;

import chopchop.util.Result;
import chopchop.util.StringView;

public class CommandParser {

    private Result<Map<String, List<String>>> parseNamedArguments(StringView input) {

        var ret = new HashMap<String, List<String>>();
        while (input.size() > 0) {

            if (input.find('/') != 0) {
                break;
            }

            var currentArg = input.drop(1).bisect('/', input);

            {
                var argName = new StringView("");
                var argValue = new StringView("");

                currentArg.bisect(argName, ' ', argValue);
                if (argName.isEmpty()) {
                    return Result.error("argument name cannot be empty");
                }

                var list = new ArrayList<String>();
                list.add(argValue.toString().strip());

                ret.merge(argName.toString().strip(), list, (oldVal, newVal) -> {
                    oldVal.addAll(newVal);
                    return oldVal;
                });
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
    public Result<CommandArguments> parse(String input) {

        var sv = new StringView(input);

        var x = new StringView("");
        var xs = new StringView("");

        sv.bisect(x, ' ', xs);

        /*
            add recipe NAME [/ingredient INGREDIENT [/qty QTY1]...]... [/step STEP]...
            delete recipe NAME
            find recipe KEYWORDS [MORE_KEYWORDS]
            list recipes

            add ingredient NAME [/qty QUANTITY] [/expiry DATE]
            delete ingredient NAME [/qty QUANTITY]
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
}

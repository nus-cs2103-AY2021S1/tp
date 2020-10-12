// CommonParser.java

package chopchop.logic.parser.commands;

import java.util.List;
import java.util.Optional;

import chopchop.logic.parser.ArgName;
import chopchop.logic.parser.CommandArguments;

public class CommonParser {

    /**
     * Finds the first named argument in the given {@code args} that isn't part of {@code knownArgs}
     * and returns it so we can print a nice error message. If all names are part of the given list,
     * then an empty optional is returned.
     */
    public static Optional<ArgName> getFirstUnknownArgument(CommandArguments args, List<ArgName> knownArgs) {
        return args.getAllArguments()
            .stream()
            .filter(p -> !knownArgs.contains(p.fst()))
            .map(p -> p.fst())
            .findFirst();
    }
}

// CommonParser.java

package chopchop.parser.commands;

import java.util.List;
import java.util.Optional;

import chopchop.util.Result;
import chopchop.util.StringView;
import chopchop.parser.ItemReference;
import chopchop.parser.CommandArguments;

public class CommonParser {

    /**
     * Parse an item reference from the given string.
     */
    public static Result<ItemReference> parseItemReference(StringView ref) {
        if (ref.startsWith("$")) {
            return ref.drop(1)
                .parseInt()
                .map(i -> ItemReference.ofOneIndex(i));
        } else {
            return Result.of(ItemReference.ofName(ref.toString()));
        }
    }

    /**
     * Finds the first named argument in the given {@code args} that isn't part of {@code knownArgs}
     * and returns it so we can print a nice error message. If all names are part of the given list,
     * then an empty optional is returned.
     */
    public static Optional<String> getFirstUnknownArgument(CommandArguments args, List<String> knownArgs) {
        return args.getAllArguments()
            .stream()
            .filter(p -> !knownArgs.contains(p.fst()))
            .map(p -> p.fst())
            .findFirst();
    }
}

package tp.cap5buddy.logic.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Stores mapping of prefixes to their respective arguments.
 */
public class ArgumentMap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, String> argumentMap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     *
     * @param prefix Prefix key with which the specified argument value is to be associated.
     * @param prefixArgument String argument value to be associated with the specified prefix key.
     */
    public void put(Prefix prefix, String prefixArgument) {
        argumentMap.put(prefix, prefixArgument);
    }

    /**
     * Returns the value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        Optional<String> prefixArgument = Optional.ofNullable(argumentMap.get(prefix));
        return prefixArgument;
    }

    /**
     * Returns true if all the prefixes in the argument are present in the hashmap
     * and none of the prefixes contains empty {@code Optional} values.
     *
     * @param prefixes Array of prefixes.
     * @return
     */
    public boolean arePrefixesPresent(Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> this.argumentMap.containsKey(prefix) &&
                getValue(prefix).isPresent());
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }
}

package seedu.address.logic.parser;

import java.util.stream.Stream;

public class ArgumentMultimapUtil {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if only one of the given prefixes is present.
     * @param argumentMultimap  the map containing the prefixes and the corresponding Strings
     * @param prefixes  prefixes to check
     */
    public static boolean isOnlyOneGivenPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
            .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
            .count() == 1;
    }

    /**
     * Returns true if there is one and only one prefix in the argumentMultimap,
     * and that prefix is one of the given prefixes.
     * @param argumentMultimap  the map containing the prefixes and the corresponding Strings
     * @param prefixes  prefixes to check
     */
    public static boolean isOnlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        if (argumentMultimap.numOfPrefixes() != 1) {
            return false;
        }
        return isOnlyOneGivenPrefixPresent(argumentMultimap, prefixes);
    }
    /**
     * Checks if the given two prefixes are the only prefixes that are present.
     * @param argumentMultimap  the map containing the prefixes and the corresponding Strings
     * @param prefix1  the first prefix to check
     * @param prefix2  the second prefix to check
     * @return  true if the given two prefixes are the only prefixes presented, and false otherwise
     */
    public static boolean areOnlyTheseTwoPrefixesPresent(ArgumentMultimap argumentMultimap,
                                                         Prefix prefix1, Prefix prefix2) {
        if (argumentMultimap.numOfPrefixes() != 2) {
            return false;
        }
        if (argumentMultimap.getValue(prefix1).isPresent()
            && argumentMultimap.getValue(prefix2).isPresent()) {
            return true;
        }
        return false;
    }

}

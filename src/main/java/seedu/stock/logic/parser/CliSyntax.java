package seedu.stock.logic.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_SOURCE = new Prefix("s/");
    public static final Prefix PREFIX_QUANTITY = new Prefix("q/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_SERIAL_NUMBER = new Prefix("sn/");
    public static final Prefix PREFIX_NEW_QUANTITY = new Prefix("nq/");
    public static final Prefix PREFIX_INCREMENT_QUANTITY = new Prefix("iq/");
    public static final Prefix PREFIX_STATISTICS_TYPE = new Prefix("st/");

    /* Prefix values descriptions */
    public static final String PREFIX_NAME_DESCRIPTION = "<name>";
    public static final String PREFIX_SOURCE_DESCRIPTION = "<source>";
    public static final String PREFIX_LOCATION_DESCRIPTION = "<location>";
    public static final String PREFIX_QUANTITY_DESCRIPTION = "<quantity>";
    public static final String PREFIX_SERIAL_NUMBER_DESCRIPTION = "<serial number>";
    public static final String PREFIX_STATISTICS_TYPE_DESCRIPTION = "<statistics type>";


    /**
     * Returns a list containing all possible prefixes.
     *
     * @return A list containing all possible prefixes.
     */
    public static List<Prefix> getAllPossiblePrefixes() {
        List<Prefix> allPrefixes = new ArrayList<>();
        allPrefixes.add(PREFIX_NAME);
        allPrefixes.add(PREFIX_SOURCE);
        allPrefixes.add(PREFIX_QUANTITY);
        allPrefixes.add(PREFIX_LOCATION);
        allPrefixes.add(PREFIX_SERIAL_NUMBER);
        allPrefixes.add(PREFIX_NEW_QUANTITY);
        allPrefixes.add(PREFIX_INCREMENT_QUANTITY);
        allPrefixes.add(PREFIX_STATISTICS_TYPE);
        return allPrefixes;
    }

    /**
     * Returns the default parameter description of a certain prefix.
     *
     * @param prefix The prefix that needs parameter description.
     * @return The default parameter description of the given prefix.
     */
    public static String getDefaultDescription(Prefix prefix) {
        if (prefix.equals(PREFIX_NAME)) {
            return PREFIX_NAME_DESCRIPTION;
        } else if (prefix.equals(PREFIX_SOURCE)) {
            return PREFIX_SOURCE_DESCRIPTION;
        } else if (prefix.equals(PREFIX_LOCATION)) {
            return PREFIX_LOCATION_DESCRIPTION;
        } else if (prefix.equals(PREFIX_QUANTITY)) {
            return PREFIX_QUANTITY_DESCRIPTION;
        } else if (prefix.equals(PREFIX_SERIAL_NUMBER)) {
            return PREFIX_SERIAL_NUMBER_DESCRIPTION;
        } else if (prefix.equals(PREFIX_STATISTICS_TYPE)) {
            return PREFIX_STATISTICS_TYPE_DESCRIPTION;
        } else {
            return "";
        }
    }
}

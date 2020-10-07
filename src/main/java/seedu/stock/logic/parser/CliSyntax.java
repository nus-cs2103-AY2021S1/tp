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
    public static final Prefix PREFIX_SERIALNUMBER = new Prefix("sn/");
    public static final Prefix PREFIX_NEW_QUANTITY = new Prefix("nq/");
    public static final Prefix PREFIX_INCREMENT_QUANTITY = new Prefix("iq/");

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
        allPrefixes.add(PREFIX_SERIALNUMBER);
        allPrefixes.add(PREFIX_NEW_QUANTITY);
        allPrefixes.add(PREFIX_INCREMENT_QUANTITY);
        return allPrefixes;
    }
}

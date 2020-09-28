package nustorage.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    // Inventory
    public static final Prefix PREFIX_QUANTITY = new Prefix("q/"); // can change to n after removing name
    public static final Prefix PREFIX_ITEM_DESCRIPTION = new Prefix("i/");

    // Finance
    public static final Prefix PREFIX_AMOUNT = new Prefix("amt/");
}

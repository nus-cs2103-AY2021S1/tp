package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_PROPERTY_ID = new Prefix("b/");
    public static final Prefix PREFIX_CLIENT = new Prefix("c/");
    public static final Prefix PREFIX_MONEY = new Prefix("m/");

    /* Calendar Prefix definitions */
    public static final Prefix PREFIX_MEETING_VENUE = new Prefix("v/");
    public static final Prefix PREFIX_MEETING_TIME = new Prefix("t/");
    public static final Prefix PREFIX_MEETING_PROPERTY_ID = new Prefix("p/");
    public static final Prefix PREFIX_MEETING_BIDDER_ID = new Prefix("b/");
    public static final Prefix PREFIX_MEETING_TYPE = new Prefix("q/");
    public static final Prefix PREFIX_MEETING_ORDER = new Prefix("o/");

    /* Property Prefix definitions */
    public static final Prefix PREFIX_PROPERTY_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PROPERTY_SELLER_ID = new Prefix("s/");
    public static final Prefix PREFIX_PROPERTY_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_PROPERTY_ASKING_PRICE = new Prefix("ap/");
    public static final Prefix PREFIX_PROPERTY_TYPE = new Prefix("t/");
    public static final Prefix PREFIX_PROPERTY_IS_RENTAL = new Prefix("r/");
    public static final Prefix PREFIX_PROPERTY_IS_CLOSED_DEAL = new Prefix("c/");
    public static final Prefix PREFIX_PROPERTY_PROPERTY_ID = new Prefix("p/");

}

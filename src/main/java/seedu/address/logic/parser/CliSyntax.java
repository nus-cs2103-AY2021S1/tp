package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    // items start here
    public static final Prefix PREFIX_ORIGINAL_ITEM_NAME = new Prefix("-o ");
    public static final Prefix PREFIX_ITEM_NAME = new Prefix("-n ");
    public static final Prefix PREFIX_ITEM_QUANTITY = new Prefix("-q ");
    public static final Prefix PREFIX_ITEM_DESCRIPTION = new Prefix("-d ");
    public static final Prefix PREFIX_ITEM_LOCATION = new Prefix("-l ");
    public static final Prefix PREFIX_ITEM_TAG = new Prefix("-t ");

    // recipes start here
    public static final Prefix PREFIX_RECIPE_PRODUCT_NAME = new Prefix("-n ");
    public static final Prefix PREFIX_RECIPE_INGREDIENTS = new Prefix("-items ");
    public static final Prefix PREFIX_RECIPE_PRODUCT_QUANTITY = new Prefix("-pc ");
    public static final Prefix PREFIX_RECIPE_DESCRIPTION = new Prefix("-d ");
    // for deletion of recipe
    public static final Prefix PREFIX_RECIPE_ID = new Prefix("-i ");

}

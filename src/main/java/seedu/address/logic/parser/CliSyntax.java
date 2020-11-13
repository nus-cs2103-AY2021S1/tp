package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TAG_NAME = new Prefix("t>");
    public static final Prefix PREFIX_OLD_TAG_NAME = new Prefix("o>");
    public static final Prefix PREFIX_FILE_ADDRESS = new Prefix("f>");
    public static final Prefix PREFIX_CHILD_PATH = new Prefix("./");
    public static final Prefix PREFIX_PARENT_PATH = new Prefix("../");
    public static final Prefix PREFIX_LABEL_NAME = new Prefix("l>");
}

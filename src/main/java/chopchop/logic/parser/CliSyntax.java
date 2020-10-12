package chopchop.logic.parser;

import chopchop.parser.ArgName;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    // argument names
    public static final ArgName ARG_STEP            = new ArgName("step");
    public static final ArgName ARG_INGREDIENT      = new ArgName("ingredient");
    public static final ArgName ARG_QUANTITY        = new ArgName("qty");
    public static final ArgName ARG_EXPIRY          = new ArgName("expiry");


    // command names
    public static final String COMMAND_ADD          = "add";

    // command targets
    public static final String TARGET_RECIPE        = "recipe";
    public static final String TARGET_INGREDIENT    = "ingredient";
}

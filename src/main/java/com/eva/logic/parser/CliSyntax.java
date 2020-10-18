package com.eva.logic.parser;

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
    public static final Prefix PREFIX_COMMENT = new Prefix("c/");
    public static final Prefix PREFIX_LEAVE = new Prefix("l/");
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_LEAVE_START = new Prefix("ls/");
    public static final Prefix PREFIX_LEAVE_END = new Prefix("le/");
    public static final Prefix PREFIX_STAFF = new Prefix("s-");
    public static final Prefix PREFIX_APPLICANT = new Prefix("a-");
    public static final Prefix PREFIX_ADDORDELETE_COMMENT = new Prefix("c-");
}

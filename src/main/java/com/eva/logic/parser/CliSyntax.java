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
    public static final Prefix PREFIX_STAFF = new Prefix("s-");
    public static final Prefix PREFIX_APPLICANT = new Prefix("a-");
    public static final Prefix PREFIX_INTERVIEW_DATE = new Prefix("id/");
    public static final Prefix PREFIX_STAFF_LIST = new Prefix("-staff");
    public static final Prefix PREFIX_APPLICANT_LIST = new Prefix("-applicant");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_DESC = new Prefix("desc/");
    public static final Prefix PREFIX_TITLE = new Prefix("ti/");
    public static final Prefix PREFIX_ADD_COMMENT = new Prefix("-a");
    public static final Prefix PREFIX_DELETE_COMMENT = new Prefix("-d");
}

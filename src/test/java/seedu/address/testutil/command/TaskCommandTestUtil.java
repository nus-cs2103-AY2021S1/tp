package seedu.address.testutil.command;

import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_TASK_NUMBER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.task.EditTaskCommandParser;
import seedu.address.model.task.Description;

public class TaskCommandTestUtil {

    public static final String VALID_DESCRIPTION_REMIND_PATIENT = "Remind Alice to change bedsheets.";
    public static final String VALID_DESCRIPTION_ORDER_BEDSHEET = "Order new bedsheets for Room #3.";
    public static final String VALID_DATETIME_DUE_REMIND_PATIENT = "20201230 2359";
    public static final String VALID_DATETIME_DUE_ORDER_BEDSHEET = "20201023 1930";
    public static final Index VALID_TASK_INDEX_ONE = Index.fromOneBased(1);
    public static final Index VALID_TASK_INDEX_TWO = Index.fromOneBased(2);

    public static final String INVALID_DESCRIPTION_EMPTY_STRING = "";
    public static final String INVALID_DESCRIPTION_EXCEED_LIMIT = "a".repeat(Description.MAXIMUM_LENGTH + 1);
    public static final String INVALID_DATETIME_DUE_VALUE = "aaa";
    public static final String INVALID_DATETIME_DUE_FORMAT = "2020-12-31";
    public static final String INVALID_TASK_NUMBER = "-1";

    public static final String DESCRIPTION_DESC_REMIND_PATIENT = " " + PREFIX_DESCRIPTION
            + VALID_DESCRIPTION_REMIND_PATIENT;
    public static final String DESCRIPTION_DESC_ORDER_BEDSHEETS = " " + PREFIX_DESCRIPTION
            + VALID_DATETIME_DUE_ORDER_BEDSHEET;
    public static final String DATETIME_DUE_DESC_REMIND_PATIENT = " " + PREFIX_DUE_DATE
            + VALID_DATETIME_DUE_REMIND_PATIENT;
    public static final String DATETIME_DUE_DESC_ORDER_BEDSHEETS = " " + PREFIX_DUE_DATE
            + VALID_DATETIME_DUE_ORDER_BEDSHEET;
    public static final String DATETIME_DUE_DESC_CLEAR_DATETIME = " " + PREFIX_DUE_DATE
            + EditTaskCommandParser.INPUT_REMOVE_DUE_DATE;
    public static final String TASK_NUMBER_DESC_ONE = " " + PREFIX_TASK_NUMBER + "1";
    public static final String TASK_NUMBER_DESC_TWO = " " + PREFIX_TASK_NUMBER + "2";

    public static final String INVALID_DESCRIPTION_EMPTY_STRING_DESC = " " + PREFIX_DESCRIPTION
            + INVALID_DESCRIPTION_EMPTY_STRING;
    public static final String INVALID_DESCRIPTION_EXCEED_LIMIT_DESC = " " + PREFIX_DESCRIPTION
            + INVALID_DESCRIPTION_EXCEED_LIMIT;
    public static final String INVALID_DATETIME_DUE_VALUE_DESC = " " + PREFIX_DUE_DATE
            + INVALID_DATETIME_DUE_VALUE;
    public static final String INVALID_DATETIME_DUE_FORMAT_DESC = " " + PREFIX_DUE_DATE
            + INVALID_DATETIME_DUE_FORMAT;
    public static final String INVALID_TASK_NUMBER_DESC = " " + PREFIX_TASK_NUMBER
            + INVALID_TASK_NUMBER;
}

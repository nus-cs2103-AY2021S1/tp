package seedu.address.logic.command.sort;

import seedu.address.logic.command.Command;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.ShowCommand;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.activity.Activity;

public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String KEYWORD_NAME = "name";
    public static final String KEYWORD_COST = "cost";
    public static final String KEYWORD_IMPORTANCE = "importance";
    public static final String KEYWORD_DATE = "date";
    public static final String KEYWORD_DATETIME = "datetime";
    public static final String KEYWORD_PASSPORT = "passport";
    public static final String KEYWORD_MOBILE = "mobile";
    public static final String COMMA_DELIMITER = ", ";

    public static final int COMMAND_TOKENS = 3;

    public static final String MESSAGE_USAGE = "Sort the list of activities/accommodations/friends in the travel plan "
            + "in the current directory based on a given parameter (possible parameters given below) "
            + "using the format:\n"
            + COMMAND_WORD + COMMAND_SEPARATOR + "OBJECT PARAMETER\n"
            + "Sorting Parameters: " + KEYWORD_NAME + COMMA_DELIMITER + KEYWORD_COST
            + COMMA_DELIMITER + KEYWORD_IMPORTANCE + COMMA_DELIMITER + KEYWORD_DATE
            + COMMA_DELIMITER + KEYWORD_DATETIME + COMMA_DELIMITER + KEYWORD_MOBILE
            + COMMA_DELIMITER + KEYWORD_PASSPORT + "\n"
            + "Example: " + COMMAND_WORD + COMMAND_SEPARATOR + Activity.TPO_WORD + " " + KEYWORD_COST;

    private final String sortKeyword;

    /**
     * Constructor for ShowCommand.
     * @param sortKeyword string of the keyword used to sort.
     */
    public SortCommand(String sortKeyword) {
        this.sortKeyword = sortKeyword;
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && sortKeyword.equals(((SortCommand) other).sortKeyword));
    }
}

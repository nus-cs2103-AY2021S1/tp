package seedu.address.logic.parser.mainpageparser;

import seedu.address.logic.commands.main.SortBudgetCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.sort.SortType;

public class SortBudgetCommandParser implements Parser<SortBudgetCommand> {

    public static final String MESSAGE_INVALID_SORT_TYPE = "Sort type is not supported.";

    /**
     * Parses the given {@code String} of arguments in the context of the OpenBudgetCommand
     * and returns an OpenBudgetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortBudgetCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            throw new ParseException(SortBudgetCommand.MESSAGE_USAGE);
        }
        SortType sortType = ParserUtil.parseSortType(args);
        return new SortBudgetCommand(sortType);
    }
}

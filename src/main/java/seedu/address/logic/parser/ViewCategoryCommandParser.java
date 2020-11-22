package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCategoryCommand object
 */
public class ViewCategoryCommandParser implements Parser<ViewCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCategoryCommand
     * and returns a ViewCategoryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ViewCategoryCommand parse(String args) throws ParseException {
        return new ViewCategoryCommand();
    }
}

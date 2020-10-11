package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collections;

import seedu.address.logic.commands.ViewDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.NameIsExactlyPredicate;

/**
 * Parses input arguments and creates a new ViewDetailsCommand object
 */
public class ViewDetailsCommandParser implements Parser<ViewDetailsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewDetailsCommand
     * and returns a ViewDetailsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewDetailsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewDetailsCommand.MESSAGE_USAGE));
        }

        return new ViewDetailsCommand(new NameIsExactlyPredicate(Collections.singletonList(trimmedArgs)));
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.list.ListContactCommand;
import seedu.address.logic.commands.list.ListEventCommand;
import seedu.address.logic.commands.list.ListTaskCommand;
import seedu.address.logic.commands.list.ListTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        if (args.trim().equals("contact")) {
            return new ListContactCommand();
        } else if (args.trim().equals("todo")) {
            return new ListTodoCommand();
        } else if (args.trim().equals("event")) {
            return new ListEventCommand();
        } else if (args.trim().equals("task")) {
            return new ListTaskCommand();
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}

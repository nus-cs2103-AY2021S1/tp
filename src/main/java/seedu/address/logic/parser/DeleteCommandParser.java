package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.Command.TYPE_CASE;
import static seedu.address.logic.parser.AddressBookParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.casecommands.DeleteCaseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.state.StateManager;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        Index index;
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        final String deleteType = matcher.group("commandWord");
        final String indexString = matcher.group("arguments");
        try {
            index = ParserUtil.parseIndex(indexString);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }

        switch (deleteType) {
        case TYPE_CASE:
            if (StateManager.atCasePage()) {
                throw new ParseException(MESSAGE_INCORRECT_MAIN_PAGE);
            }
            return new DeleteCaseCommand(index);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

}

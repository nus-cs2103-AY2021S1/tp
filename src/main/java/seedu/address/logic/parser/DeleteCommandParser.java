package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INCORRECT_CASE_PAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.Command.TYPE_CASE;
import static seedu.address.logic.commands.Command.TYPE_DOC;
import static seedu.address.logic.commands.Command.TYPE_SUSPECT;
import static seedu.address.logic.commands.Command.TYPE_VICTIM;
import static seedu.address.logic.commands.Command.TYPE_WITNESS;
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

        if (StateManager.atMainPage()) {
            if (!matcher.matches()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));
            }
            final String deleteType = matcher.group("commandWord");
            final String indexString = matcher.group("arguments");

            switch (deleteType) {

            case TYPE_CASE:
                try {
                    index = ParserUtil.parseIndex(indexString);
                } catch (ParseException pe) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE_MAIN_PAGE),
                            pe);
                }
                return new DeleteCaseCommand(index);

            case TYPE_DOC:
            case TYPE_SUSPECT:
            case TYPE_WITNESS:
            case TYPE_VICTIM:
                throw new ParseException(MESSAGE_INCORRECT_CASE_PAGE);

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));
            }
        }

        if (StateManager.atCasePage()) {
            if (!matcher.matches()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE_CASE_PAGE));
            }
            final String deleteType = matcher.group("commandWord");
            final String indexString = matcher.group("arguments");

            switch (deleteType) {
            case TYPE_CASE:
                throw new ParseException(MESSAGE_INCORRECT_MAIN_PAGE);

            case TYPE_DOC:
                //TODO: return individual deleteTYPEcommands

            case TYPE_SUSPECT:
                //TODO: return individual deleteTYPEcommands

            case TYPE_WITNESS:
                //TODO: return individual deleteTYPEcommands

            case TYPE_VICTIM:
                //TODO: return individual deleteTYPEcommands

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE_CASE_PAGE));
            }
        }

        throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
    }

}

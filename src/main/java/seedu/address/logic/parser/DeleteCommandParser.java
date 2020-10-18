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
import seedu.address.logic.commands.documentcommands.DeleteDocumentCommand;
import seedu.address.logic.commands.suspectcommands.DeleteSuspectCommand;
import seedu.address.logic.commands.victimcommands.DeleteVictimCommand;
import seedu.address.logic.commands.witnesscommands.DeleteWitnessCommand;
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
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());

        if (StateManager.atMainPage()) {
            return parseMainPage(matcher);
        }

        if (StateManager.atCasePage()) {
            return parseCasePage(matcher);
        }

        throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    /**
     * Returns relevant DeleteCommand for main page.
     *
     * @param matcher
     * @return DeleteCommand
     * @throws ParseException if matcher does not match or incorrect type.
     */
    public DeleteCommand parseMainPage(Matcher matcher) throws ParseException {
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE_MAIN_PAGE));
        }
        final String deleteType = matcher.group("commandWord");
        final String indexString = matcher.group("arguments");

        switch (deleteType) {
        case TYPE_CASE:
            Index index = ParserUtil.getParsedIndex(indexString, DeleteCommand.MESSAGE_USAGE_MAIN_PAGE);
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

    /**
     * Returns relevant DeleteCommand for case page.
     *
     * @param matcher
     * @return DeleteCommand
     * @throws ParseException if matcher does not match or incorrect type.
     */
    public DeleteCommand parseCasePage(Matcher matcher) throws ParseException {
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE_CASE_PAGE));
        }
        final String deleteType = matcher.group("commandWord");
        final String indexString = matcher.group("arguments");

        if (deleteType.equals(TYPE_CASE)) {
            throw new ParseException(MESSAGE_INCORRECT_MAIN_PAGE);
        }

        Index index = ParserUtil.getParsedIndex(indexString, DeleteCommand.MESSAGE_USAGE_CASE_PAGE);
        Index caseIndex = StateManager.getState();

        switch (deleteType) {
        case TYPE_DOC:
            return new DeleteDocumentCommand(caseIndex, index);
        case TYPE_SUSPECT:
            return new DeleteSuspectCommand(caseIndex, index);
        case TYPE_WITNESS:
            return new DeleteWitnessCommand(caseIndex, index);
        case TYPE_VICTIM:
            return new DeleteVictimCommand(caseIndex, index);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE_CASE_PAGE));
        }
    }

}

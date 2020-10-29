package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.Command.TYPE_CASE;
import static seedu.pivot.logic.parser.PivotParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.archivecommands.UnarchiveCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

/**
 * Parses input arguments and creates a new UnarchiveCommand object
 */
public class UnarchiveCommandParser implements Parser<UnarchiveCommand> {

    private static final String MESSAGE_INCORRECT_SECTION_MAIN_PAGE = "Invalid command. "
            + "Type 'list archive' to see cases that you can unarchive.";

    private static final String MESSAGE_INCORRECT_SECTION_CASE_PAGE = "Invalid command. "
            + "Return to the main page and type 'list archive' to see cases that you can unarchive.";

    /**
     * Parses the given {@code String} of arguments in the context of the UnarchiveCommand
     * and returns a UnarchiveCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnarchiveCommand parse(String args) throws ParseException {

        if (StateManager.atCasePage()) {
            if (StateManager.atDefaultSection()) {
                throw new ParseException(MESSAGE_INCORRECT_SECTION_CASE_PAGE);
            }

            if (StateManager.atArchivedSection()) {
                throw new ParseException(MESSAGE_INCORRECT_MAIN_PAGE);
            }
        }

        if (StateManager.atMainPage()) {
            if (StateManager.atDefaultSection()) {
                throw new ParseException(MESSAGE_INCORRECT_SECTION_MAIN_PAGE);
            }

            if (StateManager.atArchivedSection()) {
                final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
                return parseMainPage(matcher);
            }
        }

        throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    /**
     * Returns UnarchiveCommand.
     *
     * @param matcher
     * @return UnarchiveCommand
     * @throws ParseException if matcher does not match or incorrect type or incorrect index format.
     */
    public UnarchiveCommand parseMainPage(Matcher matcher) throws ParseException {

        // wrong format (e.g. 'unarchive')
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnarchiveCommand.MESSAGE_USAGE));
        }

        final String unarchiveType = matcher.group("commandWord");
        final String indexString = matcher.group("arguments");

        // wrong type (e.g. 'unarchive xyz')
        if (!unarchiveType.equals(TYPE_CASE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnarchiveCommand.MESSAGE_USAGE));
        }

        // obtains index. if index is of wrong format, throw error.
        Index index = ParserUtil.getParsedIndex(indexString, UnarchiveCommand.MESSAGE_USAGE);

        return new UnarchiveCommand(index);

    }

}


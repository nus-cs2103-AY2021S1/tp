package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.Command.TYPE_CASE;
import static seedu.pivot.logic.parser.PivotParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.ArchiveCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

/**
 * Parses input arguments and creates a new ArchiveCommand object
 */
public class ArchiveCommandParser implements Parser<ArchiveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveCommand
     * and returns a ArchiveCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ArchiveCommand parse(String args) throws ParseException {
        if (StateManager.atCasePage()) {
            throw new ParseException(MESSAGE_INCORRECT_MAIN_PAGE);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (StateManager.atMainPage()) {
            return parseMainPage(matcher);
        }

        throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    /**
     * Returns ArchiveCommand.
     *
     * @param matcher
     * @return ArchiveCommand
     * @throws ParseException if matcher does not match or incorrect type or incorrect index format.
     */
    public ArchiveCommand parseMainPage(Matcher matcher) throws ParseException {

        // wrong format (e.g. 'archive')
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ArchiveCommand.MESSAGE_USAGE));
        }

        final String archiveType = matcher.group("commandWord");
        final String indexString = matcher.group("arguments");

        // wrong type (e.g. 'archive xyz')
        if (!archiveType.equals(TYPE_CASE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ArchiveCommand.MESSAGE_USAGE));
        }

        // obtains index. if index is of wrong format, throw error.
        Index index = ParserUtil.getParsedIndex(indexString, ArchiveCommand.MESSAGE_USAGE);

        return new ArchiveCommand(index);

    }


}

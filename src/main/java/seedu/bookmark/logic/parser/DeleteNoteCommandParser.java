package seedu.bookmark.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.DeleteNoteCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteNoteCommand object
 */
public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {

    private static final Pattern DELETE_NOTE_COMMAND_FORMAT = Pattern.compile("^(?<index>\\d*) (?<noteIndex>\\d*)$");
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteNoteCommand
     * and returns an DeleteNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        Index noteIndex;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String indexes = argMultimap.getPreamble();
        final Matcher matcher = DELETE_NOTE_COMMAND_FORMAT.matcher(indexes.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE));
        }
        final String argsIndex = matcher.group("index");
        final String argsNoteIndex = matcher.group("noteIndex");
        try {
            index = ParserUtil.parseIndex(argsIndex);
            noteIndex = ParserUtil.parseIndex(argsNoteIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE));
        }

        return new DeleteNoteCommand(index, noteIndex);
    }

}

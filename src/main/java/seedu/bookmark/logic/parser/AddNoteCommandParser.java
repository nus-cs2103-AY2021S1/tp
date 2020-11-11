package seedu.bookmark.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOTE_TEXT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.AddNoteCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Note;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns an AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NOTE_TITLE, PREFIX_NOTE_TEXT);

        Index index;
        Note note = null;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_NOTE_TITLE).isPresent()
                    && argMultimap.getValue(PREFIX_NOTE_TEXT).isPresent()) {
            note = ParserUtil.parseNote(
                    argMultimap.getValue(PREFIX_NOTE_TITLE).get(), argMultimap.getValue(PREFIX_NOTE_TEXT).get());
        }
        // If PREFIX_NOTE_TITLE and/or PREFIX_NOTE_TEXT is not present.
        if (note == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        return new AddNoteCommand(index, note);
    }

}

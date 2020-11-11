package seedu.bookmark.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.commons.core.Messages.MESSAGE_TOO_MANY_TAGS;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TOTAL_PAGES;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.EditCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_GENRE, PREFIX_TAG, PREFIX_TOTAL_PAGES, PREFIX_BOOKMARK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditBookDescriptor editBookDescriptor = new EditCommand.EditBookDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editBookDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_GENRE).isPresent()) {
            editBookDescriptor.setGenre(ParserUtil.parseGenre(argMultimap.getValue(PREFIX_GENRE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editBookDescriptor::setTags);

        if (argMultimap.getValue(PREFIX_TOTAL_PAGES).isPresent()) {
            editBookDescriptor.setTotalPages(
                    ParserUtil.parseTotalPages(argMultimap.getValue(PREFIX_TOTAL_PAGES).get()));
        }

        if (argMultimap.getValue(PREFIX_BOOKMARK).isPresent()) {
            editBookDescriptor.setBookmark(
                    ParserUtil.parseBookmark(Optional.of(argMultimap.getValue(PREFIX_BOOKMARK).get())));
        }

        if (!editBookDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editBookDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        Set<Tag> parsedTags = ParserUtil.parseTags(tagSet);
        if (!Book.isValidNumTags(parsedTags)) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_TAGS, Book.MAX_TAG_COUNT));
        }
        return Optional.of(parsedTags);
    }

}

package seedu.bagel.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.bagel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.bagel.commons.core.index.Index;
import seedu.bagel.logic.commands.EditCommand;
import seedu.bagel.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.bagel.logic.parser.exceptions.ParseException;
import seedu.bagel.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESC, PREFIX_LINK, PREFIX_SET, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditFlashcardDescriptor editFlashcardDescriptor = new EditFlashcardDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editFlashcardDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESC).isPresent()) {
            editFlashcardDescriptor
                    .setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get()));
        }

        if (argMultimap.getValue(PREFIX_LINK).isPresent()) {
            editFlashcardDescriptor
                    .setLink(ParserUtil.parseLink(argMultimap.getValue(PREFIX_LINK).orElse("")));
        }

        if (argMultimap.getValue(PREFIX_SET).isPresent()) {
            editFlashcardDescriptor
                    .setFlashcardSet(ParserUtil.parseSet(argMultimap.getValue(PREFIX_SET).orElse("1")));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editFlashcardDescriptor::setTags);

        if (!editFlashcardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editFlashcardDescriptor);
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
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}

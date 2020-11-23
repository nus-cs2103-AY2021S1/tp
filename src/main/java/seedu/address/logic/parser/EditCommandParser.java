package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PROVIDE_COLUMN;
import static seedu.address.commons.core.Messages.MESSAGE_REMOVE_COLUMN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLUMN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.EditByStateCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditBugDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.bug.Note;
import seedu.address.model.bug.Priority;
import seedu.address.model.bug.State;
import seedu.address.model.tag.Tag;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATE,
                PREFIX_DESCRIPTION, PREFIX_NOTE, PREFIX_TAG, PREFIX_PRIORITY, PREFIX_COLUMN);

        Index index;
        String preambleIndex = argMultimap.getPreamble();
        if (!StringUtil.isNumber(preambleIndex)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        if (StringUtil.isIndexOverflow(preambleIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_BUG_DISPLAYED_INDEX);
        }
        assert preambleIndex != null;

        try {
            index = ParserUtil.parseIndex(preambleIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditBugDescriptor editBugDescriptor = new EditBugDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            assert argMultimap.getValue(PREFIX_NAME).get() != null;
            editBugDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_STATE).isPresent()) {
            editBugDescriptor.setState(ParserUtil.parseState(argMultimap.getValue(PREFIX_STATE).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editBugDescriptor.setPriority(parsePriorityForEdit(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editBugDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            String editedNoteContent = argMultimap.getValue(PREFIX_NOTE).get();
            if (editedNoteContent.isBlank()) {
                editBugDescriptor.setOptionalNote(Optional.empty());
            } else {
                Optional<Note> parsedOptionalNote = Optional.of(ParserUtil.parseNote(editedNoteContent));
                editBugDescriptor.setOptionalNote(parsedOptionalNote);
            }
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editBugDescriptor::setTags);

        if (!editBugDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        if (argMultimap.getValue(PREFIX_COLUMN).isPresent()) {
            if (!ModelManager.isKanban()) {
                throw new ParseException(MESSAGE_REMOVE_COLUMN);
            }
            State targetState = ParserUtil.parseState(argMultimap.getValue(PREFIX_COLUMN).get());
            return new EditByStateCommand(index, editBugDescriptor, targetState);
        }

        if (ModelManager.isKanban()) {
            throw new ParseException(MESSAGE_PROVIDE_COLUMN);
        }

        return new EditCommand(index, editBugDescriptor);
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

        assert (!tags.isEmpty() == true);
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code String priority} into a {@code Priority} if {@conde priority} is a non-empty string.
     * If {@code priority} is an empty string, it will be parsed into a 'null' priority.
     */
    private Priority parsePriorityForEdit(String priority) throws ParseException {
        assert priority != null;
        if (priority.equals("")) {
            return new Priority();
        }
        return ParserUtil.parsePriority(priority);
    }

}

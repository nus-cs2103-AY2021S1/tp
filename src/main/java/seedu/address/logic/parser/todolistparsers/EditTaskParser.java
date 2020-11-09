package seedu.address.logic.parser.todolistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.todolistcommands.EditTaskCommand;
import seedu.address.logic.commands.todolistcommands.EditTaskDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditTaskCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(userInput, PREFIX_NAME,
            PREFIX_TAG, PREFIX_PRIORITY, PREFIX_DATE);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTaskDescriptor.setName(ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTaskDescriptor::setTags);

        Optional<String> optionalEditedPriority = argMultimap.getValue(PREFIX_PRIORITY);
        if (optionalEditedPriority.isPresent()) {
            String editedPriority = optionalEditedPriority.get();
            if (editedPriority.equals("")) {
                editTaskDescriptor.setIsPriorityDeleted(true);
            } else {
                editTaskDescriptor.setIsPriorityDeleted(false);
                editTaskDescriptor.setPriority(ParserUtil.parseTaskPriority(editedPriority));
            }
        }

        Optional<String> optionalEditedDate = argMultimap.getValue(PREFIX_DATE);
        if (optionalEditedDate.isPresent()) {
            String editedDate = optionalEditedDate.get();
            if (editedDate.equals("")) {
                editTaskDescriptor.setIsDateDeleted(true);
            } else {
                editTaskDescriptor.setIsDateDeleted(false);
                editTaskDescriptor.setDate(ParserUtil.parseTaskDate(editedDate));
            }
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
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

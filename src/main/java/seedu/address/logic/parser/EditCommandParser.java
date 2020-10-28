package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPOURL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.global.EditCommand.EditProjectDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_DEADLINE, PREFIX_REPOURL,
                        PREFIX_PROJECT_DESCRIPTION, PREFIX_PROJECT_TAG, PREFIX_TASK, PREFIX_MEETING);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        if (argMultimap.getValue(PREFIX_PROJECT_NAME).isPresent()) {
            editProjectDescriptor.setProjectName(ParserUtil.parseProjectName(
                    argMultimap.getValue(PREFIX_PROJECT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editProjectDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_REPOURL).isPresent()) {
            editProjectDescriptor.setRepoUrl(ParserUtil.parseRepoUrl(argMultimap.getValue(PREFIX_REPOURL).get()));
        }
        if (argMultimap.getValue(PREFIX_PROJECT_DESCRIPTION).isPresent()) {
            editProjectDescriptor.setProjectDescription(ParserUtil.projectDescription(argMultimap
                    .getValue(PREFIX_PROJECT_DESCRIPTION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_PROJECT_TAG)).ifPresent(editProjectDescriptor::setTags);
        parseTasksForEdit(argMultimap.getAllValues(PREFIX_TASK)).ifPresent(editProjectDescriptor::setTasks);

        if (!editProjectDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editProjectDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<ProjectTag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<ProjectTag>} containing zero tags.
     */
    private Optional<Set<ProjectTag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> tasks} into a {@code Set<Task>} if {@code tasks} is non-empty.
     * If {@code tasks} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Task>} containing zero tasks.
     */
    private Optional<Set<Task>> parseTasksForEdit(Collection<String> tasks) {
        assert tasks != null;

        if (tasks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> taskSet = tasks.size() == 1 && tasks.contains("") ? Collections.emptySet() : tasks;
        return Optional.of(ParserUtil.parseTasks(taskSet));
    }
}

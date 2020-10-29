package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.project.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME, PREFIX_TASK_DEADLINE, PREFIX_DESCRIPTION,
                        PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_NAME).isPresent()) {
            editTaskDescriptor.setTaskName(ParserUtil.parseTaskName(
                    argMultimap.getValue(PREFIX_TASK_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setTaskDescription(ParserUtil.parseTaskDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_PROGRESS).isPresent()) {
            editTaskDescriptor.setProgress(ParserUtil.parseTaskProgress(
                argMultimap.getValue(PREFIX_TASK_PROGRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_IS_DONE).isPresent()) {
            editTaskDescriptor.setIsDone(ParserUtil.parseDoneStatus(argMultimap.getValue(PREFIX_TASK_IS_DONE).get()));
        }
        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
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

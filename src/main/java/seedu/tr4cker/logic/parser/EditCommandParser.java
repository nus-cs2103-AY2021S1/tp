package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.HashSet;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.EditCommand;
import seedu.tr4cker.logic.commands.EditExpiredCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        boolean isEditExpiredTask = false;

        if (args.trim().length() > 6 && args.trim().substring(0, 7).equals("expired")) {
            isEditExpiredTask = true;
        }

        ArgumentMultimap argMultimap = getArgMultimap(args, isEditExpiredTask);

        Index index;

        try {
            index = ParserUtil.parseTaskIndex(argMultimap.getPreamble(), EditCommand.MESSAGE_USAGE);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }

        EditCommand.EditTaskDescriptor editTaskDescriptor = new EditCommand.EditTaskDescriptor();
        EditCommand.EditTodoDescriptor editTodoDescriptor = new EditCommand.EditTodoDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTaskDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            if (argMultimap.getValue(PREFIX_MODULE_CODE).get().equals("del")) {
                editTaskDescriptor.setModuleCode(new HashSet<>()); // for deleting module code
                editTaskDescriptor.setModuleDeleted();
            } else {
                editTaskDescriptor.setModuleCode(
                        ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get()));
            }
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTodoDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editTodoDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        if (isEditExpiredTask) {
            return new EditExpiredCommand(index, editTaskDescriptor, editTodoDescriptor);
        } else {
            return new EditCommand(index, editTaskDescriptor, editTodoDescriptor);
        }
    }

    private ArgumentMultimap getArgMultimap(String args, boolean isEditExpiredTask) throws ParseException {
        try {
            if (isEditExpiredTask) {
                return ArgumentTokenizer.tokenize(args.trim().substring(8), PREFIX_NAME, PREFIX_DEADLINE,
                        PREFIX_TASK_DESCRIPTION, PREFIX_MODULE_CODE);
            } else {
                return ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE,
                        PREFIX_TASK_DESCRIPTION, PREFIX_MODULE_CODE);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

    }
}

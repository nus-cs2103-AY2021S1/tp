package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_TASK_NUMBER;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author w-yeehong
/**
 * Parses input arguments and creates a new EditTaskCommand object.
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    public static final String INPUT_REMOVE_DUE_DATE = "-";

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_ROOM_NUMBER, PREFIX_TASK_NUMBER, PREFIX_DESCRIPTION, PREFIX_DUE_DATE);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_ROOM_NUMBER, PREFIX_TASK_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
        }

        // Compulsory fields
        int roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());
        Index taskIndex = ParserUtil.parseTaskIndex(argMultimap.getValue(PREFIX_TASK_NUMBER).get());

        // Optional fields
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
            editTaskDescriptor.setDescription(TaskParserUtil.parseDescription(description));
        }

        if (argMultimap.getValue(PREFIX_DUE_DATE).isPresent()) {
            String dueAt = argMultimap.getValue(PREFIX_DUE_DATE).get();
            editTaskDescriptor.setDateTimeDue(TaskParserUtil.parseDateTimeDue((dueAt.equals(INPUT_REMOVE_DUE_DATE))
                    ? Optional.empty()
                    : Optional.of(dueAt)));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            // Do not execute as no changes have been made
            throw new ParseException(Messages.MESSAGE_TASK_NOT_EDITED);
        }

        return new EditTaskCommand(roomNumber, taskIndex, editTaskDescriptor);
    }
}

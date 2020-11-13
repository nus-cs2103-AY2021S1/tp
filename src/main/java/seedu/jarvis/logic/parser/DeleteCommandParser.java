package seedu.jarvis.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.logic.commands.delete.DeleteCommand.MESSAGE_USAGE;
import static seedu.jarvis.logic.parser.CliSyntax.DELETE_CONSULTATION;
import static seedu.jarvis.logic.parser.CliSyntax.DELETE_MASTERY_CHECK;
import static seedu.jarvis.logic.parser.CliSyntax.DELETE_TASK;

import java.util.Arrays;
import java.util.logging.Logger;

import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.delete.DeleteCommand;
import seedu.jarvis.logic.commands.delete.DeleteConsultationCommand;
import seedu.jarvis.logic.commands.delete.DeleteMasteryCheckCommand;
import seedu.jarvis.logic.commands.delete.DeleteTaskCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.flag.Flag;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    public static final String MESSAGE_INVALID_ID = "Task ID provided is not correct.";
    private static final Logger logger = LogsCenter.getLogger(DeleteCommandParser.class);
    private static final String EMPTY_DELETE_COMMAND = "Please enter an index or id after the command. e.g. delete "
            + "-mc 1, delete -c 2, or delete -t T1";
    private static final String VALID_INDEX_MSG = "Please enter a valid index or id after the command.";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        // split the string trimmedArgs with regex of one or more whitespace characters.
        String[] inputsAfterCommandType = trimmedArgs.split("\\s+");
        assert inputsAfterCommandType.length > 0 : "String array of the arguments is empty";

        Flag commandFlag;
        String flagInput = inputsAfterCommandType[0];
        try {
            commandFlag = ParserUtil.parseFlag(flagInput);
        } catch (ParseException ex) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        String editArgs = String.join(" ", Arrays.copyOfRange(inputsAfterCommandType, 1,
                inputsAfterCommandType.length));

        if (editArgs.length() <= 0) { // when editArgs is empty (no command after delete -flag)
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EMPTY_DELETE_COMMAND));
        }

        String[] split = editArgs.split("\\s+");

        switch(commandFlag.getFlag()) {
        case DELETE_TASK:
            logger.info("DeleteCommandParser attempts to parse user's delete Task input");
            if (inputsAfterCommandType.length != 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_DELETE_TASK_USAGE));

            } else {
                String taskId = inputsAfterCommandType[1];
                return new DeleteTaskCommand(taskId);
            }

        case DELETE_CONSULTATION:
            logger.info("DeleteCommandParser attempts to parse user's delete Consultation input");
            Index consultationIndex;
            String indexParamConsultation = split[0];

            try {
                consultationIndex = ParserUtil.parseIndex(indexParamConsultation);
            } catch (ParseException pe) {
                throw new ParseException(pe.getMessage());
            }

            return new DeleteConsultationCommand(consultationIndex);

        case DELETE_MASTERY_CHECK:
            logger.info("DeleteCommandParser attempts to parse user's delete MasteryCheck input");
            Index masteryCheckIndex;
            String indexParamMasteryCheck = split[0];

            try {
                masteryCheckIndex = ParserUtil.parseIndex(indexParamMasteryCheck);
            } catch (ParseException pe) {
                throw new ParseException(pe.getMessage());
            }

            return new DeleteMasteryCheckCommand(masteryCheckIndex);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }

}

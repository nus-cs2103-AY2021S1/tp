package seedu.jarvis.logic.parser;

import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.logic.parser.CliSyntax.CONSULTATION;
import static seedu.jarvis.logic.parser.CliSyntax.MASTERY_CHECK;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.jarvis.logic.parser.CliSyntax.TASK_DEADLINE;
import static seedu.jarvis.logic.parser.CliSyntax.TASK_EVENT;
import static seedu.jarvis.logic.parser.CliSyntax.TASK_TODO;

import java.util.logging.Logger;

import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.logic.commands.add.AddCommand;
import seedu.jarvis.logic.commands.add.AddConsultationCommand;
import seedu.jarvis.logic.commands.add.AddMasteryCheckCommand;
import seedu.jarvis.logic.commands.add.AddTaskCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.flag.Flag;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Todo;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final Logger logger = LogsCenter.getLogger(AddCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_ADD_USAGE));
        }

        // split the string trimmedArgs with regex of one or more whitespace characters.
        String[] nameKeywords = trimmedArgs.split("\\s+");

        Flag commandFlag;
        try {
            commandFlag = ParserUtil.parseFlag(nameKeywords[0]);
        } catch (ParseException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_ADD_USAGE));
        }

        int length = nameKeywords.length;
        boolean hasDescription = length > 1;

        // when there is only flag
        if (!hasDescription && commandFlag.getFlag().equals(TASK_TODO)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_DESCRIPTION));

        } else if (!hasDescription && (commandFlag.getFlag().equals(TASK_EVENT)
                || commandFlag.getFlag().equals(TASK_DEADLINE))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_INFO_TASK));

        } else if ((!hasDescription) && commandFlag.getFlag().equals(CONSULTATION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));

        } else if ((!hasDescription) && commandFlag.getFlag().equals(MASTERY_CHECK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        }

        boolean notPrefix = nameKeywords[1].length() >= 2
                && (!nameKeywords[1].substring(0, 2).equals(PREFIX_DATE)
                        || !nameKeywords[1].substring(0, 2).equals(PREFIX_TIME));
        boolean hasActualDescription = length > 1 && (notPrefix || nameKeywords[1].length() == 1);

        // when description is invalid (e.g. goes straight to date and time)
        if ((!hasActualDescription) && commandFlag.getFlag().equals(CONSULTATION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        } else if ((!hasActualDescription) && commandFlag.getFlag().equals(MASTERY_CHECK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        }

        // switch command to return the respective add commands
        switch (commandFlag.getFlag()) {
        case TASK_TODO:
            logger.info("AddCommandParser attempts to parse user's newly added todo task");
            Todo todo = TaskCommandParser.parseTodo(nameKeywords, length);
            return new AddTaskCommand(todo);

        case TASK_EVENT:
            logger.info("AddCommandParser attempts to parse user's newly added event task");
            Event event = TaskCommandParser.parseEvent(nameKeywords, length);
            return new AddTaskCommand(event);

        case TASK_DEADLINE:
            logger.info("AddCommandParser attempts to parse user's newly added deadline task");
            Deadline deadline = TaskCommandParser.parseDeadline(nameKeywords, length);
            return new AddTaskCommand(deadline);

        case CONSULTATION:
            if (nameKeywords.length < 4) { // missing at least one field
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
            }
            logger.info("AddCommandParser attempts to parse user's newly added consultation");
            Consultation consultation = ConsultationMasteryCheckCommandParser.parseConsultation(nameKeywords, length);
            return new AddConsultationCommand(consultation);

        case MASTERY_CHECK:
            if (nameKeywords.length < 4) { // missing at least one field
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
            }
            logger.info("AddCommandParser attempts to parse user's newly added mastery check");
            MasteryCheck masteryCheck = ConsultationMasteryCheckCommandParser.parseMasteryCheck(nameKeywords, length);
            return new AddMasteryCheckCommand(masteryCheck);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_ADD_USAGE));
        }
    }

}

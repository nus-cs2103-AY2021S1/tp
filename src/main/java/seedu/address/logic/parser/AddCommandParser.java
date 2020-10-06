package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.TASK_DAY;
import static seedu.address.logic.parser.CliSyntax.TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.TASK_EVENT;
import static seedu.address.logic.parser.CliSyntax.TASK_TODO;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.flag.Flag;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Event;
import seedu.address.model.task.Todo;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_TASK_USAGE));
        }

        // split the string trimmedArgs with regex of one or more whitespace characters.
        String[] nameKeywords = trimmedArgs.split("\\s+");
        Flag commandFlag = ParserUtil.parseFlag(nameKeywords[0]);
        int length = nameKeywords.length;
        boolean taskHasDescription = length > 1;

        if(!taskHasDescription &&
                (commandFlag.getFlag().equals(TASK_TODO) || commandFlag.getFlag().equals(TASK_EVENT) ||
                        commandFlag.getFlag().equals(TASK_DEADLINE))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_DESCRIPTION));
        }

        // switch command to return the respective add commands
        switch (commandFlag.getFlag()) {
        case TASK_TODO:
            String description = nameKeywords[1];
            for (int i = 2; i < length; i++) {
                description = description + " " + nameKeywords[i];
            }

            Todo todo = new Todo(description);
            return new AddCommand(todo);

        case TASK_EVENT:
            boolean hasTimePrefix = false;
            int timePrefixLocation = -1;
            for (int i = 2; i < length; i++) {
                if (nameKeywords[i].equals(TASK_DAY)) {
                    hasTimePrefix = true;
                    timePrefixLocation = i;
                }
            }

            if (!hasTimePrefix) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_TIME));
            }

            String eventDescription = nameKeywords[1];
            for (int i = 2; i < timePrefixLocation; i++) {
                eventDescription = eventDescription + " " + nameKeywords[i];
            }

            String eventDeadline = nameKeywords[timePrefixLocation + 1];
            Event event = new Event(eventDescription, eventDeadline);
            return new AddCommand(event);

        case TASK_DEADLINE:
            boolean hasTimePrefixD = false;
            int timePrefixLocationD = -1;
            for (int i = 2; i < length; i++) {
                if (nameKeywords[i].equals(TASK_DAY)) {
                    hasTimePrefixD = true;
                    timePrefixLocationD = i;
                }
            }

            if (!hasTimePrefixD) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_TIME));
            }

            String deadlineDescription = nameKeywords[1];
            for (int i = 2; i < timePrefixLocationD; i++) {
                deadlineDescription = deadlineDescription + " " + nameKeywords[i];
            }

            String deadlineDeadline = nameKeywords[timePrefixLocationD + 1];
            Deadline deadline = new Deadline(deadlineDescription, deadlineDeadline);
            return new AddCommand(deadline);

        default:
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }

            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Student student = new Student(name, phone, email, address, tagList);

            return new AddCommand(student);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

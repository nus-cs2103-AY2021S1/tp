package seedu.tasklist.logic.parser;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tasklist.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.tasklist.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.tasklist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tasklist.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.stream.Stream;

import seedu.tasklist.logic.commands.AddCommand;
import seedu.tasklist.logic.parser.exceptions.ParseException;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.model.assignment.Done;
import seedu.tasklist.model.assignment.Priority;
import seedu.tasklist.model.assignment.Remind;
import seedu.tasklist.model.assignment.Schedule;
import seedu.tasklist.model.task.Time;
import seedu.tasklist.model.task.ModuleCode;
import seedu.tasklist.model.task.Name;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final int NO_OCCURRENCE_FOUND = -1;

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;

        boolean isRemindPresent = args.matches(".*\\bremind\\b$");
        boolean isRemindTypo = false;
        boolean isPriorityPresent = args.matches(".*\\bp/\\b.*");

        if (!isRemindPresent) {
            isRemindTypo = args.matches(".*rem[a-z]*$");
        }

        if (isRemindPresent || isRemindTypo) {
            // remove remind from args as ArgumentTokenizer cannot parse remind without prefix
            String argsWithoutRemind = args.replace(" remind", "");
            argMultimap = ArgumentTokenizer.tokenize(argsWithoutRemind, PREFIX_NAME, PREFIX_DEADLINE,
                    PREFIX_MODULE_CODE, PREFIX_PRIORITY);
        } else {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_MODULE_CODE,
                    PREFIX_PRIORITY);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty() || isRemindTypo) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Time deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
        Remind remind = new Remind();
        Schedule schedule = new Schedule();
        Done done = new Done();
        Priority priority = new Priority();

        if (isRemindPresent) {
            remind = remind.setReminder();
        }

        if (isPriorityPresent) {
            priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        }

        Assignment assignment = new Assignment(name, deadline, moduleCode, remind, schedule, priority, done);
        return new AddCommand(assignment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

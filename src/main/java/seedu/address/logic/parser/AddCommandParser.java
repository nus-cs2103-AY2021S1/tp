package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final int FROM_START_OF_STRING = 0;
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;

        boolean isRemindPresentAsLastArgument = args.matches(".*\\bremind\\b$");
        boolean isRemindPresent = false;
        boolean isRemindTypo = false;
        boolean isPriorityPresent = args.matches(".*\\bp/.*");

        if (!isRemindPresentAsLastArgument) {
            isRemindPresent = args.matches(".*\\bremind\\b.*");
            isRemindTypo = args.matches(".*\\bre[a-z]*$");
        }

        if (isRemindPresentAsLastArgument || isRemindTypo || isRemindPresent) {
            String argsWithoutRemind;

            if (isRemindTypo) {
                Pattern typoPattern = Pattern.compile("\\bre[a-z]*\\b");
                Matcher matcher = typoPattern.matcher(args);
                matcher.find(FROM_START_OF_STRING);
                String remindTypoWord = matcher.group();
                argsWithoutRemind = args.replace(remindTypoWord, "");
            } else {
                // remove remind from args as ArgumentTokenizer cannot parse remind without prefix
                argsWithoutRemind = args.replace(" remind", "");
            }
            argMultimap = ArgumentTokenizer.tokenize(argsWithoutRemind, PREFIX_NAME, PREFIX_DEADLINE,
                    PREFIX_MODULE_CODE, PREFIX_PRIORITY);
        } else {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_MODULE_CODE,
                    PREFIX_PRIORITY);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty() || isRemindTypo || isRemindPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Time deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE)
                .get().toUpperCase());
        Remind remind = new Remind();
        Schedule schedule = new Schedule();
        Done done = new Done();
        Priority priority = new Priority();

        if (isRemindPresentAsLastArgument) {
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

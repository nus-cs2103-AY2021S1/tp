package tp.acecs2103.logic.parser;

import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_CUSTOMIZED_DEADLINE;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_INDEX;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_REMARK;
import static tp.acecs2103.logic.parser.CliSyntax.PREFIX_WEEK_NUMBER;

import java.time.LocalDate;
import java.util.stream.Stream;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.logic.commands.AddCommand;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.Admin;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.IP;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.TP;
import tp.acecs2103.model.task.Task;
import tp.acecs2103.model.task.TaskCategory;
import tp.acecs2103.model.task.Topic;
import tp.acecs2103.model.task.WeekNumber;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException, CommandException {
        if (args.equals("")) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_WEEK_NUMBER, PREFIX_DESCRIPTION,
                        PREFIX_CUSTOMIZED_DEADLINE, PREFIX_REMARK, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX,
                PREFIX_WEEK_NUMBER, PREFIX_DESCRIPTION, PREFIX_CUSTOMIZED_DEADLINE, PREFIX_CATEGORY)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String index =
                ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        Index indexObject = new Index(index);

        int weekNumber =
                ParserUtil.parseWeekNumber(argMultimap.getValue(PREFIX_WEEK_NUMBER).get());
        WeekNumber weekNumberObject = new WeekNumber(Integer.toString(weekNumber));

        String description =
                ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Description descriptionObject = new Description(description);

        LocalDate customizedDeadline =
                ParserUtil.parseCustomizedDeadline(argMultimap.getValue(PREFIX_CUSTOMIZED_DEADLINE).get());
        CustomizedDeadline customizedDeadlineObject =
                new CustomizedDeadline(customizedDeadline.toString(), customizedDeadline);

        String remark =
                ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        Remark remarkObject = new Remark(remark);

        TaskCategory category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());

        if (category.equals(TaskCategory.ADMIN)) {
            Admin admin = new Admin(indexObject, weekNumberObject, descriptionObject, null,
                    customizedDeadlineObject, remarkObject, true, false);
            return new AddCommand(admin);
        } else if (category.equals(TaskCategory.TOPIC)) {
            Topic topic = new Topic(indexObject, weekNumberObject, descriptionObject, null,
                    customizedDeadlineObject, remarkObject, true, false);
            return new AddCommand(topic);
        } else if (category.equals(TaskCategory.IP)) {
            IP ip = new IP(indexObject, weekNumberObject, descriptionObject, null,
                    customizedDeadlineObject, remarkObject, true, false);
            return new AddCommand(ip);
        } else if (category.equals(TaskCategory.TP)) {
            TP tp = new TP(indexObject, weekNumberObject, descriptionObject, null,
                    customizedDeadlineObject, remarkObject, true, false);
            return new AddCommand(tp);
        }

        Task task = new Task(indexObject, weekNumberObject, descriptionObject, null,
                customizedDeadlineObject, remarkObject, true, false);

        return new AddCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> !argumentMultimap.getValue(prefix).get().equals(""));
    }

}

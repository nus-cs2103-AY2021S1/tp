package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.stream.Stream;

import seedu.address.logic.commands.GoalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.goal.Goal;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class GoalCommandParser implements ExerciseParser<GoalCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GoalCommand
     * and returns an GoalCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */

    public GoalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CALORIES, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CALORIES, PREFIX_DATE)
            /*|| !argMultimap.getPreamble().isEmpty()*/) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE));
        }

        Calories calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        Goal goal = new Goal(calories, date);

        return new GoalCommand(goal);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

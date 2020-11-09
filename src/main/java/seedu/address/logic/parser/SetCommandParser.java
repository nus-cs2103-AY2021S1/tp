package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_GOAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SET_GOAL;

import java.util.stream.Stream;

import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.GoalTarget;

/**
 * Parses input arguments and creates a new SetCommand object
 */
public class SetCommandParser implements Parser<SetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetCommand
     * and returns a SetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_SET_GOAL, PREFIX_LIST_GOAL);

        if (!argMultimap.getPreamble().isEmpty() || argMultimap.argsContainWrongPrefix()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_SET_GOAL)) {
            // set is inputted; any list will be ignored
            GoalTarget goalTarget = ParserUtil.parseGoal(argMultimap.getValue(PREFIX_SET_GOAL).get());
            return new SetCommand(goalTarget);
        } else if (arePrefixesPresent(argMultimap, PREFIX_LIST_GOAL)) {
            ParserUtil.parseGoalLevel(argMultimap.getValue(PREFIX_LIST_GOAL).get());
            return new SetCommand(new GoalTarget());
        } else {
            // both set and list is NOT inputted
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE));
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

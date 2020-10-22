package seedu.fma.logic.parser;

import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_C;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_E;

import seedu.fma.logic.commands.AddExCommand;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;

/**
 * Parses input arguments and creates a new AddExCommand object
 */
public class AddExCommandParser implements Parser<AddExCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddExCommand
     * and returns an AddExCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddExCommand parse(String args, ReadOnlyLogBook logBook) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_E, PREFIX_C);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_E, PREFIX_C)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_E).get());
        Calories calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_C).get());
        Exercise exercise = new Exercise(name, calories);
        return new AddExCommand(exercise);
    }
}

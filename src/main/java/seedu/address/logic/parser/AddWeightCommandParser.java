package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddWeightCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.body.Weight;

public class AddWeightCommandParser implements Parser<AddWeightCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddWeightCommand
     * and returns an AddWeightCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddWeightCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEIGHT);

        if (!arePrefixesPresent(argMultimap, PREFIX_WEIGHT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeightCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_WEIGHT).size() != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeightCommand.MESSAGE_USAGE));
        }

        Weight weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());

        return new AddWeightCommand(weight);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

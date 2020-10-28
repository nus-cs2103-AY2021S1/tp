package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSBBT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSBGT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSBM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSPBT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSPGT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSPM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.SalesUpdateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Drink;

/**
 * Parses input arguments and creates a new SalesUpdateCommand object
 */
public class SalesUpdateCommandParser implements Parser<SalesUpdateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SalesUpdateCommand
     * and returns a SalesUpdateCommand object for execution.
     *
     * The default value is 0 for drink items which the user did not provide the input.
     *
     * @param args user input to parse
     * @return a SalesUpdateCommand object for execution
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SalesUpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Prefix[] drinkPrefixes =
                new Prefix[] {PREFIX_BSBM, PREFIX_BSBBT, PREFIX_BSBGT, PREFIX_BSPM, PREFIX_BSPBT, PREFIX_BSPGT};
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, drinkPrefixes);

        if (!arePrefixesPresent(argMultimap, drinkPrefixes)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SalesUpdateCommand.MESSAGE_USAGE));
        }

        HashMap<Drink, Integer> sales = new HashMap<>();
        // populate the map with argmultimap's values
        for (int i = 0; i < drinkPrefixes.length; i++) {
            Drink drink = Drink.valueOf(drinkPrefixes[i].toString().replace("/", ""));
            if (argMultimap.getValue(drinkPrefixes[i]).isPresent()) {
                int val = ParserUtil.parseNumberSold(argMultimap.getValue(drinkPrefixes[i]).get());
                sales.put(drink, val);
            }
        }

        assert !sales.isEmpty();

        return new SalesUpdateCommand(sales);
    }

    /**
     * Returns true if some of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

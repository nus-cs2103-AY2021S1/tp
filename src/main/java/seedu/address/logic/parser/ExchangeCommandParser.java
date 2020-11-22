package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIGN;

import java.util.stream.Stream;

import seedu.address.logic.commands.ExchangeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.Currency;
import seedu.address.model.expense.CurrencyConverter;


/**
 * Parses input arguments and creates a new ExchangeCommand object
 */
public class ExchangeCommandParser implements Parser<ExchangeCommand> {

    @Override
    public ExchangeCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_SIGN);

        CurrencyConverter converter = new CurrencyConverter();

        if (!arePrefixesPresent(argMultimap, PREFIX_SIGN)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExchangeCommand.MESSAGE_USAGE));
        }
        Currency currencyCode = ParserUtil.parseCurrency(argMultimap.getValue(PREFIX_SIGN).get());
        if (!converter.isValidCurrency(currencyCode)) {
            throw new ParseException(ExchangeCommand.MESSAGE_INVALID_CURRENCY);
        }
        return new ExchangeCommand(currencyCode);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

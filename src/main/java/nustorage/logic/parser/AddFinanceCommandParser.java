package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static nustorage.logic.parser.CliSyntax.PREFIX_DATETIME;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import nustorage.logic.commands.AddFinanceCommand;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.FinanceRecord;

public class AddFinanceCommandParser implements Parser<AddFinanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddFinanceCommand
     * and returns an AddFinanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFinanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFinanceCommand.MESSAGE_USAGE));
        }

        double amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        FinanceRecord record;

        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            LocalDateTime datetime = ParserUtil.parseDatetime(argMultimap.getValue(PREFIX_DATETIME).get());
            record = new FinanceRecord(amount, datetime);
        } else {
            record = new FinanceRecord(amount);
        }

        return new AddFinanceCommand(record);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

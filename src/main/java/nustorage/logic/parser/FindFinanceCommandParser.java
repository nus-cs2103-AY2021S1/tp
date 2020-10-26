package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_AFTER_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_BEFORE_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_ID;

import java.time.LocalDateTime;

import nustorage.logic.commands.FindFinanceCommand;
import nustorage.logic.commands.FindInventoryRecordCommand;
import nustorage.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindInventoryRecordCommand object
 */
public class FindFinanceCommandParser implements Parser<FindFinanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInventoryRecordCommand
     * and returns a FindInventoryRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindFinanceCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_AFTER_DATETIME, PREFIX_BEFORE_DATETIME);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInventoryRecordCommand.MESSAGE_USAGE));
        }

        FindFinanceCommand command = new FindFinanceCommand();

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            command.setIdMatch(argMultimap.getValue(PREFIX_ID).get());
        }

        if (argMultimap.getValue(PREFIX_BEFORE_DATETIME).isPresent()) {
            LocalDateTime datetime = ParserUtil.parseDatetime(argMultimap.getValue(PREFIX_BEFORE_DATETIME).get());
            command.setBeforeDatetime(datetime.toLocalDate());
        }

        if (argMultimap.getValue(PREFIX_AFTER_DATETIME).isPresent()) {
            LocalDateTime datetime = ParserUtil.parseDatetime(argMultimap.getValue(PREFIX_AFTER_DATETIME).get());
            command.setAfterDatetime(datetime.toLocalDate());
        }

        return command;
    }
}

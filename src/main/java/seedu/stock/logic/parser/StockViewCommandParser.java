package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.StockViewCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.SerialNumber;

public class StockViewCommandParser implements Parser<StockViewCommand> {

    private static final Prefix[] allPossiblePrefixes = CliSyntax.getAllPossiblePrefixesAsArray();
    private static final Prefix[] validPrefixesForNoteView = { PREFIX_SERIAL_NUMBER };
    private static final Logger logger = LogsCenter.getLogger(StockViewCommandParser.class);

    @Override
    public StockViewCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse stock view command");
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPossiblePrefixes);

        // Check if command format is correct
        if (!ParserUtil.areAllPrefixesPresent(argMultimap, validPrefixesForNoteView)
                || ParserUtil.isInvalidPrefixPresent(argMultimap, validPrefixesForNoteView)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StockViewCommand.MESSAGE_USAGE));
        }

        if (ParserUtil.isDuplicatePrefixPresent(argMultimap, validPrefixesForNoteView)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE_HEADER_FIELD,
                    StockViewCommand.MESSAGE_USAGE));
        }

        String serialNumberInput = argMultimap.getValue(PREFIX_SERIAL_NUMBER).get();
        SerialNumber serialNumber = ParserUtil.parseSerialNumber(serialNumberInput);

        logger.log(Level.INFO, "Finished parsing stock view command successfully");
        return new StockViewCommand(serialNumber);
    }

}

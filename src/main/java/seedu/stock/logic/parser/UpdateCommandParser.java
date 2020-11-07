package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.UpdateCommand.MESSAGE_TOO_MANY_QUANTITY_PREFIXES;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_FILE_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_INCREMENT_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LIST_TYPE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_STATISTICS_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.logic.commands.UpdateCommand.UpdateStockDescriptor;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;

/**
 * Parses user input and creates a new update command.
 */
public class UpdateCommandParser implements Parser<UpdateCommand> {
    private static final Logger logger = LogsCenter.getLogger(UpdateCommandParser.class);

    /**
     * Parses {@code args} into an update command.
     *
     * @param args The user input to be parsed.
     * @return A new update command.
     * @throws ParseException If a parsing error occurs.
     */
    @Override
    public UpdateCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse update command");
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeAllPrefixes(args);
        List<Prefix> allPrefixes = CliSyntax.getAllPossiblePrefixes();
        List<Prefix> illegalPrefixes = ParserUtil.generateListOfPrefixes(PREFIX_QUANTITY, PREFIX_FILE_NAME,
                PREFIX_LIST_TYPE, PREFIX_NOTE, PREFIX_NOTE_INDEX, PREFIX_SORT_FIELD, PREFIX_SORT_ORDER,
                PREFIX_SOURCE, PREFIX_STATISTICS_TYPE);
        boolean isSerialNumberPresent = argMultimap.getValue(PREFIX_SERIAL_NUMBER).isPresent();
        boolean isIncrementQuantityPresent = argMultimap.getValue(PREFIX_INCREMENT_QUANTITY).isPresent();
        boolean isNewQuantityPresent = argMultimap.getValue(PREFIX_NEW_QUANTITY).isPresent();
        boolean isNamePresent = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean isLocationPresent = argMultimap.getValue(PREFIX_LOCATION).isPresent();
        boolean isLowQuantityPresent = argMultimap.getValue(PREFIX_LOW_QUANTITY).isPresent();
        boolean isInvalidPrefixPresent = argMultimap.checkIfAnyExist(illegalPrefixes);

        // If unallowed prefixes are provided
        if (isInvalidPrefixPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }

        // If serial number is not provided
        if (!isSerialNumberPresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }

        // Check if there are unallowed duplicate prefixes
        for (Prefix prefix: allPrefixes) {
            if (argMultimap.getAllValues(prefix).size() >= 2 && !prefix.equals(PREFIX_SERIAL_NUMBER)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
            }
        }

        // If both increment and new quantity prefix provided
        if (isIncrementQuantityPresent && isNewQuantityPresent) {
            throw new ParseException(MESSAGE_TOO_MANY_QUANTITY_PREFIXES + "\n" + UpdateCommand.MESSAGE_USAGE);
        }

        UpdateStockDescriptor updateStockDescriptor = new UpdateStockDescriptor();

        // Store the serial number provided
        assert isSerialNumberPresent : "Serial number not entered by user";
        List<String> keywords = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);
        List<SerialNumber> serialNumbers = new ArrayList<>();
        for (String keyword : keywords) {
            SerialNumber parsedToSerialNumber = ParserUtil.parseSerialNumber(keyword);
            serialNumbers.add(parsedToSerialNumber);
        }
        updateStockDescriptor.setSerialNumbers(serialNumbers);

        // Update name with new name provided
        if (isNamePresent) {
            String nameLowerCased = argMultimap.getValue(PREFIX_NAME).get().toLowerCase();
            updateStockDescriptor.setName(ParserUtil.parseName(nameLowerCased));
        }

        // Update quantity with new quantity provided
        if (isNewQuantityPresent) {
            String newQuantityDescription = argMultimap.getValue(PREFIX_NEW_QUANTITY).get();
            Quantity newQuantity = ParserUtil.parseQuantity(newQuantityDescription);
            updateStockDescriptor.setQuantity(newQuantity);
        }

        // Increment quantity with increment value provided
        if (isIncrementQuantityPresent) {
            String incrementValue = argMultimap.getValue(PREFIX_INCREMENT_QUANTITY).get();
            QuantityAdder toIncrement = ParserUtil.parseQuantityAdder(incrementValue);
            updateStockDescriptor.setQuantityAdder(toIncrement);
        }

        // Update location with new location provided
        if (isLocationPresent) {
            String locationLowerCased = argMultimap.getValue(PREFIX_LOCATION).get().toLowerCase();
            updateStockDescriptor.setLocation(ParserUtil.parseLocation(locationLowerCased));
        }

        // Update low quantity threshold with new value provided
        if (isLowQuantityPresent) {
            String lowQuantity = argMultimap.getValue(PREFIX_LOW_QUANTITY).get();
            updateStockDescriptor.setLowQuantity(lowQuantity);
        }

        if (!updateStockDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateCommand.MESSAGE_NOT_UPDATED);
        }

        logger.log(Level.INFO, "Finished parsing update command successfully");
        return new UpdateCommand(updateStockDescriptor);
    }
}

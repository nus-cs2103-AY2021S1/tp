package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import nustorage.logic.commands.FindInventoryRecordCommand;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.InventoryRecordNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindInventoryRecordCommand object
 */
public class FindInventoryRecordCommandParser implements Parser<FindInventoryRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInventoryRecordCommand
     * and returns a FindInventoryRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInventoryRecordCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInventoryRecordCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindInventoryRecordCommand(
                new InventoryRecordNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}

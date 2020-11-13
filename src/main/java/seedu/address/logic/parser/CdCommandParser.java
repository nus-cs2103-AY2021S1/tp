package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHILD_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PATH;

import seedu.address.logic.commands.AddressType;
import seedu.address.logic.commands.CdCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CdCommandParser implements Parser<CdCommand> {

    private Prefix prefix = null;

    /**
     * Parses the given {@code String} of arguments in the context of the CdCommand
     * and returns an CdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CdCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILE_ADDRESS, PREFIX_CHILD_PATH, PREFIX_PARENT_PATH);

        if (!isOnlyOnePrefixPresent(argMultimap, PREFIX_FILE_ADDRESS, PREFIX_CHILD_PATH, PREFIX_PARENT_PATH)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CdCommand.MESSAGE_USAGE));
        }

        AddressType addressType;
        String addressString;
        String prefixValue = argMultimap.getValue(prefix).get();

        if (PREFIX_FILE_ADDRESS.equals(prefix)) {
            addressType = AddressType.ABSOLUTE;
            addressString = ParserUtil.parseAbsoluteAddress(prefixValue);
        } else if (PREFIX_CHILD_PATH.equals(prefix)) {
            addressType = AddressType.CHILD;
            addressString = ParserUtil.parseChildAddress(prefixValue);
        } else if (PREFIX_PARENT_PATH.equals(prefix)) {
            addressType = AddressType.PARENT;
            addressString = ParserUtil.parseParentAddress(prefixValue);
        } else {
            throw new ParseException(CdCommand.MESSAGE_UNKNOWN_ADDRESS_TYPE);
        }

        return new CdCommand(addressType, addressString);
    }

    /**
     * Returns true if only one of the prefixes contains {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private boolean isOnlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        int numberOfPresents = 0;
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                this.prefix = prefix;
                numberOfPresents++;
            }
        }
        return numberOfPresents == 1;
    }
}

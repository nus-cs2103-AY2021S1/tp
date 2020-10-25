package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ResetVendorCommand;
import seedu.address.logic.commands.VendorCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new VendorCommand object
 */
public class VendorCommandParser implements Parser<ResetVendorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the VendorCommand
     * and returns an VendorCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ResetVendorCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();
        String[] argsArr = trimArgs.split(" ");
        // No arguments supplied
        if (argsArr.length == 1 && argsArr[0].equals("")) {
            return new ResetVendorCommand();
        }
        ParserUtil.checkArgsLength(argsArr, VendorCommand.COMMAND_WORD, VendorCommand.MESSAGE_USAGE, 1);
        Index index = ParserUtil.parseIndex(argsArr[0], "Vendor Index");
        return new VendorCommand(index);
    }
}



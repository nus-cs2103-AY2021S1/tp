package seedu.address.logic.parser;

import seedu.address.logic.commands.ProfileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;

public class ProfileCommandParser implements Parser<ProfileCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ProfileCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();

        int aIndex = trimArgs.indexOf("a/");
        int pIndex = trimArgs.indexOf("p/");
        if (aIndex == -1 | pIndex == -1) {
            throw new ParseException(ProfileCommand.MESSAGE_USAGE);
        }

        String addressStr = trimArgs.substring(aIndex + 2, pIndex).trim();
        String phoneStr = trimArgs.substring(pIndex + 2).trim();

        if (!Address.isValidAddress(addressStr) || !Phone.isValidPhone(phoneStr)) {
            throw new ParseException(ProfileCommand.MESSAGE_USAGE);
        }

        return new ProfileCommand(addressStr, phoneStr);
    }
}

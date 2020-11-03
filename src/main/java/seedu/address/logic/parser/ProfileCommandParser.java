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
        String[] strArgs = trimArgs.split(" ");
        if (strArgs.length < 2) {
            throw new ParseException(ProfileCommand.MESSAGE_USAGE);
        }
        String phoneStr = strArgs[0];
        StringBuilder builder = new StringBuilder();
        for (String string : strArgs) {
            builder.append(string);
            builder.append(" ");
        }
        String addressStr = builder.toString();

        if (!Address.isValidAddress(addressStr) || !Phone.isValidPhone(phoneStr)) {
            throw new ParseException(ProfileCommand.MESSAGE_USAGE);
        }

        return new ProfileCommand(addressStr, phoneStr);
    }
}

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
        String cleanedArgs = args.replaceAll("( )+", " ");
        String trimArgs = cleanedArgs.trim();
        int firstSpace = trimArgs.indexOf(' ');
        String[] argsArr = new String[2];
        argsArr[0] = trimArgs;
        argsArr[1] = "";
        if (firstSpace != -1) {
            argsArr[0] = trimArgs.substring(0, firstSpace).trim();
            argsArr[1] = trimArgs.substring(firstSpace + 1).trim();
        }

        if (argsArr[1].equals("")) {
            throw new ParseException(ProfileCommand.MESSAGE_USAGE);
        }

        Phone phone = ParserUtil.parsePhone(argsArr[0]);
        Address address = ParserUtil.parseAddress(argsArr[1]);
        return new ProfileCommand(phone, address);
    }
}

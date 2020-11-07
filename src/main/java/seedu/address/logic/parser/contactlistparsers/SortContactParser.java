package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.contactlistcommands.SortContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactComparatorByName;

/**
 * Parses input arguments and creates a new SortContactCommand object
 */
public class SortContactParser implements Parser<SortContactCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortContactCommand
     * and returns an SortContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortContactCommand parse(String args) throws ParseException {
        Comparator<Contact> comparator = new ContactComparatorByName();
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new SortContactCommand(comparator);
        }
        if (args.trim().equals("r")) {
            return new SortContactCommand(comparator.reversed());
        }
        throw new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortContactCommand.MESSAGE_USAGE));
    }
}

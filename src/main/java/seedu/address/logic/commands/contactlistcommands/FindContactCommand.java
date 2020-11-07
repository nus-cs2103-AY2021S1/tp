package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.FindContactCriteria;

/**
 * Encapsulates methods and information to find and list all contacts in the contact list
 * which match the given predicate.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "findcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts which match all the "
            + "specified search parameters (case-insensitive) and displays them as a list with index numbers.\n"
            + "At least one of the following search parameters must be provided: \n"
            + "Parameters: [n/NAME_KEYWORDS] [t/TAG_KEYWORDS] \n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_NAME + "john amy alice "
            + PREFIX_TAG + "friend lecturer";

    private final Logger logger = LogsCenter.getLogger(FindContactCommand.class);

    /** Predicate object used to test for matching contacts. */
    private final Predicate<Contact> predicate;

    /**
     * Creates and initialises a FindContactCommand object to find contacts that match all the
     * specified search parameters.
     *
     * @param findContactCriteria FindContactCriteria object that encapsulates a list of predicates
     *                            to test contacts with.
     */
    public FindContactCommand(FindContactCriteria findContactCriteria) {
        requireNonNull(findContactCriteria);
        logger.info("The find contact command is being executed");
        this.predicate = findContactCriteria.getFindContactPredicate();
        requireNonNull(predicate);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        logger.info("The find contact command has been executed");
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }

}

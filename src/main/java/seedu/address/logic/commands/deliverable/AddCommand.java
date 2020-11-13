package seedu.address.logic.commands.deliverable;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_MILESTONE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * Adds a deliverable to the deliverable book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deliverable to the deliverable list.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_MILESTONE + "MILESTONE "
            + "[" + PREFIX_CONTACTS + "CONTACTS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Login screen "
            + PREFIX_DEADLINE + "12-12-2020 23:59 "
            + PREFIX_MILESTONE + "1.0 "
            + PREFIX_CONTACTS + "John Martin, Abby Li "
            + PREFIX_DESCRIPTION + "Must include username and password fields";

    public static final String MESSAGE_SUCCESS = "Added new deliverable: %1$s";
    public static final String MESSAGE_DUPLICATE_DELIVERABLE =
            "This deliverable already exists in the deliverable list.";

    private final Deliverable toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Deliverable}
     */
    public AddCommand(Deliverable deliverable) {
        requireNonNull(deliverable);
        toAdd = deliverable;
    }

    @Override
    public CommandResult execute(ModelDeliverable modelDeliverable) throws CommandException {
        requireNonNull(modelDeliverable);

        if (modelDeliverable.hasDeliverable(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DELIVERABLE);
        }

        modelDeliverable.addDeliverable(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

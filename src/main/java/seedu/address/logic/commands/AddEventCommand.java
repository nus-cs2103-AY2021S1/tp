package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "add-event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the address book. "
            + "\nParameters: "
            + PREFIX_EVENT_NAME + "NAME "
            + PREFIX_EVENT_DATE + "DATE "
            + PREFIX_EVENT_LOCATION + "LOCATION "
            + PREFIX_EVENT_DESC + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_NAME + "Hall Dinner at Dining Room "
            + PREFIX_EVENT_DATE + "01/01/2020 15:00 "
            + PREFIX_EVENT_LOCATION + "Dining Hall "
            + PREFIX_EVENT_DESC + "The yearly Eusoff Hall Dinner.";

    public static final String MESSAGE_SUCCESS = "New event added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";

    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert toAdd != null : "A null event was associated to this AddEventCommand";

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}

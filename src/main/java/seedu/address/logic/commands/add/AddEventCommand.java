package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Event;

/**
 * Adds a event to the address book.
 */
public class AddEventCommand extends AddCommand {


    public static final String MESSAGE_USAGE = COMMAND_WORD + " event: Adds a event to the TaskList. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_STARTDATE + "STARTDATE "
            + PREFIX_STARTTIME + "STARTTIME "
            + PREFIX_ENDDATE + "ENDDATE "
            + PREFIX_ENDTIME + "ENDTIME "
            + " [" + PREFIX_RECURRING + "RECURRING] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " event "
            + PREFIX_DESCRIPTION + "Attend meeting "
            + PREFIX_STARTDATE + "10-11-2020 "
            + PREFIX_STARTTIME + "1200 "
            + PREFIX_ENDDATE + "10-11-2020 "
            + PREFIX_ENDTIME + "1400 "
            + PREFIX_TAG + "CS2103T";

    public static final String DATE_TIME_USAGE = "Date and time format should be: startdate/DD-MM-YYYY "
            + "starttime/HHmm (24-hour) enddate/DD-MM-YYYY endtime/HHmm (24-hour)";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";

    private final Event toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.addEvent(toAdd);
        model.getDueSoonTaskList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDescriptionDateTime()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.EVENT_OVERLAP_CONSTRAINTS;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.event.Event;
import seedu.address.model.util.Overlap;

/**
 * Adds a task to the PlaNus task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event to PlaNus.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DATE + "EVENT DATE "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "career talk "
            + PREFIX_DATE + "07-11-2020 "
            + PREFIX_START_TIME + "17:00 "
            + PREFIX_END_TIME + "19:00 "
            + PREFIX_DESCRIPTION + "ByteDance online sharing session ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";

    private final Event event;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public EventCommand(Event event) {
        requireNonNull(event);
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(event)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        if (Overlap.overlapWithOtherTimeSlots(model, event)) {
            throw new CommandException(EVENT_OVERLAP_CONSTRAINTS);
        }
        model.addTask(event);
        model.addTaskToCalendar(event);
        return new CommandResult(String.format(MESSAGE_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventCommand // instanceof handles nulls
                && event.equals(((EventCommand) other).event));
    }
}

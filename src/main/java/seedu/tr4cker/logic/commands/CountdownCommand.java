package seedu.tr4cker.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DATE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DAYS;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DELETE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_NEW;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.tr4cker.model.countdown.EventDate.MESSAGE_FUTURE_CONSTRAINT;

import java.util.List;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.exceptions.TaskConversionException;

/**
 * Allows user to go to Countdown tab.
 */
public class CountdownCommand extends Command {

    public static final String COMMAND_WORD = "countdown";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Countdown tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_ADD_COUNTDOWN_USAGE = COMMAND_WORD + ": Adds an event to list in Countdown\n"
            + "Parameters: "
            + PREFIX_COUNTDOWN_NEW + "NAME " + PREFIX_COUNTDOWN_DATE + "DATE\n"
            + "Note: NAME must be alphanumeric, spaces are possible, and DATE must be a valid date in the future!\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + PREFIX_COUNTDOWN_NEW + "Halloween Party " + PREFIX_COUNTDOWN_DATE + "31-Oct-2021\n";

    public static final String MESSAGE_DELETE_COUNTDOWN_USAGE = COMMAND_WORD
            + ": Deletes an event from countdown list\n"
            + "Parameters: "
            + PREFIX_COUNTDOWN_DELETE + "INDEX\n"
            + "Note: INDEX must be a valid index!\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + PREFIX_COUNTDOWN_DELETE + "1\n";

    public static final String MESSAGE_COUNT_DAYS_USAGE = COMMAND_WORD
            + ": Count the number of events in the specified number of days\n"
            + "Parameters: "
            + PREFIX_COUNTDOWN_DAYS + "DAYS\n"
            + "Note: DAYS must be a valid non-negative number!\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + PREFIX_COUNTDOWN_DAYS + "7\n";

    public static final String MESSAGE_GENERIC_COUNTDOWN_USAGE = "Countdown tab: Add an event, "
            + "delete an event, or switch to countdowns tab.\n - "
            + COMMAND_WORD + " : Switches to Countdown tab\n - "
            + COMMAND_WORD + " " + PREFIX_COUNTDOWN_NEW + "NAME " + PREFIX_COUNTDOWN_DATE + "DATE"
            + " : Adds an event to list in Countdown\n - "
            + COMMAND_WORD + " " + PREFIX_COUNTDOWN_DELETE + "INDEX" + " : Deletes an event from countdown list\n - "
            + COMMAND_WORD + " " + PREFIX_COUNTDOWN_DAYS + "DAYS"
            + " : Counts the number of events in the next DAYS days\n";

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Countdown tab!\n";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted event from Countdowns: %1$s";

    public static final String MESSAGE_ADD_EVENT_SUCCESS = "New event added to Countdowns added: %1$s";

    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in TR4CKER.";

    public static final String MESSAGE_COUNT_EVENTS_IN_DAYS_SUCCESS = "There are %s event(s) in %s days :\n%s";

    public static final int INVALID_QUERY_DAYS = -1;

    private final EventName eventName;
    private final EventDate eventDate;
    private final Index index;
    private final boolean isDeleteCountdown;
    private final int queryDays;

    /**
     * Constructor for CountdownCommand when user wants to switch to Countdown tab.
     */
    public CountdownCommand() {
        this.eventName = null;
        this.eventDate = null;
        this.index = null;
        this.isDeleteCountdown = false;
        this.queryDays = INVALID_QUERY_DAYS;
    }

    /**
     * Constructor for CountdownCommand when User wants to add an event.
     */
    public CountdownCommand(EventName eventName, EventDate eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.index = null;
        this.isDeleteCountdown = false;
        this.queryDays = INVALID_QUERY_DAYS;
    }

    /**
     * Constructor for CountdownCommand when user wants to delete an event,
     * or add an event from tasks list.
     */
    public CountdownCommand(Index index, boolean isDeleteCountdown) {
        this.eventName = null;
        this.eventDate = null;
        this.index = index;
        this.isDeleteCountdown = isDeleteCountdown;
        this.queryDays = INVALID_QUERY_DAYS;
    }

    /**
     * Constructor for CountdownCommand when user wants to list number of countdowns in {@code days}.
     */
    public CountdownCommand(int queryDays) {
        this.eventName = null;
        this.eventDate = null;
        this.index = null;
        this.isDeleteCountdown = false;
        this.queryDays = queryDays;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> eventList = model.getFilteredEventList();

        // no valid parameters, is a switch tab command
        if (isAllNull(eventName, eventDate, index) && queryDays == INVALID_QUERY_DAYS) {
            model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
            return CommandResult.createCountdownTabSwitchCommandResult(MESSAGE_SWITCH_TAB_SUCCESS);
        }

        // is a delete or add from task command
        if (isAllNull(eventName, eventDate) && queryDays == INVALID_QUERY_DAYS) {
            if (isDeleteCountdown) {
                return executeCountdownDelete(model, eventList);
            } else {
                return executeCountdownAddFromTask(model);
            }
        }

        // is a add new countdown command
        if (isAllNull(index) && queryDays == INVALID_QUERY_DAYS) {
            return executeCountdownAddNew(model);
        }

        // is a count events in days command
        if (isAllNull(eventName, eventDate, index) && queryDays != INVALID_QUERY_DAYS) {
            return executeCountEventsInDays(eventList);
        }

        throw new CommandException(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    private CommandResult executeCountEventsInDays(List<Event> eventList) {
        int numEventsInDays = 0;
        StringBuilder listEvents = new StringBuilder();
        for (Event event : eventList) {
            if (event.getDaysRemaining() <= queryDays && event.getDaysRemaining() >= 0) {
                numEventsInDays++;
                listEvents.append(String.format("%d. %s\n", numEventsInDays, event));
            }
        }
        return new CommandResult(String.format(MESSAGE_COUNT_EVENTS_IN_DAYS_SUCCESS,
                numEventsInDays, queryDays, listEvents.toString()), true);
    }

    private CommandResult executeCountdownAddFromTask(Model model) throws CommandException {
        List<Task> taskList = model.getFilteredPendingTaskList();
        assert index != null;
        if (index.getZeroBased() >= taskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToConvert = taskList.get(index.getZeroBased());

        Event eventToAdd;
        try {
            eventToAdd = taskToConvert.toEvent();
        } catch (TaskConversionException tce) {
            throw new CommandException(MESSAGE_FUTURE_CONSTRAINT);
        }
        if (model.hasEvent(eventToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.addEvent(eventToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_EVENT_SUCCESS, eventToAdd), true);
    }

    private CommandResult executeCountdownAddNew(Model model) throws CommandException {
        Event eventToAdd = new Event(eventName, eventDate);
        if (model.hasEvent(eventToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.addEvent(eventToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_EVENT_SUCCESS, eventToAdd), true);
    }

    private CommandResult executeCountdownDelete(Model model, List<Event> eventList) throws CommandException {
        assert index != null;
        if (index.getZeroBased() >= eventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }
        Event eventToDelete = eventList.get(index.getZeroBased());
        model.deleteEvent(eventToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete), true);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // short circuit if same object
            return true;
        } else if (!(other instanceof CountdownCommand)) {
            return false;
        } else {
            CountdownCommand countdownCommand = (CountdownCommand) other;
            return (eventDate == countdownCommand.eventDate || eventDate.equals(countdownCommand.eventDate))
                    && (eventName == countdownCommand.eventName || eventName.equals(countdownCommand.eventName))
                    && (index == countdownCommand.index || index.equals(countdownCommand.index))
                    && isDeleteCountdown == countdownCommand.isDeleteCountdown
                    && queryDays == countdownCommand.queryDays;
        }
    }

    private static final boolean isAllNull(Object... objects) {
        for (Object object : objects) {
            if (!isNull(object)) {
                return false;
            }
        }
        return true;
    }
}

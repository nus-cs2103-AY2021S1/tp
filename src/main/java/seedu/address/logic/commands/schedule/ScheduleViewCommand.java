package seedu.address.logic.commands.schedule;

import java.time.LocalDateTime;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.ScheduleViewMode;

public class ScheduleViewCommand extends ScheduleCommand {
    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_INVALID_VIEW_MODE = "invalid view mode!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "\n"
            + "mode/[weekly / daily]\n"
            + "date/[yyyy-mm-dd]\n"
            + "Example: event view mode/weekly date/2020-11-22";

    private final LocalDateTime viewDateTime;
    private final ScheduleViewMode viewMode;

    /**
     * Creates a command to view schedule.
     * @param scheduleViewMode mode to view either weekly or daily.
     * @param viewDateTime the date time to view.
     */
    public ScheduleViewCommand(ScheduleViewMode scheduleViewMode, LocalDateTime viewDateTime) {

        super();
        this.viewMode = scheduleViewMode;
        this.viewDateTime = viewDateTime;
    }

    /**
     * Execute and returns CommandResult for UI.
     * UI will then change according to the view mode and date.
     * @throws CommandException for invalid view modes or invalid date format
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (viewDateTime != null) {
            model.setScheduleViewDateTime(viewDateTime);
        }

        if (viewMode != null) {
            model.setScheduleViewMode(viewMode);
        }
        return new CommandResult(COMMAND_SUCCESS_MESSAGE, false, false, true, false);
    }


}

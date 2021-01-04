package seedu.homerce.logic.commands.schedule;

import java.util.Calendar;

import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.appointment.predicate.AppointmentWeekPredicate;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.ui.schedulepanel.SchedulePanel;

public class CustomWeekCommand extends AbstractWeekCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Navigate to appointment schedule. "
        + "Without parameters, this command will simply navigate to the schedule tab.\nIf provided with a date, "
        + "Homerce will show the schedule of the week containing that date.\n"
        + "Parameters: [dt/DATE]*\n"
        + "Example: " + COMMAND_WORD + " dt/25-10-2020";

    private static final String MESSAGE_LIST_SCHEDULE_SUCCESS = "Showing schedule of appointments. Week of ";
    private final Date dateToNavigate;

    public CustomWeekCommand(Date dateToNavigate) {
        this.dateToNavigate = dateToNavigate;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(java.sql.Date.valueOf(dateToNavigate.getLocalDate()));
        // Change state of current week in the Appointment Manager.
        model.setAppointmentManagerCalendar(calendar);
        // Create predicate to show only entries on the particular week.
        AppointmentWeekPredicate predicate = new AppointmentWeekPredicate(calendar);
        model.updateFilteredSchedule(predicate);
        return new CommandResult(
            MESSAGE_LIST_SCHEDULE_SUCCESS
                + predicate.toString(),
            SchedulePanel.TAB_NAME
        );
    }
}

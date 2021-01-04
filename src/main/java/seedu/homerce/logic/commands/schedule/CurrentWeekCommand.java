package seedu.homerce.logic.commands.schedule;

import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.schedulepanel.SchedulePanel;

public class CurrentWeekCommand extends AbstractWeekCommand {
    private static final String MESSAGE_CURRENT_WEEK_SUCCESS = "Showing schedule of appointments";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        model.refreshSchedule();
        return new CommandResult(MESSAGE_CURRENT_WEEK_SUCCESS, SchedulePanel.TAB_NAME);
    }
}

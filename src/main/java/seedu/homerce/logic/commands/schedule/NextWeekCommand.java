package seedu.homerce.logic.commands.schedule;

import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.ui.schedulepanel.SchedulePanel;

public class NextWeekCommand extends AbstractWeekCommand {
    public static final String COMMAND_WORD = "nextweek";
    private static final String MESSAGE_NEXT_PAGE_SUCCESS = "Navigated to next week.";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        model.nextSchedulePage();
        return new CommandResult(MESSAGE_NEXT_PAGE_SUCCESS, SchedulePanel.TAB_NAME);
    }
}

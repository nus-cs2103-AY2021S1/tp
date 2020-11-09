package tp.acecs2103.logic.commands;
import static java.util.Objects.requireNonNull;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.commons.util.AppUtil;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.task.WeekNumber;

public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";


    public HomeCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        WeekNumber currentWeek = AppUtil.getCurrentWeekNumber();
        model.listTasks(currentWeek);

        return new CommandResult(
                String.format(Messages.MESSAGE_WEEKLY_TASKS_LISTED, currentWeek.value) + "\n -->"
                        + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof HomeCommand)) {
            return false;
        } else {
            return true;
        }
    }
}

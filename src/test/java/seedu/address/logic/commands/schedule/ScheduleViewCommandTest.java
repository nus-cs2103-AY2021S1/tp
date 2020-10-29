package seedu.address.logic.commands.schedule;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.event.ScheduleViewMode;

public class ScheduleViewCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_validViewModeAndDate_success() {
        ScheduleViewMode validViewMode = ScheduleViewMode.WEEKLY;
        LocalDateTime validDateTime = LocalDateTime.parse("2020-12-03T10:15:30");

        ScheduleViewCommand scheduleViewCommand = new ScheduleViewCommand(validViewMode, validDateTime);

        Model expectedModel = new ModelManager();
        expectedModel.setScheduleViewDateTime(validDateTime);
        expectedModel.setScheduleViewMode(validViewMode);

        CommandTestUtil.assertCommandSuccess(scheduleViewCommand, model,
                ScheduleCommand.COMMAND_SUCCESS_MESSAGE, expectedModel);
    }

}

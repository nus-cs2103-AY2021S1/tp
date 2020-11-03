package seedu.address.testutil;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.model.module.Module;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class TutorialGroupUtil {
    /**
     * Returns an add command string for adding the {@code TutorialGroup}.
     */
    public static String getAddTutorialGroupCommand(TutorialGroup tutorialGroup) {
        String correct_argument = " tg/B014 day/MON start/15:00 end/17:00";
        return AddTutorialGroupCommand.COMMAND_WORD + " " + correct_argument;
    }
}

package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_ALL_QUESTS;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;

/**
 * Lists all quest deadlines.
 */
public class ViewQuestDeadlineCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed all current quest deadlines";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateQuestsList(PREDICATE_SHOW_ALL_QUESTS);

        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.Quest);
    }
}

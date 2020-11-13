package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.quest.Quest;

/**
 * Lists all ungraded missions.
 */
public class ViewUngradedQuestCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed all ungraded quests";

    public static final Predicate<Quest> PREDICATE_SHOW_UNGRADED_QUESTS = quest -> quest.getIsGraded() == false;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateQuestsList(PREDICATE_SHOW_UNGRADED_QUESTS);

        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.Quest);
    }
}

package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_ALL_MASTERY_CHECKS;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;

/**
 * Lists all mastery checks.
 */
public class ViewMasteryChecksCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed all mastery check sessions";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateMasteryChecksList(PREDICATE_SHOW_ALL_MASTERY_CHECKS);

        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.MasteryCheck);
    }
}

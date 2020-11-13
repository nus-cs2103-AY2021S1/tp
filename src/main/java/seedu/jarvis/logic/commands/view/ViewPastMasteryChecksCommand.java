package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_PAST_MASTERY_CHECKS;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;

/**
 * Lists only past mastery checks.
 */
public class ViewPastMasteryChecksCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed past mastery check sessions";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateMasteryChecksList(PREDICATE_SHOW_PAST_MASTERY_CHECKS);

        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.MasteryCheck);
    }
}

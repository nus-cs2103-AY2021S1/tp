package seedu.pivot.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.ListCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;

/**
 * Lists all cases in PIVOT.
 */
public class ListCaseCommand extends ListCommand {

    public static final String MESSAGE_LIST_CASE_SUCCESS = "Listed all default cases (unarchived)";
    private static final Logger logger = LogsCenter.getLogger(ListCaseCommand.class);

    @Override
    public CommandResult execute(Model model) {
        logger.info("Executing list case command");
        requireNonNull(model);

        assert(StateManager.atMainPage()) : "Program should be at main page";

        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);
        StateManager.setDefaultSection();

        return new CommandResult(MESSAGE_LIST_CASE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ListCaseCommand; // instanceof handles nulls
    }

}

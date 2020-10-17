package seedu.address.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.state.StateManager;
import seedu.address.model.Model;

/**
 * Lists all cases in PIVOT.
 */
public class ListCaseCommand extends ListCommand {

    public static final String MESSAGE_LIST_CASE_SUCCESS = "Listed all cases";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        assert(StateManager.atMainPage()) : "Program should be at main page";

        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);
        return new CommandResult(MESSAGE_LIST_CASE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ListCaseCommand; // instanceof handles nulls
    }

}

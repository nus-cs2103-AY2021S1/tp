package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROBLEMS;

import seedu.address.model.Model;
import seedu.address.ui.Mode;

/**
 * Lists all problems for viewing.
 */
public class ViewProblemCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "View problems";

    /**
     * Executes view command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult object
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredProblemList((problem -> false), Mode.NORMAL);
        model.updateFilteredProblemList(PREDICATE_SHOW_ALL_PROBLEMS, Mode.NORMAL);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

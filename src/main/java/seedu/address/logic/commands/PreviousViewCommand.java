package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALGROUPS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Navigates the user to the previous view (i.e. from Student View to Tutorial Group View).
 */
public class PreviousViewCommand extends Command {
    public static final String COMMAND_WORD = "prevView";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_VIEWING_MODULES_SUCCESS = "Viewing all modules";

    public static final String MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS = "Viewing all tutorial groups of: %1$s";

    public static final String MESSAGE_IN_MODULE_VIEW = "You are currently in the Module view. "
            + "You cannot go back any further.";

    public PreviousViewCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.updateFilteredTutorialGroupList(PREDICATE_SHOW_ALL_TUTORIALGROUPS);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        if (model.isInModuleView()) {
            return new CommandResult(String.format(MESSAGE_IN_MODULE_VIEW), false, false,
                    false, false, true);
        } else if (model.isInTutorialGroupView()) {
            model.setCurrentViewToModule();
            return new CommandResult(String.format(MESSAGE_VIEWING_MODULES_SUCCESS), false, false,
                    false, false, true);
        } else if (model.isInStudentView()) {
            model.setCurrentViewToTutorialGroup();
            return new CommandResult(String.format(MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS,
                    model.getCurrentModuleInView()), false, false, true,
                    false, false);
        } else {
            throw new CommandException("View not determined");
        }
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof PreviousViewCommand); // instanceof handles nulls
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialContainsKeywordsPredicate;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;

public class FindTutorialGroupCommand extends Command {

    public static final String COMMAND_WORD = "findTG";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Tutorial Groups whose Tutorial Group Id "
        + "contain any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " B014 B015";

    public static final String MESSAGE_NOT_IN_TUTORIAL_VIEW = "You are currently not in the Tutorial view. "
        + "Run listMod to go back to the module view.";
    public static final String MESSAGE_IN_MODULE_VIEW = "You are currently in Module View. "
        + "Use viewTG MOUDLE_INDEX to view the Tutorial Groups of the Module you want";

    private final TutorialContainsKeywordsPredicate predicate;

    public FindTutorialGroupCommand(TutorialContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isInModuleView()) {
            throw new CommandException(MESSAGE_IN_MODULE_VIEW);
        } else if (model.isInStudentView()) {
            throw new CommandException(MESSAGE_NOT_IN_TUTORIAL_VIEW);
        }

        model.updateFilteredTutorialGroupList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_TUTORIAL_GROUPS_LISTED_OVERVIEW,
                    model.getFilteredTutorialGroupList().size()),
                false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindTutorialGroupCommand // instanceof handles nulls
            && predicate.equals(((FindTutorialGroupCommand) other).predicate)); // state check
    }
}

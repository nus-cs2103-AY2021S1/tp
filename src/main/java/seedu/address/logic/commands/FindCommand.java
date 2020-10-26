package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all tasks in ScheDar whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_SUCCESS = "Finished searching. %1$d persons listed!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks in which contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103";

    private final TitleContainsKeywordsPredicate titlePredicate;

//    private final DescriptionContainsKeywordsPredicate descriptionPredicate;  //for upgrade

    public FindCommand(TitleContainsKeywordsPredicate titlePredicate){
        this.titlePredicate = titlePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(titlePredicate);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && titlePredicate.equals(((FindCommand) other).titlePredicate)); // state check
    }
}

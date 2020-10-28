package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.student.NameContainsKeywordsPredicate;
import seedu.resireg.storage.Storage;

/**
 * Finds and lists all students in ResiReg whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find-students";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Finds all students whose names contain any of the specified keywords (case-insensitive) and displays"
                    + " them as a list with index numbers.",
            "Parameters: KEYWORD [MORE_KEYWORDS]...\nExample: " + COMMAND_WORD + " alice bob charlie");

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}

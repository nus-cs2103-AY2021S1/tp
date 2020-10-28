package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all students in the current tutorial view whose names contain any of the argument keywords.
 * Keyword matching is not case sensitive.
 */
public class FindStudentCommand extends Command {

    public static final String COMMAND_WORD = "findStudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all students whose names contain any of the specified keywords"
            + " (not case sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " john alice";

    public static final String MESSAGE_WRONG_VIEW = "You are currently not in the Student view";

    private final NameContainsKeywordsPredicate predicate;

    public FindStudentCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInStudentView()) {
            throw new CommandException(MESSAGE_WRONG_VIEW);
        }

        model.updateFilteredStudentList(predicate);
        int numStudents = model.getFilteredStudentList().size();
        return new CommandResult(String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, numStudents));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStudentCommand // instanceof handles nulls
                && predicate.equals(((FindStudentCommand) other).predicate)); // state check
    }
}

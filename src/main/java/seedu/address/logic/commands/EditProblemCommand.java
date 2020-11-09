package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEVERITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROBLEMS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.problem.Description;
import seedu.address.model.problem.Problem;
import seedu.address.model.problem.Severity;
import seedu.address.ui.Mode;

/**
 * Edits the details of an existing problem report in the library.
 */
public class EditProblemCommand extends Command {

    public static final String COMMAND_WORD = "editpr";
    public static final String SUGGESTION = "editpr <index> s/<severity> d/<description>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits severity and description of the specific report"
            + " identified by the index number in the current report list. "
            + "Current severity and description will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SEVERITY + "SEVERITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SEVERITY + "high "
            + PREFIX_DESCRIPTION + "The chair at Seat 4C is broken";

    public static final String MESSAGE_EDIT_REPORT_SUCCESS = "Edited Report: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_REPORT = "This report already exists in the current record.";

    private final Index index;
    private final EditProblemDescriptor editProblemDescriptor;

    /**
     * @param index of the report in the filtered report list to edit
     * @param editProblemDescriptor details to edit the report with
     */
    public EditProblemCommand(Index index, EditProblemDescriptor editProblemDescriptor) {
        requireNonNull(index);
        requireNonNull(editProblemDescriptor);

        this.index = index;
        this.editProblemDescriptor = new EditProblemDescriptor(editProblemDescriptor);
    }

    /**
     * Executes edit problem command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     * @throws CommandException if invalid problem
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Problem> currentList = model.getFilteredProblemList();

        if (index.getZeroBased() >= currentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROBLEM_DISPLAYED_INDEX);
        }

        Problem problemToEdit = currentList.get(index.getZeroBased());
        Problem editedProblem = createEditedProblem(problemToEdit, editProblemDescriptor);

        if (!problemToEdit.isSameProblem(editedProblem) && model.hasProblem(editedProblem)) {
            throw new CommandException(MESSAGE_DUPLICATE_REPORT);
        }

        model.setProblem(problemToEdit, editedProblem);
        model.updateFilteredProblemList(PREDICATE_SHOW_ALL_PROBLEMS, Mode.NORMAL);
        return new CommandResult(String.format(MESSAGE_EDIT_REPORT_SUCCESS, editedProblem));
    }

    /**
     * Creates and returns a {@code Problem} with the details of {@code problemToEdit}
     * edited with {@code editProblemDescriptor}.
     */
    private static Problem createEditedProblem(Problem problemToEdit, EditProblemDescriptor editProblemDescriptor) {
        assert problemToEdit != null;

        Severity updatedSeverity = editProblemDescriptor.getSeverity().orElse(problemToEdit.getSeverity());
        Description updatedDescription = editProblemDescriptor.getDescription().orElse(problemToEdit.getDescription());

        return new Problem(updatedSeverity, updatedDescription);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditProblemCommand)) {
            return false;
        }

        // state check
        EditProblemCommand e = (EditProblemCommand) other;
        return index.equals(e.index)
                && editProblemDescriptor.equals(e.editProblemDescriptor);
    }

    /**
     * Stores the details to edit the problem with. Each non-empty field value will replace the
     * corresponding field value of the book.
     */
    public static class EditProblemDescriptor {
        private Severity severity;
        private Description description;

        public EditProblemDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code categories} is used internally.
         */
        public EditProblemDescriptor(EditProblemDescriptor toCopy) {
            setSeverity(toCopy.severity);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(severity, description);
        }

        public void setSeverity(Severity severity) {
            this.severity = severity;
        }

        public Optional<Severity> getSeverity() {
            return Optional.ofNullable(severity);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }
            // instanceof handles nulls
            if (!(other instanceof EditProblemDescriptor)) {
                return false;
            }
            // state check
            EditProblemDescriptor e = (EditProblemDescriptor) other;

            return getSeverity().equals(e.getSeverity())
                    && getDescription().equals(e.getDescription());

        }
    }
}

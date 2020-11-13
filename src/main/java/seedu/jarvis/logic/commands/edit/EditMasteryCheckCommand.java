package seedu.jarvis.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_ALL_MASTERY_CHECKS;

import java.util.List;
import java.util.Optional;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.commons.util.CollectionUtil;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.masterycheck.MasteryCheck;

public class EditMasteryCheckCommand extends EditCommand {

    public static final String MESSAGE_EDIT_MASTERY_CHECK_SUCCESS = "Edited Mastery Check: %1$s";
    public static final String MESSAGE_DUPLICATE_MASTERY_CHECK = "There may not be any duplicates.";
    public static final String NO_SCORE_PARAMETER = "To edit the score of the mastery check, "
            + "must include s/SCORE where SCORE is 1 (pass) or 0 (fail). e.g. edit -mc 1 s/0";
    public static final String ONE_OR_MORE_PARAMS_MISSING = "One or more parameters are missing.\n"
            + "Please check if you have all the parameters required (INDEX & SCORE) for editing a mastery check, "
            + "i.e. edit -mc INDEX s/SCORE";



    private final Index index;
    private final EditMasteryCheckCommand.EditMasteryCheckDescriptor editMasteryCheckDescriptor;

    /**
     * @param index of the mastery check in the filtered mastery check list to edit
     * @param editMasteryCheckDescriptor details to edit the mastery check with
     */
    public EditMasteryCheckCommand(Index index, EditMasteryCheckCommand.EditMasteryCheckDescriptor
            editMasteryCheckDescriptor) {
        requireNonNull(index);
        requireNonNull(editMasteryCheckDescriptor);

        this.index = index;
        this.editMasteryCheckDescriptor =
                new EditMasteryCheckCommand.EditMasteryCheckDescriptor(editMasteryCheckDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<MasteryCheck> lastShownList = model.getFilteredMasteryChecksList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MASTERY_CHECK_DISPLAYED_INDEX);
        }

        MasteryCheck masteryCheckToEdit = lastShownList.get(index.getZeroBased());
        MasteryCheck editedMasteryCheck = createEditedMasteryCheck(masteryCheckToEdit, editMasteryCheckDescriptor);

        if (!masteryCheckToEdit.isSameConsultation(editedMasteryCheck) && model.hasMasteryCheck(editedMasteryCheck)) {
            throw new CommandException(MESSAGE_DUPLICATE_MASTERY_CHECK);
        }

        model.setMasteryCheck(masteryCheckToEdit, editedMasteryCheck);
        model.updateFilteredMasteryCheckList(PREDICATE_SHOW_ALL_MASTERY_CHECKS);
        return new CommandResult(String.format(MESSAGE_EDIT_MASTERY_CHECK_SUCCESS, editedMasteryCheck),
                CommandTargetFeature.MasteryCheck);
    }

    /**
     * Creates and returns a {@code MasteryCheck} with the details of {@code masteryCheckToEdit}
     * edited with {@code editMasteryCheckDescriptor}.
     */
    private static MasteryCheck createEditedMasteryCheck(MasteryCheck masteryCheckToEdit,
        EditMasteryCheckCommand.EditMasteryCheckDescriptor editMasteryCheckDescriptor) {
        assert masteryCheckToEdit != null;

        Boolean hasPassed = editMasteryCheckDescriptor.hasPassed().orElse(masteryCheckToEdit.hasPassed());

        if (hasPassed) {
            return MasteryCheck.createFullMarkMC(masteryCheckToEdit.getStudentName(),
                    masteryCheckToEdit.getDateAndTime());
        } else {
            return MasteryCheck.createZeroMarkMC(masteryCheckToEdit.getStudentName(),
                    masteryCheckToEdit.getDateAndTime());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMasteryCheckCommand)) {
            return false;
        }

        // state check
        EditMasteryCheckCommand e = (EditMasteryCheckCommand) other;
        boolean b1 = index.equals(e.index);
        boolean b2 = editMasteryCheckDescriptor.equals(e.editMasteryCheckDescriptor);
        return b1 && b2;
    }

    /**
     * Stores the details to edit the mastery check with. Each non-empty field value will replace the
     * corresponding field value of the mastery check.
     */
    public static class EditMasteryCheckDescriptor {
        private boolean hasPassed;

        public EditMasteryCheckDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code boolean} is used internally.
         */
        public EditMasteryCheckDescriptor(EditMasteryCheckDescriptor toCopy) {
            setPassed(toCopy.hasPassed);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(hasPassed);
        }

        public void setPassed(Boolean hasPassed) {
            this.hasPassed = hasPassed;
        }

        public Optional<Boolean> hasPassed() {
            return Optional.ofNullable(hasPassed);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMasteryCheckCommand.EditMasteryCheckDescriptor)) {
                return false;
            }

            // state check
            EditMasteryCheckCommand.EditMasteryCheckDescriptor e = (EditMasteryCheckCommand.EditMasteryCheckDescriptor)
                    other;

            return hasPassed().get() == e.hasPassed().get();
        }

    }
}

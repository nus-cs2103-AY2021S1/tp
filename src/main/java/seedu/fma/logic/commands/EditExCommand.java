package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_C;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_E;
import static seedu.fma.model.Model.PREDICATE_SHOW_ALL_LOGS;

import java.util.List;
import java.util.Optional;

import seedu.fma.commons.core.Messages;
import seedu.fma.commons.core.index.Index;
import seedu.fma.commons.util.CollectionUtil;
import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.model.Model;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;

/**
 *  Edits the details of an existing exercise in the log book.
 */
public class EditExCommand extends Command {

    public static final String COMMAND_WORD = "editex";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the exercise identified "
            + "by the index number used in the displayed log list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_E + "EXERCISE] "
            + "[" + PREFIX_C + "CALORIES] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_C + "3000";

    public static final String MESSAGE_EDIT_EXERCISE_SUCCESS = "Edited exercise: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the log book.";

    private final Index index;
    private final EditExCommand.EditExDescriptor editExDescriptor;

    public EditExCommand(Index index, EditExDescriptor editExDescriptor) {
        requireNonNull(index);
        requireNonNull(editExDescriptor);

        this.index = index;
        this.editExDescriptor = new EditExCommand.EditExDescriptor(editExDescriptor);
    }

    /**
     * Stores the details to edit the exercise with. Each non-empty field value will replace the
     * corresponding field value of the exercise.
     */
    public static class EditExDescriptor {
        private Name exerciseName;
        private Calories caloriesPerRep;

        /**
         * Empty constructor
         */
        public EditExDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExDescriptor(EditExCommand.EditExDescriptor toCopy) {
            setExerciseName(toCopy.exerciseName);
            setCaloriesPerRep(toCopy.caloriesPerRep);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(exerciseName, caloriesPerRep);
        }

        /**
         * Name setter
         */
        public void setExerciseName(Name exerciseName) {
            this.exerciseName = exerciseName;
        }

        /**
         * Calories setter
         */
        public void setCaloriesPerRep(Calories caloriesPerRep) {
            this.caloriesPerRep = caloriesPerRep;
        }

        /**
         * ExerciseName getter. Can return null if no name is specified.
         */
        public Optional<Name> getExerciseName() {
            return Optional.ofNullable(this.exerciseName);
        }

        /**
         * CaloriesPerRep getter. Can return null if no calories is specified.
         */
        public Optional<Calories> getCaloriesPerRep() {
            return Optional.ofNullable(this.caloriesPerRep);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExCommand.EditExDescriptor)) {
                return false;
            }

            // state check
            EditExCommand.EditExDescriptor e = (EditExCommand.EditExDescriptor) other;

            return getExerciseName().equals(e.getExerciseName())
                    && getCaloriesPerRep().equals(e.getCaloriesPerRep());
        }
    }

    private static Exercise createEditedExercise(Exercise exerciseToEdit, EditExDescriptor editExDescriptor) {
        assert exerciseToEdit != null;

        Name updatedExerciseName = editExDescriptor.getExerciseName().orElse(exerciseToEdit.getName());
        Calories updatedCaloriesPerRep = editExDescriptor.getCaloriesPerRep().orElse(exerciseToEdit.getCaloriesPerRep());

        return new Exercise(updatedExerciseName, updatedCaloriesPerRep);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Exercise> lastShownList = model.getFilteredExerciseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LOG_DISPLAYED_INDEX);
        }

        Exercise exerciseToEdit = lastShownList.get(index.getZeroBased());
        Exercise editedExercise = createEditedExercise(exerciseToEdit, editExDescriptor);

        if (!exerciseToEdit.isSameExercise(editedExercise) && model.hasExercise(editedExercise)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.setExercise(exerciseToEdit, editedExercise);
        model.updateFilteredLogList(PREDICATE_SHOW_ALL_LOGS);
        return new CommandResult(String.format(MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditExCommand)) {
            return false;
        }

        // state check
        EditExCommand e = (EditExCommand) other;
        return index.equals(e.index)
                && editExDescriptor.equals(e.editExDescriptor);
    }
}

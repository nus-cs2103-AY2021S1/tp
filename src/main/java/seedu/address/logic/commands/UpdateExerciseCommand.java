package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.ExerciseModel.PREDICATE_SHOW_ALL_EXERCISE;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Muscle;
import seedu.address.model.exercise.Name;


/**
 * Edits the details of an existing exercise in the exercise book.
 */
public class UpdateExerciseCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the exercise "
            + "by the index number used in the displayed exercise list.\n "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "EXERCISE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_CALORIES + "CALORIES] "
            + "[" + PREFIX_MUSCLES + "MUSCLES_WORKED]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Push up "
            + PREFIX_DESCRIPTION + "30 "
            + PREFIX_DATE + "09-07-2020 "
            + PREFIX_CALORIES + "260 "
            + PREFIX_MUSCLES + "chest,arm";

    public static final String MESSAGE_EDIT_EXERCISE_SUCCESS = "Edited Exercise: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the exercise book.";

    private final Index index;
    private final EditExerciseDescriptor editExerciseDescriptor;

    /**
     * @param index                  of the exercise in the filtered exercise list to edit
     * @param editExerciseDescriptor details to edit the exercise with
     */
    public UpdateExerciseCommand(Index index, EditExerciseDescriptor editExerciseDescriptor) {
        requireNonNull(index);
        requireNonNull(editExerciseDescriptor);

        this.index = index;
        this.editExerciseDescriptor = new UpdateExerciseCommand.EditExerciseDescriptor(editExerciseDescriptor);
    }

    @Override
    public CommandResult execute(ExerciseModel model) throws CommandException {
        requireNonNull(model);
        List<Exercise> lastShownList = model.getFilteredExerciseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
        }

        Exercise exerciseToEdit = lastShownList.get(index.getZeroBased());
        Exercise editedExercise = createEditedExercise(exerciseToEdit, editExerciseDescriptor);

        if (!exerciseToEdit.isSameExercise(editedExercise) && model.hasExercise(editedExercise)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.setExercise(exerciseToEdit, editedExercise);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISE);
        return new CommandResult(String.format(MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise));
    }

    /**
     * Creates and returns a {@code Exercise} with the details of {@code exerciseToEdit}
     * edited with {@code editExerciseDescriptor}.
     */
    private static Exercise createEditedExercise(Exercise exerciseToEdit,
                                                 UpdateExerciseCommand.EditExerciseDescriptor editExerciseDescriptor) {
        assert exerciseToEdit != null;

        Name updatedName = editExerciseDescriptor.getName().orElse(exerciseToEdit.getName());
        Description updatedDescription = editExerciseDescriptor.getDescription()
                .orElse(exerciseToEdit.getDescription());
        Date updatedDate = editExerciseDescriptor.getDate().orElse(exerciseToEdit.getDate());
        Calories updatedCalories = editExerciseDescriptor.getCalories().orElse(exerciseToEdit.getCalories());
        List<Muscle> updatedMusclesWorked = editExerciseDescriptor.getMusclesWorked()
                                                .orElse(exerciseToEdit.getMusclesWorked());

        return new Exercise(updatedName, updatedDescription, updatedDate, updatedCalories, updatedMusclesWorked);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateExerciseCommand)) {
            return false;
        }

        // state check
        UpdateExerciseCommand e = (UpdateExerciseCommand) other;
        return index.equals(e.index)
                && editExerciseDescriptor.equals(e.editExerciseDescriptor);
    }

    /**
     * Stores the details to edit the exercise with. Each non-empty field value will replace the
     * corresponding field value of the exercise.
     */
    public static class EditExerciseDescriptor {
        // identity field
        private Name name;
        private Date date;

        // data field
        // Functional dependencies: see Exercise class
        private Description description;
        private Calories calories;
        private List<Muscle> musclesWorked;

        public EditExerciseDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExerciseDescriptor(UpdateExerciseCommand.EditExerciseDescriptor toCopy) {
            setName(toCopy.name);
            setDate(toCopy.date);
            setDescription(toCopy.description);
            setCalories(toCopy.calories);
            setMusclesWorked(toCopy.musclesWorked);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, date, description, calories, musclesWorked);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setCalories(Calories calories) {
            this.calories = calories;
        }

        public Optional<Calories> getCalories() {
            return Optional.ofNullable(calories);
        }

        public void setMusclesWorked(List<Muscle> musclesWorked) {
            this.musclesWorked = musclesWorked;
        }

        public Optional<List<Muscle>> getMusclesWorked() {
            return Optional.ofNullable(musclesWorked);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateExerciseCommand.EditExerciseDescriptor)) {
                return false;
            }

            // state check
            UpdateExerciseCommand.EditExerciseDescriptor e = (UpdateExerciseCommand.EditExerciseDescriptor) other;

            return getName().equals(e.getName())
                    && getDate().equals(e.getDate())
                    && getDescription().equals(e.getDescription());
        }
    }
}

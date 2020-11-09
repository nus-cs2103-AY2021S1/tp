package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.TheMostRecentDatePredicate;

public class RecallCommand extends Command {

    public static final String COMMAND_WORD = "recall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the most recent exercise "
            + "with the specified name (exercises with future dates will not be listed)\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " push up";

    public static final String MESSAGE_SUCCESS = "%1$d most recent exercise found";

    private final Name name;
    private ObservableList<Exercise> filteredExercises;
    private Date date;

    /**
     * Creates an RecallCommand to recall exercise with the specified name {@code Name}
     */
    public RecallCommand(String name) {
        this.name = new Name(name);
        date = null;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        java.util.Date currentDay = new java.util.Date();
        filteredExercises = model.getFilteredExerciseList();
        for (Exercise exercise : filteredExercises) {
            if (name.fullName.equalsIgnoreCase(exercise.getName().fullName)
                && (!currentDay.before(exercise.getDate().getActualDate()))) {
                if (date == null) {
                    date = exercise.getDate();
                } else {
                    date = date.isBefore(exercise.getDate()) ? exercise.getDate() : date;
                }
            }
        }
        model.updateFilteredExerciseList(new TheMostRecentDatePredicate(name, date));
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecallCommand // instanceof handles nulls
                && name.equals(((RecallCommand) other).name)); // state check
    }

}


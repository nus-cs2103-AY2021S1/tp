package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMP;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Weight;
import seedu.address.model.goal.Goal;

public class AddExerciseFromTemplate extends CommandForExercise {

    public static final String COMMAND_WORD = "addt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to Calo using template. "
            + "Parameters: "
            + PREFIX_TEMP + "TEMPLATE NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_CALORIES + "CALORIES]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TEMP + "PUSH_UP "
            + PREFIX_DESCRIPTION + "half an hour "
            + PREFIX_DATE + "31-12-2020 "
            + PREFIX_CALORIES + "100";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s\n";
    public static final String MESSAGE_WEIGHT = "You have burnt %.5s kg\n";
    public static final String MESSAGE_GOAL = "Congratulations! Now you only have %s more calories to burn on %s!";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the exercise book";

    private final Exercise toAdd;
    private final Weight burntWeight;

    /**
     * Adds exercise from template
     * @param toAdd the exercise to be added
     */
    public AddExerciseFromTemplate(Exercise toAdd) {
        this.toAdd = toAdd;
        this.burntWeight = new Weight(toAdd.getCalories());
    }

    @Override
    public CommandResult execute(ExerciseModel model) throws CommandException {
        requireNonNull(model);
        if (model.hasExercise(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        Optional<Goal> optionalGoal = model.addExercise(toAdd);
        if (optionalGoal.isPresent()) {
            Goal goal = optionalGoal.get();
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd)
                    + String.format(MESSAGE_WEIGHT, burntWeight.getWeight())
                    + String.format(MESSAGE_GOAL, goal.getCalories(), goal.getDate()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd)
                + String.format(MESSAGE_WEIGHT, burntWeight.toString()));
    }
}

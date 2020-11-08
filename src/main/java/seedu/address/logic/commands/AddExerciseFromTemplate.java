package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEMP;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Weight;

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

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd)
                + String.format(MESSAGE_WEIGHT, burntWeight));
    }
}

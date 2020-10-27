package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.goal.Goal;


public class GoalCommand extends CommandForExercise{
    public static final String COMMAND_WORD = "goal";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a goal to Calo. "
            + "Parameters: "
            + PREFIX_CALORIES + "CALORIES"
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CALORIES + "100 kcal "
            + PREFIX_DATE + "31-12-2020 ";

    public static final String MESSAGE_SUCCESS = "New goal added: %1$s";

    public static final String MESSAGE_DUPLICATE_GOAL = "This goal already exists in the Exercise Book";

    private final Goal goal;
    
    public GoalCommand(Goal goal) {
        requireNonNull(goal);
        this.goal = goal;
    }

    @Override
    public CommandResult execute(ExerciseModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasGoal(goal)) {
            throw new CommandException(MESSAGE_DUPLICATE_GOAL);
        }

        model.addGoal(goal);
        return new CommandResult(String.format(MESSAGE_SUCCESS, goal));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoalCommand // instanceof handles nulls
                && goal.equals(((GoalCommand) other).goal)); // state check
    }
}

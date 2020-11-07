package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calorie.Calorie;

/**
 * Adds a DailyCalorie to fitNUS.
 */
public class CalorieAddCommand extends Command {

    public static final String COMMAND_WORD = "calorie_add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the user's caloric intake to today's sum. "
            + "Parameters: "
            + PREFIX_CALORIE + "CALORIE "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CALORIE + "1000 ";

    public static final String MESSAGE_SUCCESS = "Calories successfully added: %1$s" + "\nToday's calories are: ";
    public static final String MESSAGE_FAILURE = "The calories that you are adding is too large! fitNUS is unable to "
            + "calculate such large values.";

    private final Calorie toAdd;

    /**
     * Creates an CalorieAddCommand to add the specified {@code DailyCalorie}
     */
    public CalorieAddCommand(Calorie calorie) {
        toAdd = calorie;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int todayCalories = model.getCalories();

        if (todayCalories > Integer.MAX_VALUE - toAdd.getCalorie()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.addCalories(toAdd);
        todayCalories += toAdd.getCalorie();
        return new CommandResult(String.format(MESSAGE_SUCCESS + todayCalories, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalorieAddCommand // instanceof handles nulls
                && toAdd.equals(((CalorieAddCommand) other).toAdd));
    }
}


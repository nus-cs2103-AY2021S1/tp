package seedu.address.logic.commands.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calorie.Calorie;

/**
 * Adds a Calorie to fitNUS for the particular day.
 */
public class CalorieAddCommand extends Command {

    public static final String COMMAND_WORD = "calorie_add";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Adds the user's caloric intake to today's total calorie count. "
            + "Parameters: "
            + PREFIX_CALORIE + "CALORIE "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CALORIE + "1000 ";

    public static final String MESSAGE_SUCCESS = "Calories successfully added: %1$s" + "\nToday's calories are: ";
    public static final String MESSAGE_FAILURE = "Daily calorie intake is too large, please input an accurate figure.";

    /**
     * The Calorie to be added for that particular day.
     */
    private final Calorie toAdd;

    /**
     * Creates an CalorieAddCommand to add the specified {@code Calorie}
     */
    public CalorieAddCommand(Calorie calorie) {
        toAdd = calorie;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int todayCalories = model.getCalories();

        if (todayCalories + toAdd.getCalorie() > 15000) {
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


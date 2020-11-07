package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.Food;

/**
 * Adds a food item to the Food inventory.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_EXPIRY_DATE + "EXPIRY DATE "
            + PREFIX_QUANTITY + "QUANTITY "
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "[" + PREFIX_TAG + "TAG]...\n  "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Canned Tuna "
            + PREFIX_PRIORITY + "HIGH "
            + PREFIX_EXPIRY_DATE + "1-1-2020 "
            + PREFIX_QUANTITY + "100 g "
            + PREFIX_TAG + "frozen";

    public static final String MESSAGE_SUCCESS = "New food item added!\n  %1$s";
    public static final String MESSAGE_DUPLICATE_FOOD = "This food item already exists in the food inventory."
            + "\nDuplicates are not allowed in the food inventory."
            + "\n(Please change the description, expiry date or tags)";

    private final Food toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Food}
     */
    public AddCommand(Food food) {
        requireNonNull(food);
        toAdd = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFood(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOOD);
        }

        model.addFood(toAdd);
        model.commitFoodInventory();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}

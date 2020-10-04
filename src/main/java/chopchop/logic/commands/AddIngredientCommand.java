package chopchop.logic.commands;

import static chopchop.logic.parser.CliSyntax.PREFIX_EXPIRY;
import static chopchop.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import chopchop.model.ingredient.Ingredient;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;

public class AddIngredientCommand extends AddCommand {

    public static final String COMMAND_WORD = "add ingredient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the manager. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_QUANTITY + "QUANTITY "
        + PREFIX_EXPIRY + "EXPIRY "
        + "\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Chili "
        + PREFIX_QUANTITY + "3"
        + PREFIX_EXPIRY + "2020-10-05";

    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in the ingredient book";


    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddIngredientCommand(Ingredient ind) {
        super(ind);
        requireNonNull(ind);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasIngredient((Ingredient) toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
        }

        model.addIngredient((Ingredient) toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddIngredientCommand // instanceof handles nulls
            && toAdd.equals(((AddIngredientCommand) other).toAdd));
    }

}

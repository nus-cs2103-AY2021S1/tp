package chopchop.logic.commands;

import static chopchop.util.Strings.ARG_EXPIRY;
import static chopchop.util.Strings.ARG_QUANTITY;
import static java.util.Objects.requireNonNull;

import java.util.Optional;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.History;
import chopchop.model.Model;
import chopchop.model.exceptions.IncompatibleIngredientsException;
import chopchop.model.ingredient.Ingredient;

/**
 * Adds an ingredient to the ingredient book.
 */
public class AddIngredientCommand extends Command {
    public static final String COMMAND_WORD = "add ingredient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the ingredient book. "
            + "Parameters: "
            + "NAME "
            + ARG_QUANTITY + " QUANTITY "
            + ARG_EXPIRY + " EXPIRY\n"
            + "Example: " + COMMAND_WORD
            + " Chili "
            + ARG_QUANTITY + " 3 "
            + ARG_EXPIRY + " 2020-10-05";

    public static final String MESSAGE_ADD_INGREDIENT_SUCCESS = "Ingredient added: %s";
    public static final String MESSAGE_COMBINE_INGREDIENT_SUCCESS = "Ingredient updated: %s";

    private final Ingredient ingredient;

    /**
     * Creates an AddIngredientCommand to add the specified {@code Ingredient}
     */
    public AddIngredientCommand(Ingredient ingredient) {
        requireNonNull(ingredient);
        this.ingredient = ingredient;
    }

    @Override
    public CommandResult execute(Model model, History history) throws CommandException {
        requireNonNull(model);
        Optional<Ingredient> existingIngredientOptional = model.findIngredientWithName(this.ingredient.getName());

        if (existingIngredientOptional.isPresent()) {
            Ingredient existingIngredient = existingIngredientOptional.get();

            try {
                Ingredient combinedIngredient = existingIngredient.combine(this.ingredient);
                model.setIngredient(existingIngredient, combinedIngredient);

                return new CommandResult(String.format(MESSAGE_COMBINE_INGREDIENT_SUCCESS, combinedIngredient));
            } catch (IncompatibleIngredientsException e) {
                throw new CommandException(e.toString());
            }
        } else {
            model.addIngredient(this.ingredient);
            return new CommandResult(String.format(MESSAGE_ADD_INGREDIENT_SUCCESS, this.ingredient));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddIngredientCommand
                && this.ingredient.equals(((AddIngredientCommand) other).ingredient));
    }

    @Override
    public String toString() {
        return String.format("AddIngredientCommand: %s", this.ingredient);
    }
}

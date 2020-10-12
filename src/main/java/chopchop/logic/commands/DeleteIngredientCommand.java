package chopchop.logic.commands;

import chopchop.model.Model;
import chopchop.model.ingredient.Ingredient;

import chopchop.commons.core.Messages;

import chopchop.logic.parser.ItemReference;
import chopchop.logic.commands.exceptions.CommandException;

import static java.util.Objects.requireNonNull;

/**
 * Deletes an ingredient identified using it's displayed index from the ingredient book.
 */
public class DeleteIngredientCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = "delete ingredient"
            + ": Deletes the ingredient identified by the index number used in the displayed recipe list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + "delete recipe" + " 1";

    public static final String MESSAGE_DELETE_INGREDIENT_SUCCESS = "Deleted Ingredient: %1$s";

    public DeleteIngredientCommand(ItemReference item) {
        super(item);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Ingredient ingredientToDelete = null;

        if (this.item.isIndexed()) {
            var lastShownList = model.getFilteredIngredientList();

            if (this.item.getZeroIndex() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
            }

            ingredientToDelete = lastShownList.get(this.item.getZeroIndex());
        } else {

            ingredientToDelete = model
                .findIngredientWithName(this.item.getName())
                .orElseThrow(() -> new CommandException(String.format("no ingredient named '%s'",
                    this.item.getName()))
                );
        }

        model.deleteIngredient(ingredientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INGREDIENT_SUCCESS, ingredientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteIngredientCommand // instanceof handles nulls
                && this.item.equals(((DeleteIngredientCommand) other).item)); // state check
    }
}

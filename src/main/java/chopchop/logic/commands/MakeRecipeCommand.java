package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import chopchop.commons.core.Messages;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;
import chopchop.model.exceptions.IncompatibleIngredientsException;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientReference;
import chopchop.model.recipe.Recipe;
import chopchop.util.Pair;

/**
 * Makes a dish according to the recipe identified by the index number or name used in the displayed recipe list,
 * removing the ingredients used.
 */
public class MakeRecipeCommand extends Command {
    public static final String COMMAND_WORD = "make recipe";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Makes a dish according to the recipe identified by the index number or name used in the displayed "
            + "recipe list, removing the ingredients used.\n"
            + "Parameters: INDEX (must be a positive integer) / NAME\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MAKE_RECIPE_SUCCESS = "Recipe made: %s";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "No recipe named '%s'";
    public static final String MESSAGE_INGREDIENT_NOT_FOUND = "No ingredient named '%s'";

    private final ItemReference item;
    // (Original ingredient, (Consumed ingredient, Remaining ingredient))
    private List<Pair<Ingredient, Pair<Ingredient, Ingredient>>> ingredients;

    /**
     * Constructs a command that makes the given recipe item.
     */
    public MakeRecipeCommand(ItemReference item) {
        requireNonNull(item);
        this.item = item;
        this.ingredients = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Recipe recipe;

        if (this.item.isIndexed()) {
            List<Recipe> lastShownList = model.getFilteredRecipeList();

            if (this.item.getZeroIndex() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
            }

            recipe = lastShownList.get(this.item.getZeroIndex());
        } else {
            recipe = model
                    .findRecipeWithName(this.item.getName())
                    .orElseThrow(() -> new CommandException(String.format(MESSAGE_RECIPE_NOT_FOUND,
                            this.item.getName())));
        }

        for (IngredientReference ingredientRef : recipe.getIngredients()) {
            Ingredient ingredient = model
                    .findIngredientWithName(ingredientRef.getName())
                    .orElseThrow(() -> new CommandException(String.format(MESSAGE_INGREDIENT_NOT_FOUND,
                            ingredientRef.getName())));

            try {
                this.ingredients.add(new Pair<>(ingredient, ingredient.split(ingredientRef.getQuantity())));
            } catch (IncompatibleIngredientsException | IllegalArgumentException e) {
                throw new CommandException(e.getMessage());
            }
        }

        for (Pair<Ingredient, Pair<Ingredient, Ingredient>> ingredient : this.ingredients) {
            if (ingredient.snd().snd().getIngredientSets().isEmpty()) {
                model.deleteIngredient(ingredient.fst());
            } else {
                model.setIngredient(ingredient.fst(), ingredient.snd().snd());
            }
        }

        return new CommandResult(String.format(MESSAGE_MAKE_RECIPE_SUCCESS, recipe));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MakeRecipeCommand
                && this.item.equals(((MakeRecipeCommand) other).item)
                && this.ingredients.equals(((MakeRecipeCommand) other).ingredients));
    }
}

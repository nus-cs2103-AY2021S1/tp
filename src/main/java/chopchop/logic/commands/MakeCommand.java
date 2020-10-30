package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import chopchop.commons.exceptions.IllegalValueException;
import chopchop.commons.util.Pair;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;
import chopchop.model.exceptions.IncompatibleIngredientsException;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;

/**
 * Makes a dish according to the recipe identified by the index number or name used in the displayed recipe list,
 * removing the ingredients used.
 */
public class MakeCommand extends Command implements Undoable {

    private final ItemReference item;
    private Recipe recipe;
    private List<Pair<Ingredient, Ingredient>> ingredients;

    /**
     * Constructs a command that makes the given recipe item.
     */
    public MakeCommand(ItemReference item) {
        requireNonNull(item);
        this.item = item;
        this.ingredients = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager)
        throws CommandException {
        requireNonNull(model);

        var res = resolveRecipeReference(this.item, model);
        if (res.isError()) {
            return CommandResult.error(res.getError());
        }

        this.recipe = res.getValue();

        for (var ingredientRef : this.recipe.getIngredients()) {

            var find = model.findIngredientWithName(ingredientRef.getName());
            if (find.isEmpty()) {
                return CommandResult.error("Missing ingredient '%s' (not found)", ingredientRef.getName());
            }

            var ingredient = find.get();

            try {
                this.ingredients.add(new Pair<>(ingredient, ingredient.split(ingredientRef.getQuantity()).snd()));
                model.addIngredientUsage(ingredientRef);

            } catch (IncompatibleIngredientsException | IllegalValueException e) {
                return CommandResult.error("Could not make recipe '%s' (caused by ingredient '%s'): %s",
                    this.recipe.getName(), ingredient.getName(), e.getMessage());
            }
        }

        for (var ingredient : this.ingredients) {
            if (ingredient.snd().getIngredientSets().isEmpty()) {
                model.deleteIngredient(ingredient.fst());
            } else {
                model.setIngredient(ingredient.fst(), ingredient.snd());
            }

        }
        model.addRecipeUsage(this.recipe);
        return CommandResult.message("Made recipe '%s'", this.recipe.getName());
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);
        for (var ingredient : this.ingredients) {
            if (ingredient.snd().getIngredientSets().isEmpty()) {
                model.addIngredient(ingredient.fst());
            } else {
                model.setIngredient(ingredient.snd(), ingredient.fst());
            }
        }

        for (var ingredientRef : this.recipe.getIngredients()) {
            model.removeIngredientUsage(ingredientRef);
        }

        this.ingredients.clear();
        model.removeRecipeUsage(this.recipe);
        return CommandResult.message("Undo: unmade recipe '%s'", this.recipe.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MakeCommand
                && this.item.equals(((MakeCommand) other).item)
                && this.ingredients.equals(((MakeCommand) other).ingredients));
    }

    @Override
    public String toString() {
        return String.format("MakeCommand(%s)", this.item);
    }

    public static String getCommandString() {
        return "make";
    }

    public static String getCommandHelp() {
        return "Makes the given recipe";
    }
}

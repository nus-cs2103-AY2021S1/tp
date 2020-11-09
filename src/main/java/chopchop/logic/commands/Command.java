// Command.java

package chopchop.logic.commands;

import chopchop.commons.util.Result;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 * In addition to the instance methods described here, all command classes should
 * contain the following STATIC methods:
 *
 * {@code String getCommandString()}    -- the command string (eg. "add recipe")
 * {@code String getCommandHelp()}      -- a brief description of what the command does
 *
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param historyManager {@code History} which the command should record to.
     * @return feedback message of the operation result for display
     */
    public abstract CommandResult execute(Model model, HistoryManager historyManager);

    /**
     * Resolves the ingredient reference, or returns an error message.
     *
     * @param ref   the reference to resolve
     * @param model the model to use
     * @return      the found ingredient, or an error message
     */
    public Result<Ingredient> resolveIngredientReference(ItemReference ref, Model model) {

        if (ref.isIndexed()) {
            var lastShownList = model.getFilteredIngredientList();

            if (ref.getZeroIndex() >= lastShownList.size()) {
                var between = lastShownList.isEmpty() ? "there are no ingredients"
                    : String.format("should be between 1 and %d", lastShownList.size());
                return Result.error("Ingredient index '%d' is out of range (%s)", ref.getOneIndex(), between);
            }

            return Result.of(lastShownList.get(ref.getZeroIndex()));

        } else {

            return Result.ofOptional(model.findIngredientWithName(ref.getName()),
                String.format("No ingredient named '%s'", ref.getName()));
        }
    }

    /**
     * Resolves the recipe reference, or returns an error message.
     *
     * @param ref   the reference to resolve
     * @param model the model to use
     * @return      the found recipe, or an error message
     */
    public Result<Recipe> resolveRecipeReference(ItemReference ref, Model model) {

        if (ref.isIndexed()) {
            var lastShownList = model.getFilteredRecipeList();

            if (ref.getZeroIndex() >= lastShownList.size()) {
                var between = lastShownList.isEmpty() ? "there are no recipes"
                    : String.format("should be between 1 and %d", lastShownList.size());
                return Result.error("Recipe index '%d' is out of range (%s)", ref.getOneIndex(), between);
            }

            return Result.of(lastShownList.get(ref.getZeroIndex()));

        } else {

            return Result.ofOptional(model.findRecipeWithName(ref.getName()),
                String.format("No recipe named '%s'", ref.getName()));
        }
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("internal error: equals() called on Command");
    }
}

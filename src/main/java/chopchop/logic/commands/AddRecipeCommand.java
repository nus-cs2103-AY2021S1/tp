package chopchop.logic.commands;

import static chopchop.commons.util.Strings.ARG_INGREDIENT;
import static chopchop.commons.util.Strings.ARG_QUANTITY;
import static chopchop.commons.util.Strings.ARG_STEP;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.attributes.Step;
import chopchop.model.attributes.Tag;
import chopchop.model.ingredient.IngredientReference;
import chopchop.model.recipe.Recipe;

/**
 * Adds a recipe to the recipe book.
 */
public class AddRecipeCommand extends Command implements Undoable {
    public static final String COMMAND_WORD = "add recipe";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the recipe book. "
            + "Parameters: "
            + "NAME "
            + "[" + ARG_INGREDIENT + " INGREDIENT [" + ARG_QUANTITY + " QUANTITY]]... "
            + "[" + ARG_STEP + " STEP]...\n"
            + "Example: " + COMMAND_WORD
            + " Sugar Tomato "
            + ARG_INGREDIENT + " Sugar "
            + ARG_INGREDIENT + " Tomato " + ARG_QUANTITY + " 5 "
            + ARG_STEP + " Chop tomatoes. "
            + ARG_STEP + " Add sugar to it and mix well.";

    private Recipe recipe;

    private final String name;
    private final List<IngredientReference> ingredients;
    private final List<Step> steps;
    private final Set<Tag> tags;

    /**
     * Creates a command to add a recipe with the following name, steps, ingredients, and tags.
     */
    public AddRecipeCommand(String name, List<IngredientReference> ingredients, List<Step> steps, Set<Tag> tags) {

        this.recipe = null;

        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.steps = new ArrayList<>(steps);
        this.tags = new HashSet<>(tags);
    }


    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);

        if (model.findRecipeWithName(this.name).isPresent()) {
            return CommandResult.error("Recipe '%s' already exists", this.name);
        }

        // validate the ingredients.
        {
            var seenIngredients = new HashSet<String>();
            for (var ingr : this.ingredients) {

                // check it's not a dupe
                if (seenIngredients.contains(ingr.getName())) {
                    return CommandResult.error("Ingredient '%s' was specified twice", ingr.getName());
                }

                // check that it exists
                var opt = model.findIngredientWithName(ingr.getName());
                if (opt.isEmpty()) {
                    return CommandResult.error("Referenced ingredient '%s' does not exist", ingr.getName());
                }

                var existing = opt.get();
                // and check that they're compatible.
                if (!existing.getQuantity().compatibleWith(ingr.getQuantity())) {
                    return CommandResult.error(
                        "Ingredient '%s' has different, incompatible units (%s) than specified (%s)",
                        existing.getQuantity(),
                        ingr.getQuantity()
                    );
                }

                seenIngredients.add(ingr.getName());
            }
        }

        // then make the recipe.
        this.recipe = new Recipe(this.name, this.ingredients, this.steps, this.tags);

        model.addRecipe(this.recipe);
        return CommandResult.message("Added recipe '%s'", this.recipe.getName());
    }




    @Override
    public CommandResult undo(Model model) {

        requireNonNull(model);
        requireNonNull(this.recipe);

        model.deleteRecipe(this.recipe);
        return CommandResult.message("Undo: removed recipe '%s'", this.recipe.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AddRecipeCommand)) {
            return false;
        } else if (obj == this) {
            return true;
        }

        var other = (AddRecipeCommand) obj;
        return Objects.equals(this.name, other.name)
            && Objects.equals(this.steps, other.steps)
            && Objects.equals(this.ingredients, other.ingredients)
            && Objects.equals(this.tags, other.tags);
    }


    @Override
    public String toString() {
        return String.format("AddRecipeCommand(%s)", this.name);
    }
}



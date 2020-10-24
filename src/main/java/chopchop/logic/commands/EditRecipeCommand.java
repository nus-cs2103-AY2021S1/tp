package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;

import chopchop.commons.core.Messages;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.edit.RecipeEditDescriptor;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;
import chopchop.model.attributes.Step;
import chopchop.model.attributes.Tag;
import chopchop.model.attributes.units.Count;
import chopchop.model.ingredient.IngredientReference;
import chopchop.model.recipe.Recipe;

/**
 * Edits a recipe identified using it's displayed index or name from the recipe book.
 */
public class EditRecipeCommand extends Command implements Undoable {
    public static final String COMMAND_WORD = "edit recipe";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the recipe identified by the index number or name used in the displayed recipe list.\n"
            + "Parameters: INDEX (must be a positive integer) / NAME\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_EDIT_RECIPE_SUCCESS = "Recipe edited: %s";
    public static final String MESSAGE_EDIT_OPERATION_TYPE_NOT_SUPPORTED = "Edit operation type not supported";
    public static final String MESSAGE_RECIPE_NOT_FOUND = "No recipe named '%s'";
    public static final String MESSAGE_INGREDIENT_NOT_FOUND = "No ingredient named '%s' in recipe '%s'";
    public static final String MESSAGE_UNDO_SUCCESS = "Recipe unedited: %s";

    private final ItemReference item;
    private final RecipeEditDescriptor recipeEditDescriptor;
    private Recipe recipe;
    private Recipe editedRecipe;

    /**
     * Constructs a command that edits the given recipe item.
     */
    public EditRecipeCommand(ItemReference item, RecipeEditDescriptor recipeEditDescriptor) {
        requireNonNull(item);
        requireNonNull(recipeEditDescriptor);

        this.item = item;
        this.recipeEditDescriptor = recipeEditDescriptor;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(model);

        if (this.item.isIndexed()) {
            var lastShownList = model.getFilteredRecipeList();

            if (this.item.getZeroIndex() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
            }

            this.recipe = lastShownList.get(this.item.getZeroIndex());
        } else {
            this.recipe = model
                    .findRecipeWithName(this.item.getName())
                    .orElseThrow(() -> new CommandException(String.format(MESSAGE_RECIPE_NOT_FOUND,
                            this.item.getName())));
        }

        var ingredients = new ArrayList<>(this.recipe.getIngredients());
        var steps = new ArrayList<>(this.recipe.getSteps());
        var tags = new HashSet<>(this.recipe.getTags());

        for (var ingredientEditDescriptor : this.recipeEditDescriptor.getIngredientEdits()) {
            var existingIngredientOptional = ingredients.stream()
                    .filter(ingredient -> ingredient.getName()
                            .equalsIgnoreCase(ingredientEditDescriptor.getIngredientName())).findFirst();

            switch (ingredientEditDescriptor.getEditType()) {
            case ADD: {
                ingredients.add(new IngredientReference(ingredientEditDescriptor.getIngredientName(),
                        ingredientEditDescriptor.getIngredientQuantity().orElse(Count.of(1))));
                break;
            }
            case EDIT: {
                var existingIngredient = existingIngredientOptional
                        .orElseThrow(() -> new CommandException(String.format(MESSAGE_INGREDIENT_NOT_FOUND,
                                ingredientEditDescriptor.getIngredientName(), this.recipe.getName())));
                var quantity = ingredientEditDescriptor.getIngredientQuantity()
                        .orElseThrow(() -> new CommandException("Quantity required for editing ingredients"));
                ingredients.set(ingredients.indexOf(existingIngredient),
                        new IngredientReference(ingredientEditDescriptor.getIngredientName(), quantity));
                break;
            }
            case DELETE: {
                var existingIngredient = existingIngredientOptional
                        .orElseThrow(() -> new CommandException(String.format(MESSAGE_INGREDIENT_NOT_FOUND,
                                ingredientEditDescriptor.getIngredientName(), this.recipe.getName())));
                ingredients.remove(existingIngredient);
                break;
            }
            default: {
                throw new CommandException(MESSAGE_EDIT_OPERATION_TYPE_NOT_SUPPORTED);
            }
            }
        }

        for (var stepEditDescriptor : this.recipeEditDescriptor.getStepEdits()) {
            switch (stepEditDescriptor.getEditType()) {
            case ADD:
                steps.add(stepEditDescriptor.getStepNumber().map(x -> x - 1).orElse(steps.size()),
                        new Step(stepEditDescriptor.getStepText()));
                break;
            case EDIT:
                steps.set(stepEditDescriptor.getStepNumber().map(x -> x - 1).orElseThrow(),
                        new Step(stepEditDescriptor.getStepText()));
                break;
            case DELETE:
                steps.remove(stepEditDescriptor.getStepNumber().map(x -> x - 1).orElseThrow().intValue());
                break;
            default:
                throw new CommandException(MESSAGE_EDIT_OPERATION_TYPE_NOT_SUPPORTED);
            }
        }

        for (var tagEditDescriptor : this.recipeEditDescriptor.getTagEdits()) {
            switch (tagEditDescriptor.getEditType()) {
            case ADD:
                tags.add(new Tag(tagEditDescriptor.getTagName()));
                break;
            case DELETE:
                var existingTag = tags.stream().filter(tag -> tag.getTagName()
                        .equalsIgnoreCase(tagEditDescriptor.getTagName())).findFirst().orElseThrow();
                tags.remove(existingTag);
                break;
            default:
                throw new CommandException(MESSAGE_EDIT_OPERATION_TYPE_NOT_SUPPORTED);
            }
        }

        this.editedRecipe = new Recipe(this.recipe.getName(), ingredients, steps, tags);

        model.setRecipe(this.recipe, this.editedRecipe);
        return new CommandResult(String.format(MESSAGE_EDIT_RECIPE_SUCCESS, this.editedRecipe));
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);

        model.setRecipe(this.editedRecipe, this.recipe);
        return new CommandResult(String.format(MESSAGE_UNDO_SUCCESS, this.recipe));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditRecipeCommand
                && this.item.equals(((EditRecipeCommand) other).item)
                && this.recipeEditDescriptor.equals(((EditRecipeCommand) other).recipeEditDescriptor));
    }

    @Override
    public String toString() {
        return String.format("EditRecipeCommand(%s)", this.item);
    }
}

package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.edit.EditOperationType;
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
    public static final String MESSAGE_TAG_NOT_FOUND = "No tag named '%s' in recipe '%s'";
    public static final String MESSAGE_QUANTITY_MISSING = "No quantity specified for '%s'";
    public static final String MESSAGE_STEP_INDEX_MISSING = "No step index specified";
    public static final String MESSAGE_INVALID_STEP_INDEX = "No step at index #%d";
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

        var res = resolveRecipeReference(this.item, model);
        if (res.isError()) {
            return CommandResult.error(res.getError());
        }

        this.recipe = res.getValue();

        var name = recipeEditDescriptor.getNameEdit().orElse(this.recipe.getName());
        var ingredients = new ArrayList<>(this.recipe.getIngredients());
        var steps = new ArrayList<>(this.recipe.getSteps());
        var tags = new HashSet<>(this.recipe.getTags());

        for (var ingredientEditDescriptor : this.recipeEditDescriptor.getIngredientEdits()) {
            var type = ingredientEditDescriptor.getEditType();
            var ingredientName = ingredientEditDescriptor.getIngredientName();
            var quantityOptional = ingredientEditDescriptor.getIngredientQuantity();

            if (type == EditOperationType.ADD) {
                ingredients.add(new IngredientReference(ingredientName, quantityOptional.orElse(Count.of(1))));
            } else {
                var existingIngredient = ingredients.stream()
                        .filter(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName))
                        .findFirst()
                        .orElseThrow(() -> new CommandException(String.format(MESSAGE_INGREDIENT_NOT_FOUND,
                                ingredientName, this.recipe.getName())));

                if (type == EditOperationType.EDIT) {
                    var quantity = quantityOptional
                            .orElseThrow(() -> new CommandException(String.format(MESSAGE_QUANTITY_MISSING,
                                    ingredientName)));
                    ingredients.set(ingredients.indexOf(existingIngredient),
                            new IngredientReference(ingredientName, quantity));
                } else if (type == EditOperationType.DELETE) {
                    ingredients.remove(existingIngredient);
                } else {
                    throw new CommandException(MESSAGE_EDIT_OPERATION_TYPE_NOT_SUPPORTED);
                }
            }
        }

        for (var stepEditDescriptor : this.recipeEditDescriptor.getStepEdits()) {
            var type = stepEditDescriptor.getEditType();
            var numberOptional = stepEditDescriptor.getStepNumber();
            var step = stepEditDescriptor.getStepText();

            if (type == EditOperationType.ADD) {
                steps.add(numberOptional.map(x -> x - 1).orElse(steps.size()), new Step(step));
            } else {
                int index = numberOptional.map(x -> x - 1)
                        .orElseThrow(() -> new CommandException(MESSAGE_STEP_INDEX_MISSING));

                if (index < 0 || index > steps.size() - 1) {
                    throw new CommandException(String.format(MESSAGE_INVALID_STEP_INDEX, index + 1));
                }

                if (type == EditOperationType.EDIT) {
                    steps.set(index, new Step(step));
                } else if (type == EditOperationType.DELETE) {
                    steps.remove(index);
                } else {
                    throw new CommandException(MESSAGE_EDIT_OPERATION_TYPE_NOT_SUPPORTED);
                }
            }
        }

        for (var tagEditDescriptor : this.recipeEditDescriptor.getTagEdits()) {
            var tagName = tagEditDescriptor.getTagName();

            switch (tagEditDescriptor.getEditType()) {
            case ADD:
                tags.add(new Tag(tagName));
                break;
            case DELETE:
                var existingTag = tags.stream().filter(tag -> tag.equals(tagName)).findFirst()
                        .orElseThrow(() -> new CommandException(String.format(MESSAGE_TAG_NOT_FOUND,
                                tagName, this.recipe.getName())));
                tags.remove(existingTag);
                break;
            default:
                throw new CommandException(MESSAGE_EDIT_OPERATION_TYPE_NOT_SUPPORTED);
            }
        }

        this.editedRecipe = new Recipe(name, ingredients, steps, tags);

        model.setRecipe(this.recipe, this.editedRecipe);
        return CommandResult.message(MESSAGE_EDIT_RECIPE_SUCCESS, this.editedRecipe);
    }

    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);

        model.setRecipe(this.editedRecipe, this.recipe);
        return CommandResult.message(MESSAGE_UNDO_SUCCESS, this.recipe);
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

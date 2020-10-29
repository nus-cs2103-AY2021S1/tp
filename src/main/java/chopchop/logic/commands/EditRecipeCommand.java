package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chopchop.commons.util.Result;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.edit.EditOperationType;
import chopchop.logic.edit.IngredientEditDescriptor;
import chopchop.logic.edit.RecipeEditDescriptor;
import chopchop.logic.edit.StepEditDescriptor;
import chopchop.logic.edit.TagEditDescriptor;
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

        var newName = this.recipeEditDescriptor.getNameEdit()
            .orElse(this.recipe.getName());

        var newIngredients = performIngredientEdits(this.recipeEditDescriptor.getIngredientEdits());
        var newSteps = performStepEdits(this.recipeEditDescriptor.getStepEdits());
        var newTags = performTagEdits(this.recipeEditDescriptor.getTagEdits());

        var foo = Result.firstError(newIngredients, newSteps, newTags);
        if (foo.isPresent()) {
            return CommandResult.error(foo.get());
        }

        this.editedRecipe = new Recipe(newName,
            newIngredients.getValue(),
            newSteps.getValue(),
            newTags.getValue());

        model.setRecipe(this.recipe, this.editedRecipe);
        return CommandResult.message("Edited recipe '%s'%s",
            this.recipe.getName(), !this.recipe.getName().equals(newName)
                ? String.format(" (renamed to '%s')", newName)
                : "");
    }




    private Result<List<IngredientReference>> performIngredientEdits(List<IngredientEditDescriptor> edits) {

        // this is a reference.
        var ingredients = new ArrayList<>(this.recipe.getIngredients());

        for (var edit : edits) {

            var type = edit.getEditType();
            var name = edit.getIngredientName();
            var qtyOpt = edit.getIngredientQuantity();

            if (type == EditOperationType.ADD) {

                for (var ingr : ingredients) {
                    if (ingr.getName().equals(name)) {
                        return Result.error("Recipe '%s' already contains ingredient '%s'",
                            this.recipe.getName(), name);
                    }
                }

                ingredients.add(new IngredientReference(name, qtyOpt.orElse(Count.of(1))));

            } else {

                var opt = ingredients.stream()
                    .filter(ingr -> ingr.getName().equalsIgnoreCase(name))
                    .findFirst();

                if (opt.isEmpty()) {
                    return Result.error("Recipe doesn't contain an ingredient named '%s'", name);
                } else if (type == EditOperationType.EDIT && qtyOpt.isEmpty()) {
                    return Result.error("Missing quantity to edit ingredient '%s'", name);
                }

                var existing = opt.get();

                if (type == EditOperationType.EDIT) {

                    var idx = ingredients.indexOf(existing);
                    ingredients.set(idx, new IngredientReference(name, qtyOpt.get()));

                } else if (type == EditOperationType.DELETE) {

                    ingredients.remove(existing);

                } else {
                    return Result.error("Cannot %s ingredients", type);
                }
            }
        }

        return Result.of(ingredients);
    }


    private Result<List<Step>> performStepEdits(List<StepEditDescriptor> edits) {

        var steps = new ArrayList<>(this.recipe.getSteps());

        for (var edit : edits) {
            var type = edit.getEditType();
            var step = edit.getStepText();
            var numOpt = edit.getStepNumber().map(x -> x - 1);

            if (type == EditOperationType.ADD) {

                steps.add(numOpt.orElse(steps.size()), new Step(step));

            } else {

                if (numOpt.isEmpty()) {
                    return Result.error("Missing step number for %s", type);
                }

                int index = numOpt.get();
                if (index < 0 || index >= steps.size()) {
                    return Result.error("Step number '%d' is out of range (should be between 1 and %d)",
                        1 + index, steps.size());
                }

                if (type == EditOperationType.EDIT) {

                    steps.set(index, new Step(step));

                } else if (type == EditOperationType.DELETE) {

                    steps.remove(index);

                } else {
                    return Result.error("Cannot %s steps", type);
                }
            }
        }

        return Result.of(steps);
    }

    private Result<Set<Tag>> performTagEdits(List<TagEditDescriptor> edits) {

        var tags = new HashSet<>(this.recipe.getTags());

        for (var edit : edits) {

            var tagName = edit.getTagName();

            if (edit.getEditType() == EditOperationType.ADD) {

                // it's a Set<Tag>, so we don't need to check for dupes.
                tags.add(new Tag(tagName));

            } else if (edit.getEditType() == EditOperationType.DELETE) {

                var opt = tags.stream().filter(t -> t.equals(tagName)).findFirst();
                if (opt.isEmpty()) {
                    return Result.error("Recipe '%s' does not have tag '%s'",
                        this.recipe.getName(), tagName);
                }

                tags.remove(opt.get());

            } else {
                return Result.error("Cannot %s tags", edit.getEditType());
            }
        }

        return Result.of(tags);
    }


    @Override
    public CommandResult undo(Model model) {
        requireNonNull(model);

        model.setRecipe(this.editedRecipe, this.recipe);
        return CommandResult.message("Undo: un-edited recipe '%s'", this.recipe.getName());
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

    public static String getCommandString() {
        return "edit recipe";
    }

    public static String getCommandHelp() {
        return "Edits an existing recipe";
    }
}

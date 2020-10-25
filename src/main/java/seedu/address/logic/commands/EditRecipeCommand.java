package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;

//import java.util.Collections;
//import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Instruction;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;
//import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing recipe in the recipe list specified by its index.
 */
public class EditRecipeCommand extends Command {

    public static final String COMMAND_WORD = "editR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the recipe identified "
            + "by the index number used in the displayed recipe list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_INGREDIENT + "INGREDIENT] "
            + "[" + PREFIX_CALORIES + "CALORIES] "
            + "[" + PREFIX_INSTRUCTION + "INSTRUCTIONS]"
            + "[" + PREFIX_RECIPE_IMAGE + "RECIPE IMAGE]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + "13 "
            + PREFIX_NAME + "Chicken salad "
            + PREFIX_INGREDIENT + "chicken - 100g, lettuce - a bit, tomato "
            + PREFIX_CALORIES + "100 "
            + PREFIX_INSTRUCTION + "1. cook 2. eat "
            + PREFIX_RECIPE_IMAGE + "images/salad.jpg "
            + PREFIX_TAG + "healthy "
            + PREFIX_TAG + "delicious";

    public static final String MESSAGE_EDIT_RECIPE_SUCCESS = "Edited Recipe: %1$s";
    public static final String MESSAGE_NOT_EDITED = "No edit made. At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the Recipe collection.";
    private static Logger logger = Logger.getLogger("EditRecipeLogger");

    private final Index index;
    private final EditRecipeDescriptor editRecipeDescriptor;

    /**
     * @param index of the recipe in the filtered recipe list to edit
     * @param editRecipeDescriptor details to edit the recipe with
     */
    public EditRecipeCommand(Index index, EditRecipeDescriptor editRecipeDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecipeDescriptor);

        this.index = index;
        this.editRecipeDescriptor = new EditRecipeDescriptor(editRecipeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();
        logger.log(Level.INFO, "Editing recipe at position " + index.getOneBased());

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());
        Recipe editedRecipe = createEditedRecipe(recipeToEdit, editRecipeDescriptor);

        if (!recipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.setRecipe(recipeToEdit, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
        if (recipeToEdit.isSameRecipe(editedRecipe)) {
            return new CommandResult(String.format(MESSAGE_NOT_EDITED, editedRecipe));
        }
        return new CommandResult(String.format(MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe));


    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code recipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    private static Recipe createEditedRecipe(Recipe recipeToEdit, EditRecipeDescriptor editRecipeDescriptor) {
        assert recipeToEdit != null;

        Name updatedName = editRecipeDescriptor.getName().orElse(recipeToEdit.getName());
        String updatedRecipeImage = editRecipeDescriptor.getRecipeImage().orElse(recipeToEdit.getRecipeImage());
        ArrayList<Instruction> updatedInstruction =
                editRecipeDescriptor.getInstruction().orElse(recipeToEdit.getInstruction());
        ArrayList<Ingredient> updatedIngredient =
                editRecipeDescriptor.getIngredient().orElse(recipeToEdit.getIngredient());
        Calories updatedCalories = editRecipeDescriptor.getCalories().orElse(recipeToEdit.getCalories());
        Set<Tag> updatedTags = editRecipeDescriptor.getTags().orElse(recipeToEdit.getTags());

        return new Recipe(updatedName, updatedInstruction, updatedRecipeImage, updatedIngredient, updatedCalories,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRecipeCommand)) {
            return false;
        }

        // state check
        EditRecipeCommand e = (EditRecipeCommand) other;
        return index.equals(e.index)
                && editRecipeDescriptor.equals(e.editRecipeDescriptor);
    }

    /**
     * Stores the details to edit the recipe with. Each non-empty field value will replace the
     * corresponding field value of the recipe.
     */
    public static class EditRecipeDescriptor {
        private Name name;
        private ArrayList<Instruction> instruction;
        private String recipeImage;
        private ArrayList<Ingredient> ingredients;
        private Calories calories;
        private Set<Tag> tags;

        public EditRecipeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRecipeDescriptor(EditRecipeDescriptor toCopy) {
            setName(toCopy.name);
            setInstruction(toCopy.instruction);
            setRecipeImage(toCopy.recipeImage);
            setIngredient(toCopy.ingredients);
            setCalories(toCopy.calories);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, instruction, ingredients, calories, recipeImage, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setInstruction(ArrayList<Instruction> instruction) {
            this.instruction = instruction;
        }
        public Optional<ArrayList<Instruction>> getInstruction() {
            return Optional.ofNullable(instruction);
        }

        public void setRecipeImage(String recipeImage) {
            this.recipeImage = recipeImage;
        }
        public Optional<String> getRecipeImage() {
            return Optional.ofNullable(recipeImage);
        }

        public void setIngredient(ArrayList<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }


        public Optional<ArrayList<Ingredient>> getIngredient() {
            return Optional.ofNullable(ingredients);
        }


        public void setCalories(Calories calories) {
            this.calories = calories;
        }

        public Optional<Calories> getCalories() {
            return Optional.ofNullable(calories);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRecipeDescriptor)) {
                return false;
            }

            // state check
            EditRecipeDescriptor e = (EditRecipeDescriptor) other;
            return getName().equals(e.getName())
                    && getInstruction().equals(e.getInstruction())
                    && getRecipeImage().equals(e.getRecipeImage())
                    && getIngredient().equals(e.getIngredient())
                    && getCalories().equals(e.getCalories())
                    && getTags().equals(e.getTags());
        }
    }
}

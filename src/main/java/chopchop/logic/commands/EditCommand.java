package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;
import static chopchop.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static chopchop.logic.parser.CliSyntax.PREFIX_QTY;
import static chopchop.logic.parser.CliSyntax.PREFIX_STEP;
import static chopchop.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import java.util.*;

import chopchop.commons.core.Messages;
import chopchop.commons.core.index.Index;
import chopchop.commons.util.CollectionUtil;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;
import chopchop.model.recipe.Recipe;
import chopchop.model.recipe.Name;
import chopchop.model.recipe.Ingredient;
import chopchop.model.recipe.Step;

/**
 * Edits the details of an existing Recipe in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the Recipe identified "
            + "by the index number used in the displayed Recipe list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_RECIPE_SUCCESS = "Edited Recipe: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book.";

    private final Index index;
    private final EditRecipeDescriptor editRecipeDescriptor;

    /**
     * @param index of the Recipe in the filtered Recipe list to edit
     * @param editRecipeDescriptor details to edit the Recipe with
     */
    public EditCommand(Index index, EditRecipeDescriptor editRecipeDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecipeDescriptor);

        this.index = index;
        this.editRecipeDescriptor = new EditRecipeDescriptor(editRecipeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_Recipe_DISPLAYED_INDEX);
        }

        Recipe RecipeToEdit = lastShownList.get(index.getZeroBased());
        Recipe editedRecipe = createEditedRecipe(RecipeToEdit, editRecipeDescriptor);

        if (!RecipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_Recipe);
        }

        model.setRecipe(RecipeToEdit, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RecipeS);
        return new CommandResult(String.format(MESSAGE_EDIT_Recipe_SUCCESS, editedRecipe));
    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code RecipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    private static Recipe createEditedRecipe(Recipe RecipeToEdit, EditRecipeDescriptor editRecipeDescriptor) {
        assert RecipeToEdit != null;

        Name updatedName = editRecipeDescriptor.getName().orElse(RecipeToEdit.getName());
        Set<Ingredient> updatedIngredients = editRecipeDescriptor.getIngredients().orElse(RecipeToEdit.getIngredients());
        List<Step> updatedSteps = editRecipeDescriptor.getSteps().orElse(RecipeToEdit.getSteps());

        return new Recipe(updatedName, updatedIngredients, updatedSteps);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editRecipeDescriptor.equals(e.editRecipeDescriptor);
    }

    /**
     * Stores the details to edit the Recipe with. Each non-empty field value will replace the
     * corresponding field value of the Recipe.
     */
    public static class EditRecipeDescriptor {
        private Name name;
        private Step<Ingredient> ingredients;
        private List<Step> steps;

        public EditRecipeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRecipeDescriptor(EditRecipeDescriptor toCopy) {
            setName(toCopy.name);
            setIngrdients(toCopy.ingredients);
            setSteps(toCopy.steps);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setIngredients(Set<Ingredient> ingredients) {
            this.ingredients = (ingredients != null) ? new HashSet<>(ingredients) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Ingredient>> getIngredients() {
            return (ingredients != null) ? Optional.of(Collections.unmodifiableSet(ingredients)) : Optional.empty();
        }


        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setSteps(List<Step> steps) {
            this.steps = (steps != null) ? new ArrayList<>(steps) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Ingredient>> getIngredients() {
            return (ingredients != null) ? Optional.of(Collections.unmodifiableSet(ingredients)) : Optional.empty();
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
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}

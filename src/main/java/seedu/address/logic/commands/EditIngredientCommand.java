package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Ingredient;

public class EditIngredientCommand extends Command {

    public static final String COMMAND_WORD = "editF";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the ingredient identified "
            + "by the index number used in the displayed ingredient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_INGREDIENT + "INGREDIENT] "
            + "[" + PREFIX_QUANTITY + "QUANTITY" + "]" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT + "bread, oranges" + PREFIX_QUANTITY + "2kg" + ", cheese ";

    public static final String MESSAGE_EDIT_INGREDIENT_SUCCESS = "Edited Ingredient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "No edit made. At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This recipe already exists in the Recipe "
            + "collection.";

    private final Index index;
    private final EditIngredientCommand.EditIngredientDescriptor editIngredientDescriptor;

    /**
     * @param index of the ingredient in the filtered ingredient list to edit
     * @param editIngredientDescriptor details to edit the ingredient with
     */
    public EditIngredientCommand(Index index,
                                 EditIngredientCommand.EditIngredientDescriptor editIngredientDescriptor) {
        requireNonNull(index);
        requireNonNull(editIngredientDescriptor);

        this.index = index;
        this.editIngredientDescriptor = new EditIngredientCommand.EditIngredientDescriptor(editIngredientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        Ingredient ingredientToEdit = lastShownList.get(index.getZeroBased());
        Ingredient editedIngredient = createEditedIngredient(ingredientToEdit, editIngredientDescriptor);

        if (!ingredientToEdit.isSameIngredient(editedIngredient) && model.hasIngredient(editedIngredient)) {
            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
        }

        model.setIngredient(ingredientToEdit, editedIngredient);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        if (ingredientToEdit.isSameIngredient(editedIngredient)) {
            return new CommandResult(String.format(MESSAGE_NOT_EDITED, editedIngredient));
        }
        return new CommandResult(String.format(MESSAGE_EDIT_INGREDIENT_SUCCESS, editedIngredient));
    }

    /**
     * Creates and returns a {@code Ingredient} with the details of {@code ingredientToEdit}
     * edited with {@code editIngredientDescriptor}.
     */
    private static Ingredient createEditedIngredient(Ingredient ingredientToEdit,
                                        EditIngredientCommand.EditIngredientDescriptor editIngredientDescriptor) {
        assert ingredientToEdit != null;

        Ingredient updatedIngredient = editIngredientDescriptor.getIngredient().orElse(ingredientToEdit);

        return updatedIngredient;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditIngredientCommand)) {
            return false;
        }

        // state check
        EditIngredientCommand e = (EditIngredientCommand) other;
        return index.equals(e.index)
                && editIngredientDescriptor.equals(e.editIngredientDescriptor);
    }

    /**
     * Stores the details to edit the ingredient with. Each non-empty field value will replace the
     * corresponding field value of the ingredient.
     */
    public static class EditIngredientDescriptor {
        private Ingredient ingredient;

        public EditIngredientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditIngredientDescriptor(EditIngredientCommand.EditIngredientDescriptor toCopy) {
            setIngredient(toCopy.ingredient);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(ingredient);
        }

        public void setIngredient(Ingredient ingredient) {
            this.ingredient = ingredient;
        }
        public Optional<Ingredient> getIngredient() {
            return Optional.ofNullable(ingredient);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditIngredientCommand.EditIngredientDescriptor)) {
                return false;
            }

            // state check
            EditIngredientCommand.EditIngredientDescriptor e =
                    (EditIngredientCommand.EditIngredientDescriptor) other;

            return getIngredient().equals(e.getIngredient());
        }
    }
}

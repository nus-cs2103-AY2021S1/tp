package seedu.address.logic.commands.ingredientcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * Sets the level of one specific ingredient to a specific level.
 */
public class IngredientSetCommand extends Command {

    public static final String COMMAND_WORD = "i-set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " :set the ingredient in tCheck.\n"
            + "Parameters: "
            + PREFIX_INGREDIENT + " "
            + PREFIX_AMOUNT + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT + "Milk "
            + PREFIX_AMOUNT + "90 ";

    public static final String MESSAGE_SUCCESS = "Ingredient set: %1$s";
    public static final String MESSAGE_NO_CHANGE = "Ingredient level is already set to the specified amount.";
    public static final String MESSAGE_NOT_FOUND = "Ingredient not found in ingredient book.";
    private final IngredientName targetName;
    private final SetIngredientDescriptor setIngredientDescriptor;

    /**
     * Constructs a set command with the given ingredient name and amount.
     */
    public IngredientSetCommand(IngredientName targetName, SetIngredientDescriptor setIngredientDescriptor) {
        requireNonNull(targetName);
        requireNonNull(setIngredientDescriptor);

        this.targetName = targetName;
        this.setIngredientDescriptor = setIngredientDescriptor;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        Ingredient ingredientToEdit = null;
        boolean isNoChange = false;

        for (Ingredient ingredient : lastShownList) {
            if (ingredient.getIngredientName().equals(targetName)) {
                ingredientToEdit = ingredient;

            }
        }

        if (ingredientToEdit == null) {
            throw new CommandException(MESSAGE_NOT_FOUND);
        }

        if (ingredientToEdit.getAmount().equals(setIngredientDescriptor.getAmount().get())) {
            isNoChange = true;
        }

        Ingredient updatedIngredient = createSetIngredient(ingredientToEdit, setIngredientDescriptor);

        if (isNoChange) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }


        model.setIngredient(ingredientToEdit, updatedIngredient);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedIngredient));
    }

    private static Ingredient createSetIngredient(Ingredient target,
                                                  SetIngredientDescriptor setIngredientDescriptor) {
        assert target != null;

        Amount updatedAmount = setIngredientDescriptor.getAmount().orElse(target.getAmount());

        return new Ingredient(target.getIngredientName(), updatedAmount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IngredientSetCommand)) {
            return false;
        }

        IngredientSetCommand e = (IngredientSetCommand) other;

        return targetName.equals(e.targetName)
                && setIngredientDescriptor.getAmount().equals(e.setIngredientDescriptor.getAmount());
    }

    /**
     * Helps initialise the ingredient book by filling in the book with the six available ingredients.
     *
     * @return the filled ingredient book
     */
    protected static IngredientBook fillIngredientBookHelper(IngredientBook tempBook) {

        tempBook.addIngredient(new Ingredient(new IngredientName("Milk")));
        tempBook.addIngredient(new Ingredient(new IngredientName("Pearl")));
        tempBook.addIngredient(new Ingredient(new IngredientName("Boba")));
        tempBook.addIngredient(new Ingredient(new IngredientName("Black Tea")));
        tempBook.addIngredient(new Ingredient(new IngredientName("Green Tea")));
        tempBook.addIngredient(new Ingredient(new IngredientName("Brown Sugar")));

        return tempBook;
    }

    /**
     * Stores the details to set the ingredient with. Each non-empty field value will replace the
     * corresponding field value of the ingredient.
     */
    public static class SetIngredientDescriptor {

        private IngredientName ingredientName;
        private Amount amount;

        public SetIngredientDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy is used internally.
         */
        public SetIngredientDescriptor(SetIngredientDescriptor toCopy) {
            setAmount(toCopy.amount);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public void setIngredientName(IngredientName name) {
            this.ingredientName = name;
        }

        public Optional<IngredientName> getIngredientName() {
            return Optional.ofNullable(ingredientName);
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof IngredientSetCommand.SetIngredientDescriptor)) {
                return false;
            }

            // state check
            IngredientSetCommand.SetIngredientDescriptor e = (IngredientSetCommand.SetIngredientDescriptor) other;

            return getIngredientName().equals(e.getIngredientName())
                    && getAmount().equals(e.getAmount());
        }
    }
}

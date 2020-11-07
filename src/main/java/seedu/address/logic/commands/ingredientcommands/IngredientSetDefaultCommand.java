package seedu.address.logic.commands.ingredientcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * Set the ingredient to the default levels.
 */
public class IngredientSetDefaultCommand extends Command {

    public static final String LINE_SEPARATOR = "\n";
    public static final String COMMAND_WORD = "i-set-default";
    public static final String MESSAGE_SUCCESS = "All ingredients have been set to the default level:\n"
            + LINE_SEPARATOR
            + "Milk : 50 L\n" + LINE_SEPARATOR
            + "Pearl : 20 KG\n" + LINE_SEPARATOR
            + "Boba : 20 KG\n" + LINE_SEPARATOR
            + "Black Tea : 50 L\n" + LINE_SEPARATOR
            + "Green Tea : 50 L\n" + LINE_SEPARATOR
            + "Brown Sugar : 20 KG\n" + LINE_SEPARATOR;
    public static final String MESSAGE_NO_CHANGE = "All ingredients have already been set to the default amounts.";

    private static final Amount MILK_DEFAULT_AMOUNT = new Amount("50");
    private static final Amount PEARL_DEFAULT_AMOUNT = new Amount("20");
    private static final Amount BOBA_DEFAULT_AMOUNT = new Amount("20");
    private static final Amount BLACK_TEA_DEFAULT_AMOUNT = new Amount("50");
    private static final Amount GREEN_TEA_DEFAULT_AMOUNT = new Amount("50");
    private static final Amount BROWN_SUGAR_DEFAULT_AMOUNT = new Amount("20");

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        IngredientBook defaultIngredientBook = new IngredientBook();
        IngredientBook filledBook = IngredientSetCommand.fillIngredientBookHelper(defaultIngredientBook);

        if (IngredientSetAllCommand.noChangeToCurrentAmount(model, MILK_DEFAULT_AMOUNT, PEARL_DEFAULT_AMOUNT,
                BOBA_DEFAULT_AMOUNT, BLACK_TEA_DEFAULT_AMOUNT, GREEN_TEA_DEFAULT_AMOUNT, BROWN_SUGAR_DEFAULT_AMOUNT)) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }

        filledBook.setIngredient(new Ingredient(new IngredientName("Milk")),
                new Ingredient(new IngredientName("Milk"), new Amount("50")));
        filledBook.setIngredient(new Ingredient(new IngredientName("Pearl")),
                new Ingredient(new IngredientName("Pearl"), new Amount("20")));
        filledBook.setIngredient(new Ingredient(new IngredientName("Boba")),
                new Ingredient(new IngredientName("Boba"), new Amount("20")));
        filledBook.setIngredient(new Ingredient(new IngredientName("Black Tea")),
                new Ingredient(new IngredientName("Black Tea"), new Amount("50")));
        filledBook.setIngredient(new Ingredient(new IngredientName("Green Tea")),
                new Ingredient(new IngredientName("Green Tea"), new Amount("50")));
        filledBook.setIngredient(new Ingredient(new IngredientName("Brown Sugar")),
                new Ingredient(new IngredientName("Brown Sugar"), new Amount("20")));

        ReadOnlyIngredientBook defaultReadOnlyFilledBook = filledBook;

        model.setIngredientBook(defaultReadOnlyFilledBook);
        model.updateFilteredIngredientList(Model.PREDICATE_SHOW_ALL_INGREDIENTS);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}

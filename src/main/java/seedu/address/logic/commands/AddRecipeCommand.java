package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_INGREDIENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_QUANTITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipePrecursor;

/**
 * Adds a item to the item list.
 */
public class AddRecipeCommand extends Command {

    public static final String COMMAND_WORD = "addr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the recipe list. "
            + "Parameters: "
            + PREFIX_RECIPE_PRODUCT_NAME + "PRODUCT NAME "
            + PREFIX_RECIPE_INGREDIENTS + "ITEM NAME[QUANTITY] "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + "PRODUCT QUANTITY "
            + PREFIX_RECIPE_DESCRIPTION + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RECIPE_PRODUCT_NAME + "banana cake "
            + PREFIX_RECIPE_INGREDIENTS + "banana[2], flour[2], water[1] "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + "1 "
            + PREFIX_RECIPE_DESCRIPTION + "Banana cake ";


    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe list";
    public static final String MESSAGE_ITEM_NOT_FOUND = "Item specified in recipe not found in item list";

    private final RecipePrecursor recipePre;

    /**
     * Creates an AddCommand to add the specified {@code Item}
     */
    public AddRecipeCommand(RecipePrecursor recipePre) {
        requireNonNull(recipePre);
        this.recipePre = recipePre;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Recipe recipeToAdd;
        try {
            recipeToAdd = model.processPrecursor(recipePre);
        } catch (ItemNotFoundException ex) {
            throw new CommandException(MESSAGE_ITEM_NOT_FOUND);
        }

        if (model.hasRecipe(recipeToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe(recipeToAdd);

        model.commitInventory();

        return new CommandResult(String.format(MESSAGE_SUCCESS, recipeToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecipeCommand // instanceof handles nulls
                && recipePre.equals(((AddRecipeCommand) other).recipePre));
    }
}

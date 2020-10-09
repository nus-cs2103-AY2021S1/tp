package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;
import static chopchop.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static chopchop.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static chopchop.logic.parser.CliSyntax.PREFIX_STEP;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;
import chopchop.model.recipe.Recipe;

/**
 * Adds a person to the address book.
 */
public class AddRecipeCommand extends AddCommand {

    public static final String COMMAND_WORD = "add recipe";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the recipe book. "
            + "Parameters: "
            + "NAME "
            + "[" + PREFIX_INGREDIENT + "INGREDIENT [" + PREFIX_QUANTITY + " QUANTITY]]..."
            + "[" + PREFIX_STEP + "STEP]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Sugar Tomato"
            + PREFIX_INGREDIENT + "Sugar "
            + PREFIX_INGREDIENT + "Tomato " + PREFIX_QUANTITY + " 5 "
            + PREFIX_STEP + "Chop tomatoes. "
            + PREFIX_STEP + "Add sugar to it and mix well. ";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book";


    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddRecipeCommand(Recipe recipe) {
        super(recipe);
        requireNonNull(recipe);
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRecipe((Recipe) toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe((Recipe) toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecipeCommand // instanceof handles nulls
                && toAdd.equals(((AddRecipeCommand) other).toAdd));
    }
}

package seedu.address.logic.commands.ingredientcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * View the level of a single specific ingredient.
 */
public class IngredientViewSingleCommand extends Command {

    public static final String COMMAND_WORD = "i-view-single";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " :views a single ingredient level in tCheck.\n"
            + "Parameters: i/INGREDIENT_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT + "Milk ";

    public static final String MESSAGE_SUCCESS = "Here is the ingredient and its level: %1$s";
    private final IngredientName target;
    private final ViewIngredientDescriptor viewIngredientDescriptor;

    /**
     * Constructs an IngredientViewSingle command with the given ingredient name.
     */
    public IngredientViewSingleCommand(IngredientName target, ViewIngredientDescriptor viewIngredientDescriptor) {
        requireNonNull(target);
        requireNonNull(viewIngredientDescriptor);

        this.target = target;
        this.viewIngredientDescriptor = viewIngredientDescriptor;

    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Ingredient ingredientToView;
        ingredientToView = model.findIngredientByName(target);
        return new CommandResult(String.format(MESSAGE_SUCCESS, ingredientToView));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IngredientViewSingleCommand)) {
            return false;
        }

        IngredientViewSingleCommand e = (IngredientViewSingleCommand) other;

        return target.equals(e.target)
                && viewIngredientDescriptor.equals(e.viewIngredientDescriptor);
    }

    public static class ViewIngredientDescriptor {

        private IngredientName ingredientName;

        public ViewIngredientDescriptor(){}

        /**
         * Copy constructor.
         */
        public ViewIngredientDescriptor(ViewIngredientDescriptor toCopy) {
        }

        public Optional<IngredientName> getIngredientName() {
            return Optional.ofNullable(ingredientName);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SetCommand.SetIngredientDescriptor)) {
                return false;
            }

            // state check
            IngredientViewSingleCommand.ViewIngredientDescriptor e =
                    (IngredientViewSingleCommand.ViewIngredientDescriptor) other;

            return getIngredientName().equals(e.getIngredientName());
        }
    }
}


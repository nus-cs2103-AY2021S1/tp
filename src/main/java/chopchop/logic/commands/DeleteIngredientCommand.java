package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import chopchop.commons.core.Messages;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.parser.ItemReference;
import chopchop.model.Model;
import chopchop.model.ingredient.Ingredient;

/**
 * Deletes an ingredient identified using it's displayed index or name from the ingredient book.
 */
public class DeleteIngredientCommand extends Command {
    public static final String COMMAND_WORD = "delete ingredient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the ingredient identified by the index number or name used in the displayed ingredient list.\n"
            + "Parameters: INDEX (must be a positive integer) / NAME\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INGREDIENT_SUCCESS = "Ingredient deleted: %s";
    public static final String MESSAGE_INGREDIENT_NOT_FOUND = "No ingredient named '%s'";

    private final ItemReference item;
    private Ingredient ingredient;

    /**
     * Constructs a command that deletes the given ingredient item.
     */
    public DeleteIngredientCommand(ItemReference item) {
        requireNonNull(item);
        this.item = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.item.isIndexed()) {
            List<Ingredient> lastShownList = model.getFilteredIngredientList();

            if (this.item.getZeroIndex() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
            }

            this.ingredient = lastShownList.get(this.item.getZeroIndex());
        } else {
            this.ingredient = model
                    .findIngredientWithName(this.item.getName())
                    .orElseThrow(() -> new CommandException(String.format(MESSAGE_INGREDIENT_NOT_FOUND,
                            this.item.getName())));
        }

        model.deleteIngredient(this.ingredient);
        return new CommandResult(String.format(MESSAGE_DELETE_INGREDIENT_SUCCESS, this.ingredient));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteIngredientCommand
                && this.item.equals(((DeleteIngredientCommand) other).item));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the Wishful Shrinking.
 */
public class ClearIngredientCommand extends Command {

    public static final String COMMAND_WORD = "clearF";
    public static final String MESSAGE_SUCCESS = "Fridge's ingredients has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearIngredient();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

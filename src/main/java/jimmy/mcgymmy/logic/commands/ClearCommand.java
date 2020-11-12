package jimmy.mcgymmy.logic.commands;

import static java.util.Objects.requireNonNull;

import jimmy.mcgymmy.model.Model;

/**
 * Clears current list of items in McGymmy.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String SHORT_DESCRIPTION = "Delete all food items currently shown in McGymmy.";
    public static final String MESSAGE_SUCCESS = "List has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearFilteredFood();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

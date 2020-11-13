package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.MyFitnessBuddy;

/**
 * Clears the days in My Fitness Buddy.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "My Fitness Buddy has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setMyFitnessBuddy(new MyFitnessBuddy());
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}

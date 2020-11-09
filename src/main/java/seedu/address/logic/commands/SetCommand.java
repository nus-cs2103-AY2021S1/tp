package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_GOAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SET_GOAL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.GoalTarget;



/**
 * Set the goal of the user for S/U recommendations.
 */
public class SetCommand extends Command {

    public static final String COMMAND_WORD = "goal";

    public static final String MESSAGE_SUCCESS = "Your goal has been set to: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets your goal.\n"
            + "Parameters: '"
            + PREFIX_SET_GOAL + "LEVEL' " + "or '"
            + PREFIX_LIST_GOAL + "'\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SET_GOAL + "2";

    private final GoalTarget goalTarget;

    /**
     * Constructor for SetCommand.
     * @param goalTarget User goal.
     */
    public SetCommand(GoalTarget goalTarget) {
        requireNonNull(goalTarget);
        this.goalTarget = goalTarget;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (goalTarget.goalTarget == GoalTarget.DEFAULT_GOAL) {
            return new CommandResult(GoalTarget.GOAL_LIST);
        }
        model.setGoalTarget(goalTarget);
        return new CommandResult(String.format(MESSAGE_SUCCESS, goalTarget));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetCommand // instanceof handles nulls
                && goalTarget.equals(((SetCommand) other).goalTarget));
    }
}

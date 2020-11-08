package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.body.Weight;

public class AddWeightCommand extends Command {
    public static final String COMMAND_WORD = "weight";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds user's weight (in kg) to fitNUS. "
            + "Parameters: "
            + PREFIX_WEIGHT + "WEIGHT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WEIGHT + "70.8";

    public static final String MESSAGE_SUCCESS = "Weight added: %1$s";

    private final Weight weight;

    /**
     * Creates an AddWeightCommand to add the specified weight in kilograms.
     */
    public AddWeightCommand(Weight weight) {
        requireNonNull(weight);
        this.weight = weight;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addWeight(weight);
        return new CommandResult(String.format(MESSAGE_SUCCESS, weight));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddWeightCommand // instanceof handles nulls
                && this.weight.equals(((AddWeightCommand) other).weight));
    }
}

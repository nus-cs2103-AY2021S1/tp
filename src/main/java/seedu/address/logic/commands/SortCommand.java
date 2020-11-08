package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts the patients in Hospify.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts records in Hospify by name or NRIC.\n"
            + "Example: " + COMMAND_WORD + " name"
            + " or " + COMMAND_WORD + " nric";

    public static final String MESSAGE_SUCCESS = "Patient list sorted!";

    private final String predicate;

    /**
     * Creates a sort command with a predicate input.
     */
    public SortCommand(String predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.sort(new SortBy(predicate));

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && predicate.equals(((SortCommand) other).predicate));
    }

}

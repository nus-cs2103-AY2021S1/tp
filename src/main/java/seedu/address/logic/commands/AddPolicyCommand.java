package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.policy.Policy;

public class AddPolicyCommand extends Command {

    public static final String COMMAND_WORD = "addp";

    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists in the policy list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Policy to the policy list. "
            + "Parameters: "
            + PREFIX_POLICY_NAME + "POLICY NAME "
            + PREFIX_POLICY_DESCRIPTION + "POLICY DESCRIPTION";

    public static final String MESSAGE_SUCCESS = "New policy added: %1$s";

    private final Policy toAdd;

    /**
     * Creates an AddPolicyCommand to add a Policy.
     */
    public AddPolicyCommand(Policy toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPolicy(toAdd.getPolicyName())) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        model.addPolicy(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPolicyCommand // instanceof handles nulls
                && toAdd.equals(((AddPolicyCommand) other).toAdd));
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTSOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyName;

/**
 * Adds a person to the client list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the client list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_CLIENTSOURCE + "CLIENTSOURCE]..."
            + "[" + PREFIX_NOTE + "NOTE]"
            + "[" + PREFIX_PRIORITY + "PRIORITY]"
            + "[" + PREFIX_POLICY_NAME + "POLICY_NAME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_CLIENTSOURCE + "friends "
            + PREFIX_CLIENTSOURCE + "classmate "
            + PREFIX_NOTE + "fakefriend "
            + PREFIX_PRIORITY + " H "
            + PREFIX_POLICY_NAME + " Life Plan";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "This person already exists in the client list\n"
            + "Check the archive if you do not see the client in the active list";
    public static final String MESSAGE_DISABLE_IN_ARCHIVE_MODE =
            "This command is disabled in archive mode\n"
            + "To add a new client, switch to active mode with the list command, then call the add command\n"
            + "To archive an existing client, switch to active mode with the list command, "
            + "then call the archive command";
    public static final String MESSAGE_POLICY_NOT_FOUND =
            "The policy name is not found in the policy list.\n"
            + "Please add the policy into the policy list prior to adding the person.\n"
            + "You can add a new policy by using the addp command.";

    private final Person toAdd;
    private final PolicyName policyName;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person, PolicyName policyName) {
        requireNonNull(person);
        toAdd = person;
        this.policyName = policyName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person toAdd = this.toAdd;

        if (model.getIsArchiveMode()) {
            throw new CommandException(MESSAGE_DISABLE_IN_ARCHIVE_MODE);
        }
        if (policyName != null) {
            if (!model.hasPolicy(policyName)) {
                throw new CommandException(MESSAGE_POLICY_NOT_FOUND);
            }
            toAdd = addPolicyToPerson(model);
        }

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Method updates person Policy from Policy List.
     * policyName has to be in model's policyList.
     */
    private Person addPolicyToPerson(Model model) {
        Policy policy = model.getPolicyList().getPolicy(policyName);
        return new Person(
                toAdd.getName(),
                toAdd.getPhone(),
                toAdd.getEmail(),
                toAdd.getAddress(),
                toAdd.getClientSources(),
                toAdd.getNote(),
                toAdd.getIsArchive(),
                toAdd.getPriority(),
                policy
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
    @Override
    public String toString() {
        return "AddCommand " + toAdd.toString() + " Policy Name: " + policyName.toString();
    }
}

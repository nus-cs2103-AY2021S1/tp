package seedu.address.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;

public class AddCaseCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_CASE
            + ": Adds a case to PIVOT. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "John Doe "
            + PREFIX_STATUS + "closed";

    public static final String MESSAGE_SUCCESS = "New case added: %1$s";
    public static final String MESSAGE_DUPLICATE_CASE = "This case already exists in the address book";

    private final Case toAdd;

    /**
     * Creates an AddCaseCommand to add the specified {@code Case}
     *
     * @param investigationCase
     */
    public AddCaseCommand(Case investigationCase) {
        requireNonNull(investigationCase);
        toAdd = investigationCase;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCase(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CASE);
        }

        model.addCase(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCaseCommand // instanceof handles nulls
                && toAdd.equals(((AddCaseCommand) other).toAdd));
    }
}

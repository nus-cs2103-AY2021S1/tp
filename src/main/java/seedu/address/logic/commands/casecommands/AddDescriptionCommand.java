package seedu.address.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Description;


public class AddDescriptionCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_DESC
            + ": Adds a description to opened case in PIVOT. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + TYPE_DESC + " "
            + PREFIX_DESCRIPTION + "7 people arrested for rioting";

    public static final String MESSAGE_ADD_DESCRIPTION_SUCCESS = "New description added: %1$s";
    public static final String MESSAGE_DUPLICATE_DESCRIPTION = "This description already exists for the case!";

    private final Index index;
    private final Description description;

    /**
     * Creates an AddDescriptionCommand to add the specified {@code Description}
     *
     * @param index
     * @param description
     */
    public AddDescriptionCommand(Index index, Description description) {
        requireNonNull(index);
        requireNonNull(description);
        this.index = index;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
        }

        Case stateCase = lastShownList.get(index.getZeroBased());
        Description stateCaseDescription = stateCase.getDescription();

        // check for same description
        if (stateCaseDescription.equals(this.description)) {
            throw new CommandException(MESSAGE_DUPLICATE_DESCRIPTION);
        }

        // create new updated case
        Case updatedCase = new Case(stateCase.getTitle(), this.description, stateCase.getStatus(),
                stateCase.getDocuments(), stateCase.getSuspects(), stateCase.getVictims(), stateCase.getWitnesses(),
                stateCase.getTags());
        model.setCase(stateCase, updatedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);

        return new CommandResult(String.format(MESSAGE_ADD_DESCRIPTION_SUCCESS, this.description));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDescriptionCommand // instanceof handles nulls
                && index.equals(((AddDescriptionCommand) other).index)
                && description.equals(((AddDescriptionCommand) other).description));
    }
}

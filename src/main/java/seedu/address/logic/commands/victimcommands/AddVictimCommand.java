package seedu.address.logic.commands.victimcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Victim;

public class AddVictimCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_VICTIM
            + ": Adds a victim to current case in PIVOT. "
            + "Parameters: "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " " + TYPE_VICTIM + " "
            + PREFIX_NAME + "John Doe ";

    public static final String MESSAGE_ADD_VICTIM_SUCCESS = "New victim added: %1$s";
    public static final String MESSAGE_DUPLICATE_CASE = "This victim already exists in the case";

    private final Victim toAdd;
    private final Index index;

    /**
     * Creates an AddVictimCommand to add the specified {@code Case}
     *
     * @param victim The victim to be added.
     */
    public AddVictimCommand(Index index, Victim victim) {
        requireNonNull(index);
        requireNonNull(victim);
        toAdd = victim;
        this.index = index;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
        }

        Case caseToEdit = lastShownList.get(index.getZeroBased());
        List<Victim> updatedVictims = caseToEdit.getVictims();

        if (updatedVictims.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CASE);
        }

        updatedVictims.add(toAdd);

        Case editedCase = new Case(caseToEdit.getTitle(), caseToEdit.getDescription(),
                caseToEdit.getStatus(), caseToEdit.getDocuments(), caseToEdit.getSuspects(),
                updatedVictims, caseToEdit.getWitnesses(), caseToEdit.getTags());

        model.setCase(caseToEdit, editedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);

        return new CommandResult(String.format(MESSAGE_ADD_VICTIM_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVictimCommand // instanceof handles nulls
                && toAdd.equals(((AddVictimCommand) other).toAdd));
    }
}

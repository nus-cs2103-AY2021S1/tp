package seedu.pivot.logic.commands.witnesscommands;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.pivot.commons.core.Messages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.victimcommands.AddVictimCommand;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Witness;

public class AddWitnessCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_WITNESS
            + ": Adds a victim to current case in PIVOT. "
            + "Parameters: "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " " + TYPE_WITNESS + " "
            + PREFIX_NAME + "John Doe ";

    public static final String MESSAGE_ADD_WITNESS_SUCCESS = "New witness added: %1$s";
    public static final String MESSAGE_DUPLICATE_VICTIM = "This witness already exists in the case";

    private final Index index;
    private final Witness witness;

    /**
     * Creates an AddVictimCommand to add the specified {@code Case}
     *
     * @param witness The victim to be added.
     */
    public AddWitnessCommand(Index index, Witness witness) {
        requireNonNull(index);
        requireNonNull(witness);
        this.index = index;
        this.witness = witness;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
        }

        Case stateCase = lastShownList.get(index.getZeroBased());
        List<Witness> updatedWitnesses = stateCase.getWitnesses();

        if (updatedWitnesses.contains(witness)) {
            throw new CommandException(MESSAGE_DUPLICATE_VICTIM);
        }

        updatedWitnesses.add(witness);

        Case editedCase = new Case(stateCase.getTitle(), stateCase.getDescription(),
                stateCase.getStatus(), stateCase.getDocuments(), stateCase.getSuspects(),
                stateCase.getVictims(), updatedWitnesses, stateCase.getTags());

        model.setCase(stateCase, editedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);

        return new CommandResult(String.format(MESSAGE_ADD_WITNESS_SUCCESS, witness));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVictimCommand // instanceof handles nulls
                && witness.equals(((AddWitnessCommand) other).witness)
                && index.equals(((AddWitnessCommand) other).index));
    }
}

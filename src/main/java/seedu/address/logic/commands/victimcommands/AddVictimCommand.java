package seedu.address.logic.commands.victimcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.StateManager;
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
    public static final String MESSAGE_DUPLICATE_VICTIM = "This victim already exists in the case";

    private final Index index;
    private final Victim victim;

    /**
     * Creates an AddVictimCommand to add the specified {@code Case}
     *
     * @param victim The victim to be added.
     */
    public AddVictimCommand(Index index, Victim victim) {
        requireNonNull(index);
        requireNonNull(victim);
        this.index = index;
        this.victim = victim;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        //check for valid index
        assert(StateManager.atCasePage()) : "Program should be at case page";
        assert(index.getZeroBased() < lastShownList.size()) : "index should be valid";

        Case stateCase = lastShownList.get(index.getZeroBased());
        List<Victim> updatedVictims = stateCase.getVictims();

        if (updatedVictims.contains(victim)) {
            throw new CommandException(MESSAGE_DUPLICATE_VICTIM);
        }

        updatedVictims.add(victim);

        Case updatedCase = new Case(stateCase.getTitle(), stateCase.getDescription(),
                stateCase.getStatus(), stateCase.getDocuments(), stateCase.getSuspects(),
                updatedVictims, stateCase.getWitnesses(), stateCase.getTags());

        model.setCase(stateCase, updatedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);

        return new CommandResult(String.format(MESSAGE_ADD_VICTIM_SUCCESS, victim));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddVictimCommand // instanceof handles nulls
                && victim.equals(((AddVictimCommand) other).victim)
                && index.equals(((AddVictimCommand) other).index));
    }
}

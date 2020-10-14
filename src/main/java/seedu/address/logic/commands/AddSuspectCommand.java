package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.StateManager;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Suspect;

public class AddSuspectCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_SUSPECT
            + ": Adds a suspect to the opened case in PIVOT.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " " + TYPE_SUSPECT + " "
            + PREFIX_NAME + "John Doe";

    private static final String MESSAGE_ADD_SUSPECT_SUCCESS = "New suspect added: %1$s";
    private static final String MESSAGE_DUPLICATE_SUSPECT = "This suspect already exists in the case.";

    private final Suspect toAdd;

    /**
     * Creates an AddSuspectCommand to add the specified {@code Suspect
     * }
     *
     * @param suspect
     */
    public AddSuspectCommand(Suspect suspect) {
        requireNonNull(suspect);
        toAdd = suspect;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atCasePage()) : "Program should be at case page";
        Index index = StateManager.getState();

        Case openCase = lastShownList.get(index.getZeroBased());
        List<Suspect> updatedSuspects = openCase.getSuspects();

        if (updatedSuspects.contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUSPECT);
        }

        updatedSuspects.add(toAdd);
        Case editedCase = new Case(openCase.getTitle(), openCase.getDescription(), openCase.getStatus(),
                openCase.getDocuments(), updatedSuspects, openCase.getVictims(), openCase.getWitnesses(),
                openCase.getTags());

        model.setCase(openCase, editedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);
        return new CommandResult(String.format(MESSAGE_ADD_SUSPECT_SUCCESS, editedCase));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSuspectCommand // instanceof handles nulls
                && toAdd.equals(((AddSuspectCommand) other).toAdd));
    }
}

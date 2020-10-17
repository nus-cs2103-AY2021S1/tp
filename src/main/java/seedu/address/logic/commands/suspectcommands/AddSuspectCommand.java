package seedu.address.logic.commands.suspectcommands;

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

    private final Index index;
    private final Suspect suspect;

    /**
     * Creates an AddSuspectCommand to add the specified {@code Suspect}
     *
     * @param suspect
     */
    public AddSuspectCommand(Index index, Suspect suspect) {
        requireNonNull(index);
        requireNonNull(suspect);
        this.index = index;
        this.suspect = suspect;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atCasePage()) : "Program should be at case page";
        assert(index.getZeroBased() < lastShownList.size()) : "index should be valid";

        Case openCase = lastShownList.get(index.getZeroBased());
        List<Suspect> updatedSuspects = openCase.getSuspects();

        if (updatedSuspects.contains(suspect)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUSPECT);
        }

        updatedSuspects.add(suspect);
        Case updatedCase = new Case(openCase.getTitle(), openCase.getDescription(), openCase.getStatus(),
                openCase.getDocuments(), updatedSuspects, openCase.getVictims(), openCase.getWitnesses(),
                openCase.getTags());

        model.setCase(openCase, updatedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);
        return new CommandResult(String.format(MESSAGE_ADD_SUSPECT_SUCCESS, suspect));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSuspectCommand // instanceof handles nulls
                && suspect.equals(((AddSuspectCommand) other).suspect)
                && index.equals(((AddSuspectCommand) other).index));
    }
}

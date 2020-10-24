package seedu.pivot.logic.commands.suspectcommands;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;
import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.caseperson.Suspect;

public class AddSuspectCommand extends AddCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_SUSPECT
            + ": Adds a suspect to the opened case in PIVOT.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " " + TYPE_SUSPECT + " "
            + PREFIX_NAME + "John Doe";

    private static final String MESSAGE_ADD_SUSPECT_SUCCESS = "New suspect added: %1$s";
    private static final String MESSAGE_DUPLICATE_SUSPECT = "This suspect already exists in the case.";
    private static final Logger logger = LogsCenter.getLogger(AddSuspectCommand.class);

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
        logger.info("Adding suspect to current case...");
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atCasePage()) : "Program should be at case page";
        assert(index.getZeroBased() < lastShownList.size()) : "index should be valid";

        Case openCase = lastShownList.get(index.getZeroBased());
        List<Suspect> updatedSuspects = openCase.getSuspects();

        if (updatedSuspects.contains(suspect)) {
            logger.warning("Failed to add suspect: Tried to add a suspect that exists in PIVOT");
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

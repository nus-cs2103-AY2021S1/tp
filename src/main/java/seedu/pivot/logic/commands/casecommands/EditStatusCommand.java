package seedu.pivot.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_CASE_PAGE;
import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_VALID_INDEX;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.EditCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Status;

/**
 * Edits the { @code Status } to an opened { @case Case } in PIVOT.
 */
public class EditStatusCommand extends EditCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_STATUS
            + ": Edits the status of the opened case.\n"
            + "Parameters: "
            + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD + " " + TYPE_STATUS + " "
            + PREFIX_STATUS + "closed";

    public static final String MESSAGE_EDIT_STATUS_SUCCESS = "Status updated: %1$s";
    private static final Logger logger = LogsCenter.getLogger(EditStatusCommand.class);

    private final Index index;
    private final Status status;

    /**
     * Creates an EditStatusCommand to update the specified { @code Status } for a { @code Case }.
     *
     * @param index Index of the Case in PIVOT.
     * @param status Status to be updated.
     */
    public EditStatusCommand(Index index, Status status) {
        requireNonNull(index);
        requireNonNull(status);
        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Updating status to current case...");
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        //check for valid index
        assert(StateManager.atCasePage()) : ASSERT_CASE_PAGE;
        assert(index.getZeroBased() < lastShownList.size()) : ASSERT_VALID_INDEX;

        Case stateCase = lastShownList.get(index.getZeroBased());

        // create new updated case
        Case updatedCase = new Case(stateCase.getTitle(), stateCase.getDescription(), status,
                stateCase.getDocuments(), stateCase.getSuspects(), stateCase.getVictims(), stateCase.getWitnesses(),
                stateCase.getTags(), stateCase.getArchiveStatus());
        model.setCase(stateCase, updatedCase);
        model.commitPivot(String.format(MESSAGE_EDIT_STATUS_SUCCESS, status));

        return new CommandResult(String.format(MESSAGE_EDIT_STATUS_SUCCESS, status));
    }
}

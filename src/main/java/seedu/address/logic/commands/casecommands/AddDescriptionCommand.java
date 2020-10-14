package seedu.address.logic.commands.casecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Description;
import seedu.address.model.investigationcase.Document;
import seedu.address.model.investigationcase.Status;
import seedu.address.model.investigationcase.Suspect;
import seedu.address.model.investigationcase.Title;
import seedu.address.model.investigationcase.Victim;
import seedu.address.model.investigationcase.Witness;
import seedu.address.model.tag.Tag;


public class AddDescriptionCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_DESCRIPTION
            + ": Adds a description to opened case in PIVOT. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "7 people arrested for rioting";

    public static final String MESSAGE_ADD_DESCRIPTION_SUCCESS = "New description added: %1$s";
    public static final String MESSAGE_DESCRIPTION_NOT_EDITED = "Please provide a description.";
    public static final String MESSAGE_DUPLICATE_DESCRIPTION = "This description already exists for the case!";

    private final Index index;
    private final EditCommand.EditCaseDescriptor editCaseDescriptor;

    /**
     * Creates an AddDescriptionCommand to add the specified {@code Description}
     *
     * @param index
     * @param editCaseDescriptor
     */
    public AddDescriptionCommand(Index index, EditCommand.EditCaseDescriptor editCaseDescriptor) {
        requireNonNull(index);
        requireNonNull(editCaseDescriptor);
        this.index = index;
        this.editCaseDescriptor = editCaseDescriptor;
    }

    /**
     * Creates and returns a {@code Case} with the details of {@code caseToEdit}
     * edited with {@code editCaseDescriptor}.
     */
    private static Case createEditedCase(Case caseToEdit, EditCommand.EditCaseDescriptor editCaseDescriptor) {
        assert caseToEdit != null;

        Title updatedTitle = editCaseDescriptor.getTitle().orElse(caseToEdit.getTitle());
        Description updatedDescription = editCaseDescriptor.getDescription().orElse(caseToEdit.getDescription());
        Status updatedStatus = editCaseDescriptor.getStatus().orElse(caseToEdit.getStatus());
        List<Document> updatedDocuments = editCaseDescriptor.getDocuments().orElse(caseToEdit.getDocuments());
        List<Suspect> updatedSuspects = editCaseDescriptor.getSuspects().orElse(caseToEdit.getSuspects());
        List<Victim> updatedVictims = editCaseDescriptor.getVictims().orElse(caseToEdit.getVictims());
        Set<Tag> updatedTags = editCaseDescriptor.getTags().orElse(caseToEdit.getTags());
        List<Witness> updatedWitnesses =
                editCaseDescriptor.getWitnesses().orElse(caseToEdit.getWitnesses());
        return new Case(updatedTitle, updatedDescription, updatedStatus, updatedDocuments,
                updatedSuspects, updatedVictims, updatedWitnesses, updatedTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
        }

        Case caseToEdit = lastShownList.get(index.getZeroBased());
        Case editedCase = createEditedCase(caseToEdit, editCaseDescriptor);

        if (!caseToEdit.isSameCase(editedCase) && model.hasCase(editedCase)) {
            throw new CommandException(MESSAGE_DUPLICATE_DESCRIPTION);
        }
        model.setCase(caseToEdit, editedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);
        return new CommandResult(String.format(MESSAGE_ADD_DESCRIPTION_SUCCESS, editedCase));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDescriptionCommand // instanceof handles nulls
                && index.equals(((AddDescriptionCommand) other).index)
                && editCaseDescriptor.equals(((AddDescriptionCommand) other).editCaseDescriptor));
    }
}

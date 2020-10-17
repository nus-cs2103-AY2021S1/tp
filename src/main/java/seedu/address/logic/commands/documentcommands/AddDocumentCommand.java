package seedu.address.logic.commands.documentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFERENCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.StateManager;
import seedu.address.model.Model;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Document;

public class AddDocumentCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + TYPE_DOC
            + ": Adds a document to the opened case.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_REFERENCE + "REFERENCE\n"
            + "Example: " + COMMAND_WORD + " " + TYPE_DOC + " "
            + PREFIX_NAME + "Location file "
            + PREFIX_REFERENCE + "test1.txt";

    private static final String MESSAGE_ADD_DOCUMENT_SUCCESS = "New document added: %1$s";
    private static final String MESSAGE_DUPLICATE_DOCUMENT = "This document already exists in the case.";
    private final Index index;
    private final Document doc;

    /**
     * Creates an AddDocumentCommand to add a {@code Document} to a case with {@code Index}.
     *
     * @param index index of a case in the list.
     * @param doc document to be added.
     */
    public AddDocumentCommand(Index index, Document doc) {
        requireNonNull(index);
        requireNonNull(doc);

        this.index = index;
        this.doc = doc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Case> lastShownList = model.getFilteredCaseList();

        //check for valid index
        assert(StateManager.atCasePage()) : "Program should be at case page";
        assert(index.getZeroBased() < lastShownList.size()) : "index should be valid";

        //get current case in state
        Case stateCase = lastShownList.get(index.getZeroBased());
        List<Document> updatedDocuments = stateCase.getDocuments();

        //check for duplicate
        if (updatedDocuments.contains(this.doc)) {
            throw new CommandException(MESSAGE_DUPLICATE_DOCUMENT);
        }

        //add document to existing list
        updatedDocuments.add(this.doc);

        //create new updated case
        Case updatedCase = new Case(stateCase.getTitle(), stateCase.getDescription(), stateCase.getStatus(),
                updatedDocuments, stateCase.getSuspects(), stateCase.getVictims(), stateCase.getWitnesses(),
                stateCase.getTags());

        model.setCase(stateCase, updatedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);

        return new CommandResult(String.format(MESSAGE_ADD_DOCUMENT_SUCCESS, this.doc));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDocumentCommand // instanceof handles nulls
                && doc.equals(((AddDocumentCommand) other).doc)
                && index.equals(((AddDocumentCommand) other).index));
    }
}

package seedu.address.logic.commands.deliverable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_MILESTONE;
import static seedu.address.logic.parser.deliverable.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.deliverable.Deadline;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.deliverable.deliverable.Milestone;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

/**
 * Edits the details of an existing deliverable.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the deliverable identified "
            + "by the index number used in the displayed deliverable list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_MILESTONE + "MILESTONE] "
            + "[" + PREFIX_CONTACTS + "CONTACTS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Finalise beta release features "
            + PREFIX_DEADLINE + "01-01-2020 12:00";


    public static final String MESSAGE_EDIT_DELIVERABLE_SUCCESS = "Edited deliverable: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DELIVERABLE = "This deliverable already"
            + " exists in the deliverable list.";
    public static final String MESSAGE_UNCHANGED = "Deliverable unchanged. At least one field must differ "
            + "from the deliverable that is being edited.";


    private final Index index;
    private final EditDeliverableDescriptor editDeliverableDescriptor;

    /**
     * @param index                 of the deliverable in the filtered deliverable list to edit
     * @param editDeliverableDescriptor details to edit the deliverable with
     */
    public EditCommand(Index index, EditDeliverableDescriptor editDeliverableDescriptor) {
        requireNonNull(index);
        requireNonNull(editDeliverableDescriptor);

        this.index = index;
        this.editDeliverableDescriptor = new EditDeliverableDescriptor(editDeliverableDescriptor);
    }

    @Override
    public CommandResult execute(ModelDeliverable modelDeliverable) throws CommandException {
        requireNonNull(modelDeliverable);

        List<Deliverable> lastShownList = modelDeliverable.getFilteredDeliverableList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
        }

        Deliverable deliverableToEdit = lastShownList.get(index.getZeroBased());
        Deliverable editedDeliverable = createEditedDeliverable(deliverableToEdit, editDeliverableDescriptor);

        if (deliverableToEdit.equals(editedDeliverable)) {
            throw new CommandException(MESSAGE_UNCHANGED);
        }

        if (!deliverableToEdit.isSameDeliverable(editedDeliverable) && modelDeliverable.hasDeliverable(
                editedDeliverable)) {
            throw new CommandException(MESSAGE_DUPLICATE_DELIVERABLE);
        }

        modelDeliverable.setDeliverable(deliverableToEdit, editedDeliverable);
        return new CommandResult(String.format(MESSAGE_EDIT_DELIVERABLE_SUCCESS, editedDeliverable));
    }

    /**
     * Creates and returns a {@code Deliverable} with the details of {@code deliverableToEdit}
     * edited with {@code editDeliverableDescriptor}.
     */
    private static Deliverable createEditedDeliverable(
            Deliverable deliverableToEdit, EditDeliverableDescriptor editDeliverableDescriptor)
            throws IllegalArgumentException {
        assert deliverableToEdit != null;

        Title updatedTitle = editDeliverableDescriptor.getTitle().orElse(deliverableToEdit.getTitle());
        Milestone updatedMilestone = editDeliverableDescriptor.getMilestone().orElse(deliverableToEdit.getMilestone());

        // Description takes optional String
        Description updatedDesc = editDeliverableDescriptor.getDescription()
                .orElse(deliverableToEdit.getDescription());

        Deadline updatedDeadline = editDeliverableDescriptor.getDeadline().orElse(deliverableToEdit.getDeadline());

        boolean updatedIsComplete = deliverableToEdit.getIsComplete();

        // Contacts takes optional String
        Contacts updatedContacts = editDeliverableDescriptor.getContacts().orElse(deliverableToEdit.getContacts());

        return new Deliverable(
                updatedTitle, updatedMilestone, updatedDesc, updatedDeadline, updatedIsComplete, updatedContacts);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editDeliverableDescriptor.equals(e.editDeliverableDescriptor);
    }

    /**
     * Stores the details to edit the deliverable with. Each non-empty field value will replace the
     * corresponding field value of the deliverable.
     */
    public static class EditDeliverableDescriptor {
        private Title title;
        private Milestone milestone;
        private Description description;
        private Deadline deadline;
        private boolean isComplete;
        private Contacts contacts;

        public EditDeliverableDescriptor() {
        }

        /**
         * Copy attributes from deliverable to be edited.
         *
         * @param toCopy deliverable to be edited.
         */
        public EditDeliverableDescriptor(EditDeliverableDescriptor toCopy) {
            setTitle(toCopy.title);
            setMilestone(toCopy.milestone);
            setDescription(toCopy.description);
            setDeadline(toCopy.deadline);
            setIsComplete(toCopy.isComplete);
            setContacts(toCopy.contacts);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, milestone, description, deadline, contacts);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(this.title);
        }

        public void setMilestone(Milestone milestone) {
            this.milestone = milestone;
        }

        public Optional<Milestone> getMilestone() {
            return Optional.ofNullable(this.milestone);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setIsComplete(boolean isComplete) {
            this.isComplete = isComplete;
        }

        public Optional<Boolean> getIsComplete() {
            return Optional.ofNullable(this.isComplete);
        }

        public void setContacts(Contacts contact) {
            this.contacts = contact;
        }

        public Optional<Contacts> getContacts() {
            return Optional.ofNullable(this.contacts);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDeliverableDescriptor)) {
                return false;
            }

            // state check
            EditDeliverableDescriptor e = (EditDeliverableDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getMilestone().equals(e.getMilestone())
                    && getDescription().equals(e.getDescription())
                    && getDeadline().equals(e.getDeadline())
                    && getIsComplete().equals(e.getIsComplete())
                    && getContacts().equals(e.getContacts());
        }

    }
}


package seedu.address.logic.commands.contactlistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Encapsulates methods and information to edit the details of an existing contact
 * in the contact list.
 */
public class EditContactCommand extends Command {

    public static final String COMMAND_WORD = "editcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the contact identified "
            + "by the index number used in the displayed contact list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "johndoe "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CONTACT_SUCCESS = "Edited Contact: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the contact list.";

    private final Logger logger = LogsCenter.getLogger(EditContactCommand.class);

    /** Index object representing the index of the contact to be edited. */
    private final Index index;
    /** EditContactDescriptor object that encapsulates details of the edited contact. */
    private final EditContactDescriptor editContactDescriptor;

    /**
     * Creates and initialises a new EditContactCommand object to edit a contact in the contact list.
     *
     * @param index Index object encapsulating the index of the target contact in the filtered contact list.
     * @param editContactDescriptor EditContactDescriptor object that encapsulates details to edit the contact with.
     */
    public EditContactCommand(Index index, EditContactDescriptor editContactDescriptor) {
        requireAllNonNull(index, editContactDescriptor);

        assert index.getZeroBased() >= 0 : "Zero-based index must be non-negative";

        this.index = index;
        this.editContactDescriptor = new EditContactDescriptor(editContactDescriptor);
        logger.info("Editing a contact at index " + index.getOneBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());
        Contact editedContact = createEditedContact(contactToEdit, editContactDescriptor);

        if (!contactToEdit.isSameContact(editedContact) && model.hasContact(editedContact)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.commitContactList();
        logger.info("Contact has been edited: " + editedContact.toString());
        return new CommandResult(String.format(MESSAGE_EDIT_CONTACT_SUCCESS, editedContact));
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToEdit}
     * edited with {@code editContactDescriptor}.
     */
    private Contact createEditedContact(Contact contactToEdit, EditContactDescriptor editContactDescriptor) {
        requireAllNonNull(contactToEdit, editContactDescriptor);

        ContactName updatedName = editContactDescriptor.getName().orElse(contactToEdit.getName());
        Email updatedEmail = editContactDescriptor.getEmail().orElse(contactToEdit.getEmail());
        Set<Tag> updatedTags = editContactDescriptor.getTags().orElse(contactToEdit.getTags());
        boolean isImportant = contactToEdit.isImportant();

        if (editContactDescriptor.getTelegram().isPresent()) {
            Telegram updatedTelegram = editContactDescriptor.getTelegram().get();
            return new Contact(updatedName, updatedEmail, updatedTelegram, updatedTags, isImportant);
        } else if (editContactDescriptor.isTelegramDeleted() || !contactToEdit.getTelegram().isPresent()) {
            return new Contact(updatedName, updatedEmail, updatedTags, isImportant);
        }

        Telegram updatedTelegram = contactToEdit.getTelegram().get();
        logger.info("Edited contact has been created");
        return new Contact(updatedName, updatedEmail, updatedTelegram, updatedTags, isImportant);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand e = (EditContactCommand) other;
        return index.equals(e.index)
                && editContactDescriptor.equals(e.editContactDescriptor);
    }

    /**
     * Stores the details to edit the contact with. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditContactDescriptor {

        private ContactName name;
        private Email email;
        private Telegram telegram;
        private boolean isTelegramDeleted = false;
        private Set<Tag> tags;

        /**
         * Creates and initialises a new EditContactDescriptor with none of the contact fields initialised.
         */
        public EditContactDescriptor() {}

        /**
         * Creates and initialises a new EditContactDescriptor encapsulating the contact fields specified
         * in {@code toCopy}.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param toCopy EditContactDescriptor object which contains edited contact fields to be copied.
         */
        public EditContactDescriptor(EditContactDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setTelegram(toCopy.telegram);
            setTags(toCopy.tags);
            this.isTelegramDeleted = toCopy.isTelegramDeleted;
        }

        /**
         * Determines if at least one contact field is edited.
         *
         * @return True if at least one contact field is edited, false otherwise.
         */
        public boolean isAnyFieldEdited() {
            boolean isAtLeastOneFieldEdited = CollectionUtil.isAnyNonNull(name, email, telegram, tags);
            return isAtLeastOneFieldEdited || isTelegramDeleted;
        }

        public void setName(ContactName name) {
            this.name = name;
        }

        public Optional<ContactName> getName() {
            return Optional.ofNullable(name);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setTelegram(Telegram telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return Optional.ofNullable(telegram);
        }

        public void setTelegramDeleted() {
            this.isTelegramDeleted = true;
        }

        public boolean isTelegramDeleted() {
            return this.isTelegramDeleted;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param tags Set of edited tags.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         *
         * @return Optional object describing the set of edited tags.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditContactDescriptor)) {
                return false;
            }

            // state check
            EditContactDescriptor e = (EditContactDescriptor) other;

            return getName().equals(e.getName())
                    && getEmail().equals(e.getEmail())
                    && getTelegram().equals(e.getTelegram())
                    && getTags().equals(e.getTags());
        }


    }
}

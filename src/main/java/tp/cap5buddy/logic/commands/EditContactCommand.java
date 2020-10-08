package tp.cap5buddy.logic.commands;

import java.util.Optional;

import tp.cap5buddy.contacts.Contact;
import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.contacts.Email;
import tp.cap5buddy.contacts.Name;
import tp.cap5buddy.contacts.exceptions.ContactNotFoundException;
import tp.cap5buddy.contacts.exceptions.DuplicateContactException;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;
import tp.cap5buddy.util.CollectionUtil;

public class EditContactCommand extends Command {

    private final int contactID;
    private final EditContactDescriptor editContactDescriptor;

    /**
     * Creates and initialises a EditContactCommand object.
     *
     * @param contactID ID of the contact.
     * @param editContactDescriptor EditContactDescriptor object containing the updated contact details.
     */
    public EditContactCommand(int contactID, EditContactDescriptor editContactDescriptor) {
        this.contactID = contactID;
        this.editContactDescriptor = editContactDescriptor;
    }

    @Override
    public CommandResult execute(ModuleList modules, ContactList contacts, TodoList todoList) throws CommandException {
        try {
            Contact contactToEdit = contacts.getContact(this.contactID - 1);
            Contact editedContact = createEditedContact(contactToEdit, this.editContactDescriptor);
            contacts.editContact(contactToEdit, editedContact);
            String successMessage = createSuccessMessage(contactToEdit, editedContact);
            return new CommandResult(successMessage, isExit());
        } catch (ContactNotFoundException | DuplicateContactException ex) {
            String error = "The contact could not be edited as the contact was not found/already exists";
            throw new CommandException(error);
        }
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToEdit}
     * edited with {@code editContactDescriptor}.
     */
    private static Contact createEditedContact(Contact contactToEdit, EditContactDescriptor editContactDescriptor) {
        assert contactToEdit != null;

        Name updatedName = editContactDescriptor.getName().orElse(contactToEdit.getName());
        Email updatedEmail = editContactDescriptor.getEmail().orElse(contactToEdit.getEmail());

        return new Contact(updatedName, updatedEmail);
    }

    /**
     * Creates success message after successfully executing this command.
     *
     * @param contactToEdit the original contact.
     * @param editedContact the contact after editing.
     * @return String that represent a success message followed by the content of the original
     *         contact and the content after the contact has been edited.
     */
    public String createSuccessMessage(Contact contactToEdit, Contact editedContact) {
        String message = ""
                + "Contact " + this.contactID + " has been successfully edited!\n\n"
                + "Original Contact: \n"
                + "Name: " + contactToEdit.getName().toString() + "\n"
                + "Email: " + contactToEdit.getEmail().toString() + "\n\n"
                + "New Contact: \n"
                + "Name: " + editedContact.getName().toString() + "\n"
                + "Email: " + editedContact.getEmail().toString() + "\n";
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditContactDescriptor {
        private Name name;
        private Email email;

        public EditContactDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditContactDescriptor(EditContactDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, email);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }
    }
}

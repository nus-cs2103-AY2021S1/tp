package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.Contact;
import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.modules.ModuleList;

public class DeleteContactCommand extends Command {

    private final int contactID;

    public DeleteContactCommand(int contactID) {
        this.contactID = contactID;
    }

    @Override
    public CommandResult execute(ModuleList modules, ContactList contacts) throws CommandException {
        if (this.contactID < 0 || this.contactID > contacts.getContactListSize()) {
            String error = "Invalid contact ID";
            throw new CommandException(error);
        }
        Contact toRemove = contacts.getContact(this.contactID);
        contacts.removeContact(this.contactID);
        String successMessage = createSuccessMessage(toRemove);
        return new CommandResult(successMessage, this.isExit());
    }

    /**
     * Creates a success message when the contact has been successfully removed from the contact list.
     *
     * @param toRemove Contact to be removed.
     * @return String containing the success message.
     */
    public String createSuccessMessage(Contact toRemove) {
        String message = toRemove.toString() + " has been successfully removed from your contact list";
        return message;
    }

    /**
     * Indicates if the application session has ended.
     *
     * @return False since the sessions has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

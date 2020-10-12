package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.Contact;
import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.contacts.Email;
import tp.cap5buddy.contacts.Name;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;

public class AddContactCommand extends Command {

    private final Name name;
    private final Email email;

    /**
     * Creates a AddContactCommand.
     *
     * @param name
     * @param email
     */
    public AddContactCommand(String name, String email) {
        this.name = new Name(name);
        this.email = new Email(email);
    }

    @Override
    public CommandResult execute(ModuleList modules, ContactList contacts, TodoList todoList)
            throws CommandException {
        Contact contact = new Contact(this.name, this.email);
        contacts.addContact(contact);
        String successMessage = createSuccessMessage();
        return new CommandResult(successMessage, this.isExit());
    }

    /**
     * Creates a success message when the contact has been successfully added to the contact list.
     *
     * @return String containing the success message.
     */
    public String createSuccessMessage() {
        String message = this.name + " has been successfully added to your contact list";
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

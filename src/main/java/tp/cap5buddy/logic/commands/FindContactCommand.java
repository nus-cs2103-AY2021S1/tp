package tp.cap5buddy.logic.commands;

import java.util.ArrayList;
import java.util.List;

import tp.cap5buddy.contacts.Contact;
import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;

public class FindContactCommand extends Command {

    private final String keyword;

    /**
     * Creates and initialises a new FindContactCommand object.
     *
     * @param keyword Search keyword.
     */
    public FindContactCommand(String keyword) {
        assert keyword != null : "keyword cannot be null";
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the command to search for all contacts in the user's contact list matching
     * the search keyword.
     *
     * @param modules List of modules.
     * @param contacts List of contacts.
     * @return CommandResult.
     * @throws CommandException If the command could not be successfuly executed.
     */
    @Override
    public CommandResult execute(ModuleList modules, ContactList contacts, TodoList todolist) throws CommandException {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts.getContactList()) {
            if (contact.getName().toString().contains(keyword)) {
                result.add(contact);
            }
        }
        String message;
        if (result.isEmpty()) {
            message = "No contacts were found.";
        } else {
            message = createSuccessMessage(result);
        }
        return new CommandResult(message, isExit());
    }

    /**
     * Creates a success message when the contacts matching the search keyword
     * has been successfully found.
     *
     * @param contacts List of contacts found that match the search keyword.
     * @return String containing the success message.
     */
    public String createSuccessMessage(List<Contact> contacts) {
        StringBuilder message;
        message = new StringBuilder("Here are the contacts that I have found:");
        for (int i = 0; i < contacts.size(); i++) {
            int index = i + 1;
            String contact = "\n" + String.format("%d. %s", index, contacts.get(i).toString());
            message.append(contact);
        }
        return message.toString();
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

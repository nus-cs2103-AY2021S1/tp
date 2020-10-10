package tp.cap5buddy.logic;

import java.io.IOException;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.CommandResult;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.logic.parser.ParserManager;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.storage.StorageManager;
import tp.cap5buddy.todolist.TodoList;


/**
 * The brain of the program, handles parsing and commands.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    //private ModuleList modlist;
    private ParserManager pm;
    private final StorageManager storage;
    private final ModuleList moduleList;
    private final TodoList todoList;
    private final ContactList contactList;

    /**
     * Represents the constructor of the Manager.
     */
    public LogicManager(StorageManager storage, ModuleList moduleList, ContactList contactList, TodoList todoList) {
        //this.modlist = new ModuleList(new ArrayList<Module>());
        this.pm = new ParserManager();
        this.storage = storage;
        this.moduleList = moduleList;
        this.todoList = todoList;
        this.contactList = contactList;
    }

    /**
     * Returns the result container with all the relevant information.
     *
     * @param userInput user input of user.
     * @return ResultCommand result container.
     * @throws ParseException invalid command.
     */
    @Override
    public CommandResult execute(String userInput) throws ParseException, CommandException {
        Command command = pm.parse(userInput);
        CommandResult result = command.execute(moduleList, contactList, todoList);
        try {
            storage.saveModuleList(moduleList);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return result;
    }
}

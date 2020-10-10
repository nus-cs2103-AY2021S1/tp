package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.Task;
import tp.cap5buddy.todolist.TodoList;

public class ViewTodoListCommand extends Command {
    @Override
    public CommandResult execute(ModuleList moduleList, ContactList contactList, TodoList todolist) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < todolist.getSize(); i++) {
            Task task = todolist.get(i);
            builder.append("Task ")
                    .append(String.valueOf(i + 1))
                    .append(": ")
                    .append("\n")
                    .append(task)
                    .append("\n");
        }
        return new CommandResult(builder.toString(), isExit());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.grades.Grade;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.modules.Module;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.TodoList;

public class AddGradeCommand extends Command {
    private final String moduleName;
    private final Grade grade;

    /**
     * Creates an AddGradeCommand.
     *
     * @param name
     * @param percentageOfFinalGrade
     * @param results
     */
    public AddGradeCommand(String name, int percentageOfFinalGrade, double results) {
        this.moduleName = name;
        this.grade = new Grade(name, percentageOfFinalGrade, results);
    }

    @Override
    public CommandResult execute(ModuleList modules, ContactList contacts, TodoList todolist) throws CommandException {
        Module module = modules.getModule(moduleName);
        module.getGradeList().addGrade(grade);
        String successMessage = createSuccessMessage();
        return new CommandResult(successMessage, this.isExit());
    }

    /**
     * Creates a success message when the grade has been successfully added.
     *
     * @return String containing the success message.
     */
    public String createSuccessMessage() {
        String message = this.grade + " has been successfully added";
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

package tp.cap5buddy.todolist.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task not found in the list!");
    }
}

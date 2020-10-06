package tp.cap5buddy.todolist;

import java.util.Comparator;

public class TaskComparatorByDescription implements Comparator<Task> {
    @Override
    public int compare(Task task, Task otherTask) {
        return task.getDescription().compareTo(otherTask.getDescription());
    }
}

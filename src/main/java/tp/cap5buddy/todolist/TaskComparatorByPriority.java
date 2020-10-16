package tp.cap5buddy.todolist;

import java.util.Comparator;

public class TaskComparatorByPriority implements Comparator<Task> {
    @Override
    public int compare(Task task, Task otherTask) {
        return task.getPriority().compareTo(otherTask.getPriority());
    }
}

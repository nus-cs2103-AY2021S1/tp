package tp.cap5buddy.todolist;

import java.util.Comparator;

public class TaskComparatorByDate implements Comparator<Task> {
    @Override
    public int compare(Task task, Task otherTask) {
        return task.getDate().compareTo(otherTask.getDate());
    }
}

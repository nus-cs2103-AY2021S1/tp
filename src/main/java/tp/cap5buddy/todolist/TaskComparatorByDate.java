package tp.cap5buddy.todolist;

import java.util.Comparator;

public class TaskComparatorByDate implements Comparator<Task> {
    @Override
    public int compare(Task task, Task otherTask) {
        Date date = task.getDate();
        Date otherDate = otherTask.getDate();
        DateComparator descriptionComparator = new DateComparator();
        return descriptionComparator.compare(date, otherDate);
    }
}

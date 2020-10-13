package tp.cap5buddy.todolist;

import java.util.Comparator;

public class TaskComparatorByDescription implements Comparator<Task> {
    @Override
    public int compare(Task task, Task otherTask) {
        Description description = task.getDescription();
        Description otherDescription = otherTask.getDescription();
        DescriptionComparator descriptionComparator = new DescriptionComparator();
        return descriptionComparator.compare(description, otherDescription);
    }
}

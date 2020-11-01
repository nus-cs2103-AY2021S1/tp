package trackitnus.model.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    /**
     * Compares 2 tasks to faciliate sorting.
     *
     * @param t1 First task to compare.
     * @param t2 Second task to compare.
     * @return an int < 0 if t1 is "less than" t2.
     */
    public int compare(Task t1, Task t2) {
        int res = t1.getDate().compareTo(t2.getDate());
        return res == 0
            ? t1.getName().value.compareTo(t2.getName().value)
            : res;
    }
}

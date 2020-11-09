package seedu.address.model.task;

import java.util.Comparator;

public class TaskDateComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1.equals(o2)) {
            return 0;
        } else if (o1.getStart().compareTo(o2.getStart()) == 0) {
            return o1.getDescription().compareTo(o2.getDescription());
        } else {
            return o1.getStart().compareTo(o2.getStart());
        }
    }
}

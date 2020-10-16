package tp.cap5buddy.todolist;

import java.util.Comparator;

public class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date date, Date otherDate) {
        return date.getValue().compareTo(otherDate.getValue());
    }
}

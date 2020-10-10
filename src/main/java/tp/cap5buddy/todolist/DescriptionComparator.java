package tp.cap5buddy.todolist;

import java.util.Comparator;

public class DescriptionComparator implements Comparator<Description> {
    @Override
    public int compare(Description description, Description otherDescription) {
        return description.getValue().compareTo(otherDescription.getValue());
    }
}

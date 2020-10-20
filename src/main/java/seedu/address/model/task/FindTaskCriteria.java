package seedu.address.model.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Stores the list of criteria to be used when finding matching {@code Task}.
 */
public class FindTaskCriteria {

    private final List<Predicate<Task>> predicateList = new ArrayList<>();

    public void addPredicate(Predicate<Task> predicate) {
        predicateList.add(predicate);
    }

    public Predicate<Task> getFindTaskPredicate() {
        Predicate<Task> findTaskPredicate = predicateList.get(0);
        for (int i = 1; i < predicateList.size(); i++) {
            findTaskPredicate.and(predicateList.get(i));
        }
        return findTaskPredicate;
    }

}

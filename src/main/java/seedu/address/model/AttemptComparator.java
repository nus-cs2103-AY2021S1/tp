package seedu.address.model;

import java.util.Comparator;

import seedu.address.model.quiz.Attempt;

public class AttemptComparator implements Comparator<Attempt> {

    public int compare(Attempt f1, Attempt f2) {
        return f1.compareTo(f2);
    }
}

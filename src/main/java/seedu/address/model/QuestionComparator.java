package seedu.address.model;

import java.util.Comparator;

import seedu.address.model.quiz.Question;

public class QuestionComparator implements Comparator<Question> {

    public int compare(Question f1, Question f2) {
        return f1.compareTo(f2);
    }
}

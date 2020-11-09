package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.QuizBook;
import seedu.address.model.quiz.Mcq;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.TrueFalse;

/**
 * A utility class containing a list of {@code Question} objects to be used in tests.
 */
public class TypicalQuestions {

    public static final Question BUBBLESORTMCQ = new Mcq("What is the time complexity of Bubble Sort?",
            4, Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)"));
    public static final Question SELECTIONSORTMCQ = new Mcq("What is the time complexity of Selection Sort?",
            4, Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)"));
    public static final Question INSERTIONSORTMCQ = new Mcq("What is the time complexity of Insertion Sort?",
            4, Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)"));
    public static final Question MERGESORTMCQ = new Mcq("What is the time complexity of Merge Sort?",
            3, Arrays.asList("O(1)", "O(n)", "O(nlogn)", "O(n^2)"));
    public static final Question HEAPTF = new TrueFalse("The sequence [20, 15, 18, 7, 9, 5, 12, 3, 6, 2]"
            + " is a max-heap", true);
    public static final Question GRAPHTF = new TrueFalse("Every directed acyclic graph has exactly "
            + "one valid topological ordering", false);


    private TypicalQuestions() {} // prevents instantiation

    /**
     * Returns an {@code QuizBook} with all the typical flashcards.
     */
    public static QuizBook getTypicalQuizBook() {
        QuizBook ab = new QuizBook();
        for (Question question : getTypicalQuestions()) {
            ab.addQuestion(question);
        }
        return ab;
    }

    public static List<Question> getTypicalQuestions() {
        return new ArrayList<>(Arrays.asList(BUBBLESORTMCQ, SELECTIONSORTMCQ, INSERTIONSORTMCQ, MERGESORTMCQ,
                HEAPTF, GRAPHTF));
    }
}

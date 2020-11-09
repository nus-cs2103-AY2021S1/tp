package seedu.address.model.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalQuestions.HEAPTF;
import static seedu.address.testutil.TypicalQuestions.MERGESORTMCQ;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.quiz.exceptions.DuplicateQuestionException;
import seedu.address.model.quiz.exceptions.QuestionNotFoundException;

public class UniqueQuestionListTest {

    private final UniqueQuestionList uniqueQuestionList = new UniqueQuestionList();

    @Test
    public void contains_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.contains(null));
    }

    @Test
    public void contains_quizNotInList_returnsFalse() {
        assertFalse(uniqueQuestionList.contains(MERGESORTMCQ));
    }

    @Test
    public void contains_quizInList_returnsTrue() {
        uniqueQuestionList.add(MERGESORTMCQ);
        assertTrue(uniqueQuestionList.contains(MERGESORTMCQ));
    }

    @Test
    public void add_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.add(null));
    }

    @Test
    public void add_duplicateQuestion_throwsDuplicateQuestionException() {
        uniqueQuestionList.add(MERGESORTMCQ);
        assertThrows(DuplicateQuestionException.class, () -> uniqueQuestionList.add(MERGESORTMCQ));
    }

    @Test
    public void setQuestion_nullTargetQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestion(null, MERGESORTMCQ));
    }

    @Test
    public void setQuestion_nullEditedQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestion(MERGESORTMCQ, null));
    }

    @Test
    public void setQuestion_targetQuestionNotInList_throwsQuestionNotFoundException() {
        assertThrows(QuestionNotFoundException.class, () -> uniqueQuestionList.setQuestion(MERGESORTMCQ, MERGESORTMCQ));
    }


    @Test
    public void remove_nullQuestion_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.remove(null));
    }

    @Test
    public void remove_quizDoesNotExist_throwsQuestionNotFoundException() {
        assertThrows(QuestionNotFoundException.class, () -> uniqueQuestionList.remove(MERGESORTMCQ));
    }

    @Test
    public void remove_existingQuestion_removesQuestion() {
        uniqueQuestionList.add(MERGESORTMCQ);
        uniqueQuestionList.remove(MERGESORTMCQ);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_nullUniqueQuestionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestions((UniqueQuestionList) null));
    }

    @Test
    public void setQuestions_uniqueQuestionList_replacesOwnListWithProvidedUniqueQuestionList() {
        uniqueQuestionList.add(MERGESORTMCQ);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(HEAPTF);
        uniqueQuestionList.setQuestions(expectedUniqueQuestionList);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueQuestionList.setQuestions((List<Question>) null));
    }

    @Test
    public void setQuestions_list_replacesOwnListWithProvidedList() {
        uniqueQuestionList.add(MERGESORTMCQ);
        List<Question> flashcardList = Collections.singletonList(HEAPTF);
        uniqueQuestionList.setQuestions(flashcardList);
        UniqueQuestionList expectedUniqueQuestionList = new UniqueQuestionList();
        expectedUniqueQuestionList.add(HEAPTF);
        assertEquals(expectedUniqueQuestionList, uniqueQuestionList);
    }

    @Test
    public void setQuestions_listWithDuplicateQuestions_throwsDuplicateQuestionException() {
        List<Question> listWithDuplicateQuestions = Arrays.asList(MERGESORTMCQ, MERGESORTMCQ);
        assertThrows(DuplicateQuestionException.class, () ->
                uniqueQuestionList.setQuestions(listWithDuplicateQuestions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueQuestionList.asUnmodifiableObservableList().remove(0));
    }
}

package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.StudentBuilder.DEFAULT_QUESTION_NEWTON;
import static seedu.address.testutil.StudentBuilder.DEFAULT_SOLUTION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

public class JsonAdaptedQuestionTest {

    @Test
    public void toModelType_validQuestion_returnsQuestion() throws Exception {
        Question question = new UnsolvedQuestion(DEFAULT_QUESTION_NEWTON);
        assertEquals(question, new JsonAdaptedQuestion(question).toModelType());

        question = new SolvedQuestion(DEFAULT_QUESTION_NEWTON, DEFAULT_SOLUTION);
        assertEquals(question, new JsonAdaptedQuestion(question).toModelType());
    }

    @Test
    public void toModelType_invalidQuestion_throwsIllegalArgumentException() {
        JsonAdaptedQuestion test = new JsonAdaptedQuestion(false, " ", "");
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedQuestion(true, " ", DEFAULT_SOLUTION);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_invalidSolution_throwsIllegalArgumentException() {
        JsonAdaptedQuestion test = new JsonAdaptedQuestion(true, DEFAULT_QUESTION_NEWTON, "");
        assertThrows(IllegalValueException.class, test::toModelType);
    }
}

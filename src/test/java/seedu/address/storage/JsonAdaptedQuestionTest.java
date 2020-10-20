package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;
import seedu.address.model.student.question.UnsolvedQuestion;

public class JsonAdaptedQuestionTest {

    @Test
    public void constructor() {
        Question question = new UnsolvedQuestion("test");
        String expectedString = "0 | test";
        assertEquals(expectedString, new JsonAdaptedQuestion(question).getQuestion());

        question = new SolvedQuestion("test", "string");
        expectedString = "1 | test | string";
        assertEquals(expectedString, new JsonAdaptedQuestion(question).getQuestion());
    }

    @Test
    public void toModelType_invalidString_throwsIllegalArgumentException() {
        JsonAdaptedQuestion question = new JsonAdaptedQuestion("0 |   ");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("1 | ");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("- | test");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("2 | test");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("1 test");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("1 | test");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("1 |  | test");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("1 | test | ");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("1 | test | test | string");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("0 ");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("0 | test | something");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("");
        assertThrows(IllegalValueException.class, question::toModelType);
    }

    @Test
    public void toModelType_validString_success() throws IllegalValueException {
        String testString = "Hi";
        JsonAdaptedQuestion question = new JsonAdaptedQuestion("0 | " + testString);
        Question modelQuestion = new UnsolvedQuestion(testString);
        assertEquals(question.toModelType(), modelQuestion);

        String testQuestion = "test!";
        String testSolution = "131?";
        testString = testQuestion + " | " + testSolution;
        question = new JsonAdaptedQuestion("1 | " + testString);
        modelQuestion = new SolvedQuestion(testQuestion, testSolution);
        assertEquals(question.toModelType(), modelQuestion);

    }
}

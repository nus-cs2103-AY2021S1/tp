package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Question;

public class JsonAdaptedQuestionTest {

    @Test
    public void constructor() {
        Question question = new Question("test", false);
        String expectedString = "0 | test";
        assertEquals(expectedString, new JsonAdaptedQuestion(question).getQuestion());

        question = new Question("test | string", true);
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

        question = new JsonAdaptedQuestion("0 ");
        assertThrows(IllegalValueException.class, question::toModelType);

        question = new JsonAdaptedQuestion("");
        assertThrows(IllegalValueException.class, question::toModelType);
    }

    @Test
    public void toModelType_validString_success() throws IllegalValueException {
        String testString = "Hi";
        JsonAdaptedQuestion question = new JsonAdaptedQuestion("0 | " + testString);
        Question modelQuestion = new Question(testString, false);
        assertEquals(question.toModelType(), modelQuestion);

        testString = "test! | 131?";
        question = new JsonAdaptedQuestion("1 | " + testString);
        modelQuestion = new Question(testString, true);
        assertEquals(question.toModelType(), modelQuestion);

    }
}

package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class MultipleChoiceQuestionTest {

    @Test
    public void constructor_listStringChoices_success() {
        List<String> choicesList = new ArrayList<>();
        choicesList.add("Choice 1");
        choicesList.add("Choice 2");
        choicesList.add("Choice 3");
        String question = "Which choice is the answer?";
        Answer answer = new Answer("Choice 1");
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(question, choicesList, answer);
        assertEquals(question, multipleChoiceQuestion.getValue());
        assertEquals(choicesList,
            Arrays.stream(
                multipleChoiceQuestion.getChoices().get()).map((Object::toString)).collect(Collectors.toList()));

    }

    @Test
    public void isValidMultipleChoiceQuestion() {
        // null choice
        assertThrows(NullPointerException.class, () -> MultipleChoiceQuestion.isValidQuestion(null));

        // blank choice
        assertFalse(MultipleChoiceQuestion.isValidQuestion(""));
        assertFalse(MultipleChoiceQuestion.isValidQuestion(" "));

        // missing parts
        assertTrue(MultipleChoiceQuestion.isValidQuestion("How much is the cost of 1 DVD?"));
        assertTrue(MultipleChoiceQuestion.isValidQuestion("How much is a DVD!")); // with punctuation
        assertTrue(MultipleChoiceQuestion.isValidQuestion("DVDs 12345")); // with numbers
    }

}

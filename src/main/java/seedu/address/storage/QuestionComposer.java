package seedu.address.storage;

import java.util.List;

import seedu.address.model.quiz.Mcq;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.TrueFalse;

public class QuestionComposer {

    /**
     * Composes a Question.
     */
    public String composeQuestion(Question question) {
        String composedQuestion = "";
        if (question.isMcq()) {
            Mcq multipleChoice = (Mcq) (question);
            composedQuestion += multipleChoice.getPrompt();
            composedQuestion += "##";
            composedQuestion += multipleChoice.getAnswer();
            composedQuestion += "##";
            List<String> options = multipleChoice.getOptions();
            for (int i = 0; i < options.size() - 1; i++) {
                composedQuestion += options.get(i);
                composedQuestion += "#";
            }
            if (options.size() > 0) {
                composedQuestion += options.get(options.size() - 1);
            }
            return composedQuestion;
        } else {
            TrueFalse trueFalse = (TrueFalse) (question);
            composedQuestion += trueFalse.getPrompt();
            composedQuestion += "##";
            composedQuestion += trueFalse.getAnswer();
            return composedQuestion;
        }
    }
}

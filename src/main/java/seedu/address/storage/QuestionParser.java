package seedu.address.storage;

import java.util.ArrayList;

import seedu.address.model.quiz.Mcq;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.TrueFalse;

public class QuestionParser {

    /**
     * Parses a Question.
     */
    public Question parseQuestion(String stringQuestion) {
        String[] questionFields = stringQuestion.split("##");
        String prompt = questionFields[0];
        if (questionFields.length > 2) { // for MCQ
            int answer = Integer.parseInt(questionFields[1]);
            String[] options = questionFields[2].split("#");
            ArrayList<String> optionsList = new ArrayList<>();
            for (String option : options) {
                optionsList.add(option);
            }
            return new Mcq(prompt, answer, optionsList);
        } else {
            boolean answer = questionFields[1].equals("true");
            return new TrueFalse(prompt, answer);
        }
    }
}

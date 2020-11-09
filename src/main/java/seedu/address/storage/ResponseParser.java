package seedu.address.storage;

import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.model.quiz.UniqueResponseList;

public class ResponseParser {

    /**
     * Returns list of responses
     */
    public UniqueResponseList parseResponses(String stringResponse) {
        String[] stringResponses = stringResponse.split("\\|");
        UniqueResponseList responses = new UniqueResponseList();
        for (String s : stringResponses) {
            responses.add(parseResponseFields(s));
        }
        return responses;
    }

    /**
     * Returns parsed Response
     */
    public Response parseResponseFields(String stringResponse) {
        String[] responseFields = stringResponse.split("###");
        String response = responseFields[0];
        boolean isCorrect = responseFields[1].equals("true");
        Question question = new QuestionParser().parseQuestion(responseFields[2]);
        return new Response(response, question, isCorrect);
    }
}

package seedu.address.storage;

import seedu.address.model.quiz.Response;

public class ResponseComposer {

    /**
     * Composes a Response.
     */
    public String composeResponse(Response response) {
        String composedResponse = "";
        composedResponse += response.getResponse();
        composedResponse += "###";
        composedResponse += response.getIsCorrect();
        composedResponse += "###";
        QuestionComposer questionComposer = new QuestionComposer();
        composedResponse += questionComposer.composeQuestion(response.getQuestion());
        return composedResponse;
    }
}

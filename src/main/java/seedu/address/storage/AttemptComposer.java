package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Response;

public class AttemptComposer {

    /**
     * Composes Attempt.
     */
    public String composeAttempt(Attempt attempt) {
        String composedAttempt = "";
        composedAttempt += getFormattedTimestamp(attempt.getTimestamp());
        composedAttempt += "####";
        ResponseComposer responseComposer = new ResponseComposer();
        ObservableList<Response> responses = attempt.getResponses();
        for (int i = 0; i < responses.size() - 1; i++) {
            composedAttempt += responseComposer.composeResponse(responses.get(i));
            composedAttempt += "|";
        }
        if (responses.size() > 0) {
            composedAttempt += responseComposer.composeResponse(responses.get(responses.size() - 1));
        }
        return composedAttempt;
    }


    /**
     * Converts timestamp to formatted string.
     * @param timestamp LocalDateTime object
     * @return string representing formatted timestamp
     */
    private String getFormattedTimestamp(LocalDateTime timestamp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(timestamp);
    }
}

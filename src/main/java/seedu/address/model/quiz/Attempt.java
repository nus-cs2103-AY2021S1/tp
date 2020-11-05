package seedu.address.model.quiz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a record of user's responses to the respective questions within a quiz attempt.
 */
public class Attempt {
    private ArrayList<Response> responses;
    private LocalDateTime timestamp;

    /**
     * Constructor of Attempt
     */
    public Attempt() {
        if (this.responses == null) {
            this.responses = new ArrayList<>();
        }
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs Attempt;
     */
    public Attempt(ArrayList<Response> responses, LocalDateTime timestamp) {
        this.responses = responses;
        this.timestamp = timestamp;
    }

    /**
     * Adds a response to current attempt.
     * If response to question already exists, replace previous response.
     * @param newResponse Response to add.
     */
    public void addResponse(Response newResponse) {
        Question qn = newResponse.getQuestion();
        Iterator<Response> itr = responses.iterator();
        while (itr.hasNext()) {
            Response oldResponse = itr.next();
            if (qn.isSameQuestion(oldResponse.getQuestion())) {
                itr.remove();
            }
        }
        responses.add(newResponse);
    }

    /**
     * Calculates the total score of attempt based on number of correct responses.
     * @return value representing the score
     */
    public int calculateScore() {
        int score = 0;
        Iterator<Response> itr = responses.iterator();
        while (itr.hasNext()) {
            Response response = itr.next();
            if (response.getIsCorrect()) {
                score += 1;
            }
        }
        return score;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public int getNumOfResponses() {
        return responses.size();
    }

    public ArrayList<Response> getResponses() {
        return responses;
    }
}

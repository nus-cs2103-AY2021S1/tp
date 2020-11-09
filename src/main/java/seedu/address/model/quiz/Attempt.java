package seedu.address.model.quiz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;

/**
 * Represents a record of user's responses to the respective questions within a quiz attempt.
 */
public class Attempt implements Comparable<Attempt> {
    private UniqueResponseList responses;
    private LocalDateTime timestamp;

    /**
     * Constructor of Attempt
     */
    public Attempt() {
        if (this.responses == null) {
            this.responses = new UniqueResponseList();
        }
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs Attempt;
     */
    public Attempt(UniqueResponseList responses, LocalDateTime timestamp) {
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

    /**
     * Returns a more detailed attempt analysis.
     */
    public String attemptAnalysis() {
        int totalScore = 0;
        ArrayList<String> wrongResponses = new ArrayList<>();
        Iterator<Response> itr = responses.iterator();
        while (itr.hasNext()) {
            Response response = itr.next();
            if (response.getIsCorrect()) {
                totalScore += 1;
            } else {
                String wrongQuestion = "";
                wrongQuestion += System.lineSeparator();
                wrongQuestion += "Question: " + response.getQuestion().getQuestion();
                wrongQuestion += System.lineSeparator();
                wrongQuestion += "Your response: " + response.getResponse();
                wrongResponses.add(wrongQuestion);
            }
        }
        String formattedAnalysis = "";
        //formattedAnalysis += "Out of " + getNumOfResponses() + " responses, You got " + totalScore + " responses "
        //        + "correct.";
        formattedAnalysis += totalScore + " out of " + getNumOfResponses() + " attempted question(s) correct";
        formattedAnalysis += System.lineSeparator();
        //formattedAnalysis += "Wrong questions: " + System.lineSeparator();
        //        for (String wrongResponse : wrongResponses) {
        //            formattedAnalysis += wrongResponse + System.lineSeparator();
        //        }
        return formattedAnalysis;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public int getNumOfResponses() {
        return responses.size();
    }

    public ObservableList<Response> getResponses() {
        return responses.asUnmodifiableObservableList();
    }

    /**
     * Returns true if both attempts of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two questions.
     */
    public boolean isSameAttempt(Attempt otherAttempt) {
        if (otherAttempt == this) {
            return true;
        }
        return otherAttempt != null
                && otherAttempt.getTimestamp().equals(getTimestamp());
    }
    @Override
    public int compareTo(Attempt f2) {
        return timestamp.compareTo(f2.getTimestamp());
    }

    public boolean isEmpty() {
        return responses.size() <= 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Attempt)) {
            return false;
        }
        Attempt attempt = (Attempt) (other);
        System.out.println(attempt.getResponses());
        System.out.println(responses);
        return attempt.getTimestamp().equals(timestamp) && attempt.getResponses().equals(this.getResponses());
    }
}

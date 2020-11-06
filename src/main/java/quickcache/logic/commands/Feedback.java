package quickcache.logic.commands;

import java.util.Objects;
import java.util.Optional;

import quickcache.model.flashcard.Question;
import quickcache.model.flashcard.Statistics;

public class Feedback {

    private Boolean isCorrect;
    private String body;
    private Question question;
    private Statistics statistics;

    public Feedback(String body) {
        this.body = body;
    }

    public Optional<Question> getQuestion() {
        return Optional.ofNullable(question);
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Optional<Statistics> getStatistics() {
        return Optional.ofNullable(statistics);
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(body);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Optional<Boolean> isCorrect() {
        return Optional.ofNullable(isCorrect);
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return body;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Feedback otherFeedback = (Feedback) object;
        return isCorrect == otherFeedback.isCorrect
                && Objects.equals(body, otherFeedback.body)
                && Objects.equals(question, otherFeedback.question)
                && Objects.equals(statistics, otherFeedback.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCorrect, body, question, statistics);
    }
}

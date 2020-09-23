package seedu.address.flashcard;

import java.util.List;

/**
 * Represents a Flashcard. Each Flashcard will contain one question and
 * one answer. In addition, it can have multiple tags.
 */
public class Flashcard {
    final private Question question;
    final private Answer answer;
    final private List<Tag> tags;

    public Flashcard(Question question, Answer answer, List<Tag> tags) {
        this.question = question;
        this.answer = answer;
        this.tags = tags;
    }

    /**
     * Gets the question.
     * @return the question.
     */
    public String getQuestion() {
        return question.getQuestion();
    }

    /**
     * Gets the answer.
     * @return the answer.
     */
    public String getAnswer() {
        return answer.getAnswer();
    }

    /**
     * Checks the given userAnswer with the correct answer.
     * @param userAnswer the user's answer.
     * @return {@code true} if the user's answer is equal to the actual answer.
     */
    public boolean checkAnswer(String userAnswer) {
        return answer.checkAnswer(userAnswer);
    }

    /**
     * Gets the tags of this flashcard.
     * @return a list of tags.
     */
    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return String.format("Question:\n%s\nAnswer:\n%s", question.getQuestion(), answer.getAnswer());
    }
}

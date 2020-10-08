package seedu.address.flashcard;

import java.util.Set;

/**
 * Represents a Flashcard. Each Flashcard will contain one question and
 * one answer. In addition, it can have multiple tags.
 */
public class Flashcard {

    public static final String TIMES_TESTED_CONSTRAINTS = "Times tested should be a positive integer";
    public static final String TIMES_TESTED_LESS_THAN_TIMES_TESTED_CORRECT_CONSTRAINT = "Times tested should be more "
            + "than times tested correctly";

    private final Question question;
    private final Answer answer;
    private final Set<Tag> tags;
    private final int timesTested;
    private final int timesTestedCorrect;

    /**
     * A constructor to create flashcard object.
     * @param question Question of the flashcard.
     * @param answer Answer of the flashcard.
     * @param tags Tags of the flashcard.
     */
    public Flashcard(Question question, Answer answer, Set<Tag> tags) {
        this.question = question;
        this.answer = answer;
        this.tags = tags;
        this.timesTested = 0;
        this.timesTestedCorrect = 0;
    }

    /**
     * A constructor to create flashcard object.
     * @param question Question of the flashcard.
     * @param answer Answer of the flashcard.
     * @param tags Tags of the flashcard.
     * @param timesTested Number of times the flashcard is tested.
     * @param timesTestedCorrect Number of times the flashcard is tested correctly.
     */
    public Flashcard(Question question, Answer answer, Set<Tag> tags, int timesTested, int timesTestedCorrect) {
        if (timesTested < 0 || timesTestedCorrect < 0) {
            throw new IllegalArgumentException(TIMES_TESTED_CONSTRAINTS);
        }
        if (timesTested < timesTestedCorrect) {
            throw new IllegalArgumentException(TIMES_TESTED_LESS_THAN_TIMES_TESTED_CORRECT_CONSTRAINT);
        }
        this.question = question;
        this.answer = answer;
        this.tags = tags;
        this.timesTested = timesTested;
        this.timesTestedCorrect = timesTestedCorrect;
    }

    /**
     * Gets the question.
     * @return the question.
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Gets the answer.
     * @return the answer.
     */
    public Answer getAnswer() {
        return answer;
    }

    /**
     * Checks the given userAnswer with the correct answer.
     * @param userAnswer the user's answer.
     * @return {@code true} if the user's answer is equal to the actual answer.
     */
    public boolean checkAnswer(Answer userAnswer) {
        return answer.checkAnswer(userAnswer);
    }

    /**
     * Gets the tags of this flashcard.
     * @return a list of tags.
     */
    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return String.format("Question:\n%s\nAnswer:\n%s", question.getQuestion(), answer.getAnswer());
    }

    /**
     * A method to check if otherFlashcard is the same with the current one.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getQuestion().equals(getQuestion())
                && otherFlashcard.getAnswer().equals(getAnswer())
                && otherFlashcard.getTags().equals(getTags())
                && otherFlashcard.getTimesTested() == getTimesTested()
                && otherFlashcard.getTimesTestedCorrect() == getTimesTestedCorrect();
    }

    /**
     * Checks if this flashcard contains the tag.
     * @param tag the tag to be checked.
     * @return {@code true} if this flashcard has the tag.
     */
    public boolean matchTag(Tag tag) {
        return tags.contains(tag);
    }

    /**
     * A method to check if otherFlashcard is the same with the current one.
     */
    @Override
    public boolean equals(Object otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        } else if (otherFlashcard instanceof Flashcard) {
            Flashcard other = (Flashcard) otherFlashcard;
            return other.getAnswer().equals(getAnswer())
                    && other.getQuestion().equals(getQuestion())
                    && other.getTags().equals(getTags())
                    && other.getTimesTested() == getTimesTested()
                    && other.getTimesTestedCorrect() == getTimesTestedCorrect();
        }
        return false;
    }

    public int getTimesTested() {
        return timesTested;
    }

    public int getTimesTestedCorrect() {
        return timesTestedCorrect;
    }

    public Flashcard getFlashcardAfterTestSuccess() {
        return new Flashcard(question, answer, tags, timesTested + 1, timesTestedCorrect + 1);
    }

    public Flashcard getFlashcardAfterTestFailure() {
        return new Flashcard(question, answer, tags, timesTested + 1, timesTestedCorrect);
    }
}

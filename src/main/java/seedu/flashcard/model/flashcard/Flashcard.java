package seedu.flashcard.model.flashcard;

import static seedu.flashcard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Flashcard in the flashcard list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Question question;

    // Data fields
    private final Answer answer;
    private final Category category;
    private final Note note;

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Question question, Answer answer, Category category, Note note) {
        requireAllNonNull(question, answer, category, note);
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.note = note;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Category getCategory() {
        return category;
    }

    public Note getNote() {
        return note;
    }


    /**
     * Returns true if the input is the correct answer.
     */
    public boolean isCorrect(String inputAnswer) {
        return answer.toString().equals(inputAnswer);
    }

    /**
     * Returns true if both flashcards have the same question.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameQuestion(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getQuestion().equals(getQuestion());
    }

    /**
     * Returns true if both flashcards have the same identity and data fields.
     * This defines a stronger notion of equality between two flashcards.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Flashcard)) {
            return false;
        }

        Flashcard otherFlashcard = (Flashcard) other;

        return otherFlashcard.getQuestion().equals(getQuestion())
                && otherFlashcard.getAnswer().equals(getAnswer())
                && otherFlashcard.getCategory().equals(getCategory())
                && otherFlashcard.getNote().equals(getNote());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer, category);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Category: ")
                .append(getCategory())
                .append(" Question: ")
                .append(getQuestion())
                .append(" Answer: ")
                .append(getAnswer());
        if (getNote().toString().length() > 0) {
            builder.append(" Note: ")
                    .append(getNote());
        }
        return builder.toString();
    }

}

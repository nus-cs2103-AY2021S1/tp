package seedu.flashcard.model.flashcard;

import static seedu.flashcard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.flashcard.model.tag.Tag;

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
    private final Rating rating;
    private final Set<Tag> tags = new HashSet<>();
    private final Diagram diagram;
    private final Statistics statistics;

    // State fields
    private final boolean isFavourite;

    /**
     * Identity and Data fields must be present and not null.
     */
    public Flashcard(Question question, Answer answer, Category category,
                     Note note, Rating rating, Set<Tag> tags, Diagram diagram) {
        requireAllNonNull(question, answer, category, note, rating, tags, diagram);
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.note = note;
        this.rating = rating;
        this.tags.addAll(tags);
        this.diagram = diagram;
        this.isFavourite = false;
        this.statistics = new Statistics();
    }

    /**
     * Overloaded constructor for creating a flashcard.
     * All fields must be present and not null.
     */
    public Flashcard(Question question, Answer answer, Category category, Note note, Rating rating, Set<Tag> tags,
                     Diagram diagram, Statistics statistics, boolean isFavourite) {
        requireAllNonNull(question, answer, category, note, rating, diagram, statistics, isFavourite);
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.note = note;
        this.rating = rating;
        this.tags.addAll(tags);
        this.diagram = diagram;
        this.isFavourite = isFavourite;
        this.statistics = statistics;
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

    public Rating getRating() {
        return rating;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public boolean isFavourite() {
        return isFavourite;
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
                && otherFlashcard.getNote().equals(getNote())
                && otherFlashcard.getRating().equals(getRating())
                && otherFlashcard.getTags().equals(getTags())
                && otherFlashcard.getDiagram().equals(getDiagram())
                && otherFlashcard.getStatistics().equals(getStatistics())
                && otherFlashcard.isFavourite() == isFavourite();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer, category, note, rating, diagram, statistics, isFavourite);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Question: ")
                .append(getQuestion());

        return builder.toString();
    }

}

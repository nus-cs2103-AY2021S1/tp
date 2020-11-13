package seedu.bagel.model.flashcard;

import static seedu.bagel.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.bagel.model.tag.Tag;

/**
 * Represents a Flashcard in Bagel list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Title title;

    // Data fields
    private final Description description;
    private final Link link;
    private final FlashcardSet flashcardSet;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Title title, Description description, Link link, FlashcardSet flashcardSet, Set<Tag> tags) {
        requireAllNonNull(title, description, link, flashcardSet, tags);
        this.title = title;
        this.description = description;
        this.link = link;
        this.flashcardSet = flashcardSet;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return this.title;
    }

    public Description getDescription() {
        return this.description;
    }

    public Link getLink() {
        return this.link;
    }

    /**
     * Returns an immutable set of FlashcardSets the flashcard belongs to, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public FlashcardSet getFlashcardSet() {
        return this.flashcardSet;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both flashcards of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getTitle().equals(getTitle());
//                && otherFlashcard.getDescription().equals(getDescription())
//                && otherFlashcard.getFlashcardSet().equals(getFlashcardSet());
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
        return otherFlashcard.getTitle().equals(getTitle())
                && otherFlashcard.getDescription().equals(getDescription())
                && otherFlashcard.getLink().equals(getLink())
                && otherFlashcard.getFlashcardSet().equals(getFlashcardSet())
                && otherFlashcard.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, link, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("\nDescription: ")
                .append(getDescription())
                .append("\nLink: ")
                .append(getLink())
                .append("\nSet: ")
                .append(getFlashcardSet())
                .append("\nTags: ");
        getTags().forEach(builder::append);

        return builder.toString();
    }

}

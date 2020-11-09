package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Flashcard in the flashcard folder.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard implements Comparable<Flashcard> {

    // Identity fields
    private final Title title;

    // Data fields
    private final Definition definition;
    private Definition visibleDefinition;
    private final Priority priority;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Title title, Definition definition, Set<Tag> tags, Priority priority) {
        requireAllNonNull(title, definition, tags, priority);
        this.title = title;
        this.definition = definition;
        this.visibleDefinition = new Definition();
        this.tags.addAll(tags);
        this.priority = priority;
    }

    public Title getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public Definition getDefinition() {
        return definition;
    }

    public Definition getVisibleDefinition() {
        return visibleDefinition;
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
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }
        return otherFlashcard != null
                && otherFlashcard.getTitle().equals(getTitle());
    }

    /**
     * Switches between an empty "hidden" displayed definition and the full definition of the flashcard.
     */
    public void toggleDefinition() {
        if (this.visibleDefinition.toString().equals("")) {
            this.visibleDefinition = this.definition;
        } else {
            this.visibleDefinition = new Definition();
        }
    }

    /**
     * Returns true if the flashcard is flipped to show its definition.
     * @return boolean value to indicate if flashcard is currently flipped.
     */
    public boolean isFlipped() {
        return this.definition.equals(this.visibleDefinition);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
                && otherFlashcard.getDefinition().equals(getDefinition())
                && otherFlashcard.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, definition, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Definition: ")
                .append(getDefinition())
                .append(" Priority: ")
                .append(getPriority())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    public int compareTo(Flashcard other) {
        return this.priority.compareTo(other.priority);
    }
}

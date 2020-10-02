package seedu.address.flashcard;

/**
 * Represents a MCQ choice.
 */
public class Choice {
    final private String content;

    public Choice(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    /**
     * Compares this choice to the specified object.
     * The result is true if and only if the argument is not null and is a
     * Choice object that has the same contents as this object
     * @param o the object to compare this Choice against
     * @return {@code true} if the given object represents a Choice equivalent to this choice, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Choice) {
            Choice other = (Choice) o;
            return content.equals(other.content);
        } else {
            return false;
        }
    }
}

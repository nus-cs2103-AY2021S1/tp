package seedu.bookmark.model.wordstore;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Word {
    private static final int STARTING_COUNT = 1;
    private String word;
    private int count;
    private int distance;

    /**
     * Creates a word object with default field
     */
    public Word(String word) {
        requireNonNull(word);
        this.word = word;
        this.count = STARTING_COUNT;
        this.distance = 0;
    };

    /**
     * Create a word object with a set Distance
     */
    public Word(String word, int distance) {
        requireNonNull(word);
        this.word = word;
        this.count = STARTING_COUNT;
        this.distance = distance;
    }

    /**
     * Returns the word
     * @return word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Returns the count of the word
     * @return count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Increases the count by 1
     */
    public void addCount() {
        this.count++;
    }

    /**
     * Decrease the count by 1
     */
    public void minusCount() {
        this.count--;
    }


    /**
     * Returns the distance of a word
     * @return int
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * Sets the distance of a word
     * @param distance int
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Returns true if both words have the same {@code String word}
     * This defines a weaker notion of equality between two words.
     */
    public boolean isSameWord(Word otherWord) {
        if (otherWord == this) {
            return true;
        }

        return otherWord != null
                && otherWord.getWord().equals(this.getWord());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Word)) {
            return false;
        }

        Word otherWord = (Word) other;
        return otherWord.getWord().equals(this.getWord())
                && otherWord.getCount() == this.getCount()
                && otherWord.getDistance() == this.getDistance();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.word, this.count, this.distance);
    }

    @Override
    public String toString() {
        return "Word: " + this.word + ", Count: " + this.count + ", Distance: " + this.distance;
    }
}

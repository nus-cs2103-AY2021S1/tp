package seedu.bagel.testutil;

import seedu.bagel.model.Bagel;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * A utility class to help with building Bagel objects.
 * Example usage: <br>
 *     {@code Bagel bagel = new BagelBuilder().withFlashcard("John", "Doe").build();}
 */
public class BagelBuilder {

    private Bagel bagel;

    public BagelBuilder() {
        bagel = new Bagel();
    }

    public BagelBuilder(Bagel bagel) {
        this.bagel = bagel;
    }

    /**
     * Adds a new {@code Flashcard} to the {@code Bagel} that we are building.
     */
    public BagelBuilder withFlashcard(Flashcard flashcard) {
        bagel.addFlashcard(flashcard);
        return this;
    }

    public Bagel build() {
        return bagel;
    }
}

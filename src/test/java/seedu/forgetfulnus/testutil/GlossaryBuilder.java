package seedu.forgetfulnus.testutil;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * A utility class to help with building Glossary objects.
 * Example usage: <br>
 *     {@code Glossary ab = new GlossaryBuilder().withPerson("John", "Doe").build();}
 */
public class GlossaryBuilder {

    private Glossary glossary;

    public GlossaryBuilder() {
        glossary = new Glossary();
    }

    public GlossaryBuilder(Glossary glossary) {
        this.glossary = glossary;
    }

    /**
     * Adds a new {@code FlashCard} to the {@code Glossary} that we are building.
     */
    public GlossaryBuilder withFlashCard(FlashCard flashCard) {
        glossary.addFlashCard(flashCard);
        return this;
    }

    public Glossary build() {
        return glossary;
    }
}

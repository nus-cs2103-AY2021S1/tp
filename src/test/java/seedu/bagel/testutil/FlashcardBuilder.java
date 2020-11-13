package seedu.bagel.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;
import seedu.bagel.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_TITLE = "Alice Pauline";
    public static final String DEFAULT_DESCRIPTION = "456, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_LINK = "";
    public static final String DEFAULT_SET = "1";

    private Title title;
    private Description description;
    private Link link;
    private FlashcardSet flashcardSet;
    private Set<Tag> tags;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        link = new Link(DEFAULT_LINK);
        flashcardSet = new FlashcardSet(DEFAULT_SET);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        title = flashcardToCopy.getTitle();
        description = flashcardToCopy.getDescription();
        flashcardSet = flashcardToCopy.getFlashcardSet();
        link = flashcardToCopy.getLink();
        tags = new HashSet<>(flashcardToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code flashcardSet} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withSet(String flashcardSet) {
        this.flashcardSet = new FlashcardSet(flashcardSet);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withLink(String link) {
        this.link = new Link(link);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(title, description, link, flashcardSet, tags);
    }

}

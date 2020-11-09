package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Priority;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Flashcard objects.
 */
public class FlashcardBuilder {

    public static final String DEFAULT_NAME = "AVL Tree";
    public static final String DEFAULT_DEFINITION = "<DEFAULT DEFINITION> placeholder";
    public static final String DEFAULT_PRIORITY = "";

    private Title title;
    private Definition definition;
    private Set<Tag> tags;
    private Priority priority;

    /**
     * Creates a {@code FlashcardBuilder} with the default details.
     */
    public FlashcardBuilder() {
        title = new Title(DEFAULT_NAME);
        definition = new Definition(DEFAULT_DEFINITION);
        priority = Priority.identifyPriority(DEFAULT_PRIORITY);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FlashcardBuilder with the data of {@code flashcardToCopy}.
     */
    public FlashcardBuilder(Flashcard flashcardToCopy) {
        title = flashcardToCopy.getTitle();
        definition = flashcardToCopy.getDefinition();
        tags = new HashSet<>(flashcardToCopy.getTags());
        priority = flashcardToCopy.getPriority();
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
     * Sets the {@code Definition} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withDefinition(String definition) {
        this.definition = new Definition(definition);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Flashcard} that we are building.
     */
    public FlashcardBuilder withPriority(String priority) {
        this.priority = Priority.identifyPriority(priority);
        return this;
    }

    public Flashcard build() {
        return new Flashcard(title, definition, tags, priority);
    }

}

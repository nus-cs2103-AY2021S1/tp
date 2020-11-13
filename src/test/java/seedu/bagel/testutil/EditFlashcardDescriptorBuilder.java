package seedu.bagel.testutil;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.bagel.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;

/**
 * A utility class to help with building EditFlashcardDescriptor objects.
 */
public class EditFlashcardDescriptorBuilder {

    private EditFlashcardDescriptor descriptor;

    public EditFlashcardDescriptorBuilder() {
        descriptor = new EditFlashcardDescriptor();
    }

    public EditFlashcardDescriptorBuilder(EditFlashcardDescriptor descriptor) {
        this.descriptor = new EditFlashcardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFlashcardDescriptor} with fields containing {@code flashcard}'s details
     */
    public EditFlashcardDescriptorBuilder(Flashcard flashcard) {
        descriptor = new EditFlashcardDescriptor();
        descriptor.setTitle(flashcard.getTitle());
        descriptor.setDescription(flashcard.getDescription());
        descriptor.setFlashcardSet(flashcard.getFlashcardSet());
        descriptor.setLink(flashcard.getLink());
        descriptor.setFlashcardSet(flashcard.getFlashcardSet());
        descriptor.setTags(flashcard.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withLink(String link) {
        descriptor.setLink(new Link(link));
        return this;
    }

    /**
     * Sets the {@code FlashcardSet} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withSet(String flashcardSet) {
        descriptor.setFlashcardSet(new FlashcardSet(flashcardSet));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditFlashcardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditFlashcardDescriptor build() {
        return descriptor;
    }
}

package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.Priority;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
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
     * Returns an {@code EditPersonDescriptor} with fields containing {@code flashcard}'s details
     */
    public EditFlashcardDescriptorBuilder(Flashcard flashcard) {
        descriptor = new EditFlashcardDescriptor();
        descriptor.setTitle(flashcard.getTitle());
        descriptor.setDefinition(flashcard.getDefinition());
        descriptor.setPriority(flashcard.getPriority());
        descriptor.setTags(flashcard.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withName(String name) {
        descriptor.setTitle(new Title(name));
        return this;
    }

    /**
     * Sets the {@code Definition} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withDefinition(String definition) {
        descriptor.setDefinition(new Definition(definition));
        return this;
    }
    /**
     * Sets the {@code Definition} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(Priority.identifyPriority(priority));
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

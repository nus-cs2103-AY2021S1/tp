package seedu.forgetfulnus.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.forgetfulnus.logic.commands.EditCommand.EditFlashCardDescriptor;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.flashcard.Order;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * A utility class to help with building EditFlashCardDescriptor objects.
 */
public class EditFlashCardDescriptorBuilder {

    private EditFlashCardDescriptor descriptor;

    public EditFlashCardDescriptorBuilder() {
        descriptor = new EditFlashCardDescriptor();
    }

    public EditFlashCardDescriptorBuilder(EditFlashCardDescriptor descriptor) {
        this.descriptor = new EditFlashCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFlashCardDescriptor} with fields containing {@code flashCard}'s details
     */
    public EditFlashCardDescriptorBuilder(FlashCard flashCard) {
        descriptor = new EditFlashCardDescriptor();
        descriptor.setGermanPhrase(flashCard.getGermanPhrase());
        descriptor.setEnglishPhrase(flashCard.getEnglishPhrase());
        descriptor.setDifficultyTag(flashCard.getDifficultyTag());
        descriptor.setGenderTag(flashCard.getGenderTag());
        descriptor.setTags(flashCard.getTags());
        descriptor.setOrder(flashCard.getOrder());
    }

    /**
     * Sets the {@code German Phrase} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withGermanPhrase(String germanPhrase) {
        descriptor.setGermanPhrase(new GermanPhrase(germanPhrase));
        return this;
    }

    /**
     * Sets the {@code English Phrase} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withEnglishPhrase(String englishPhrase) {
        descriptor.setEnglishPhrase(new EnglishPhrase(englishPhrase));
        return this;
    }

    /**
     * Sets the {@code Difficulty Tag} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withDifficultyTag(String difficultyTag) {
        descriptor.setDifficultyTag(new DifficultyTag(difficultyTag));
        return this;
    }

    /**
     * Sets the {@code Gender Tag} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withGenderTag(String genderTag) {
        descriptor.setGenderTag(new GenderTag(genderTag));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditFlashCardDescriptor}
     * that we are building.
     */
    public EditFlashCardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }
    /**
     * Sets the {@code Order} of the {@code EditFlashCardDescriptor} that we are building.
     */
    public EditFlashCardDescriptorBuilder withOrder(String order) {
        descriptor.setOrder(new Order(Integer.parseInt(order)));
        return this;
    }

    public EditFlashCardDescriptor build() {
        return descriptor;
    }
}

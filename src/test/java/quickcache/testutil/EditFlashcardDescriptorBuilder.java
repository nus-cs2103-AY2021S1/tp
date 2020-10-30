package quickcache.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import quickcache.logic.commands.EditCommand;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.MultipleChoiceQuestion;
import quickcache.model.flashcard.Tag;


/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditFlashcardDescriptorBuilder {

    private final EditCommand.EditFlashcardDescriptor descriptor;

    public EditFlashcardDescriptorBuilder() {
        descriptor = new EditCommand.EditFlashcardDescriptor();
    }

    public EditFlashcardDescriptorBuilder(EditCommand.EditFlashcardDescriptor descriptor) {
        this.descriptor = new EditCommand.EditFlashcardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditFlashcardDescriptorBuilder(Flashcard flashcard) {
        descriptor = new EditCommand.EditFlashcardDescriptor();
        descriptor.setQuestion(flashcard.getQuestion().getValue());
        descriptor.setTags(flashcard.getTags());
        descriptor.setAnswer(flashcard.getAnswer());
        if (flashcard.getQuestion() instanceof MultipleChoiceQuestion) {
            descriptor.setChoices(flashcard.getQuestion().getChoices().get());
        }
    }

    /**
     * Sets the {@code question} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(question);
        return this;
    }

    /**
     * Sets the {@code answer} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }

    /**
     * Sets the {@code choices} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withChoices(Choice[] choices) {
        descriptor.setChoices(choices);
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

    public EditCommand.EditFlashcardDescriptor build() {
        return descriptor;
    }

    public EditCommand.EditFlashcardDescriptor buildWithNoChoices(){
        EditCommand.EditFlashcardDescriptor finalDescriptor = new EditCommand.EditFlashcardDescriptor();
        if (this.descriptor.getQuestion().isPresent()) {
            finalDescriptor.setQuestion(this.descriptor.getQuestion().get());
        }
        if (this.descriptor.getAnswer().isPresent()) {
            finalDescriptor.setAnswer(this.descriptor.getAnswer().get());
        }
        if (this.descriptor.getDifficulty().isPresent()) {
            finalDescriptor.setDifficulty(this.descriptor.getDifficulty().get());
        }
        if (this.descriptor.getTags().isPresent()) {
            finalDescriptor.setTags(this.descriptor.getTags().get());
        }
        return finalDescriptor;
    }
}

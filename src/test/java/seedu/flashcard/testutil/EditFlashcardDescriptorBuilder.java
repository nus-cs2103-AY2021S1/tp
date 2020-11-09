package seedu.flashcard.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.flashcard.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Note;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.tag.Tag;

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
        descriptor.setQuestion(flashcard.getQuestion());
        descriptor.setAnswer(flashcard.getAnswer());
        descriptor.setCategory(flashcard.getCategory());
        descriptor.setNote(flashcard.getNote());
        descriptor.setRating(flashcard.getRating());
        descriptor.setTags(flashcard.getTags());
        descriptor.setDiagram(flashcard.getDiagram());
    }

    /**
     * Sets the {@code Question} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(new Question(question));
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Diagram} of the {@code EditFlashcardDescriptor} that we are building.
     */
    public EditFlashcardDescriptorBuilder withDiagram(String diagramFilePath) {
        descriptor.setDiagram(new Diagram(diagramFilePath));
        return this;
    }

    public EditFlashcardDescriptor build() {
        return descriptor;
    }
}

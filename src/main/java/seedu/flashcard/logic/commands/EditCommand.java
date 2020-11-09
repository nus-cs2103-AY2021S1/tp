package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_DIAGRAM;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.commons.util.CollectionUtil;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Diagram;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Note;
import seedu.flashcard.model.flashcard.Question;
import seedu.flashcard.model.flashcard.Rating;
import seedu.flashcard.model.flashcard.Statistics;
import seedu.flashcard.model.tag.Tag;


/**
 * Edits the details of an existing flashcard in the flashcard deck.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
            + "by the index number used in the displayed flashcard list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer greater than 0) "
            + "[" + PREFIX_QUESTION + "QUESTION] "
            + "[" + PREFIX_ANSWER + "ANSWER] "
            + "[" + PREFIX_CATEGORY + "CATEGORY]"
            + "[" + PREFIX_NOTE + "NOTE]"
            + "[" + PREFIX_RATING + "RATING]"
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_DIAGRAM + "DIAGRAM]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_QUESTION + "What does the S in SOLID stand for? "
            + PREFIX_ANSWER + "Single responsibility principle";

    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited Flashcard: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in the flashcard deck.";

    private final Index index;
    private final EditFlashcardDescriptor editFlashcardDescriptor;

    /**
     * @param index of the flashcard in the filtered flashcard list to edit
     * @param editFlashcardDescriptor details to edit the flashcard with
     */
    public EditCommand(Index index, EditFlashcardDescriptor editFlashcardDescriptor) {
        requireNonNull(index);
        requireNonNull(editFlashcardDescriptor);

        this.index = index;
        this.editFlashcardDescriptor = new EditFlashcardDescriptor(editFlashcardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        Flashcard flashcardToEdit = lastShownList.get(index.getZeroBased());
        Flashcard editedFlashcard = createEditedFlashcard(flashcardToEdit, editFlashcardDescriptor);

        if (!flashcardToEdit.isSameQuestion(editedFlashcard) && model.hasFlashcard(editedFlashcard)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.setFlashcard(flashcardToEdit, editedFlashcard);
        return new CommandResult(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard));
    }

    /**
     * Creates and returns a {@code Flashcard} with the details of {@code flashcardToEdit}
     * edited with {@code editFlashcardDescriptor}.
     */
    private static Flashcard createEditedFlashcard(Flashcard flashcardToEdit,
                                                   EditFlashcardDescriptor editFlashcardDescriptor) {
        assert flashcardToEdit != null;

        Question updatedQuestion = editFlashcardDescriptor.getQuestion().orElse(flashcardToEdit.getQuestion());
        Answer updatedAnswer = editFlashcardDescriptor.getAnswer().orElse(flashcardToEdit.getAnswer());
        Category updatedCategory = editFlashcardDescriptor.getCategory().orElse(flashcardToEdit.getCategory());
        Note updatedNote = editFlashcardDescriptor.getNote().orElse(flashcardToEdit.getNote());
        Rating updatedRating = editFlashcardDescriptor.getRating().orElse(flashcardToEdit.getRating());
        Set<Tag> updatedTags = editFlashcardDescriptor.getTags().orElse(flashcardToEdit.getTags());
        Diagram updatedDiagram = editFlashcardDescriptor.getDiagram().orElse(flashcardToEdit.getDiagram());
        Statistics statistics = flashcardToEdit.getStatistics();
        boolean isFavourite = flashcardToEdit.isFavourite();
        return new Flashcard(updatedQuestion, updatedAnswer, updatedCategory, updatedNote, updatedRating,
                updatedTags, updatedDiagram, statistics, isFavourite);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editFlashcardDescriptor.equals(e.editFlashcardDescriptor);
    }

    /**
     * Stores the details to edit the flashcard with. Each non-empty field value will replace the
     * corresponding field value of the flashcard.
     */
    public static class EditFlashcardDescriptor {
        private Question question;
        private Answer answer;
        private Category category;
        private Note note;
        private Rating rating;
        private Set<Tag> tags;
        private Diagram diagram;

        public EditFlashcardDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFlashcardDescriptor(EditFlashcardDescriptor toCopy) {
            setQuestion(toCopy.question);
            setAnswer(toCopy.answer);
            setCategory(toCopy.category);
            setNote(toCopy.note);
            setRating(toCopy.rating);
            setTags(toCopy.tags);
            setDiagram(toCopy.diagram);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(question, answer, category, note, rating, tags, diagram);
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public Optional<Question> getQuestion() {
            return Optional.ofNullable(question);
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Optional<Answer> getAnswer() {
            return Optional.ofNullable(answer);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Optional<Category> getCategory() {
            return Optional.ofNullable(category);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setDiagram(Diagram diagram) {
            this.diagram = diagram;
        }

        public Optional<Diagram> getDiagram() {
            return Optional.ofNullable(diagram);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFlashcardDescriptor)) {
                return false;
            }

            // state check
            EditFlashcardDescriptor e = (EditFlashcardDescriptor) other;

            return getQuestion().equals(e.getQuestion())
                    && getAnswer().equals(e.getAnswer())
                    && getCategory().equals(e.getCategory())
                    && getNote().equals(e.getNote())
                    && getRating().equals(e.getRating())
                    && getTags().equals(e.getTags())
                    && getDiagram().equals(e.getDiagram());
        }
    }
}

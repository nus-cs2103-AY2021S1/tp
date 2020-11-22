package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.forgetfulnus.model.Model.PREDICATE_SHOW_ALL_FLASHCARDS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.commons.util.CollectionUtil;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.flashcard.Order;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Edits the details of an existing flashcard in the glossary.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
            + "by the index number used in the displayed flashcard list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited FlashCard: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in the glossary.";

    public static final String QUIZ_MODE_REMINDER = "Flashcards cannot be edited in quiz mode. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    private final Index index;

    private final EditFlashCardDescriptor editFlashCardDescriptor;

    /**
     * @param index of the flashcard in the filtered flashcard list to edit
     * @param editFlashCardDescriptor details to edit the flashcard with
     */
    public EditCommand(Index index, EditFlashCardDescriptor editFlashCardDescriptor) {
        requireNonNull(index);
        requireNonNull(editFlashCardDescriptor);

        this.index = index;
        this.editFlashCardDescriptor = new EditFlashCardDescriptor(editFlashCardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        FlashCard flashCardToEdit = lastShownList.get(index.getZeroBased());
        FlashCard editedFlashCard = createEditedFlashCard(flashCardToEdit, editFlashCardDescriptor);

        if (!flashCardToEdit.isSameFlashCard(editedFlashCard) && model.hasFlashCard(editedFlashCard)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.setFlashCard(flashCardToEdit, editedFlashCard);
        model.updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
        return new CommandResult(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashCard));
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }

    /**
     * Creates and returns a {@code FlashCard} with the details of {@code flashCardToEdit}
     * edited with {@code editFlashCardDescriptor}.
     */
    private static FlashCard createEditedFlashCard(FlashCard flashCardToEdit,
                                                   EditFlashCardDescriptor editFlashCardDescriptor) {
        assert flashCardToEdit != null;

        GermanPhrase updatedGermanPhrase = editFlashCardDescriptor
                .getGermanPhrase()
                .orElse(flashCardToEdit.getGermanPhrase());
        EnglishPhrase updatedEnglishPhrase = editFlashCardDescriptor
                .getEnglishPhrase()
                .orElse(flashCardToEdit.getEnglishPhrase());
        DifficultyTag updatedDifficultyTag = editFlashCardDescriptor
                .getDifficultyTag()
                .orElse(flashCardToEdit.getDifficultyTag());
        GenderTag updatedGenderTag = editFlashCardDescriptor
                .getGenderTag()
                .orElse(flashCardToEdit.getGenderTag());
        Set<Tag> updatedTags = editFlashCardDescriptor.getTags().orElse(flashCardToEdit.getTags());

        Order unchangedOrder = flashCardToEdit.getOrder(); // User can never edit Order

        return new FlashCard(updatedGermanPhrase, updatedEnglishPhrase,
                updatedDifficultyTag, updatedGenderTag, updatedTags, unchangedOrder);
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
                && editFlashCardDescriptor.equals(e.editFlashCardDescriptor);
    }

    /**
     * Stores the details to edit the flashcard with. Each non-empty field value will replace the
     * corresponding field value of the flashcard.
     */
    public static class EditFlashCardDescriptor {
        private GermanPhrase germanPhrase;
        private EnglishPhrase englishPhrase;
        private DifficultyTag difficultyTag;
        private GenderTag genderTag;
        private Set<Tag> tags;
        private Order order;

        public EditFlashCardDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFlashCardDescriptor(EditFlashCardDescriptor toCopy) {
            setGermanPhrase(toCopy.germanPhrase);
            setEnglishPhrase(toCopy.englishPhrase);
            setDifficultyTag(toCopy.difficultyTag);
            setGenderTag(toCopy.genderTag);
            setTags(toCopy.tags);
            setOrder(toCopy.order);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(germanPhrase, englishPhrase, difficultyTag, genderTag, tags);
        }

        public void setGermanPhrase(GermanPhrase germanPhrase) {
            this.germanPhrase = germanPhrase;
        }

        public Optional<GermanPhrase> getGermanPhrase() {
            return Optional.ofNullable(germanPhrase);
        }

        public void setEnglishPhrase(EnglishPhrase englishPhrase) {
            this.englishPhrase = englishPhrase;
        }

        public Optional<EnglishPhrase> getEnglishPhrase() {
            return Optional.ofNullable(englishPhrase);
        }

        public void setDifficultyTag(DifficultyTag difficultyTag) {
            this.difficultyTag = difficultyTag;
        }

        public Optional<DifficultyTag> getDifficultyTag() {
            return Optional.ofNullable(difficultyTag);
        }

        public void setGenderTag(GenderTag genderTag) {
            this.genderTag = genderTag;
        }

        public Optional<GenderTag> getGenderTag() {
            return Optional.ofNullable(genderTag);
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public Optional<Order> getOrder() {
            return Optional.ofNullable(order);
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFlashCardDescriptor)) {
                return false;
            }

            // state check
            EditFlashCardDescriptor e = (EditFlashCardDescriptor) other;

            return getGermanPhrase().equals(e.getGermanPhrase())
                    && getEnglishPhrase().equals(e.getEnglishPhrase())
                    && getDifficultyTag().equals(e.getDifficultyTag())
                    && getGenderTag().equals(e.getGenderTag())
                    && getTags().equals(e.getTags());
        }
    }
}

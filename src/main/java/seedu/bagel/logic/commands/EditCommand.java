package seedu.bagel.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.bagel.commons.core.Messages;
import seedu.bagel.commons.core.index.Index;
import seedu.bagel.commons.util.CollectionUtil;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.model.Model;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
            + "by the index number used in the displayed flashcard list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DESC + "DESCRIPTION] "
            + "[" + PREFIX_LINK + "LINK] "
            + "[" + PREFIX_SET + "SET] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "91234567 "
            + PREFIX_DESC + "johndoe@example.com "
            + PREFIX_SET + "1 "
            + PREFIX_TAG + "friend\n";

    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited Flashcard: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n" + MESSAGE_USAGE;
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in Bagel.";

    private final Index index;
    private final EditFlashcardDescriptor editFlashcardDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editFlashcardDescriptor details to edit the person with
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

        if (!flashcardToEdit.isSameFlashcard(editedFlashcard) && model.hasFlashcard(editedFlashcard)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.setFlashcard(flashcardToEdit, editedFlashcard);
        model.updateFilteredFlashcardList(Model.PREDICATE_SHOW_ALL_FLASHCARDS);

        return new CommandResult(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard),
                false, false, true);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editFlashcardDescriptor}.
     */
    private static Flashcard createEditedFlashcard(Flashcard flashcardToEdit,
                                                   EditFlashcardDescriptor editFlashcardDescriptor) {
        assert flashcardToEdit != null;

        Title updatedTitle = editFlashcardDescriptor.getTitle().orElse(flashcardToEdit.getTitle());
        Description updatedDescription = editFlashcardDescriptor.getDescription()
                .orElse(flashcardToEdit.getDescription());

        Link updatedLink = editFlashcardDescriptor.getLink().orElse(flashcardToEdit.getLink());
        FlashcardSet updatedFlashcardSet = editFlashcardDescriptor.getFlashcardSet()
                .orElse(flashcardToEdit.getFlashcardSet());

        Set<Tag> updatedTags = new HashSet<>();
        editFlashcardDescriptor.getTags().ifPresentOrElse(newTags -> {
            if (newTags.size() != 0) {
                updatedTags.addAll(newTags);
                updatedTags.addAll(flashcardToEdit.getTags());
            }
        }, () -> updatedTags.addAll(flashcardToEdit.getTags()));

        return new Flashcard(updatedTitle, updatedDescription, updatedLink, updatedFlashcardSet, updatedTags);
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
        private Title title;
        private Description description;
        private Link link;
        private FlashcardSet flashcardSet;
        private Set<Tag> tags;

        public EditFlashcardDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} and {@code flashcardSets} is used internally.
         */
        public EditFlashcardDescriptor(EditFlashcardDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setLink(toCopy.link);
            setFlashcardSet(toCopy.flashcardSet);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, link, flashcardSet, tags);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
        * Sets {@code flashcardSet} to this object's {@code flashcardSet}.
        * A defensive copy of {@code flashcardSet} is used internally.
        */
        public void setFlashcardSet(FlashcardSet flashcardSet) {
            this.flashcardSet = flashcardSet;
        }

        /**
        * Returns an unmodifiable flashcardSet, which throws {@code UnsupportedOperationException}
        * if modification is attempted.
        * Returns {@code Optional#empty()} if {@code flashcardSet} is null.
        */
        public Optional<FlashcardSet> getFlashcardSet() {
            return Optional.ofNullable(flashcardSet);
        }

        public void setLink(Link link) {
            this.link = link;
        }

        public Optional<Link> getLink() {
            return Optional.ofNullable(link);
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
            if (!(other instanceof EditFlashcardDescriptor)) {
                return false;
            }

            // state check
            EditFlashcardDescriptor e = (EditFlashcardDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getLink().equals(e.getLink())
                    && getFlashcardSet().equals(e.getFlashcardSet())
                    && getTags().equals(e.getTags());
        }
    }
}

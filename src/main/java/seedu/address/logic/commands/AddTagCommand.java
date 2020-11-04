package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "tag add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the person specified.\n"
            + "Parameters: NAME (must be name of person existing in ModDuke) "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + "Roy "
            + PREFIX_TAG + "classmate";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Tagged Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_INVALID_TAG = "Person can only have either prof or ta tag.";

    private final Name targetName;
    private final TagPersonDescriptor tagPersonDescriptor;

    /**
     * @param targetName of the person in the filtered person list to tag
     * @param tagPersonDescriptor of the tag to be added
     */
    public AddTagCommand(Name targetName, TagPersonDescriptor tagPersonDescriptor) {
        requireNonNull(targetName);
        requireNonNull(tagPersonDescriptor);

        this.targetName = targetName;
        this.tagPersonDescriptor = new TagPersonDescriptor(tagPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isValidContact = model.hasPersonName(targetName);

        if (!isValidContact) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
        }

        List<Person> filteredList = model.getFilteredPersonList().stream()
                .filter(person -> person.isSameName(targetName)).collect(Collectors.toList());
        Person personToTag = filteredList.get(0);

        if (!checkTag(personToTag, tagPersonDescriptor)) {
            throw new CommandException(MESSAGE_INVALID_TAG);
        }

        Person taggedPerson = createTaggedPerson(personToTag, tagPersonDescriptor);

        if (!personToTag.isSamePerson(taggedPerson) && model.hasPerson(taggedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToTag, taggedPerson);

        // update meeting book
        model.updatePersonInMeetingBook(personToTag, taggedPerson);

        // update module book
        model.updatePersonInModuleBook(personToTag, taggedPerson);

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, taggedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToTag}
     * edited with {@code tagPersonDescriptor}.
     */
    private static Person createTaggedPerson(Person personToTag, TagPersonDescriptor tagPersonDescriptor) {
        assert personToTag != null;

        Set<Tag> updatedTags = new HashSet<>(personToTag.getTags());

        if (tagPersonDescriptor.getTags().isPresent()) {
            updatedTags.addAll(tagPersonDescriptor.getTags().get());
        }

        return new Person(personToTag.getName(), personToTag.getPhone(), personToTag.getEmail(), updatedTags);
    }

    /**
     * Checks tags to ensure the person to tag will not have both prof and ta tag.r
     */
    private static boolean checkTag(Person personToTag, TagPersonDescriptor tagPersonDescriptor) {
        assert personToTag != null;

        Tag prof = new Tag(Tag.PROF_TAG_NAME);
        Tag ta = new Tag(Tag.TA_TAG_NAME);
        if (tagPersonDescriptor.tags.contains(prof) && tagPersonDescriptor.tags.contains(ta)) {
            return false;
        }
        if (personToTag.getTags().contains(prof) && tagPersonDescriptor.tags.contains(ta)) {
            return false;
        }
        if (personToTag.getTags().contains(ta) && tagPersonDescriptor.tags.contains(prof)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        return targetName.equals(e.targetName)
                && tagPersonDescriptor.equals(e.tagPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class TagPersonDescriptor {
        private Set<Tag> tags;

        public TagPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public TagPersonDescriptor(TagPersonDescriptor toCopy) {
            setTags(toCopy.tags);
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
            if (!(other instanceof TagPersonDescriptor)) {
                return false;
            }

            // state check
            TagPersonDescriptor e = (TagPersonDescriptor) other;

            return getTags().equals(e.getTags());
        }
    }
}

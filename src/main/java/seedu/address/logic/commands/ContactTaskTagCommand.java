package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.edit.EditContactCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Adds a same tag to an existing person and task in the Lifebook.
 */
public class ContactTaskTagCommand extends Command {
    public static final String COMMAND_WORD = "contactTaskTag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a same tag for a specified Person and Task.\n"
            + "Parameters: " + PREFIX_TAG + "TAG "
            + PREFIX_CONTACT_INDEX + "CONTACT INDEX "
            + PREFIX_TASK_INDEX + "TASK INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "CS2103T "
            + PREFIX_CONTACT_INDEX + "1 "
            + PREFIX_TASK_INDEX + "2";
    public static final String MESSAGE_CONTACT_TASK_TAG_SUCCESS = "Created tag for \n Person: %1$s, \n Task: %2$s";

    private final Index contactIndex;
    private final Index taskIndex;
    private final EditPersonTags editPersonTags;
    private final EditTaskTags editTaskTags;

    /**
     * Creates a ContactTaskTagCommand to modify a Person and a Task.
     * @param contactIndex index of the Person.
     * @param taskIndex index of the Task.
     * @param editPersonTags the tag that is to be added to the person.
     * @param editTaskTags the tag that is to be added to the task.
     */
    public ContactTaskTagCommand(Index contactIndex, Index taskIndex,
                                 EditPersonTags editPersonTags, EditTaskTags editTaskTags) {
        requireNonNull(contactIndex);
        requireNonNull(taskIndex);
        this.contactIndex = contactIndex;
        this.taskIndex = taskIndex;
        this.editPersonTags = new EditPersonTags(editPersonTags);
        this.editTaskTags = new EditTaskTags(editTaskTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> contactList = model.getFilteredPersonList();
        List<Task> taskList = model.getFilteredTaskList();
        boolean isContactIndexValid = contactIndex.getZeroBased() >= contactList.size();
        boolean isCheckTaskIndexValid = taskIndex.getZeroBased() >= taskList.size();

        if (isContactIndexValid && isCheckTaskIndexValid) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }
        if (isContactIndexValid) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (isCheckTaskIndexValid) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Person personToEdit = contactList.get(contactIndex.getZeroBased());
        Task taskToEdit = taskList.get(taskIndex.getZeroBased());

        Person editedPerson = createEditedPerson(personToEdit, editPersonTags);
        Task editedTask = createEditedTask(taskToEdit, editTaskTags);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_CONTACT_TASK_TAG_SUCCESS, editedPerson, editedTask), "CONTACT");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactTaskTagCommand)) {
            return false;
        }

        // state check
        ContactTaskTagCommand e = (ContactTaskTagCommand) other;
        return contactIndex.equals(e.contactIndex)
                && editPersonTags.equals(e.editPersonTags)
                && taskIndex.equals(e.taskIndex)
                && editTaskTags.equals(e.editTaskTags);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonTags}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonTags editPersonTags) {
        assert personToEdit != null;

        Set<Tag> updatedTags = mergeSet(editPersonTags.getTags()
                .orElse(personToEdit.getTags()), personToEdit.getTags());

        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), updatedTags);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskTags}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskTags editTaskTags) {
        assert taskToEdit != null;

        Set<Tag> updatedTags = mergeSet(editTaskTags.getTags()
                .orElse(taskToEdit.getTags()), taskToEdit.getTags());

        if (taskToEdit.isTodo()) {
            return new Todo(taskToEdit.getDescription(), taskToEdit.getDeadline(), taskToEdit.getRecurrence(), (
                    (Todo) taskToEdit).getCollaborativeLink(), updatedTags);
        } else if (taskToEdit.isEvent()) {
            return new Event(taskToEdit.getDescription(), taskToEdit.getStart(), taskToEdit.getEnd(),
                    taskToEdit.getRecurrence(), updatedTags);
        } else {
            assert false : "task is neither a todo or event!";
            return null;
        }
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonTags {
        private Set<Tag> tags;

        public EditPersonTags() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonTags(EditPersonTags toCopy) {
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
         * Returns a tag set.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(tags) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonTags)) {
                return false;
            }

            // state check
            EditPersonTags e = (EditPersonTags) other;

            return getTags().equals(e.getTags());
        }
    }


    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskTags {
        private Set<Tag> tags;

        public EditTaskTags() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskTags(EditTaskTags toCopy) {
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
         * Returns a tag set.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(tags) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskTags)) {
                return false;
            }

            // state check
            EditTaskTags e = (EditTaskTags) other;

            return getTags().equals(e.getTags());
        }
    }

    /**
     * Merges two sets of tags.
     * @param currentTags current tags of a Person/Task.
     * @param newTags new tags to be added to Person and Task.
     * @return a merged set.
     */
    public static <Tag> Set<Tag> mergeSet(Set<Tag> currentTags, Set<Tag> newTags) {
        if (currentTags.equals(newTags)) {
            return currentTags;
        } else {
            return new HashSet<Tag>() {
                {
                    addAll(currentTags);
                    addAll(newTags);
                }
            };
        }
    }
}

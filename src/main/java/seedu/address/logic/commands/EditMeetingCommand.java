package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.person.Person;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBERS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

public class EditMeetingCommand {

    public static final String COMMAND_WORD = "meeting edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the name of the meeting in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: MEETINGNAME (must be name of meeting existing in ModDuke) "
            + "[" + PREFIX_NAME + "NEW_MEETINGNAME] "
            + "[" + PREFIX_DATE + "NEW_DATE] "
            + "[" + PREFIX_TIME + "NEW_TIME] "
            + "[" + PREFIX_MEMBERS + "NEW_MEMBERS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103 Project Meeting "
            + PREFIX_DATE + "2020-10-10 "
            + PREFIX_TIME + "11:30";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final MeetingName meetingName;
    private final EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param meetingName of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(MeetingName meetingName, EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(meetingName);
        requireNonNull(editMeetingDescriptor);

        this.meetingName = meetingName;
        this.editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        boolean isValidMeeting = model.hasMeetingName(meetingName);
        if (!isValidMeeting) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED);
        }

        List<Meeting> filteredList = lastShownList.stream()
                .filter(meeting -> meeting.isSameMeetingName(meetingName)).collect(Collectors.toList());
        Meeting meetingToEdit = filteredList.get(0);

        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        MeetingName updatedMeetingName = editMeetingDescriptor.getMeetingName().orElse(meetingToEdit.getMeetingName());
        Date updatedDate = editMeetingDescriptor.getDate().orElse(meetingToEdit.getDate());
        Time updatedTime = editMeetingDescriptor.getTime().orElse(meetingToEdit.getTime());
        Set<Person> updatedMembers = editMeetingDescriptor.getMembers().orElse(meetingToEdit.getMembers());

        return new Meeting(updatedMeetingName, updatedDate, updatedTime, updatedMembers);
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
        EditMeetingCommand e = (EditMeetingCommand) other;
        return meetingName.equals(e.meetingName)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private MeetingName meetingName;
        private Date date;
        private Time time;
        private Set<Person> members;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingCommand.EditMeetingDescriptor toCopy) {
            setMeetingName(toCopy.meetingName);
            setDate(toCopy.date);
            setTime(toCopy.time);
            setMembers(toCopy.members);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(meetingName, date, time, members);
        }

        public void setMeetingName(MeetingName meetingName) {
            this.meetingName = meetingName;
        }

        public Optional<MeetingName> getMeetingName() {
            return Optional.ofNullable(meetingName);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
        }

        /**
         * Sets {@code members} to this object's {@code members}.
         * A defensive copy of {@code members} is used internally.
         */
        public void setMembers(Set<Person> members) {
            this.members = (members != null) ? new HashSet<>(members) : null;
        }

        /**
         * Returns an unmodifiable person set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Person>> getMembers() {
            return (members != null) ? Optional.of(Collections.unmodifiableSet(members)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingCommand.EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingCommand.EditMeetingDescriptor e = (EditMeetingCommand.EditMeetingDescriptor) other;

            return getMeetingName().equals(e.getMeetingName())
                    && getDate().equals(e.getDate())
                    && getTime().equals(e.getTime())
                    && getMembers().equals(e.getMembers());
        }
    }
}

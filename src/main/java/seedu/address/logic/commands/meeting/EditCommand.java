package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_CONTACTS;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.meeting.CliSyntax.PREFIX_TO;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.meeting.From;
import seedu.address.model.meeting.meeting.Location;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.To;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

/**
 * Edits the details of an existing meeting.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_FROM + "FROM] "
            + "[" + PREFIX_TO + "TO] "
            + "[" + PREFIX_CONTACTS + "CONTACTS] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Discuss ALL features "
            + PREFIX_FROM + "12-10-2020 09:00";


    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the meeting list.";
    public static final String MESSAGE_UNCHANGED = "Meeting unchanged. At least one field must differ "
            + "from the meeting that is being edited.";

    private final Index targetIndex;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index                 of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.targetIndex = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(ModelMeeting modelMeeting) throws CommandException {
        requireNonNull(modelMeeting);

        List<Meeting> lastShownList = modelMeeting.getFilteredMeetingList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(targetIndex.getZeroBased());
        Meeting editedMeeting;

        try {
            editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        assert Meeting.isValidFromAndTo(editedMeeting.getFrom(), editedMeeting.getTo())
                : "From should be earlier than To";

        if (meetingToEdit.equals(editedMeeting)) {
            throw new CommandException(MESSAGE_UNCHANGED);
        }

        if (!meetingToEdit.isSameMeeting(editedMeeting) && modelMeeting.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        modelMeeting.setMeeting(meetingToEdit, editedMeeting);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor)
            throws IllegalArgumentException {
        assert meetingToEdit != null;

        Title updatedTitle = editMeetingDescriptor.getTitle().orElse(meetingToEdit.getTitle());

        // Description takes optional String
        Description updatedDesc = editMeetingDescriptor.getDescription()
                .orElse(meetingToEdit.getDescription());

        From updatedFrom = editMeetingDescriptor.getFrom().orElse(meetingToEdit.getFrom());
        To updatedTo = editMeetingDescriptor.getTo().orElse(meetingToEdit.getTo());

        // Contacts takes optional String
        Contacts updatedContacts = editMeetingDescriptor.getContacts().orElse(meetingToEdit.getContacts());

        // Location takes optional String
        Location updatedLocation = editMeetingDescriptor.getLocation().orElse(meetingToEdit.getLocation());

        return new Meeting(updatedTitle, updatedDesc, updatedFrom, updatedTo, updatedContacts, updatedLocation);
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
        return targetIndex.equals(e.targetIndex)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     *
     */
    public static class EditMeetingDescriptor {
        private Title title;
        private Description description;
        private From from;
        private To to;
        private Contacts contacts;
        private Location location;

        public EditMeetingDescriptor() {
        }

        /**
         * Copy attributes from meeting to be edited.
         *
         * @param toCopy meeting to be edited.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setTitle(toCopy.title);
            setDescription(toCopy.description);
            setFrom(toCopy.from);
            setTo(toCopy.to);
            setContacts(toCopy.contacts);
            setLocation(toCopy.location);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, description, from, to, contacts, location);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(this.title);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setFrom(From from) {
            this.from = from;
        }

        public Optional<From> getFrom() {
            return Optional.ofNullable(this.from);
        }

        public void setTo(To to) {
            this.to = to;
        }

        public Optional<To> getTo() {
            return Optional.ofNullable(this.to);
        }

        public void setContacts(Contacts contact) {
            this.contacts = contact;
        }

        public Optional<Contacts> getContacts() {
            return Optional.ofNullable(this.contacts);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(this.location);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getFrom().equals(e.getFrom())
                    && getTo().equals(e.getTo())
                    && getContacts().equals(e.getContacts())
                    && getLocation().equals(e.getLocation());
        }

    }
}

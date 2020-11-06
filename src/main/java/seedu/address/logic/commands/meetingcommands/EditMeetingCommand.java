package seedu.address.logic.commands.meetingcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.Venue;

/**
 * Edits the details of an existing meeting in the meeting book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "edit-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "\n\nParameters: \nINDEX (must be a positive integer) "
            + "\n[" + PREFIX_MEETING_BIDDER_ID + "B12] "
            + "\n[" + PREFIX_MEETING_PROPERTY_ID + "P14] "
            + "\n[" + PREFIX_MEETING_DATE + "20-10-2020] "
            + "\n[" + PREFIX_MEETING_VENUE + "bedok]"
            + "\n[" + PREFIX_MEETING_STARTTIME + "12:30]"
            + "\n[" + PREFIX_MEETING_ENDTIME + "13:30]"
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING_VENUE + "eunos "
            + PREFIX_MEETING_DATE + "20-10-2019 ";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.equals(editedMeeting) && model.hasMeeting(editedMeeting)) {
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
    private static Meeting createEditedMeeting(Meeting meetingToEdit,
                                               EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        BidderId updatedBidderId =
                editMeetingDescriptor.getMeetingBidderId().orElse(meetingToEdit.getBidderId());
        PropertyId updatedPropertyId =
                editMeetingDescriptor.getMeetingPropertyId().orElse(meetingToEdit.getPropertyId());
        Venue updatedVenue =
                editMeetingDescriptor.getMeetingVenue().orElse(meetingToEdit.getVenue());
        MeetingDate updatedMeetingDate =
                editMeetingDescriptor.getMeetingTime().orElse(meetingToEdit.getMeetingDate());
        StartTime updatedStartTime =
                editMeetingDescriptor.getMeetingStartTime().orElse(meetingToEdit.getStartTime());
        EndTime updatedEndTime =
                editMeetingDescriptor.getMeetingEndTime().orElse(meetingToEdit.getEndTime());

        String typeMeeting = meetingToEdit.checkMeetingType();
        return meetingToEdit.createMeeting(typeMeeting, updatedBidderId, updatedPropertyId, updatedMeetingDate,
                updatedVenue, updatedStartTime, updatedEndTime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        // state check
        EditMeetingCommand e = (EditMeetingCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private BidderId bidderId;
        private PropertyId propertyId;
        private MeetingDate meetingDate;
        private Venue venue;
        private StartTime startTime;
        private EndTime endTime;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setMeetingBidderId(toCopy.bidderId);
            setMeetingPropertyId(toCopy.propertyId);
            setMeetingVenue(toCopy.venue);
            setMeetingDate(toCopy.meetingDate);
            setMeetingStartTime(toCopy.startTime);
            setMeetingEndTime(toCopy.endTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(bidderId, propertyId, venue, meetingDate, startTime, endTime);
        }

        public void setMeetingBidderId(BidderId bidderId) {
            this.bidderId = bidderId;
        }

        public Optional<BidderId> getMeetingBidderId() {
            return Optional.ofNullable(bidderId);
        }

        public void setMeetingPropertyId(PropertyId propertyId) {
            this.propertyId = propertyId;
        }

        public Optional<PropertyId> getMeetingPropertyId() {
            return Optional.ofNullable(propertyId);
        }

        public void setMeetingVenue(Venue venue) {
            this.venue = venue;
        }

        public Optional<Venue> getMeetingVenue() {
            return Optional.ofNullable(venue);
        }

        public void setMeetingDate(MeetingDate meetingDate) {
            this.meetingDate = meetingDate;
        }

        public Optional<MeetingDate> getMeetingTime() {
            return Optional.ofNullable(meetingDate);
        }

        public void setMeetingStartTime(StartTime startTime) {
            this.startTime = startTime;
        }

        public Optional<StartTime> getMeetingStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setMeetingEndTime(EndTime endTime) {
            this.endTime = endTime;
        }

        public Optional<EndTime> getMeetingEndTime() {
            return Optional.ofNullable(endTime);
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

            return getMeetingBidderId().equals(e.getMeetingBidderId())
                    && getMeetingPropertyId().equals(e.getMeetingPropertyId())
                    && getMeetingVenue().equals(e.getMeetingVenue())
                    && getMeetingTime().equals(e.getMeetingTime())
                    && getMeetingStartTime().equals(e.getMeetingStartTime())
                    && getMeetingEndTime().equals(e.getMeetingEndTime());
        }
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_VENUE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calendar.CalendarBidderId;
import seedu.address.model.calendar.CalendarMeeting;
import seedu.address.model.calendar.CalendarPropertyId;
import seedu.address.model.calendar.CalendarTime;
import seedu.address.model.calendar.CalendarVenue;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "edit-m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CALENDAR_BIDDER_ID + "B-ID] "
            + "[" + PREFIX_CALENDAR_PROPERTY_ID + "P-ID] "
            + "[" + PREFIX_CALENDAR_TIME + "TIME] "
            + "[" + PREFIX_CALENDAR_VENUE + "VENUE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CALENDAR_VENUE + "EUNOS ";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editMeetingDescriptor details to edit the person with
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
        List<CalendarMeeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        CalendarMeeting meetingToEdit = lastShownList.get(index.getZeroBased());
        CalendarMeeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.equals(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting))
                .setEntity(EntityType.MEETING);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static CalendarMeeting createEditedMeeting(CalendarMeeting meetingToEdit,
                                                       EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        CalendarBidderId updatedBidderId =
                editMeetingDescriptor.getCalendarBidderId().orElse(meetingToEdit.getCalendarBidderId());
        CalendarPropertyId updatedPropertyId =
                editMeetingDescriptor.getCalendarPropertyId().orElse(meetingToEdit.getCalendarPropertyId());
        CalendarVenue updatedVenue =
                editMeetingDescriptor.getCalendarVenue().orElse(meetingToEdit.getCalendarVenue());
        CalendarTime updatedTime =
                editMeetingDescriptor.getCalendarTime().orElse(meetingToEdit.getCalendarTime());

        String typeMeeting = meetingToEdit.checkMeetingType();
        return meetingToEdit.createMeeting(typeMeeting, updatedBidderId, updatedPropertyId, updatedTime, updatedVenue);
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
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditMeetingDescriptor {
        private CalendarBidderId bidderId;
        private CalendarPropertyId propertyId;
        private CalendarTime time;
        private CalendarVenue venue;

        public EditMeetingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setCalendarBidderId(toCopy.bidderId);
            setCalendarPropertyId(toCopy.propertyId);
            setCalendarVenue(toCopy.venue);
            setCalendarTime(toCopy.time);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(bidderId, propertyId, venue, time);
        }

        public void setCalendarBidderId(CalendarBidderId bidderId) {
            this.bidderId = bidderId;
        }

        public Optional<CalendarBidderId> getCalendarBidderId() {
            return Optional.ofNullable(bidderId);
        }

        public void setCalendarPropertyId(CalendarPropertyId propertyId) {
            this.propertyId = propertyId;
        }

        public Optional<CalendarPropertyId> getCalendarPropertyId() {
            return Optional.ofNullable(propertyId);
        }

        public void setCalendarVenue(CalendarVenue venue) {
            this.venue = venue;
        }

        public Optional<CalendarVenue> getCalendarVenue() {
            return Optional.ofNullable(venue);
        }

        public void setCalendarTime(CalendarTime time) {
            this.time = time;
        }

        public Optional<CalendarTime> getCalendarTime() {
            return Optional.ofNullable(time);
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

            return getCalendarBidderId().equals(e.getCalendarBidderId())
                    && getCalendarPropertyId().equals(e.getCalendarPropertyId())
                    && getCalendarVenue().equals(e.getCalendarVenue())
                    && getCalendarTime().equals(e.getCalendarTime());
        }
    }
}

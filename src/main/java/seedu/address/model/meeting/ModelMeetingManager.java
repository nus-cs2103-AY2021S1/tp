package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.meeting.Meeting;

/**
 * Represents the in-memory model of the meeting book data
 */
public class ModelMeetingManager implements ModelMeeting {

    private static final Logger logger = LogsCenter.getLogger(ModelMeetingManager.class);
    private final MeetingBook meetingBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Meeting> filteredMeetings;
    private Meeting meetingInView;

    /**
     * Initializes a ModelMeetingManager with the given meetingBook WITHOUT userPrefs.
     */
    public ModelMeetingManager(ReadOnlyMeetingBook meetingBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(meetingBook, userPrefs);

        logger.fine("Initializing with meeting book: " + meetingBook + " and user prefs " + userPrefs);
        this.meetingBook = new MeetingBook(meetingBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMeetings = new FilteredList<>(this.meetingBook.getMeetingList());
    }

    public ModelMeetingManager() {
        this(new MeetingBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getMeetingBookFilePath() {
        return userPrefs.getMeetingBookFilePath();
    }

    @Override
    public void setMeetingBookFilePath(Path meetingBookFilePath) {
        requireNonNull(meetingBookFilePath);
        userPrefs.setMeetingBookFilePath(meetingBookFilePath);
    }

    @Override
    public ObservableList<Meeting> getInternalMeetingList() {
        return meetingBook.getInternalMeetingList();
    }

    //=========== MeetingBook ================================================================================

    @Override
    public void setMeetingBook(ReadOnlyMeetingBook meetingBook) {
        this.meetingBook.resetData(meetingBook);
        setMeetingInView(null);
    }

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return meetingBook;
    }

    @Override
    public Meeting getMeetingInView() {
        return meetingInView;
    }

    @Override
    public void setMeetingInView(Meeting meetingInView) {
        this.meetingInView = meetingInView;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        setMeetingInView(meeting);
    }

    @Override
    public void deleteMeeting(Meeting target) {
        meetingBook.removeMeeting(target);
        if (target.isSameMeeting(meetingInView)) {
            setMeetingInView(null);
        }
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetingBook.hasMeeting(meeting);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);
        meetingBook.setMeeting(target, editedMeeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        setMeetingInView(editedMeeting);
    }

    //=========== Filtered Meeting List Accessors =============================================================
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
        setMeetingInView(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ModelMeetingManager)) {
            return false;
        }

        ModelMeetingManager other = (ModelMeetingManager) obj;
        return meetingBook.equals(other.meetingBook)
                && userPrefs.equals(other.userPrefs)
                && filteredMeetings.equals(other.filteredMeetings);
    }
}

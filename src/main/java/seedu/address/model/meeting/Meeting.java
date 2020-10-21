package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Meeting {

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String NAME_MESSAGE_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String DESCRIPTION_MESSAGE_CONSTRAINTS =
            "Task descriptions should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String PUBLISH_DATE_MESSAGE_CONSTRAINTS =
            "Publish date should only be in the format of dd-MM-yyyy";
    public static final String START_DATE_TIME_MESSAGE_CONSTRAINTS =
            "Start date time should only be in the format of dd-MM-yyyy";
    public static final String DURATION_MESSAGE_CONSTRAINTS =
            "Duration values should only contain a long, and it should not be blank";
    public static final String IS_DONE_MESSAGE_CONSTRAINTS =
            "Is done values should only contain booleans, and it should not be blank";
    public static final String NOTE_MESSAGE_CONSTRAINTS =
            "Meeting notes should only contain alphanumeric characters and spaces, and it should not be blank";

    private String name;
    private String description;
    private LocalDate publishDate;
    private LocalDateTime startDateTime;
    private Time duration;
    private String note;
    private Boolean isDone;

    /**
     * Constructor for meeting.
     * Date and timing for the meeting should be present and not null.
     *
     * @param localDateTime date and time of meeting
     */
    public Meeting(String localDateTime) {
        requireAllNonNull(localDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        startDateTime = LocalDateTime.parse(localDateTime, formatter);
        isDone = false;
        publishDate = LocalDate.now();
    }

    /**
     * Finishes a meeting.
     */
    public void done() {
        isDone = true;
    }

    public String getMeetingName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public Time getDuration() {
        return duration;
    }

    public String getNote() {
        return note;
    }

    public String getName() {
        return name;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMeetingName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidMeetingDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid publish date.
     */
    public static boolean isValidPublishDate(String test) {
        return Meeting.isValidDate(test);
    }

    /**
     * Returns true if a given string is a valid start date time.
     */
    public static boolean isValidStartDateTime(String test) {
        return Meeting.isValidDate(test);
    }

    /**
     * Returns true if a given string is a valid isDone value.
     */
    public static boolean isValidIsDone(String test) {
        return Boolean.getBoolean(test);
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid duration value.
     */
    public static boolean isValidDuration(String test) {
        return null != Long.getLong(test);
    }

    /**
     * Return true if a given string is a valid date.
     */
    static boolean isValidDate(String date) {
        String[] strings = date.split("-");
        int day = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        int year = Integer.parseInt(strings[2]);
        boolean isValidDay = day <= 31 && day >= 1;
        boolean isValidMonth = month <= 12 && month >= 1;
        //year is always valid because it matches the regex as 4 digits of integers (1000 - 9999)
        if (day == 29 && month == 2) {
            if (year % 400 == 0) {
                return true;
            } else if (year % 100 == 0) {
                return false;
            } else {
                return year % 4 == 0;
            }
        } else if ((day == 30 || day == 31) && month == 2) {
            return false;
        } else if (day == 31 && (month == 4 || month == 6
                || month == 9 || month == 11)) {
            return false;
        } else {
            return isValidDay && isValidMonth;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getStartDateTime().equals(getStartDateTime())
                && otherMeeting.getName().equals(getName())
                && otherMeeting.getPublishDate().equals(getPublishDate())
                && otherMeeting.getDuration().equals(getDuration());

    }
}

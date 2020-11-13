package seedu.jarvis.testutil;

import java.time.LocalDateTime;

import seedu.jarvis.model.masterycheck.MasteryCheck;

/**
 * A utility class to help with building {@code MasteryCheck} objects.
 */
public class MasteryCheckBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final LocalDateTime DEFAULT_DATE_AND_TIME =
            LocalDateTime.of(2020, 12, 31, 8, 30);
    public static final boolean DEFAULT_HASPASSED = true;

    private String studentName;
    private LocalDateTime dateAndTime;
    private boolean hasPassed;

    /**
     * Creates a {@code MasteryCheckBuilder} with the default details.
     */
    public MasteryCheckBuilder() {
        studentName = DEFAULT_NAME;
        dateAndTime = DEFAULT_DATE_AND_TIME;
        hasPassed = DEFAULT_HASPASSED;
    }

    /**
     * Initializes the {@code MasteryCheckBuilder} with the data of {@code masteryCheckToCopy}.
     */
    public MasteryCheckBuilder(MasteryCheck masteryCheckToCopy) {
        studentName = masteryCheckToCopy.getStudentName();
        dateAndTime = masteryCheckToCopy.getDateAndTime();
        hasPassed = masteryCheckToCopy.hasPassed();
    }

    /**
     * Sets the studentName of the {@code MasteryCheck} that we are building.
     */
    public MasteryCheckBuilder withName(String name) {
        this.studentName = name;
        return this;
    }

    /**
     * Sets the dateAndTime of the {@code MasteryCheck} that we are building.
     */
    public MasteryCheckBuilder withDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
        return this;
    }

    /**
     * Sets the hasPassed of the {@code MasteryCheck} that we are building.
     */
    public MasteryCheckBuilder withHasPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
        return this;
    }

    /**
     * Builds the MasteryCheck with current class variables.
     */
    public MasteryCheck build() {
        if (hasPassed) {
            return MasteryCheck.createFullMarkMC(studentName, dateAndTime);
        } else {
            return MasteryCheck.createZeroMarkMC(studentName, dateAndTime);
        }
    }
}

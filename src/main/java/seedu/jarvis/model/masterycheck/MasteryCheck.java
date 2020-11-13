package seedu.jarvis.model.masterycheck;

import java.time.LocalDateTime;

import seedu.jarvis.model.consultation.Consultation;

public class MasteryCheck extends Consultation {
    private boolean hasPassed;

    /**
     * Creates a {@code MasteryCheck} with the given parameters. {@code lengthOfMeeting} and {@code notes} may be null.
     *
     * @param studentName         subject of the mastery check
     * @param dateAndTime     date and time of the mastery check
     */
    public MasteryCheck(String studentName, LocalDateTime dateAndTime) {
        super(studentName, dateAndTime);
        hasPassed = false; // defaulted to false
    }

    public boolean hasPassed() {
        return hasPassed;
    }

    public MasteryCheck setPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
        return this;
    }

    /**
     * Creates a {@code MasteryCheck} with a full mark with the given parameters.
     */
    public static MasteryCheck createFullMarkMC(String studentName, LocalDateTime dateAndTime) {
        return new MasteryCheck(studentName, dateAndTime).setPassed(true);
    }

    /**
     * Creates a {@code MasteryCheck} with zero marks with the given parameters.
     */
    public static MasteryCheck createZeroMarkMC(String studentName, LocalDateTime dateAndTime) {
        return new MasteryCheck(studentName, dateAndTime).setPassed(false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mastery Check with ");
        sb.append(getStudentName());
        sb.append(" [");
        LocalDateTime date = getDateAndTime();
        sb.append(date.getDayOfMonth() + " " + date.getMonth() + ", " + date.getYear() + " @ ");
        sb.append(date.getHour() + ":" + date.getMinute());
        sb.append("] / " + (hasPassed ? "PASS" : "FAIL"));

        return sb.toString();
    }
}

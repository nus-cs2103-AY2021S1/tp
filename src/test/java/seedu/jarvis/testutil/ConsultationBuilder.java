package seedu.jarvis.testutil;

import java.time.LocalDateTime;

import seedu.jarvis.model.consultation.Consultation;

/**
 * A utility class to help with building Consultation objects.
 */
public class ConsultationBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final LocalDateTime DEFAULT_DATE_AND_TIME =
            LocalDateTime.of(2020, 12, 31, 8, 30);

    private String studentName;
    private LocalDateTime dateAndTime;

    /**
     * Creates a {@code ConsultationBuilder} with the default details.
     */
    public ConsultationBuilder() {
        studentName = DEFAULT_NAME;
        dateAndTime = DEFAULT_DATE_AND_TIME;
    }

    /**
     * Initializes the {@code ConsultationBuilder} with the data of {@code consultationToCopy}.
     */
    public ConsultationBuilder(Consultation consultationToCopy) {
        studentName = consultationToCopy.getStudentName();
        dateAndTime = consultationToCopy.getDateAndTime();
    }

    /**
     * Sets the studentName f the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    /**
     * Sets the dateAndTime of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
        return this;
    }

    public Consultation build() {
        return new Consultation(studentName, dateAndTime);
    }
}

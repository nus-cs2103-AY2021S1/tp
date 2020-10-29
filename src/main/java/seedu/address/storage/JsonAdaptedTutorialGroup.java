package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleId;
import seedu.address.model.person.UniqueStudentList;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class JsonAdaptedTutorialGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Group's %s field is missing!";

    private final String tutorialGroupId;
    private final String startTime;
    private final String endTime;
    private final String dayOfWeek;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutorialGroup} with the given tutorial group details.
     */
    @JsonCreator
    public JsonAdaptedTutorialGroup(@JsonProperty("tutorialGroupId") String tutorialGroupId,
                                    @JsonProperty("startTime") String startTime,
                                    @JsonProperty("endTime") String endTime,
                                    @JsonProperty("dayOfWeek") String dayOfWeek,
                                    @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.tutorialGroupId = tutorialGroupId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code TutorialGroup} into this class for Jackson use.
     */
    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        tutorialGroupId = source.getId().toString();
        dayOfWeek = source.getDayOfWeek().toString();
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
        this.students.addAll(source.getStudents().stream().map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public TutorialGroup toModelType() throws IllegalValueException {

        final UniqueStudentList modelStudents = new UniqueStudentList();
        for (JsonAdaptedStudent student: students) {
            modelStudents.addStudent(student.toModelType());
        }

        if (tutorialGroupId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleId.class.getSimpleName()));
        }
        if (!TutorialGroupId.isValidTutorialGroupId(tutorialGroupId)) {
            throw new IllegalValueException(TutorialGroupId.MESSAGE_CONSTRAINTS);
        }
        final TutorialGroupId modelTutorialGroupId = new TutorialGroupId(tutorialGroupId);

        if (dayOfWeek == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DayOfWeek.class.getSimpleName()));
        }
        if (!DayOfWeek.isValidDayOfWeek(dayOfWeek)) {
            throw new IllegalValueException(DayOfWeek.MESSAGE_CONSTRAINTS);
        }
        final DayOfWeek modelDayOfWeek = new DayOfWeek(dayOfWeek);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TimeOfDay.class.getSimpleName()));
        }
        if (!TimeOfDay.isValidTimeOfDay(startTime)) {
            throw new IllegalValueException(TimeOfDay.MESSAGE_CONSTRAINTS);
        }
        final TimeOfDay modelStartTime = new TimeOfDay(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TimeOfDay.class.getSimpleName()));
        }
        if (!TimeOfDay.isValidTimeOfDay(endTime)) {
            throw new IllegalValueException(TimeOfDay.MESSAGE_CONSTRAINTS);
        }
        final TimeOfDay modelEndTime = new TimeOfDay(endTime);

        if (!TimeOfDay.isValidTimes(startTime, endTime)) {
            throw new IllegalValueException(TimeOfDay.TIME_CONSTRAINTS);
        }

        return new TutorialGroup(modelTutorialGroupId, modelDayOfWeek,
                modelStartTime, modelEndTime, modelStudents);
    }


}

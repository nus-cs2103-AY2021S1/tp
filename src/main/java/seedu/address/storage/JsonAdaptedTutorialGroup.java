package seedu.address.storage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleId;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class JsonAdaptedTutorialGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Group's %s field is missing!";

    private final String tutorialGroupId;
    private String startTime;
    private String endTime;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutorialGroup} with the given tutorial group details.
     */
    @JsonCreator
    public JsonAdaptedTutorialGroup(@JsonProperty("tutorialGroupId") String tutorialGroupId,
                                    @JsonProperty("startTime") String startTime,
                                    @JsonProperty("endTime") String endTime,
                                    @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.tutorialGroupId = tutorialGroupId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code TutorialGroup} into this class for Jackson use.
     */
    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        tutorialGroupId = source.getId().toString();
//        startTime = source.getStartTime().toString();
//        endTime = source.getEndTime().toString();
        this.students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public TutorialGroup toModelType() throws IllegalValueException {

        if (tutorialGroupId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleId.class.getSimpleName()));
        }
        if (!TutorialGroupId.isValidTutorialGroupId(tutorialGroupId)) {
            throw new IllegalValueException(ModuleId.MESSAGE_CONSTRAINTS);
        }
        final TutorialGroupId modelTutorialGroupId = new TutorialGroupId(tutorialGroupId);



        return new TutorialGroup(modelTutorialGroupId, LocalTime.parse(startTime), LocalTime.parse(endTime));
    }


}

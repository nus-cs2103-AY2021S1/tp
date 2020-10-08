package tp.cap5buddy.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import tp.cap5buddy.modules.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String name;
    private final String zoomLink;
    private final List<JsonAdaptedGrade> gradeList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name, @JsonProperty("zoomLink") String zoomLink,
                             @JsonProperty("gradeList") List<JsonAdaptedGrade> gradeList) {
        this.name = name;
        this.zoomLink = zoomLink;
        if (gradeList != null) {
            this.gradeList.addAll(gradeList);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName();
        zoomLink = source.getLink();
        gradeList.addAll(source.getGradeList().getGradesList()
                .stream().map(JsonAdaptedGrade::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        return new Module(name, zoomLink);
    }
}

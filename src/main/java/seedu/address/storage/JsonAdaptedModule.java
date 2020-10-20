package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Module;

public class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleId;
    private final List<JsonAdaptedTutorialGroup> tutorialGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("id") String moduleId, @JsonProperty("classes") List<JsonAdaptedTutorialGroup> tutorialGroups) {
        this.moduleId = moduleId;
        if (tutorialGroups != null) {
            this.tutorialGroups.addAll(tutorialGroups);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        moduleId = source.getModuleId();
        tutorialGroups.addAll(source.getTutorialGroups().stream()
                .map(JsonAdaptedTutorialGroup::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {

        if (moduleId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Module ID"));
        }

        return new Module(moduleId);
    }

}

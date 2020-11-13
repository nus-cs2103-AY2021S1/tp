package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleId;
    private final List<JsonAdaptedTutorialGroup> tutorialGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleId") String moduleId,
                             @JsonProperty("tutorialGroups") List<JsonAdaptedTutorialGroup> tutorialGroups) {
        this.moduleId = moduleId;
        if (tutorialGroups != null) {
            this.tutorialGroups.addAll(tutorialGroups);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        assert source != null;
        moduleId = source.getModuleId().toString();
        tutorialGroups.addAll(source.getTutorialGroups().stream()
                .map(JsonAdaptedTutorialGroup::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {

        final UniqueTutorialGroupList modelTutorialGroups = new UniqueTutorialGroupList();
        for (JsonAdaptedTutorialGroup tutorialGroup : tutorialGroups) {
            modelTutorialGroups.addTutorialGroup(tutorialGroup.toModelType());
        }

        if (moduleId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleId.class.getSimpleName()));
        }
        if (!ModuleId.isValidModuleId(moduleId)) {
            throw new IllegalValueException(ModuleId.MESSAGE_CONSTRAINTS);
        }
        final ModuleId modelModuleId = new ModuleId(moduleId);

        return new Module(modelModuleId, modelTutorialGroups);
    }

}

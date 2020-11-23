package seedu.tr4cker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleName;
    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleName") String moduleName,
                      @JsonProperty("moduleCode") String moduleCode) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        this.moduleName = source.moduleName;
        this.moduleCode = source.moduleCode.codeName;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Module toModelType() throws IllegalValueException {
        if (moduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "moduleName"));
        }
        if (!Module.isValidModuleName(moduleName)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new Module(moduleName, new ModuleCode(moduleCode));
    }
}

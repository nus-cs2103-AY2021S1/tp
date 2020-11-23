package seedu.tr4cker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;

public class JsonAdaptedModuleCode {

    private final String codeName;

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code codeName}.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String codeName) {
        this.codeName = codeName;
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        codeName = source.codeName;
    }

    @JsonValue
    public String getCodeName() {
        return codeName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ModuleCode toModelType() throws IllegalValueException {
        if (!ModuleCode.isValidModuleCode(codeName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(codeName);
    }
}

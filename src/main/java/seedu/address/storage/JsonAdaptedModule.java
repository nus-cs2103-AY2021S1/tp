package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Grade;
import seedu.address.model.module.ModularCredit;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.semester.Semester;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String modName;
    private final String grade;
    private final int modularCredit;
    private final String semester;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String modName, @JsonProperty("address") String grade,
                             @JsonProperty("modularCredit") int modularCredit,
                             @JsonProperty("semester") String semester) {
        this.modName = modName;
        this.grade = grade;
        this.modularCredit = modularCredit;
        this.semester = semester;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        modName = source.getModuleName().fullModName;
        grade = source.getGrade().toString();
        modularCredit = source.getModularCredit().modularCredit;
        semester = source.getSemester().toString();
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        if (modName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidModName(modName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelModuleName = new ModuleName(modName);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }

        final Grade modelGrade = new Grade(grade);

        final ModularCredit modelModularCredit = new ModularCredit(modularCredit);

        final Semester modelSemester = Semester.valueOf(semester);

        return new Module(modelModuleName, modelGrade, modelModularCredit, modelSemester);
    }

}

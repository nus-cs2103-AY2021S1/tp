package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS1010S;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;

public class JsonAdaptedModuleTest {
    private static final String INVALID_MODULE_CODE = "CS50*";
    private static final String INVALID_MODULE_NAME = "Programming & Algorithms";

    private static final String INVALID_PERSON_NAME = "#Damith";
    private static final String VALID_PERSON_PHONE = "98765432";
    private static final String VALID_PERSON_EMAIL = "dcsdamith@comp.nus.edu";
    private static final String VALID_PERSON_DEPARTMENT = "School of Computing";
    private static final String VALID_PERSON_OFFICE = "COM2-02-17";
    private static final String VALID_PERSON_REMARK = "Loves monkeys";

    private static final String VALID_MODULE_CODE = CS1010S.getModuleCode().toString();
    private static final String VALID_MODULE_NAME = CS1010S.getModuleName().toString();
    private static final List<JsonAdaptedPerson> VALID_INSTRUCTORS = CS1010S.getInstructors().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS1010S);
        assertEquals(CS1010S, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_MODULE_CODE, VALID_MODULE_NAME, VALID_INSTRUCTORS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_MODULE_NAME, VALID_INSTRUCTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidModuleName_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_MODULE_CODE, INVALID_MODULE_NAME, VALID_INSTRUCTORS);
        String expectedMessage = ModuleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleName_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE, null, VALID_INSTRUCTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidInstructors_throwsIllegalValueException() {
        List<JsonAdaptedPerson> invalidInstructors = new ArrayList<>(VALID_INSTRUCTORS);
        invalidInstructors.add(new JsonAdaptedPerson(INVALID_PERSON_NAME, VALID_PERSON_PHONE, VALID_PERSON_EMAIL,
                VALID_PERSON_DEPARTMENT, VALID_PERSON_OFFICE, VALID_PERSON_REMARK, null));
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_MODULE_CODE, VALID_MODULE_NAME, invalidInstructors);
        assertThrows(IllegalValueException.class, module::toModelType);
    }
}

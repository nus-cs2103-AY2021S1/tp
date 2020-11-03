package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2100;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleId;

public class JsonAdaptedModuleTest {

    private static final String INVALID_MODULE_ID = "CS2##3T";

    private static final String VALID_MODULE_ID = CS2100.getModuleId().toString();

    private static final List<JsonAdaptedTutorialGroup> DUMMY_TUTORIAL_GROUP_LIST = new ArrayList<>();

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2100);
        assertEquals(CS2100, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleId_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_MODULE_ID, DUMMY_TUTORIAL_GROUP_LIST);
        String expectedMessage = ModuleId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleId_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, DUMMY_TUTORIAL_GROUP_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }


}

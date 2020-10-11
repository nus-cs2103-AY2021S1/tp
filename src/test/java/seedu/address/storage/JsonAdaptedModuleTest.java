package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.Email;
import seedu.address.model.module.Name;
import seedu.address.model.module.Phone;

public class JsonAdaptedModuleTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ZOOM_LINK = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ZOOM_LINK = BENSON.getZoomLink().toString();

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule Module = new JsonAdaptedModule(BENSON);
        assertEquals(BENSON, Module.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedModule Module =
                new JsonAdaptedModule(INVALID_NAME,  VALID_ZOOM_LINK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, Module::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedModule Module = new JsonAdaptedModule(null, VALID_ZOOM_LINK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, Module::toModelType);
    }

    @Test
    public void toModelType_invalidZOOM_LINK_throwsIllegalValueException() {
        JsonAdaptedModule Module =
                new JsonAdaptedModule(VALID_NAME, INVALID_ZOOM_LINK);
        String expectedMessage = ZoomLink.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, Module::toModelType);
    }

    @Test
    public void toModelType_nullZOOM_LINK_throwsIllegalValueException() {
        JsonAdaptedModule Module = new JsonAdaptedModule(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ZoomLink.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, Module::toModelType);
    }
}

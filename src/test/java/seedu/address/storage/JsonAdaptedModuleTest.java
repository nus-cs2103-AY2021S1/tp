package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_CONSTRAINTS_CODE;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;

public class JsonAdaptedModuleTest {
    private static final String INVALID_CODE = "CS1#212";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_CODE = CS2103T.getModuleCode().toString();
    private static final String VALID_TITLE = CS2103T.getModuleTitle().toString();
    private static final String VALID_CREDITS = CS2103T.getModularCredits().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2103T.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2103T);
        assertEquals(CS2103T, module.toModelType());
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_CODE, VALID_TITLE, VALID_CREDITS, VALID_TAGS);
        String expectedMessage = MESSAGE_CONSTRAINTS_CODE;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_TITLE, VALID_CREDITS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, null, VALID_CREDITS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModularCredits_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_TITLE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModularCredits.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_CODE, VALID_TITLE, VALID_CREDITS, invalidTags);
        assertThrows(IllegalValueException.class, module::toModelType);
    }
}

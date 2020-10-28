package seedu.address.storage;

/*import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;*/
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedGradeTrackerTest.INVALID_ASSIGNMENT;
import static seedu.address.storage.JsonAdaptedGradeTrackerTest.INVALID_ASSIGNMENT_LIST;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalPersons.BENSON;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;

public class JsonAdaptedModuleTest {
    private static final String INVALID_MODULE_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MC = "-1.0";
    private static final JsonAdaptedGradeTracker INVALID_GRADE_TRACKER;

    private static final String VALID_NAME = CS2030.getName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedZoomLink> VALID_ZOOM_LINKS = new ArrayList<>();
    private static final JsonAdaptedGradeTracker VALID_GRADE_TRACKER = new JsonAdaptedGradeTracker(
            CS2030.getGradeTracker());
    private static final List<JsonAdaptedTag> VALID_TAGS = CS2030.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_MC = CS2030.getModularCredits().toString();
    static {
        ArrayList<JsonAdaptedAssignment> invalidAssignmentList = new ArrayList<>();
        invalidAssignmentList.add(INVALID_ASSIGNMENT);
        INVALID_GRADE_TRACKER = new JsonAdaptedGradeTracker(invalidAssignmentList, 0, "5.0");
        CS2030.getAllLinks().forEach((key, link) -> VALID_ZOOM_LINKS
                .add(new JsonAdaptedZoomLink(key, link.getLink())));
    }
    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2030);
        assertEquals(CS2030, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleName_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_MODULE_NAME, VALID_ZOOM_LINKS, VALID_GRADE_TRACKER,
                        VALID_MC, VALID_TAGS);
        String expectedMessage = ModuleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(null, VALID_ZOOM_LINKS, VALID_GRADE_TRACKER,
                        VALID_MC, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidModularCredits_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_ZOOM_LINKS, VALID_GRADE_TRACKER,
                        INVALID_MC, VALID_TAGS);
        String expectedMessage = ModularCredits.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
    /*
    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedModule person = new JsonAdaptedModule(VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_ZOOM_LINKS, VALID_GRADE_TRACKER,
                        INVALID_MC, invalidTags);
        assertThrows(IllegalValueException.class, module::toModelType);
    }

    @Test
    public void toModelType_invalidGradeTracker_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_ZOOM_LINKS, INVALID_GRADE_TRACKER,
                        VALID_MC, VALID_TAGS);
        String expectedMessage = Assignment.MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}

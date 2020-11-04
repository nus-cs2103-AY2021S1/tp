package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutorialGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialGroups.S12;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class JsonAdaptedTutorialGroupTest {
    private static final String INVALID_TUTORIAL_GROUP_ID = "$12";
    private static final String INVALID_DAY_OF_WEEK = "M0N";
    private static final String INVALID_START_TIME = "1200";
    private static final String INVALID_END_TIME = "Thirteen O-clock";
    private static final String INVALID_TIME_PAIR_START = "11:00";
    private static final String INVALID_TIME_PAIR_END = "06:00";


    private static final String VALID_TUTORIAL_GROUP_ID = S12.getId().toString();
    private static final String VALID_DAY_OF_WEEK = S12.getDayOfWeek().toString();
    private static final String VALID_START_TIME = S12.getStartTime().toString();
    private static final String VALID_END_TIME = S12.getEndTime().toString();

    private static final List<JsonAdaptedStudent> DUMMY_STUDENT_LIST = new ArrayList<>();

    @Test
    public void toModelType_validTutorialGroupDetails_returnsTutorialGroup() throws Exception {
        JsonAdaptedTutorialGroup module = new JsonAdaptedTutorialGroup(S12);
        assertEquals(S12, module.toModelType());
    }

    @Test
    public void toModelType_invalidTutorialGroupId_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(
                        INVALID_TUTORIAL_GROUP_ID,
                        VALID_DAY_OF_WEEK,
                        VALID_START_TIME,
                        VALID_END_TIME,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = TutorialGroupId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullTutorialGroupId_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(
                        null,
                        VALID_DAY_OF_WEEK,
                        VALID_START_TIME,
                        VALID_END_TIME,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialGroupId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_invalidDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(
                        VALID_TUTORIAL_GROUP_ID,
                        INVALID_DAY_OF_WEEK,
                        VALID_START_TIME,
                        VALID_END_TIME,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = DayOfWeek.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullDayOfWeek_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup = new JsonAdaptedTutorialGroup(
                VALID_TUTORIAL_GROUP_ID,
                null,
                VALID_START_TIME,
                VALID_END_TIME,
                DUMMY_STUDENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DayOfWeek.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(
                        VALID_TUTORIAL_GROUP_ID,
                        VALID_DAY_OF_WEEK,
                        INVALID_START_TIME,
                        VALID_END_TIME,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = TimeOfDay.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(VALID_TUTORIAL_GROUP_ID,
                        VALID_DAY_OF_WEEK,
                        null,
                        VALID_END_TIME,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeOfDay.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(
                        VALID_TUTORIAL_GROUP_ID,
                        VALID_DAY_OF_WEEK,
                        VALID_START_TIME,
                        INVALID_END_TIME,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = TimeOfDay.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(VALID_TUTORIAL_GROUP_ID,
                        VALID_DAY_OF_WEEK,
                        VALID_START_TIME,
                        null,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeOfDay.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_invalidTimePair_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(
                        VALID_TUTORIAL_GROUP_ID,
                        VALID_DAY_OF_WEEK,
                        INVALID_TIME_PAIR_START,
                        INVALID_TIME_PAIR_END,
                        DUMMY_STUDENT_LIST);
        String expectedMessage = TimeOfDay.TIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }


}

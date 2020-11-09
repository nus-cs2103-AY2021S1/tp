package tp.acecs2103.storage;

import static tp.acecs2103.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static tp.acecs2103.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tp.acecs2103.commons.exceptions.IllegalValueException;
import tp.acecs2103.model.task.Admin;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Deadline;
import tp.acecs2103.model.task.Description;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.OfficialDeadline;
import tp.acecs2103.model.task.Remark;
import tp.acecs2103.model.task.WeekNumber;

public class JsonAdaptedTaskTest {
    private static final String INVALID_INDEX = "1110";
    private static final String INVALID_WEEKNUMBER = "14";
    private static final String INVALID_DEADLINE = "2020-1-1";

    private static Admin task = new Admin(new Index("0101"), new WeekNumber("1"), new Description("Description"),
            new OfficialDeadline("2020-01-01", LocalDate.of(2020, 1, 1)),
            new CustomizedDeadline("2020-01-01", LocalDate.of(2020, 1, 1)),
            new Remark("no remark"), true, false);

    private static final String VALID_INDEX = task.getIndex().toString();
    private static final String VALID_WEEKNUMBER = task.getWeekNumber().toString();
    private static final String VALID_OFFCIALDEADLINE = task.getOfficialDeadline().toString();
    private static final String VALID_CUSTOMIZEDDEADLINE = task.getCustomizedDeadline().toString();

    private static final String DESCRIPTION = "Test Task";
    private static final String REMARK = "no remark";
    private static final boolean ISCUSTOMIZED = true;
    private static final boolean ISDONE = false;
    private static final String CATEGORY = "Admin";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedTask testTask = new JsonAdaptedTask(task);
        Assertions.assertTrue(task.isSameTask(testTask.toModelType()));
    }

    @Test
    public void toModelType_invalidIndex_throwsIllegalValueException() {
        JsonAdaptedTask testTask =
                new JsonAdaptedTask(INVALID_INDEX, VALID_WEEKNUMBER, DESCRIPTION,
                        VALID_OFFCIALDEADLINE, VALID_CUSTOMIZEDDEADLINE, REMARK, CATEGORY, ISCUSTOMIZED, ISDONE);
        String expectedMessage = Index.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, testTask::toModelType);
    }

    @Test
    public void toModelType_nullIndex_throwsIllegalValueException() {
        JsonAdaptedTask testTask =
                new JsonAdaptedTask(null, VALID_WEEKNUMBER, DESCRIPTION,
                        VALID_OFFCIALDEADLINE, VALID_CUSTOMIZEDDEADLINE, REMARK, CATEGORY, ISCUSTOMIZED, ISDONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "index");
        assertThrows(IllegalValueException.class, expectedMessage, testTask::toModelType);
    }

    @Test
    public void toModelType_invalidWeekNumber_throwsIllegalValueException() {
        JsonAdaptedTask testTask =
                new JsonAdaptedTask(VALID_INDEX, INVALID_WEEKNUMBER, DESCRIPTION,
                        VALID_OFFCIALDEADLINE, VALID_CUSTOMIZEDDEADLINE, REMARK, CATEGORY, ISCUSTOMIZED, ISDONE);
        String expectedMessage = WeekNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, testTask::toModelType);
    }

    @Test
    public void toModelType_nullWeekNumber_throwsIllegalValueException() {
        JsonAdaptedTask testTask =
                new JsonAdaptedTask(VALID_INDEX, null, DESCRIPTION,
                        VALID_OFFCIALDEADLINE, VALID_CUSTOMIZEDDEADLINE, REMARK, CATEGORY, ISCUSTOMIZED, ISDONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "weekNumber");
        assertThrows(IllegalValueException.class, expectedMessage, testTask::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask testTask =
                new JsonAdaptedTask(VALID_INDEX, VALID_WEEKNUMBER, null,
                        VALID_OFFCIALDEADLINE, VALID_CUSTOMIZEDDEADLINE, REMARK, CATEGORY, ISCUSTOMIZED, ISDONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "description");
        assertThrows(IllegalValueException.class, expectedMessage, testTask::toModelType);
    }

    @Test
    public void toModelType_invalidOfficialDeadline_throwsIllegalValueException() {
        JsonAdaptedTask testTask =
                new JsonAdaptedTask(VALID_INDEX, VALID_WEEKNUMBER, DESCRIPTION,
                        INVALID_DEADLINE, VALID_CUSTOMIZEDDEADLINE, REMARK, CATEGORY, ISCUSTOMIZED, ISDONE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, testTask::toModelType);
    }

    @Test
    public void toModelType_invalidCustomizedDeadline_throwsIllegalValueException() {
        JsonAdaptedTask testTask =
                new JsonAdaptedTask(VALID_INDEX, VALID_WEEKNUMBER, DESCRIPTION,
                        VALID_OFFCIALDEADLINE, INVALID_DEADLINE, REMARK, CATEGORY, ISCUSTOMIZED, ISDONE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, testTask::toModelType);
    }
}

package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.testutil.TypicalSemesters.AY2020_SEM_1;

import org.junit.jupiter.api.Test;

class JsonAdaptedSemesterTest {
    //    private static final int INVALID_YEAR = 0;
    //
    //    private static final int VALID_SEMESTER_NUMBER = 2;
    //    private static final List<JsonAdaptedAllocation> VALID_ALLOCATIONS = null;
    //    private static final Map<String, Integer> VALID_ROOM_FEES = null;

    @Test
    void toModelType_validSemesterDetails_returnsSemester() throws Exception {
        JsonAdaptedSemester semester = new JsonAdaptedSemester(AY2020_SEM_1);
        assertEquals(AY2020_SEM_1, semester.toModelType());
    }

    //    @Test
    //    void toModelType_invalidAcademicYear_throwsIllegalValueException() {
    //        JsonAdaptedSemester semester =
    //                new JsonAdaptedSemester(INVALID_YEAR, VALID_SEMESTER_NUMBER, VALID_ALLOCATIONS,
    //                        VALID_ROOM_FEES);
    //        String expectedMessage = AcademicYear.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, semester::toModelType);
    //    }
}

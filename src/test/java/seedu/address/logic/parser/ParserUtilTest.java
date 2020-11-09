package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Grade;
import seedu.address.model.module.ModuleName;
import seedu.address.model.semester.Semester;

public class ParserUtilTest {
    private static final String INVALID_MOD_NAME = "C@2103T";
    private static final String INVALID_GRADE = " ";
    private static final String INVALID_SEMESTER = Semester.NA.toString();

    private static final String VALID_MOD_NAME = "CS2100";
    private static final String VALID_GRADE = "A+";
    private static final String VALID_SEMESTER = Semester.Y2S1.toString();

    private static final String VALID_GOAL = "1";
    private static final String INVALID_GOAL = "ABC";
    private static final String INVALID_GOAL_RANGE = "0";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseIndex(Long.toString
                (Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_MOD_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        ModuleName expectedModuleName = new ModuleName(VALID_MOD_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseName(VALID_MOD_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_MOD_NAME + WHITESPACE;
        ModuleName expectedModuleName = new ModuleName(VALID_MOD_NAME);
        assertEquals(expectedModuleName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseGrade_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGrade((String) null));
    }

    @Test
    public void parseGrade_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGrade(INVALID_GRADE));
    }

    @Test
    public void parseGrade_validValueWithoutWhitespace_returnsGrade() throws Exception {
        Grade expectedGrade = new Grade(VALID_GRADE);
        assertEquals(expectedGrade, ParserUtil.parseGrade(VALID_GRADE));
    }

    @Test
    public void parseGrade_validValueWithWhitespace_returnsTrimmedGrade() throws Exception {
        String gradesWithWhitespace = WHITESPACE + VALID_GRADE + WHITESPACE;
        Grade expectedGrade = new Grade(VALID_GRADE);
        assertEquals(expectedGrade, ParserUtil.parseGrade(gradesWithWhitespace));
    }

    @Test
    public void parseSemester_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSemester((String) null));
    }

    @Test
    public void parseSemester_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSemester(INVALID_SEMESTER));
    }

    @Test
    public void parseSemester_validValueWithoutWhitespace_returnsSemester() throws Exception {
        Semester expectedSemester = Semester.valueOf(VALID_SEMESTER);
        assertEquals(expectedSemester, ParserUtil.parseSemester(VALID_SEMESTER));
    }

    @Test
    public void parseSemester_validValueWithWhitespace_returnsTrimmedSemester() throws Exception {
        String semesterWithWhitespace = WHITESPACE + VALID_SEMESTER + WHITESPACE;
        Semester expectedSemester = Semester.valueOf(VALID_SEMESTER);
        assertEquals(expectedSemester, ParserUtil.parseSemester(semesterWithWhitespace));
    }

    @Test
    public void parseGoal_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGoal(null));
    }

    @Test
    public void parseGoal_invalidGoal_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGoal(INVALID_GOAL));
    }

    @Test
    public void parseGoal_invalidGoalRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGoal(INVALID_GOAL_RANGE));
    }

    @Test
    public void parseGoal_validGoal_returnsGoalTarget() throws ParseException {
        GoalTarget expectedGoalTarget = new GoalTarget(Integer.parseInt(VALID_GOAL));
        assertEquals(expectedGoalTarget, ParserUtil.parseGoal(VALID_GOAL));
    }

    @Test
    public void parseGoalLevel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGoalLevel(null));
    }

    @Test
    public void parseGoalLevel_invalidUserInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGoalLevel(INVALID_GOAL));
    }
}

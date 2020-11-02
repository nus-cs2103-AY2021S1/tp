package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

public class ParserUtilTest {
    private static final String INVALID_PROJECT_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "21022020 203333";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_REPOURL = "https://github.com/a/b";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_PROJECT_NAME = "Rachel Walker";
    private static final String VALID_DEADLINE = "21-02-2020 20:33:33";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_REPOURL = "https://github.com/a/b.git";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_TASK_1 = "Write DG";
    private static final String VALID_TASK_2 = "Brainstorm user stories";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PROJECT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PROJECT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseProjectName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProjectName((String) null));
    }

    @Test
    public void parseProjectName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProjectName(INVALID_PROJECT_NAME));
    }

    @Test
    public void parseProjectName_validValueWithoutWhitespace_returnsName() throws Exception {
        ProjectName expectedProjectName = new ProjectName(VALID_PROJECT_NAME);
        assertEquals(expectedProjectName, ParserUtil.parseProjectName(VALID_PROJECT_NAME));
    }

    @Test
    public void parseProjectName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String projectNameWithWhitespace = WHITESPACE + VALID_PROJECT_NAME + WHITESPACE;
        ProjectName expectedProjectName = new ProjectName(VALID_PROJECT_NAME);
        assertEquals(expectedProjectName, ParserUtil.parseProjectName(projectNameWithWhitespace));
    }

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline((String) null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.projectDescription((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.projectDescription(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        ProjectDescription expectedProjectDescription = new ProjectDescription(VALID_ADDRESS);
        assertEquals(expectedProjectDescription, ParserUtil.projectDescription(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        ProjectDescription expectedProjectDescription = new ProjectDescription(VALID_ADDRESS);
        assertEquals(expectedProjectDescription, ParserUtil.projectDescription(addressWithWhitespace));
    }

    @Test
    public void parseRepoUrl_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRepoUrl((String) null));
    }

    @Test
    public void parseRepoUrl_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRepoUrl(INVALID_REPOURL));
    }

    @Test
    public void parseRepoUrl_validValueWithoutWhitespace_returnsRepoUrl() throws Exception {
        RepoUrl expectedRepoUrl = new RepoUrl(VALID_REPOURL);
        assertEquals(expectedRepoUrl, ParserUtil.parseRepoUrl(VALID_REPOURL));
    }

    @Test
    public void parseRepoUrl_validValueWithWhitespace_returnsTrimmedRepoUrl() throws Exception {
        String repoUrlWithWhitespace = WHITESPACE + VALID_REPOURL + WHITESPACE;
        RepoUrl expectedRepoUrl = new RepoUrl(VALID_REPOURL);
        assertEquals(expectedRepoUrl, ParserUtil.parseRepoUrl(repoUrlWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProjectTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProjectTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        ProjectTag expectedProjectTag = new ProjectTag(VALID_TAG_1);
        assertEquals(expectedProjectTag, ParserUtil.parseProjectTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        ProjectTag expectedProjectTag = new ProjectTag(VALID_TAG_1);
        assertEquals(expectedProjectTag, ParserUtil.parseProjectTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<ProjectTag> actualProjectTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<ProjectTag>
            expectedProjectTagSet = new HashSet<ProjectTag>(Arrays.asList(new ProjectTag(VALID_TAG_1),
            new ProjectTag(VALID_TAG_2)));

        assertEquals(expectedProjectTagSet, actualProjectTagSet);
    }

    // TODO: task invalid tests
    @Test
    public void parseTask_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTask(null));
    }

    @Test
    public void parseTask_validValueWithoutWhitespace_returnsTask() {
        Task expectedTask = new Task(VALID_TASK_1, null, null, 0);
        assertEquals(expectedTask, ParserUtil.parseTask(VALID_TASK_1));
    }

    @Test
    public void parseTask_validValueWithWhitespace_returnsTrimmedTask() {
        String taskWithWhitespace = WHITESPACE + VALID_TASK_1 + WHITESPACE;
        Task expectedTask = new Task(VALID_TASK_1, null, null, 0);
        assertEquals(expectedTask, ParserUtil.parseTask(taskWithWhitespace));
    }

    @Test
    public void parseTasks_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTasks(null));
    }

    @Test
    public void parseTasks_emptyCollection_returnsEmptySet() {
        assertTrue(ParserUtil.parseTasks(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTasks_collectionWithValidTasks_returnsTaskSet() {
        Set<Task> actualTaskSet = ParserUtil.parseTasks(Arrays.asList(VALID_TASK_1, VALID_TASK_2));
        Set<Task> expectedTaskSet = new HashSet<Task>(Arrays.asList(
                new Task(VALID_TASK_1, null, null, 0),
                new Task(VALID_TASK_2, null, null, 0)));

        assertEquals(expectedTaskSet, actualTaskSet);
    }

}

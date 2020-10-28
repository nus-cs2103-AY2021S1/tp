package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.BRICK;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;

public class JsonAdaptedTaskTest {
    private static final String INVALID_TASK_NAME = "Refactor Cl@ss";
    private static final String INVALID_DESCRIPTION = "29022020000000";
    private static final String INVALID_PROJECT_DESCRIPTION = " ";
    private static final String INVALID_REPOURL = "https://github.com/a/b";
    private static final String INVALID_PROJECT_TAG = "#friend";
    //    private static final String INVALID_TEAMMATE = "";
    //    private static final String INVALID_TASKS = "";

    private static final String VALID_PROJECT_NAME = BRICK.getProjectName().toString();
    private static final String VALID_DEADLINE = BRICK.getDeadline().toString();
    private static final String VALID_REPOURL = BRICK.getRepoUrl().toString();
    private static final String VALID_PROJECT_DESCRIPTION = BRICK.getProjectDescription().toString();
    private static final List<JsonAdaptedTag> VALID_PROJECT_TAGS = BRICK.getProjectTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTask> VALID_TASKS = BRICK.getTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedMeeting> VALID_MEETINGS = BRICK.getMeetings().stream()
            .map(JsonAdaptedMeeting::new)
            .collect(Collectors.toList());
    private static final List<JsonParticipation> VALID_TEAMMATES = BRICK.getParticipationList().stream()
            .map(JsonParticipation::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(BRICK);
        assertEquals(BRICK, project.toModelType());
    }

    @Test
    public void toModelType_invalidProjectName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_PROJECT_NAME, VALID_DEADLINE, VALID_REPOURL, VALID_PROJECT_DESCRIPTION,
                        VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = ProjectName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectName_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(null, VALID_DEADLINE, VALID_REPOURL,
                VALID_PROJECT_DESCRIPTION,
                VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, INVALID_DEADLINE, VALID_REPOURL, VALID_PROJECT_DESCRIPTION,
                        VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, null, VALID_REPOURL,
                VALID_PROJECT_DESCRIPTION,
                VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidRepoUrl_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, INVALID_REPOURL, VALID_PROJECT_DESCRIPTION,
                        VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = RepoUrl.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, null,
                VALID_PROJECT_DESCRIPTION,
                VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RepoUrl.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidProjectDescription_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, VALID_REPOURL, INVALID_PROJECT_DESCRIPTION,
                        VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = ProjectDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectDescription_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, VALID_REPOURL, null,
                VALID_PROJECT_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_PROJECT_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_PROJECT_TAG));
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, VALID_REPOURL, VALID_PROJECT_DESCRIPTION,
                        invalidTags, new ArrayList<>());
        assertThrows(IllegalValueException.class, project::toModelType);
    }
    /*
    what constitutes an invalid task?
     */
    // TODO: invalidTasks
    //    @Test
    //    public void toModelType_invalidTasks_throwsIllegalValueException() {
    //        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_PROJECT_TAGS);
    //        invalidTags.add(new JsonAdaptedTag(INVALID_PROJECT_TAG));
    //        JsonAdaptedProject project =
    //          new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, VALID_REPOURL, VALID_PROJECT_DESCRIPTION,
    //                        invalidTags, new ArrayList<>(), VALID_TEAMMATES);
    //        assertThrows(IllegalValueException.class, project::toModelType);
    //    }

    /*
        tasks and teammates can be null so dont need to test?
    */
    //    @Test
    //    public void toModelType_nullTasks_throwsIllegalValueException() {
    //        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE,
    //                VALID_REPOURL, VALID_PROJECT_DESCRIPTION,
    //                VALID_PROJECT_TAGS, null, VALID_TEAMMATES);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Task.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    //    }
    //    @Test
    //    public void toModelType_nullTeammates_throwsIllegalValueException() {
    //        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE,
    //                VALID_REPOURL, VALID_PROJECT_DESCRIPTION,
    //                VALID_PROJECT_TAGS, VALID_TASKS, null);
    //        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Participation.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    //    }

}

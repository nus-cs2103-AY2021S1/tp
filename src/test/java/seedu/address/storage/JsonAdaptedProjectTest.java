package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedProject.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Email;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;

public class JsonAdaptedProjectTest {
    private static final String INVALID_PROJECT_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "29022020000000";
    private static final String INVALID_PROJECT_DESCRIPTION = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_PROJECT_NAME = BENSON.getProjectName().toString();
    private static final String VALID_DEADLINE = BENSON.getDeadline().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_PROJECT_DESCRIPTION = BENSON.getProjectDescription().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTask> VALID_TASKS = BENSON.getTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validProjectDetails_returnsProject() throws Exception {
        JsonAdaptedProject project = new JsonAdaptedProject(BENSON);
        assertEquals(BENSON, project.toModelType());
    }

    @Test
    public void toModelType_invalidProjectName_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(INVALID_PROJECT_NAME, VALID_DEADLINE, VALID_EMAIL, VALID_PROJECT_DESCRIPTION,
                        VALID_TAGS, VALID_TASKS);
        String expectedMessage = ProjectName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectName_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(null, VALID_DEADLINE, VALID_EMAIL,
                VALID_PROJECT_DESCRIPTION, VALID_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, INVALID_DEADLINE, VALID_EMAIL, VALID_PROJECT_DESCRIPTION,
                        VALID_TAGS, VALID_TASKS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, null, VALID_EMAIL,
            VALID_PROJECT_DESCRIPTION, VALID_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, INVALID_EMAIL, VALID_PROJECT_DESCRIPTION,
                        VALID_TAGS, VALID_TASKS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, null,
            VALID_PROJECT_DESCRIPTION, VALID_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidProjectDescription_throwsIllegalValueException() {
        JsonAdaptedProject project =
            new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, VALID_EMAIL, INVALID_PROJECT_DESCRIPTION,
                VALID_TAGS, VALID_TASKS);
        String expectedMessage = ProjectDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_nullProjectDescription_throwsIllegalValueException() {
        JsonAdaptedProject project = new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, VALID_EMAIL,
                null, VALID_TAGS, VALID_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, project::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedProject project =
                new JsonAdaptedProject(VALID_PROJECT_NAME, VALID_DEADLINE, VALID_EMAIL, VALID_PROJECT_DESCRIPTION,
                        invalidTags, new ArrayList<>());
        assertThrows(IllegalValueException.class, project::toModelType);
    }

    // TODO: invalidTasks

}

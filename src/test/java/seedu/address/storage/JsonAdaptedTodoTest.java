package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTodos.HOMEWORK;
import static seedu.address.testutil.TypicalTodos.USER_GUIDE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Todo;

public class JsonAdaptedTodoTest {
    public static final String INVALID_LINK_URL = "invalid";
    public static final String INVALID_TAG = "#CS2100";
    public static final String BLANK = "";
    public static final String VALID_DESCRIPTION = HOMEWORK.getDescription();
    public static final LocalDateTime VALID_DEADLINE = LocalDateTime.of(2020, 05, 15, 20, 00);
    public static final Boolean VALID_IS_DONE = true;
    public static final String VALID_LINK_DESCRIPTION = "Project google document";
    public static final String VALID_LINK_URL = "https://www.isvalidlink.com";
    public static final CollaborativeLink VALID_COLLABORATIVE_LINK = new CollaborativeLink(VALID_LINK_DESCRIPTION,
            VALID_LINK_URL);
    public static final Recurrence VALID_RECURRENCE = new Recurrence(1,
            "day");
    public static final JsonAdaptedRecurrence VALID_JSON_ADAPTED_RECURRENCE =
            new JsonAdaptedRecurrence(VALID_RECURRENCE);
    public static final List<JsonAdaptedTag> VALID_TAGS = USER_GUIDE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validTodoDetails_returnsTodo() throws Exception {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(HOMEWORK);
        assertEquals(HOMEWORK, todo.toModelType());
    }
    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(null, VALID_IS_DONE, VALID_DEADLINE, VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalValueException.class, todo::toModelType);
    }
    @Test
    public void toModelType_nullIsDone_throwsIllegalValueException() {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, null, VALID_DEADLINE, VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalValueException.class, todo::toModelType);
    }
    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, null, VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalValueException.class, todo::toModelType);
    }
    @Test
    public void toModelType_nullLinkDescription_throwsNullPointerException() throws IllegalValueException {
        Todo modelTodo = new Todo(VALID_IS_DONE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_RECURRENCE,
                        USER_GUIDE.getTags());
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE, null,
                VALID_LINK_URL, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertEquals(modelTodo, todo.toModelType());
    }
    @Test
    public void toModelType_blankLinkDescription_returnsTodo() throws IllegalValueException {
        Todo modelTodo = new Todo(VALID_IS_DONE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_RECURRENCE,
                new CollaborativeLink(BLANK, VALID_LINK_URL),
                USER_GUIDE.getTags());
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE, BLANK,
                VALID_LINK_URL, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertEquals(modelTodo, todo.toModelType());
    }
    @Test
    public void toModelType_nullLinkUrl_returnsTodo() throws IllegalValueException {
        Todo modelTodo = new Todo(VALID_IS_DONE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_RECURRENCE,
                USER_GUIDE.getTags());
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE,
                VALID_LINK_DESCRIPTION, null, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertEquals(modelTodo, todo.toModelType());
    }
    @Test
    public void toModelType_blankLinkUrl_throwsIllegalArgumentException() throws IllegalValueException {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE,
                VALID_LINK_DESCRIPTION, BLANK, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalArgumentException.class, todo::toModelType);
    }
    @Test
    public void toModelType_invalidLinkUrl_throwsIllegalValueException() {
        Todo modelTodo = new Todo(VALID_IS_DONE, VALID_DESCRIPTION, VALID_DEADLINE, VALID_RECURRENCE,
                USER_GUIDE.getTags());
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE,
                VALID_LINK_DESCRIPTION,
                INVALID_LINK_URL, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalArgumentException.class, todo::toModelType);
    }
    @Test
    public void toModelType_nullRecurrence_returnsTodo() throws IllegalValueException {
        Todo modelTodo = new Todo(VALID_IS_DONE, VALID_DESCRIPTION, VALID_DEADLINE,
                VALID_COLLABORATIVE_LINK,
                USER_GUIDE.getTags());
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE,
                VALID_LINK_DESCRIPTION, VALID_LINK_URL, null, VALID_TAGS);
        assertEquals(modelTodo, todo.toModelType());
    }
    @Test
    public void toModelType_blankLinkDescriptionNullRecurrence_returnsTodo()
            throws IllegalValueException {
        Todo modelTodo = new Todo(VALID_IS_DONE, VALID_DESCRIPTION, VALID_DEADLINE, new CollaborativeLink(BLANK,
                VALID_LINK_URL),
                USER_GUIDE.getTags());
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE,
                "", VALID_LINK_URL, null, VALID_TAGS);
        assertEquals(modelTodo, todo.toModelType());
    }
    @Test
    public void toModelType_blankLinkUrlNullRecurrence_throwsIllegalArgumentException()
            throws IllegalValueException {
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE,
                VALID_LINK_DESCRIPTION, BLANK, null, VALID_TAGS);
        assertThrows(IllegalArgumentException.class, todo::toModelType);
    }
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTodo todo = new JsonAdaptedTodo(VALID_DESCRIPTION, VALID_IS_DONE, VALID_DEADLINE,
                VALID_LINK_DESCRIPTION, VALID_LINK_URL, null, invalidTags);
        assertThrows(IllegalValueException.class, todo::toModelType);
    }
}

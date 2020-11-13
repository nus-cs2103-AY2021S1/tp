package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.LECTURE;
import static seedu.address.testutil.TypicalLessons.VALID_MODULE_CODE;
import static seedu.address.testutil.TypicalLessons.VALID_NAME;
import static seedu.address.testutil.TypicalLessons.VALID_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.model.lesson.exceptions.DuplicateLessonException;
import seedu.address.model.lesson.exceptions.LessonNotFoundException;
import seedu.address.testutil.LessonBuilder;

public class UniqueLessonListTest {

    private final UniqueLessonList uniqueLessonList = new UniqueLessonList();

    @Test
    public void contains_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.contains(null));
    }

    @Test
    public void contains_lessonNotInList_returnsFalse() {
        assertFalse(uniqueLessonList.contains(LECTURE));
    }

    @Test
    public void contains_lessonInList_returnsTrue() {
        uniqueLessonList.add(LECTURE);
        assertTrue(uniqueLessonList.contains(LECTURE));
    }

    @Test
    public void contains_lessonWithDifferentIdentity_returnsFalse() {
        uniqueLessonList.add(LECTURE);
        Lesson editedLecture = new LessonBuilder(LECTURE).withName(VALID_NAME).withTime(VALID_TIME)
                .withModuleCode(VALID_MODULE_CODE).build();
        assertFalse(uniqueLessonList.contains(editedLecture));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.add(null));
    }

    @Test
    public void add_duplicateLesson_throwsDuplicatePersonException() {
        uniqueLessonList.add(LECTURE);
        assertThrows(DuplicateLessonException.class, () -> uniqueLessonList.add(LECTURE));
    }

    @Test
    public void remove_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLessonList.remove(null));
    }

    @Test
    public void remove_lessonDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(LessonNotFoundException.class, () -> uniqueLessonList.remove(LECTURE));
    }

    @Test
    public void remove_existingLesson_removesLesson() {
        uniqueLessonList.add(LECTURE);
        uniqueLessonList.remove(LECTURE);
        UniqueLessonList expectedUniquePersonList = new UniqueLessonList();
        assertEquals(expectedUniquePersonList, uniqueLessonList);
    }
}

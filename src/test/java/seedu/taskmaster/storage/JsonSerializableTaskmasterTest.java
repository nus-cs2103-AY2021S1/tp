package seedu.taskmaster.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taskmaster.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.commons.util.JsonUtil;
import seedu.taskmaster.model.Taskmaster;
import seedu.taskmaster.testutil.TypicalStudents;

public class JsonSerializableTaskmasterTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskmasterTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsTaskmaster.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentTaskmaster.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentTaskmaster.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableTaskmaster dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableTaskmaster.class).get();
        Taskmaster taskmasterFromFile = dataFromFile.toModelType();
        Taskmaster typicalStudentsTaskmaster = TypicalStudents.getTypicalTaskmaster();
        assertEquals(taskmasterFromFile, typicalStudentsTaskmaster);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskmaster dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableTaskmaster.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskmaster dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableTaskmaster.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskmaster.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}

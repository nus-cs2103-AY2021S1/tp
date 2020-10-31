package seedu.taskmaster.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.taskmaster.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.commons.util.JsonUtil;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionDateTime;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.testutil.TypicalStudents;

public class JsonSerializableSessionTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableSessionTest");
    private static final Path TYPICAL_SESSION_FILE = TEST_DATA_FOLDER.resolve("typicalSession.json");
    private static final Path INVALID_SESSION_FILE = TEST_DATA_FOLDER.resolve("invalidSession.json");
    private static final Path DUPLICATE_STUDENT_SESSION_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentSession.json");

    private Session getTypicalSession() {
        LocalDateTime dateTime = LocalDateTime.parse("01-01-2020 1200",
                DateTimeFormatter.ofPattern("DD-MM-yyyy HHmm"));
        Session session = new Session(new SessionName("Typical session"), new SessionDateTime(dateTime),
                TypicalStudents.getTypicalStudents());
        session.markStudentAttendance(new NusnetId("e0123456"), AttendanceType.PRESENT);
        session.scoreStudentParticipation(new NusnetId("e0456789"), 5);

        session.markStudentAttendance(new NusnetId("e0946875"), AttendanceType.PRESENT);
        session.scoreStudentParticipation(new NusnetId("e0946875"), 7);

        return session;
    }

    @Test
    public void toModelType_typicalSessionFile_success() throws Exception {
        JsonSerializableSession dataFromFile = JsonUtil.readJsonFile(TYPICAL_SESSION_FILE,
                JsonSerializableSession.class).get();
        Session sessionFromFile = dataFromFile.toModelType();

        Session typicalSession = getTypicalSession();
        assertEquals(sessionFromFile, typicalSession);
    }

    @Test
    public void toModelType_invalidSessionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSession dataFromFile = JsonUtil.readJsonFile(INVALID_SESSION_FILE,
                JsonSerializableSession.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudent_throwsIllegalValueException() throws Exception {
        JsonSerializableSession dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_SESSION_FILE,
                JsonSerializableSession.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSession.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }
}


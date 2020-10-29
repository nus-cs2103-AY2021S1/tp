package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FILENAME;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import seedu.taskmaster.commons.exceptions.DataConversionException;
import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.student.exceptions.StudentNotFoundException;

/**
 * Loads the attendance of all students from a file with the specified name.
 */
public class LoadAttendanceCommand extends StorageCommand {
    public static final String COMMAND_WORD = "load_record";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Loads the attendance of each student from a json file whose name is specified by the input parameter."
            + "\n"
            + "Parameters: "
            + PREFIX_ATTENDANCE_FILENAME + "FILE_NAME (only alphanumeric characters permitted)\n"
            + "Example: " + COMMAND_WORD + PREFIX_ATTENDANCE_FILENAME + "tutorial01";

    public static final String MESSAGE_LOAD_ATTENDANCE_SUCCESS =
            "Successfully loaded attendance from %1$s.json";
    public static final String MESSAGE_NO_FILE_FOUND =
            "A file with that name does not exist";

    public static final String INVALID_FILE_ERROR_MESSAGE = "Error when reading from file %1$s.json: %2$s";
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not load data from file: ";

    private final String filename;

    /**
     * @param filename of the file the attendance is saved to
     */
    public LoadAttendanceCommand(String filename) {
        assert filename.length() > 0;
        this.filename = filename;
        this.storage = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path filepath = Path.of("data", filename + ".json");

        if (!storage.fileExists(filepath)) {
            throw new CommandException(MESSAGE_NO_FILE_FOUND);
        }

        model.clearAttendance();

        try {
            List<StudentRecord> newAttendanceList = storage.readAttendance(filepath).get();

            // TODO: Build a list of students whose IDs aren't found + IDs stored that aren't in the
            //      current StudentList and display it to the user

            for (StudentRecord studentRecord: newAttendanceList) {
                try {
                    model.markStudentWithNusnetId(studentRecord.getNusnetId(), studentRecord.getAttendanceType());
                } catch (StudentNotFoundException snfe) {
                    // skip
                }
            }
        } catch (DataConversionException dce) {
            throw new CommandException(String.format(INVALID_FILE_ERROR_MESSAGE, filename, dce));
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + filename);
        }

        return new CommandResult(String.format(MESSAGE_LOAD_ATTENDANCE_SUCCESS, filename));
    }
}

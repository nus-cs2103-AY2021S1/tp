package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FILENAME;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;

/**
 * Stores the attendance of all students to a file with the specified name.
 */
public class StoreAttendanceCommand extends StorageCommand {
    public static final String COMMAND_WORD = "store_record";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves the attendance of each student to a json file whose name is specified by the input parameter.\n"
            + "Parameters: "
            + PREFIX_ATTENDANCE_FILENAME + "FILE_NAME (only alphanumeric characters permitted)\n"
            + "Example: " + COMMAND_WORD + PREFIX_ATTENDANCE_FILENAME + "tutorial01";

    public static final String MESSAGE_SAVE_SUCCESS_NEWFILE =
            "Created a new file %1$s.json and saved attendance to that file";
    public static final String MESSAGE_SAVE_SUCCESS_OVERWRITE =
            "Saved attendance to existing file %1$s.json";

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private final String filename;

    /**
     * @param filename of the file the attendance is saved to
     */
    public StoreAttendanceCommand(String filename) {
        assert filename.length() > 0;
        this.filename = filename;
        this.storage = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path filepath = Path.of("data", filename + ".json");

        boolean isExistingFile = Files.exists(filepath);

        // TODO
        try {
            String newlineCharacter = "\n";
            storage.saveAttendance(model.getTaskmaster(), filepath);
            Files.write(filepath, newlineCharacter.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        model.clearAttendance();

        if (isExistingFile) {
            return new CommandResult(String.format(MESSAGE_SAVE_SUCCESS_OVERWRITE, filename));
        } else {
            return new CommandResult(String.format(MESSAGE_SAVE_SUCCESS_NEWFILE, filename));
        }
    }
}

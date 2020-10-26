package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_COMMAND;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalStudents.AMY;
import static seedu.resireg.testutil.TypicalStudents.BOB;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.bin.Binnable;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNameContainsKeywordPairsPredicate;
import seedu.resireg.model.student.NameContainsKeywordsPredicate;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;
import seedu.resireg.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    // Valid students
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_FACULTY_AMY = "FASS";
    public static final String VALID_FACULTY_BOB = "SOC";
    public static final String VALID_STUDENT_ID_AMY = "E0111111";
    public static final String VALID_STUDENT_ID_BOB = "E0222222";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    // Valid rooms
    public static final String VALID_FLOOR_A = "21";
    public static final String VALID_FLOOR_B = "7";
    public static final String VALID_FLOOR_C = "11";
    public static final String VALID_ROOM_NUMBER_A = "120";
    public static final String VALID_ROOM_NUMBER_B = "105";
    public static final String VALID_ROOM_NUMBER_C = "103";
    public static final String VALID_ROOM_TYPE_A = "CA";
    public static final String VALID_ROOM_TYPE_B = "NA";
    public static final String VALID_ROOM_TYPE_C = "NN";
    public static final String VALID_TAG_RENOVATED = "renovated";
    public static final String VALID_TAG_DAMAGED = "damaged";

    // Valid bin items
    public static final String VALID_DATE_DELETED_A = "2020-10-26";
    public static final String VALID_DATE_DELETED_B = "2019-10-21";
    public static final Binnable VALID_ITEM_A = AMY;
    public static final Binnable VALID_ITEM_B = BOB;

    // Valid command word aliases
    public static final String VALID_COMMAND_ROOMS_RO = "rooms";
    public static final String VALID_COMMAND_STUDENTS_ST = "students";
    public static final String VALID_ALIAS_ROOMS_RO = "ro";
    public static final String VALID_ALIAS_STUDENTS_ST = "st";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String FACULTY_DESC_AMY = " " + PREFIX_FACULTY + VALID_FACULTY_AMY;
    public static final String FACULTY_DESC_BOB = " " + PREFIX_FACULTY + VALID_FACULTY_BOB;
    public static final String STUDENT_ID_DESC_AMY = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY;
    public static final String STUDENT_ID_DESC_BOB = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String COMMAND_DESC_ROOMS_RO = " " + PREFIX_COMMAND + VALID_COMMAND_ROOMS_RO;
    public static final String COMMAND_DESC_STUDENTS_STU = " " + PREFIX_COMMAND + VALID_COMMAND_STUDENTS_ST;
    public static final String ALIAS_DESC_ROOMS_RO = " " + PREFIX_ALIAS + VALID_ALIAS_ROOMS_RO;
    public static final String ALIAS_DESC_STUDENTS_STU = " " + PREFIX_ALIAS + VALID_ALIAS_STUDENTS_ST;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_FACULTY_DESC = " " + PREFIX_FACULTY; // empty string not allowed for faculties
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_STUDENT_ID; // empty string not allowed for ids
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_FLOOR = "asfdj";
    public static final String INVALID_ROOM_NUMBER = "asdfj";
    public static final String INVALID_ROOM_TYPE = "asdfjk";

    public static final String INVALID_DATE = "asfdj";


    public static final String INVALID_COMMAND_DESC = " " + PREFIX_COMMAND + "71ndn"; // command word doesn't exist
    // alias cant be a command word
    public static final String INVALID_ALIAS_DESC = " " + PREFIX_ALIAS + ListRoomCommand.COMMAND_WORD;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withFaculty(VALID_FACULTY_AMY)
            .withStudentId(VALID_STUDENT_ID_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withFaculty(VALID_FACULTY_BOB)
            .withStudentId(VALID_STUDENT_ID_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     * - the {@code actualHistory} remains unchanged
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualHistory,
                                            CommandResult expectedCommandResult,
                                            Model expectedModel) {
        StorageStub storageStub = new StorageStub();
        try {
            CommandResult result = command.execute(actualModel, storageStub, actualHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualHistory,
                                            String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link ToggleCommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertToggleCommandSuccess(Command command, Model actualModel, CommandHistory actualHistory,
                                                  String expectedMessage,
                                                  Model expectedModel, TabView tabView) {
        ToggleCommandResult expectedCommandResult = new ToggleCommandResult(expectedMessage, tabView);
        assertCommandSuccess(command, actualModel, actualHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the rest of the model, filtered student list and selected student in {@code actualModel} remain unchanged
     * - {@code actualHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualHistory,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ResiReg expectedResiReg = new ResiReg(actualModel.getResiReg());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());
        StorageStub storageStub = new StorageStub();

        assertThrows(CommandException.class, expectedMessage, () ->
                command.execute(actualModel, storageStub, actualHistory));
        assertEquals(expectedResiReg, actualModel.getResiReg());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s ResiReg.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getNameAsString().split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the room at the given {@code targetIndex} in the
     * {@code model}'s ResiReg.
     */
    public static void showRoomAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRoomList().size());

        Room room = model.getFilteredRoomList().get(targetIndex.getZeroBased());
        final String[] info = new String[]{room.getFloor().value, room.getRoomNumber().value};
        model.updateFilteredRoomList(
            new RoomNameContainsKeywordPairsPredicate(Arrays.asList(Map.entry(info[0], info[1])))
        );

        assertEquals(1, model.getFilteredRoomList().size());
    }

    /**
     * Deletes the first student in {@code model}'s filtered list from {@code model}'s resireg.
     */
    public static void deleteFirstStudent(Model model) {
        Student firstStudent = model.getFilteredStudentList().get(0);
        model.deleteStudent(firstStudent);
        model.saveStateResiReg();
    }

    /**
     * A stub class for Storage.
     */
    private static class StorageStub implements Storage {
        @Override
        public Path getUserPrefsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getResiRegFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyResiReg> readResiReg(Path filePath) throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveResiReg(ReadOnlyResiReg resiReg) throws IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archiveResiReg(ReadOnlyResiReg resiReg) throws IOException {
            throw new AssertionError("This method should not be called.");
        }
    }
}

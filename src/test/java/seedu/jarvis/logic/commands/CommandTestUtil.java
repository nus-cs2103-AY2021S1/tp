package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.jarvis.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jarvis.commons.core.index.Index;
import seedu.jarvis.logic.commands.edit.EditLoginCommand.EditLoginDescriptor;
import seedu.jarvis.logic.commands.edit.EditMasteryCheckCommand;
import seedu.jarvis.logic.commands.edit.EditStudentCommand.EditPersonDescriptor;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.consultation.ConsultationNameContainsKeywordsPredicate;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.masterycheck.MasteryCheckNameContainsKeywordsPredicate;
import seedu.jarvis.model.student.NameContainsKeywordsPredicate;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.testutil.EditLoginDescriptorBuilder;
import seedu.jarvis.testutil.EditMasteryCheckDescriptorBuilder;
import seedu.jarvis.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";

    public static final String VALID_DATE_AMY_CONSULTATION = "2020-09-20";
    public static final String VALID_DATE_BOB_CONSULTATION = "2020-10-23";
    public static final String VALID_TIME_AMY_CONSULTATION = "11:20";
    public static final String VALID_TIME_BOB_CONSULTATION = "14:30";

    public static final String TODO_PREFIX = "-t";
    public static final String EVENT_PREFIX = "-e";
    public static final String DEADLINE_PREFIX = "-d";
    public static final String DATE_PREFIX = "d/";
    public static final String TIME_PREFIX = "t/";
    public static final String VALID_DESCRIPTION = "CS2103T project tp submission 1";
    public static final String VALID_DATE_TASK = "2020-10-15";
    public static final String VALID_USERINPUT_DATE = "d/" + VALID_DATE_TASK;
    public static final String VALID_TIME_TASK = "18:18";
    public static final String VALID_USERINPUT_TIME = "t/" + VALID_TIME_TASK;
    public static final String VALID_DATE_TIME = VALID_DATE_TASK + " " + VALID_TIME_TASK;
    public static final String INVALID_DATE_EXPLICIT = "2020-13-13";
    public static final String INVALID_DATE_IMPLICIT = "2020-02-30";
    public static final String INVALID_TIME = "23:60";

    public static final String VALID_USERNAME_STUDENT = "nusstu\\e1234567";
    public static final String VALID_USERNAME_PROF = "nusstf\\e1234567";
    public static final String VALID_PASSWORD_1 = "test132";
    public static final String VALID_PASSWORD_2 = "test133";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String PHONE_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    public static final String NAME_DESC_AMY_CONSULTATION = " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB_CONSULTATION = " " + VALID_NAME_BOB;
    public static final String DATE_DESC_AMY_CONSULTATION = " " + PREFIX_DATE + VALID_DATE_AMY_CONSULTATION;
    public static final String DATE_DESC_BOB_CONSULTATION = " " + PREFIX_DATE + VALID_DATE_BOB_CONSULTATION;
    public static final String TIME_DESC_AMY_CONSULTATION = " " + PREFIX_TIME + VALID_TIME_AMY_CONSULTATION;
    public static final String TIME_DESC_BOB_CONSULTATION = " " + PREFIX_TIME + VALID_TIME_BOB_CONSULTATION;

    public static final String INVALID_DATE_ONE = ""; // empty string
    public static final String INVALID_DATE_TWO = "0"; // not in correct format
    public static final String INVALID_DATE_THREE = "2019-13-27"; // invalid month - over 12
    public static final String INVALID_DATE_FOUR = "2019-02-30"; // invalid date - over 28 for Feb
    public static final String INVALID_DATE_FIVE = "999-02-27"; // invalid year - wrong format (YYY)
    public static final String INVALID_DATE_SIX = "99999-02-27"; // invalid year - wrong format (YYYYY)
    public static final String INVALID_DATE_SEVEN = "2019-3-27"; // invalid month - wrong format
    public static final String INVALID_DATE_EIGHT = "2019-03-2"; // invalid date - wrong format

    public static final String INVALID_TIME_ONE = " t/25:51"; // invalid hour - over 24
    public static final String INVALID_TIME_TWO = " t/25:61"; // invalid minute - over 60
    public static final String INVALID_TIME_THREE = " t/0:51"; // invalid hour - wrong format
    public static final String INVALID_TIME_FOUR = " t/00:5"; // invalid minute - wrong format

    public static final String INVALID_DATE_ONE_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_ONE;
    public static final String INVALID_DATE_TWO_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_TWO;
    public static final String INVALID_DATE_THREE_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_THREE;
    public static final String INVALID_DATE_FOUR_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_FOUR;
    public static final String INVALID_DATE_FIVE_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_FIVE;
    public static final String INVALID_DATE_SIX_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_SIX;
    public static final String INVALID_DATE_SEVEN_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_SEVEN;
    public static final String INVALID_DATE_EIGHT_WITH_PREFIX = " " + PREFIX_DATE + INVALID_DATE_EIGHT;

    public static final String INVALID_TIME_ONE_WITH_PREFIX = " " + PREFIX_DATE + INVALID_TIME_ONE;
    public static final String INVALID_TIME_TWO_WITH_PREFIX = " " + PREFIX_DATE + INVALID_TIME_TWO;
    public static final String INVALID_TIME_THREE_WITH_PREFIX = " " + PREFIX_DATE + INVALID_TIME_THREE;
    public static final String INVALID_TIME_FOUR_WITH_PREFIX = " " + PREFIX_DATE + INVALID_TIME_FOUR;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_TELEGRAM + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol

    public static final EditPersonDescriptor DESC_AMY;
    public static final EditPersonDescriptor DESC_BOB;

    public static final EditLoginDescriptor DESC_STUDENT;
    public static final EditLoginDescriptor DESC_PROF;

    public static final EditMasteryCheckCommand.EditMasteryCheckDescriptor DESC_MC_ONE;
    public static final EditMasteryCheckCommand.EditMasteryCheckDescriptor DESC_MC_TWO;

    public static final String EDIT_STUDENT = "-s ";
    public static final String EDIT_LOGIN = "-l ";

    public static final String MASTERY_CHECK_PREFIX = "-mc";
    public static final String CONSULTATION_PREFIX = "-c";


    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        DESC_STUDENT = new EditLoginDescriptorBuilder().withUsername(VALID_USERNAME_STUDENT)
                .withPassword(VALID_PASSWORD_1).build();
        DESC_PROF = new EditLoginDescriptorBuilder().withUsername(VALID_USERNAME_PROF)
                .withPassword(VALID_PASSWORD_1).build();
        DESC_MC_ONE = new EditMasteryCheckDescriptorBuilder().withHasPassed(false).build();
        DESC_MC_TWO = new EditMasteryCheckDescriptorBuilder().withHasPassed(true).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the jarvis book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s jarvis book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the consultation at the given {@code targetIndex} in the
     * {@code model}'s jarvis book.
     */
    public static void showConsultationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Consultation consultation = model.getFilteredConsultationsList().get(targetIndex.getZeroBased());
        final String[] splitName = consultation.getStudentName().split("\\s+");
        model.updateFilteredConsultationList(
                new ConsultationNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredConsultationsList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the mastery check at the given {@code targetIndex} in the
     * {@code model}'s jarvis book.
     */
    public static void showMasteryCheckAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMasteryChecksList().size());

        MasteryCheck masteryCheck = model.getFilteredMasteryChecksList().get(targetIndex.getZeroBased());
        final String[] splitName = masteryCheck.getStudentName().split("\\s+");
        model.updateFilteredMasteryCheckList(
                new MasteryCheckNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredMasteryChecksList().size());
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_START_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Trackr;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_ALEX = "Alex Tan";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_BENG = "Ah Beng";
    public static final String VALID_NAME_CHARLIE = "CHARLIE CHEN";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_ALEX = "91234567";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_BENG = "81234567";
    public static final String VALID_PHONE_CHARLIE = "82223333";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_ALEX = "alextan@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_BENG = "abeng@u.nus.edu";
    public static final String VALID_EMAIL_CHARLIE = "charlie@hi.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_STUDENT_ID_AMY = "A1234567X";
    public static final String VALID_STUDENT_ID_ALEX = "A1234567X";
    public static final String VALID_STUDENT_ID_BOB = "A7654321X";
    public static final String VALID_STUDENT_ID_BENG = "A7654321B";
    public static final String VALID_STUDENT_ID_CHARLIE = "A1928835B";
    public static final String VALID_TUTORIAL_GROUP_B014 = "B014";
    public static final String VALID_TUTORIAL_GROUP_DAY_MON = "MON";
    public static final String VALID_TUTORIAL_GROUP_1300 = "13:00";
    public static final String VALID_TUTORIAL_GROUP_1500 = "15:00";
    public static final String INVALID_TUTORIAL_GROUP_1500 = "1500";

    public static final String VALID_MODULE_CS2103T = "CS2103T";
    public static final String VALID_MODULE_CS2040 = "CS2040";

    public static final String MODULE_ID_DESC_CS2103T = " " + PREFIX_MODULE + VALID_MODULE_CS2103T;
    public static final String MODULE_ID_DESC_CS2040 = " " + PREFIX_MODULE + VALID_MODULE_CS2040;
    public static final String INVALID_MODULE_ID = " " + PREFIX_MODULE + "CS@";

    public static final String TUTORIAL_GROUP_ID_B014 = " " + PREFIX_TUTORIAL_GRP + VALID_TUTORIAL_GROUP_B014
        + " " + PREFIX_TUTORIAL_GRP_DAY + VALID_TUTORIAL_GROUP_DAY_MON
        + " " + PREFIX_TUTORIAL_GRP_START_TIME + VALID_TUTORIAL_GROUP_1300
        + " " + PREFIX_TUTORIAL_GRP_END_TIME + VALID_TUTORIAL_GROUP_1500;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String NAME_DESC_ALEX = " " + PREFIX_NAME + VALID_NAME_ALEX;
    public static final String NAME_DESC_BENG = " " + PREFIX_NAME + VALID_NAME_BENG;
    public static final String NAME_DESC_CHARLIE = " " + PREFIX_NAME + VALID_NAME_CHARLIE;
    public static final String PHONE_DESC_ALEX = " " + PREFIX_PHONE + VALID_PHONE_ALEX;
    public static final String PHONE_DESC_BENG = " " + PREFIX_PHONE + VALID_PHONE_BENG;
    public static final String PHONE_DESC_CHARLIE = " " + PREFIX_PHONE + VALID_PHONE_CHARLIE;
    public static final String EMAIL_DESC_ALEX = " " + PREFIX_EMAIL + VALID_EMAIL_ALEX;
    public static final String EMAIL_DESC_BENG = " " + PREFIX_EMAIL + VALID_EMAIL_BENG;
    public static final String EMAIL_DESC_CHARLIE = " " + PREFIX_EMAIL + VALID_EMAIL_CHARLIE;
    public static final String STUDENT_ID_DESC_ALEX = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_ALEX;
    public static final String STUDENT_ID_DESC_BENG = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BENG;
    public static final String STUDENT_ID_DESC_CHARLIE = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_CHARLIE;
    public static final String MODULE_DESC_ALEX = " " + PREFIX_MODULE + VALID_MODULE_CS2103T;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_STUDENT_ID + "a1234567x"; // capital letters only

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_ALEX;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BENG;
    public static final EditStudentCommand.EditStudentDescriptor DESC_CHARLIE;


    static {
        DESC_ALEX = new EditStudentDescriptorBuilder().withName(VALID_NAME_ALEX)
                .withPhone(VALID_PHONE_ALEX).withEmail(VALID_EMAIL_ALEX)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BENG = new EditStudentDescriptorBuilder().withName(VALID_NAME_BENG)
                .withPhone(VALID_PHONE_BENG).withEmail(VALID_EMAIL_BENG)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_CHARLIE = new EditStudentDescriptorBuilder().withName(VALID_NAME_CHARLIE)
                .withPhone(VALID_PHONE_CHARLIE).withEmail(VALID_EMAIL_CHARLIE)
                .withTags(VALID_TAG_FRIEND).build();
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
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link
     #assertCommandSuccess(Command, Model, CommandResult, Model)}
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
     * - the trackr, filtered module list and selected module in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Trackr expectedTrackr = new Trackr(actualModel.getModuleList());
        List<Module> expectedFilteredList = new ArrayList<>(actualModel.getFilteredModuleList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTrackr, actualModel.getModuleList());
        assertEquals(expectedFilteredList, actualModel.getFilteredModuleList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the module at the given {@code targetIndex} in the
     * {@code model}'s module list.
     */
    public static void showModuleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());

        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());

        final String[] splitName = module.getModuleId().toString().split("\\s+");

        model.updateFilteredModuleList(new ModuleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredModuleList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the give {@code targetIndex} in the
     * {@code mode}'s student list.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex, Module targetModule,
                                          TutorialGroup targetTutorialGroup) {
        model.setViewToTutorialGroup(targetModule);
        model.setViewToStudent(targetTutorialGroup);
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().toString().split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }
}

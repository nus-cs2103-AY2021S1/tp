package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.HashMap;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand.EditContactDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.commands.todolistcommands.EditTaskDescriptor;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactNameContainsKeywordsPredicate;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameContainsKeyWordsPredicate;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.contact.EditContactDescriptorBuilder;
import seedu.address.testutil.todolist.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    //========= Contact List ==================================================
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_TELEGRAM_AMY = "@amytele";
    public static final String VALID_TELEGRAM_BOB = "@bobtele";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "bobtele"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    //======= Module List =======================================================================
    public static final String VALID_MODULENAME_CS2030 = "CS2030";
    public static final String VALID_MODULENAME_CS2103T = "CS2103T";
    public static final String VALID_MODULENAME_ES2660 = "ES2660";
    public static final String INVALID_MODULENAME_CSA200 = "CSA200";

    public static final String VALID_TAG_CORE_MODULE = "Core";
    public static final String VALID_TAG_UNGRADED_MODULE = "Ungraded";
    public static final String VALID_MODULE_LESSON_LECTURE = "Lecture";
    public static final String VALID_MODULE_LESSON_TUTORIAL = "Tutorial";
    public static final String VALID_ZOOM_LINK_CS2103T = "https://nus-sg.zoom.us/CS2103t";
    public static final String VALID_ZOOM_LINK_ES2660 = "https://nus-sg.zoom.us/ES2660";
    public static final String VALID_ZOOM_LINK_CS2030 = "https://nus-sg.zoom.us/CS2030";
    public static final HashMap<ModuleLesson, ZoomLink> VALID_ZOOMLINKS_CS2030 = new HashMap<>();
    public static final HashMap<ModuleLesson, ZoomLink> VALID_ZOOMLINKS_CS2103T = new HashMap<>();
    public static final HashMap<ModuleLesson, ZoomLink> VALID_ZOOMLINKS_ES2660 = new HashMap<>();

    public static final double VALID_MC_4 = 4.0;
    public static final double VALID_MC_2 = 2.0;
    public static final double VALID_GRADEPOINT_4 = 4.0;
    public static final double VALID_GRADEPOINT_5 = 5.0;
    public static final String VALID_TAG_LECTURE = "Lecture";
    public static final String VALID_TAG_TUTORIAL = "Tutorial";

    public static final String MODULE_LESSON_DESC_LECTURE = " " + PREFIX_NAME + VALID_MODULE_LESSON_LECTURE;
    public static final String MODULE_LESSON_DESC_TUTORIAL = " " + PREFIX_NAME + VALID_MODULE_LESSON_TUTORIAL;
    public static final String INVALID_MODULE_LESSON_DESC = " " + PREFIX_NAME + ".10()";
    public static final String ZOOM_LINK_DESC_CS2103T = " " + PREFIX_ZOOM_LINK + VALID_ZOOM_LINK_CS2103T;
    public static final String ZOOM_LINK_DESC_ES2660 = " " + PREFIX_ZOOM_LINK + VALID_ZOOM_LINK_ES2660;
    public static final String INVALID_ZOOM_LINK_DESC = " " + PREFIX_ZOOM_LINK + "https://incorrectLink.zoom.us";
    public static final String NAME_DESC_ES2660 = " " + PREFIX_NAME + VALID_MODULENAME_ES2660;
    public static final String NAME_DESC_CS2103T = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T;


    // ================================== TodoList ===================================== //

    public static final String VALID_NAME_ASSIGNMENT01 = "Submit Assignment 1";
    public static final String VALID_PRIORITY_HIGH = "HIGH";
    public static final String VALID_PRIORITY_NORMAL = "NORMAL";
    public static final String VALID_TAG_CS2100 = "CS2100";
    public static final String VALID_TAG_CS2105 = "CS2105";
    public static final String VALID_TAG_CS2030 = "CS2030";
    public static final String VALID_TAG_LAB = "Lab";
    public static final String VALID_TAG_DAILY = "Daily";
    public static final String VALID_DATE1 = "2020-11-05";
    public static final String VALID_DATE2 = "2020-12-03";
    public static final String VALID_STATUS_COMPLETED = "COMPLETED";
    public static final String VALID_STATUS_NOT_COMPLETED = "NOT_COMPLETED";
    public static final String VALID_DATE_CREATED_1 = "2020-11-01";
    public static final String VALID_DATE_CREATED_2 = "2020-12-01";

    // unnamed desc
    public static final String TAG_DESC_CS2100 = " " + PREFIX_TAG + VALID_TAG_CS2100;
    public static final String TAG_DESC_LAB = " " + PREFIX_TAG + VALID_TAG_LAB;
    public static final String TAG_DESC_DAILY = " " + PREFIX_TAG + VALID_TAG_DAILY;

    // invalid value
    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_NAME + "Finish lab 5 report for next week lab";
    public static final String INVALID_TASK_TAG_DESC = " " + PREFIX_TAG + "Lab CS2100";
    public static final String INVALID_TASK_PRIORITY_DESC = " " + PREFIX_PRIORITY + "important";
    public static final String INVALID_TASK_DATE_DESC = " " + PREFIX_DATE + "2020-02-30";

    // LAB05 and LAB07
    public static final String VALID_NAME_LAB05 = "Finish Lab 5 Report";
    public static final String VALID_NAME_LAB07 = "Finish Lab 7 Report";
    public static final String VALID_TAG_LAB05 = "CS2100";
    public static final String VALID_TAG_LAB07 = "CS2030";
    public static final String VALID_PRIORITY_LAB05 = "HIGH";
    public static final String VALID_PRIORITY_LAB07 = "NORMAL";
    public static final String VALID_DATE_LAB05 = "2020-11-05";
    public static final String VALID_DATE_LAB07 = "2020-12-03";
    public static final String VALID_STATUS_LAB05 = "NOT_COMPLETED";
    public static final String VALID_STATUS_LAB07 = "COMPLETED";

    public static final EditTaskDescriptor DESC_LAB05;
    public static final EditTaskDescriptor DESC_LAB07;

    public static final String NAME_DESC_LAB05 = " " + PREFIX_NAME + VALID_NAME_LAB05;
    public static final String NAME_DESC_LAB07 = " " + PREFIX_NAME + VALID_NAME_LAB07;
    public static final String TAG_DESC_LAB05 = " " + PREFIX_TAG + VALID_TAG_LAB05;
    public static final String TAG_DESC_LAB07 = " " + PREFIX_TAG + VALID_TAG_LAB07;
    public static final String PRIORITY_DESC_LAB05 = " " + PREFIX_PRIORITY + VALID_PRIORITY_LAB05;
    public static final String PRIORITY_DESC_LAB07 = " " + PREFIX_PRIORITY + VALID_PRIORITY_LAB07;
    public static final String DATE_DESC_LAB05 = " " + PREFIX_DATE + VALID_DATE_LAB05;
    public static final String DATE_DESC_LAB07 = " " + PREFIX_DATE + VALID_DATE_LAB07;

    // =============================== PREAMBLE ========================================= //

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    ////////////////////////////////////// SCHEDULER /////////////////////////////////////
    public static final String VALID_EVENT_NAME_1 = "CS2103T Quiz";
    public static final String VALID_EVENT_NAME_2 = "ES2660 Essay";
    public static final String VALID_EVENT_DATE_1 = "1-2-2020 1200";
    public static final String VALID_EVENT_DATE_2 = "2-2-2020 1200";
    public static final String INVALID_EVENT_NAME = "CS@2103T Quiz";
    public static final String INVALID_EVENT_DATE = "123-2-2020 1200";
    public static final EditContactDescriptor DESC_AMY;
    public static final EditContactDescriptor DESC_BOB;
    public static final EditModuleCommand.EditModuleDescriptor DESC_CS2030;
    public static final EditModuleCommand.EditModuleDescriptor DESC_CS2103T;

    // ================================== GradeTrackerTest ===================================== //
    public static final String VALID_ASSIGNMENT_NAME_1 = "Quiz 1";
    public static final String VALID_ASSIGNMENT_NAME_2 = "Oral Presentation 2";
    public static final String INVALID_ASSIGNMENT_NAME = "Quiz 2(@b)";
    public static final double VALID_ASSIGNMENT_PERCENTAGE_1 = 10;
    public static final double VALID_ASSIGNMENT_PERCENTAGE_2 = 20;
    public static final double INVALID_ASSIGNMENT_PERCENTAGE = -1;
    public static final double VALID_ASSIGNMENT_RESULT_1 = 70;
    public static final double VALID_ASSIGNMENT_RESULT_2 = 90;
    public static final double INVALID_ASSIGNMENT_RESULT = -1;
    public static final double VALID_GRADE_1 = 90;
    public static final double VALID_GRADE_2 = 85;
    public static final double INVALID_GRADE = -1;

    static {
        VALID_ZOOMLINKS_CS2030.put(new ModuleLesson(VALID_MODULE_LESSON_LECTURE), new ZoomLink(VALID_ZOOM_LINK_CS2030));
        VALID_ZOOMLINKS_CS2103T.put(new ModuleLesson(VALID_MODULE_LESSON_LECTURE),
                new ZoomLink(VALID_ZOOM_LINK_CS2103T));
        VALID_ZOOMLINKS_ES2660.put(new ModuleLesson(VALID_MODULE_LESSON_LECTURE), new ZoomLink(VALID_ZOOM_LINK_ES2660));
        DESC_CS2030 = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_CS2030)
                .withZoomLinks(VALID_ZOOMLINKS_CS2030).withTags(VALID_TAG_CORE_MODULE)
                .build();
        DESC_CS2103T = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLinks(VALID_ZOOMLINKS_CS2103T).withTags(VALID_TAG_CORE_MODULE)
                .build();
        DESC_AMY = new EditContactDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY).withTags(VALID_TAG_FRIEND)
                .build();
        DESC_BOB = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .build();
        DESC_LAB05 = new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB05)
                .withTags(VALID_TAG_LAB05).withPriority(VALID_PRIORITY_LAB05)
                .withDate(VALID_DATE_LAB05)
                .build();
        DESC_LAB07 = new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB07)
                .withTags(VALID_TAG_LAB07).withPriority(VALID_PRIORITY_LAB07)
                .withDate(VALID_DATE_LAB07)
                .build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        // ModuleList expectedModuleList = new ModuleList(actualModel.getModuleList());
        // List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        // assertEquals(expectedModuleList, actualModel.getAddressBook());
        // assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the module at the given {@code targetIndex} in the
     * {@code model}'s module list.
     */
    public static void showModuleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());
        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
        final String[] splitName = module.getName().fullName.split("\\s+");
        model.updateFilteredModuleList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredModuleList().size());
    }
    /**
     * Updates {@code model}'s archived module filtered list to show only the module at the given {@code targetIndex}
     * in the {@code model}'s archived module list.
     */
    public static void showArchivedModuleAtIndex(Model model, Index targetIndex) {
        model.displayArchivedModules();
        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());
        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
        final String[] splitName = module.getName().fullName.split("\\s+");
        model.updateFilteredModuleList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredModuleList().size());
    }

    /**Updates {@code model}'s filtered list to show only the contact at the given {@code targetIndex} in the
     * {@code model}'s contact list.
     */
    public static void showContactAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredContactList().size());
        Contact contact = model.getFilteredContactList().get(targetIndex.getZeroBased());
        final String[] splitName = contact.getName().toString().split("\\s+");
        model.updateFilteredContactList(new ContactNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredContactList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s todo list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTodoList().size());
        Task task = model.getFilteredTodoList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().get().getValue().split("\\s+");
        // name of the task has to match exactly
        model.updateFilteredTodoList(t -> t.getName().get().equals(task.getName().get()));
        assertEquals(1, model.getFilteredTodoList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s event list.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());
        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().getName().split("\\s+");
        model.updateFilteredEventList(new EventNameContainsKeyWordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredEventList().size());
    }

}

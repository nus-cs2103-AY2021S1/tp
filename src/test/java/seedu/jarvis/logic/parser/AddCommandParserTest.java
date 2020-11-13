package seedu.jarvis.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.logic.commands.CommandTestUtil.CONSULTATION_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.DATE_DESC_AMY_CONSULTATION;
import static seedu.jarvis.logic.commands.CommandTestUtil.DATE_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.DEADLINE_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.EVENT_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_EIGHT_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_EXPLICIT;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_FIVE_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_FOUR_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_IMPLICIT;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_ONE_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_SEVEN_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_SIX_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_THREE_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_DATE_TWO_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_TIME_FOUR_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_TIME_ONE_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_TIME_THREE_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.INVALID_TIME_TWO_WITH_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.MASTERY_CHECK_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.NAME_DESC_AMY_CONSULTATION;
import static seedu.jarvis.logic.commands.CommandTestUtil.TIME_DESC_AMY_CONSULTATION;
import static seedu.jarvis.logic.commands.CommandTestUtil.TIME_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.TODO_PREFIX;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_DATE_AMY_CONSULTATION;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_DATE_TASK;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_DATE_TIME;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_TIME_AMY_CONSULTATION;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_TIME_TASK;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_USERINPUT_DATE;
import static seedu.jarvis.logic.commands.CommandTestUtil.VALID_USERINPUT_TIME;
import static seedu.jarvis.logic.commands.add.AddCommand.MESSAGE_INVALID_DATETIME;
import static seedu.jarvis.logic.commands.add.AddCommand.MESSAGE_MISSING_INFO;
import static seedu.jarvis.logic.commands.add.AddCommand.MESSAGE_MISSING_INFO_CONSULTATION;
import static seedu.jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.jarvis.logic.parser.TaskCommandParser.DATE_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.add.AddCommand;
import seedu.jarvis.logic.commands.add.AddConsultationCommand;
import seedu.jarvis.logic.commands.add.AddMasteryCheckCommand;
import seedu.jarvis.logic.commands.add.AddTaskCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Todo;

public class AddCommandParserTest {
    private static final String PREAMBLE_NON_EMPTY = "asdfasdf";

    private AddCommandParser parser = new AddCommandParser();

    /**
     * Test for invalid empty string input
     */
    @Test
    public void parse_emptyStringInput_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_ADD_USAGE));
    }

    @Test
    public void parse_invalidCommandFormat_throwsParseException() {
        assertParseFailure(parser, "-2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_ADD_USAGE));
    }

    @Test
    public void parseInvalidUserInput_onlyFlagPresent_throwsParseException() {
        assertParseFailure(parser, "-t", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DESCRIPTION));
        assertParseFailure(parser, "-e", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_INFO_TASK));
        assertParseFailure(parser, "-d", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_INFO_TASK));
        assertParseFailure(parser, "-c", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        assertParseFailure(parser, "-mc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
    }

    @Test
    public void parseTask_invalidDateTime_throwsParseException() {
        //Explicit error: date time is outright wrong
        //Implicit error: date time is subtlely wrong, eg. leap year 29 days in February
        //Event Explicit and Implicit errors.
        String userInputEventExplicitError = EVENT_PREFIX + " " + VALID_DESCRIPTION + " " + DATE_PREFIX
                + INVALID_DATE_EXPLICIT + " " + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputEventExplicitError, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_INVALID_DATETIME));
        String userInputEventImplicitError = EVENT_PREFIX + " " + VALID_DESCRIPTION + " " + DATE_PREFIX
                + INVALID_DATE_IMPLICIT + " " + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputEventImplicitError, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_INVALID_DATETIME));

        //Deadline Explicit and Implicit errors.
        String userInputDeadlineExplicitError = DEADLINE_PREFIX + " " + VALID_DESCRIPTION + " " + DATE_PREFIX
                + INVALID_DATE_EXPLICIT + " " + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputDeadlineExplicitError, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_INVALID_DATETIME));
        String userInputDeadlineImplicitError = DEADLINE_PREFIX + " " + VALID_DESCRIPTION + " " + DATE_PREFIX
                + INVALID_DATE_IMPLICIT + " " + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputDeadlineImplicitError, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_INVALID_DATETIME));

        //When time flag and input is inputted after date flag and input.
        String userInputEventTimeDateError = EVENT_PREFIX + " " + VALID_DESCRIPTION + " " + TIME_PREFIX
                + VALID_TIME_TASK + " " + DATE_PREFIX + INVALID_DATE_EXPLICIT;
        assertParseFailure(parser, userInputEventTimeDateError, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_INVALID_DATETIME));

        String userInputDeadlineTimeDateError = DEADLINE_PREFIX + " " + VALID_DESCRIPTION + " " + TIME_PREFIX
                + VALID_TIME_TASK + " " + DATE_PREFIX + INVALID_DATE_EXPLICIT;
        assertParseFailure(parser, userInputDeadlineTimeDateError, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_INVALID_DATETIME));
    }

    @Test
    public void parseTimedTask_missingDescription_throwsParseException() {
        String userInputEventMissingDesc = EVENT_PREFIX + " " + DATE_PREFIX
                + VALID_TIME_TASK + " " + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputEventMissingDesc, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DESCRIPTION));

        String userInputDeadlineMissingDesc = DEADLINE_PREFIX + " " + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputDeadlineMissingDesc, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DESCRIPTION));
    }

    @Test
    public void parseTimedTask_missingDateOrTime_throwsParseException() {
        String userInputEventMissingDate = EVENT_PREFIX + " " + VALID_DESCRIPTION + " "
                + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputEventMissingDate, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DATE));
        String userInputDeadlineMissingDate = DEADLINE_PREFIX + " " + VALID_DESCRIPTION + " "
                + TIME_PREFIX + VALID_TIME_TASK;
        assertParseFailure(parser, userInputDeadlineMissingDate, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DATE));

        String userInputEventMissingTime = EVENT_PREFIX + " " + VALID_DESCRIPTION + " "
                + DATE_PREFIX + VALID_DATE_TASK;
        assertParseFailure(parser, userInputEventMissingTime, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DATE));
        String userInputDeadlineMissingTime = DEADLINE_PREFIX + " " + VALID_DESCRIPTION + " "
                + DATE_PREFIX + VALID_DATE_TASK;
        assertParseFailure(parser, userInputDeadlineMissingTime, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DATE));

        String userInputEventMissingDateTime = EVENT_PREFIX + " " + VALID_DESCRIPTION + " ";
        assertParseFailure(parser, userInputEventMissingDateTime, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DATE));
        String userInputDeadlineMissingDateTime = DEADLINE_PREFIX + " " + VALID_DESCRIPTION + " ";
        assertParseFailure(parser, userInputDeadlineMissingDateTime, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_DATE));
    }

    @Test
    public void parseTodo_allFieldsPresent_success() throws ParseException {
        Todo expectedTodo = new Todo(VALID_DESCRIPTION);
        AddTaskCommand newAddTaskCommand = new AddTaskCommand(expectedTodo);
        String userInput = TODO_PREFIX + " " + VALID_DESCRIPTION;
        AddCommand expectedAddCommand = parser.parse(userInput);
        AddTaskCommand typeCastExpectedAddCommand = (AddTaskCommand) expectedAddCommand;
        assertEquals(newAddTaskCommand.getTaskType(), typeCastExpectedAddCommand.getTaskType());

        Todo actualTask = (Todo) newAddTaskCommand.getTask();
        Todo expectedTask = (Todo) typeCastExpectedAddCommand.getTask();
        assertEquals(expectedTodo, actualTask);
        assertEquals(actualTask.getDescription(), VALID_DESCRIPTION);
        assertEquals(actualTask.getDescription(), expectedTask.getDescription());
    }

    @Test
    public void parseEvent_allFieldsPresent_success() throws ParseException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime validLocalDateTime = LocalDateTime.parse(VALID_DATE_TIME, dateTimeFormat);
        Event expectedEvent = new Event(VALID_DESCRIPTION, validLocalDateTime);
        AddTaskCommand newAddTaskCommand = new AddTaskCommand(expectedEvent);

        String userInput = EVENT_PREFIX + " " + VALID_DESCRIPTION + " "
                + VALID_USERINPUT_DATE + " " + VALID_USERINPUT_TIME;
        AddCommand expectedAddCommand = parser.parse(userInput);
        AddTaskCommand typeCastExpectedAddCommand = (AddTaskCommand) expectedAddCommand;

        assertEquals(newAddTaskCommand.getTaskType(), typeCastExpectedAddCommand.getTaskType());

        Event actualTask = (Event) newAddTaskCommand.getTask();
        Event expectedTask = (Event) typeCastExpectedAddCommand.getTask();
        assertEquals(expectedEvent, actualTask);
        assertEquals(actualTask.getDescription(), VALID_DESCRIPTION);
        assertEquals(actualTask.getLocalDateTime(), validLocalDateTime);
        assertEquals(actualTask.getDescription(), expectedTask.getDescription());
        assertEquals(actualTask.getLocalDateTime(), expectedTask.getLocalDateTime());
    }

    @Test
    public void parseDeadline_allFieldsPresent_success() throws ParseException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime validLocalDateTime = LocalDateTime.parse(VALID_DATE_TIME, dateTimeFormat);
        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION, validLocalDateTime);
        AddTaskCommand newAddTaskCommand = new AddTaskCommand(expectedDeadline);

        String userInput = DEADLINE_PREFIX + " " + VALID_DESCRIPTION + " "
                + VALID_USERINPUT_DATE + " " + VALID_USERINPUT_TIME;
        AddCommand expectedAddCommand = parser.parse(userInput);
        AddTaskCommand typeCastExpectedAddCommand = (AddTaskCommand) expectedAddCommand;

        assertEquals(newAddTaskCommand.getTaskType(), typeCastExpectedAddCommand.getTaskType());

        Deadline actualTask = (Deadline) newAddTaskCommand.getTask();
        Deadline expectedTask = (Deadline) typeCastExpectedAddCommand.getTask();
        assertEquals(expectedDeadline, actualTask);
        assertEquals(actualTask.getDescription(), VALID_DESCRIPTION);
        assertEquals(actualTask.getLocalDateTime(), validLocalDateTime);
        assertEquals(actualTask.getDescription(), expectedTask.getDescription());
        assertEquals(actualTask.getLocalDateTime(), expectedTask.getLocalDateTime());
    }

    @Test
    public void parseConsultationMasteryCheck_missingInfo_throwsParseException() {
        assertParseFailure(parser, "-c d/ t/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        assertParseFailure(parser, "-mc d/ t/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        assertParseFailure(parser, "-m d t", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_ADD_USAGE));
        assertParseFailure(parser, "-mc d t", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
    }

    @Test
    public void parseConsultation_allFieldsPresent_success() {
        LocalDateTime t = LocalDateTime.parse(VALID_DATE_AMY_CONSULTATION + "T" + VALID_TIME_AMY_CONSULTATION);
        Consultation expectedConsultation = new Consultation(VALID_NAME_AMY, t);
        String u = CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION + DATE_DESC_AMY_CONSULTATION
                + TIME_DESC_AMY_CONSULTATION;
        assertParseSuccess(parser, u, new AddConsultationCommand(expectedConsultation));
    }

    @Test
    public void parseMasteryCheck_allFieldsPresent_success() {
        MasteryCheck expectedMasteryCheck = new MasteryCheck(VALID_NAME_AMY,
                LocalDateTime.parse(VALID_DATE_AMY_CONSULTATION + "T" + VALID_TIME_AMY_CONSULTATION));
        assertParseSuccess(parser, MASTERY_CHECK_PREFIX + " " + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + TIME_DESC_AMY_CONSULTATION,
                new AddMasteryCheckCommand(expectedMasteryCheck));
    }

    @Test
    public void parseConsultation_compulsoryFieldMissing_failure() {
        String expectedConsultationMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultationCommand.MESSAGE_ADD_USAGE);

        String expectedConsultationMessage2 =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO);

        String expectedConsultationMessage3 =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME);

        // Consultation - missing name prefix
        assertParseFailure(parser, CONSULTATION_PREFIX + VALID_NAME_AMY + DATE_DESC_AMY_CONSULTATION
                + TIME_DESC_AMY_CONSULTATION, expectedConsultationMessage);

        // Consultation - missing date prefix
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                + VALID_DATE_AMY_CONSULTATION + TIME_DESC_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // Consultation - missing time prefix
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                + DATE_DESC_AMY_CONSULTATION + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // Consultation - all prefixes missing
        assertParseFailure(parser, CONSULTATION_PREFIX + VALID_NAME_AMY + VALID_DATE_AMY_CONSULTATION
                + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultationCommand.MESSAGE_ADD_USAGE));
    }

    @Test
    public void parseMasteryCheck_compulsoryFieldMissing_failure() {
        String expectedMasteryCheckMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMasteryCheckCommand.MESSAGE_ADD_USAGE);

        String expectedMasteryCheckMessage2 =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMasteryCheckCommand.MESSAGE_MISSING_INFO);

        String expectedMasteryCheckMessage3 =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME);

        // MasteryCheck - missing name prefix
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + VALID_NAME_AMY + DATE_DESC_AMY_CONSULTATION
                        + TIME_DESC_AMY_CONSULTATION, expectedMasteryCheckMessage);

        // MasteryCheck - missing date prefix
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                + VALID_DATE_AMY_CONSULTATION + TIME_DESC_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // MasteryCheck - missing time prefix
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                + DATE_DESC_AMY_CONSULTATION + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // MasteryCheck - all prefixes missing
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + VALID_NAME_AMY + VALID_DATE_AMY_CONSULTATION
                        + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultationCommand.MESSAGE_ADD_USAGE));
    }

    @Test
    public void parseConsultation_invalidValue_failure() {
        // invalid date 1
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_ONE_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 2
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_TWO_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 3
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_THREE_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 4
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_FOUR_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 5
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_FIVE_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 6
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_SIX_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 7
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_SEVEN_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 8
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_EIGHT_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid time 1
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_ONE_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // invalid time 2
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_TWO_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // invalid time 3
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_THREE_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // invalid time 4
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_FOUR_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // both invalid date and time
        assertParseFailure(parser, CONSULTATION_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_ONE_WITH_PREFIX + INVALID_TIME_ONE_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // non-empty preamble
        assertParseFailure(parser, CONSULTATION_PREFIX + PREAMBLE_NON_EMPTY + NAME_DESC_AMY_CONSULTATION
                        + VALID_DATE_AMY_CONSULTATION + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultationCommand.MESSAGE_ADD_USAGE));
    }

    @Test
    public void parseMasteryCheck_invalidValue_failure() {
        // invalid date 1
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_ONE_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 2
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_TWO_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 3
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_THREE_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 4
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_FOUR_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 5
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_FIVE_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 6
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_SIX_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 7
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_SEVEN_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid date 8
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_EIGHT_WITH_PREFIX + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INFO_CONSULTATION));

        // invalid time 1
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_ONE_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // invalid time 2
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_TWO_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // invalid time 3
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_THREE_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // invalid time 4
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + DATE_DESC_AMY_CONSULTATION + INVALID_TIME_FOUR_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // both invalid date and time
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + NAME_DESC_AMY_CONSULTATION
                        + INVALID_DATE_ONE_WITH_PREFIX + INVALID_TIME_ONE_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_DATETIME));

        // non-empty preamble
        assertParseFailure(parser, MASTERY_CHECK_PREFIX + PREAMBLE_NON_EMPTY
                        + NAME_DESC_AMY_CONSULTATION + VALID_DATE_AMY_CONSULTATION + VALID_TIME_AMY_CONSULTATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultationCommand.MESSAGE_ADD_USAGE));
    }
}

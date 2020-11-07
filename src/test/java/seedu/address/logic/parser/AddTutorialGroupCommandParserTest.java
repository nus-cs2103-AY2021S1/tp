package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_ID_B014;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_1300;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_1500;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_B014;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_DAY_MON;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class AddTutorialGroupCommandParserTest {

    private AddTutorialGroupCommandParser parser = new AddTutorialGroupCommandParser();

    @Test
    public void parse_validArgs_returnsAddTutorialGroupCommand() {

        TutorialGroup expectedTutorialGroup = new TutorialGroup(new TutorialGroupId(VALID_TUTORIAL_GROUP_B014),
            new DayOfWeek(VALID_TUTORIAL_GROUP_DAY_MON), new TimeOfDay(VALID_TUTORIAL_GROUP_1300),
            new TimeOfDay(VALID_TUTORIAL_GROUP_1500));
        assertParseSuccess(parser, TUTORIAL_GROUP_ID_B014, new AddTutorialGroupCommand(expectedTutorialGroup));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddTutorialGroupCommand.MESSAGE_USAGE));
    }
}

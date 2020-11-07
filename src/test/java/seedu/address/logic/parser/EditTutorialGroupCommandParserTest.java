package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_GROUP_1500;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_ID_B014;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_1300;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_1500;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_B014;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_DAY_MON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.tutorialgroup.TimeOfDay.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTutorialGroupCommand;
import seedu.address.logic.commands.EditTutorialGroupCommand.EditTutorialGroupDescriptor;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class EditTutorialGroupCommandParserTest {

    private EditTutorialGroupCommandParser parser = new EditTutorialGroupCommandParser();

    @Test
    public void parse_allValidArgs_returnsEditTutorialGroupCommand() {
        TutorialGroup expectedTutorialGroup = new TutorialGroup(new TutorialGroupId(VALID_TUTORIAL_GROUP_B014),
            new DayOfWeek(VALID_TUTORIAL_GROUP_DAY_MON), new TimeOfDay(VALID_TUTORIAL_GROUP_1300),
            new TimeOfDay(VALID_TUTORIAL_GROUP_1500));
        EditTutorialGroupDescriptor editTutorialGroupDescriptor =
            new EditTutorialGroupDescriptor(expectedTutorialGroup);
        EditTutorialGroupCommand editTutorialGroupCommand =
            new EditTutorialGroupCommand(INDEX_FIRST_PERSON, editTutorialGroupDescriptor);
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + " "
            + TUTORIAL_GROUP_ID_B014, editTutorialGroupCommand);
    }

    @Test
    public void parse_oneValidArgs_returnsEditTutorialGroupCommand() {

        EditTutorialGroupDescriptor editTutorialGroupDescriptor = new EditTutorialGroupDescriptor();
        editTutorialGroupDescriptor.setId(new TutorialGroupId("B014"));

        EditTutorialGroupCommand editTutorialGroupCommand =
            new EditTutorialGroupCommand(INDEX_FIRST_PERSON, editTutorialGroupDescriptor);
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + " "
            + PREFIX_TUTORIAL_GRP + VALID_TUTORIAL_GROUP_B014, editTutorialGroupCommand);
    }

    @Test
    public void parse_oneValidOneInvalidArgs_throwsParseException() {
        EditTutorialGroupDescriptor editTutorialGroupDescriptor = new EditTutorialGroupDescriptor();
        editTutorialGroupDescriptor.setId(new TutorialGroupId("B014"));

        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " "
            + PREFIX_TUTORIAL_GRP + VALID_TUTORIAL_GROUP_B014 + " "
            + PREFIX_TUTORIAL_GRP_START_TIME + INVALID_TUTORIAL_GROUP_1500, String.format(MESSAGE_CONSTRAINTS));
    }



    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditTutorialGroupCommand.MESSAGE_USAGE));
    }
}

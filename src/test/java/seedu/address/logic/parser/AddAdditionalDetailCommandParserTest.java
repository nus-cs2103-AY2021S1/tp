package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDITIONAL_DETAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAdditionalDetailCommand;
import seedu.address.model.student.admin.AdditionalDetail;


public class AddAdditionalDetailCommandParserTest {

    private AddAdditionalDetailCommandParser parser = new AddAdditionalDetailCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        String userInput = targetStudentIndex.getOneBased() + ADDITIONAL_DETAIL_DESC_AMY;
        AddAdditionalDetailCommand expectedCommand = new AddAdditionalDetailCommand(targetStudentIndex,
                new AdditionalDetail(VALID_ADDITIONAL_DETAILS_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAdditionalDetailCommand.MESSAGE_USAGE);
        Index targetStudentIndex = INDEX_SECOND_PERSON;

        // missing index and prefix
        assertParseFailure(parser, " ", expectedMessage);

        // valid index, missing prefix
        assertParseFailure(parser, targetStudentIndex + " ", expectedMessage);

        // missing index, valid prefix
        assertParseFailure(parser, ADDITIONAL_DETAIL_DESC_AMY, expectedMessage);
    }



}

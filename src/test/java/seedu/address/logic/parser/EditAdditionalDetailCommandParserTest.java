package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDITIONAL_DETAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_DETAILS_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAdditionalDetailCommand;
import seedu.address.model.student.admin.AdditionalDetail;

public class EditAdditionalDetailCommandParserTest {

    private EditAdditionalDetailCommandParser parser = new EditAdditionalDetailCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        Index targetDetailIndex = INDEX_SECOND_PERSON;
        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_DETAIL_INDEX, targetDetailIndex.getOneBased());
        String userInput = targetStudentIndex.getOneBased() + targetDetailIndexDesc + ADDITIONAL_DETAIL_DESC_AMY;

        EditAdditionalDetailCommand expectedCommand = new EditAdditionalDetailCommand(targetStudentIndex,
                targetDetailIndex, new AdditionalDetail(VALID_ADDITIONAL_DETAILS_AMY));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAdditionalDetailCommand.MESSAGE_USAGE);

        Index targetStudentIndex = INDEX_SECOND_PERSON;
        Index targetDetailIndex = INDEX_SECOND_PERSON;
        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_DETAIL_INDEX, targetDetailIndex.getOneBased());

        // missing 3 arguments
        assertParseFailure(parser, " ", expectedMessage);

        // missing 2 arguments
        assertParseFailure(parser, String.format("%s", targetStudentIndex.getOneBased()), expectedMessage);
        assertParseFailure(parser, targetDetailIndexDesc, expectedMessage);
        assertParseFailure(parser, ADDITIONAL_DETAIL_DESC_AMY, expectedMessage);

        // missing 1 argument
        assertParseFailure(parser, targetStudentIndex.getOneBased() + " " + ADDITIONAL_DETAIL_DESC_AMY,
                expectedMessage);
        assertParseFailure(parser, targetStudentIndex.getOneBased() + targetDetailIndexDesc,
                expectedMessage);
        assertParseFailure(parser, targetDetailIndexDesc + ADDITIONAL_DETAIL_DESC_AMY,
                expectedMessage);

        // wrong detail index
        assertParseFailure(parser, targetStudentIndex.getOneBased() + " " + PREFIX_DETAIL_INDEX + "0"
                + ADDITIONAL_DETAIL_DESC_AMY, expectedMessage);
    }

}

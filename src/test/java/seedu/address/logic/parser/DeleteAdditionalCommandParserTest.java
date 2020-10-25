package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAdditionalDetailCommand;

public class DeleteAdditionalCommandParserTest {

    private DeleteAdditionalDetailCommandParser parser = new DeleteAdditionalDetailCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetStudentIndex = INDEX_SECOND_PERSON;
        Index targetDetailIndex = INDEX_SECOND_PERSON;
        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_INDEX, targetDetailIndex.getOneBased());
        String userInput = targetStudentIndex.getOneBased() + targetDetailIndexDesc;
        DeleteAdditionalDetailCommand expectedCommand = new DeleteAdditionalDetailCommand(targetStudentIndex,
                targetDetailIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAdditionalDetailCommand.MESSAGE_USAGE);

        Index targetStudentIndex = INDEX_SECOND_PERSON;
        Index targetDetailIndex = INDEX_SECOND_PERSON;
        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_INDEX, targetDetailIndex.getOneBased());

        // missing 2 arguments
        assertParseFailure(parser, " ", expectedMessage);

        // missing 1 argument
        assertParseFailure(parser, String.format("%s", targetStudentIndex.getOneBased()), expectedMessage);
        assertParseFailure(parser, targetDetailIndexDesc, expectedMessage);

        // wrong detail index
        assertParseFailure(parser, targetStudentIndex.getOneBased() + " " + PREFIX_INDEX + "0",
                expectedMessage);
    }
}

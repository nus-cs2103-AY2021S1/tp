package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteExamCommand;

public class DeleteExamCommandParserTest {

    private DeleteExamCommandParser parser = new DeleteExamCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String targetExamIndexDesc = String.format(" %s%s", PREFIX_EXAM_INDEX, INDEX_SECOND_PERSON.getOneBased());
        String userInput = INDEX_SECOND_PERSON.getOneBased() + targetExamIndexDesc;
        DeleteExamCommand expectedCommand = new DeleteExamCommand(INDEX_SECOND_PERSON,
                INDEX_SECOND_PERSON);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingArguments_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteExamCommand.MESSAGE_USAGE);

        String targetDetailIndexDesc = String.format(" %s%s", PREFIX_EXAM_INDEX, INDEX_SECOND_PERSON.getOneBased());

        // missing 2 arguments
        assertParseFailure(parser, " ", expectedMessage);

        // missing 1 argument
        assertParseFailure(parser, String.format("%s", INDEX_SECOND_PERSON.getOneBased()), expectedMessage);
        assertParseFailure(parser, targetDetailIndexDesc, expectedMessage);

        // wrong detail index
        assertParseFailure(parser, INDEX_SECOND_PERSON.getOneBased() + " " + INDEX_SECOND_PERSON + "0",
                expectedMessage);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.*;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditProblemCommand;
import seedu.address.testutil.EditProblemDescriptorBuilder;

public class EditProblemCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProblemCommand.MESSAGE_USAGE);

    private EditProblemCommandParser parser = new EditProblemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "1", EditProblemCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 q/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_REPORT;
        String userInput = targetIndex.getOneBased() + VALID_SEVERITY_P1_DESC + VALID_DESCRIPTION_P1_DESC;

        EditProblemCommand.EditProblemDescriptor descriptor = new EditProblemDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_P1).withSeverity(VALID_SEVERITY_P1)
                .build();
        EditProblemCommand expectedCommand = new EditProblemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_REPORT;
        String userInput = targetIndex.getOneBased() + VALID_SEVERITY_P1_DESC;

        EditProblemCommand.EditProblemDescriptor descriptor = new EditProblemDescriptorBuilder()
                .withSeverity(VALID_SEVERITY_P1)
                .build();
        EditProblemCommand expectedCommand = new EditProblemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // severity
        Index targetIndex = INDEX_SECOND_REPORT;
        String userInput = targetIndex.getOneBased() + VALID_SEVERITY_P2_DESC;

        EditProblemCommand.EditProblemDescriptor descriptor = new EditProblemDescriptorBuilder()
                .withSeverity(VALID_SEVERITY_P2)
                .build();
        EditProblemCommand expectedCommand = new EditProblemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        targetIndex = INDEX_SECOND_REPORT;
        userInput = targetIndex.getOneBased() + VALID_DESCRIPTION_P2_DESC;

        descriptor = new EditProblemDescriptorBuilder()
                    .withDescription(VALID_DESCRIPTION_P2)
                .build();
        expectedCommand = new EditProblemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_SECOND_REPORT;
        String userInput = targetIndex.getOneBased() + VALID_SEVERITY_P1_DESC + VALID_SEVERITY_P2_DESC;

        EditProblemCommand.EditProblemDescriptor descriptor = new EditProblemDescriptorBuilder()
                .withSeverity(VALID_SEVERITY_P2)
                .build();
        EditProblemCommand expectedCommand = new EditProblemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}

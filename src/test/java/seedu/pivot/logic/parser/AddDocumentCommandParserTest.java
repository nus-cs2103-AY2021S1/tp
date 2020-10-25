package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.documentcommands.AddDocumentCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Document;
import seedu.pivot.model.investigationcase.Name;
import seedu.pivot.model.investigationcase.Reference;

public class AddDocumentCommandParserTest {

    public final String PREFIX_NAME = " " + CliSyntax.PREFIX_NAME.getPrefix();
    public final String VALID_NAME = "I am a valid name";
    public final String INVALID_NAME = "| am |nval|d name";
    public final String PREFIX_REFERENCE = " " + CliSyntax.PREFIX_REFERENCE.getPrefix();
    public final String VALID_REFERENCE = "test1.txt";
    public final String INVALID_REFERENCE = "invalidReference.myExt";

    private static Index index = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());

    private AddDocumentCommandParser parser = new AddDocumentCommandParser();

    @BeforeAll
    public static void setStateZero() {
        StateManager.setState(index);
    }

    @AfterAll
    public static void setStateNull() {
        StateManager.resetState();
    }

    @Test
    public void parse_allFieldsArePresent_success() {

        Document legalDocument = new Document(new Name(VALID_NAME), new Reference(VALID_REFERENCE));

        // normal input
        assertParseSuccess(parser, PREFIX_NAME + VALID_NAME + PREFIX_REFERENCE + VALID_REFERENCE,
                new AddDocumentCommand(index, legalDocument));
    }

    @Test
    public void parse_prefixesMissing_throwsParseException() {
        // missing name prefix
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_REFERENCE + VALID_REFERENCE));
        assertParseFailure(parser, PREFIX_REFERENCE + VALID_REFERENCE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddDocumentCommand.MESSAGE_USAGE));

        // missing reference prefix
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_NAME + VALID_NAME));
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddDocumentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArguments_throwsParseException() {
        // invalid name
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_NAME + INVALID_NAME + PREFIX_REFERENCE + VALID_REFERENCE));
        assertParseFailure(parser, PREFIX_NAME + INVALID_NAME + PREFIX_REFERENCE + VALID_REFERENCE, Name.MESSAGE_CONSTRAINTS);

        // invalid reference
        assertThrows(ParseException.class, () -> parser.parse(PREFIX_NAME + VALID_NAME + PREFIX_REFERENCE + INVALID_REFERENCE));
        assertParseFailure(parser, PREFIX_NAME + VALID_NAME + PREFIX_REFERENCE + INVALID_REFERENCE,
                Reference.MESSAGE_CONSTRAINTS);
    }

}

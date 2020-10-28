package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.INVALID_CASEPERSON_NAME;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_NAME_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_CASEPERSON_NAME_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.pivot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.suspectcommands.AddSuspectCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.testutil.CasePersonBuilder;

public class AddSuspectCommandParserTest {
    // Todo: move static fields to CommandTestUtil
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_CASEPERSON_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_CASEPERSON_NAME_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + INVALID_CASEPERSON_NAME;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;

    private static Index index = Index.fromZeroBased(INDEX_FIRST_PERSON.getZeroBased());

    private AddSuspectCommandParser parser = new AddSuspectCommandParser();

    @BeforeAll
    public static void setStateZero() {
        StateManager.setState(index);
    }

    @AfterAll
    public static void setStateNull() {
        StateManager.resetState();
    }

    //TODO: add all the fields in for suspect here
    @Test
    public void parse_allFieldsPresent_success() {
        Suspect expectedSuspect = new CasePersonBuilder().withName(VALID_CASEPERSON_NAME_BOB)
                .withGender(VALID_GENDER_BOB)
                .buildSuspect();

        // normal input
        assertParseSuccess(parser, NAME_DESC_BOB + GENDER_DESC_BOB,
                new AddSuspectCommand(index, expectedSuspect));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + GENDER_DESC_BOB,
                new AddSuspectCommand(index, expectedSuspect));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + GENDER_DESC_BOB,
                new AddSuspectCommand(index, expectedSuspect));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSuspectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, NAME_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + GENDER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + GENDER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSuspectCommand.MESSAGE_USAGE));
    }
}

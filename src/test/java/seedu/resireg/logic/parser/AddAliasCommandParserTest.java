package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.commands.CommandTestUtil.ALIAS_DESC_ROOMS_RO;
import static seedu.resireg.logic.commands.CommandTestUtil.COMMAND_DESC_ROOMS_RO;
import static seedu.resireg.logic.commands.CommandTestUtil.COMMAND_DESC_STUDENTS_STU;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_ALIAS_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.INVALID_COMMAND_DESC;
import static seedu.resireg.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.resireg.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ALIAS_ROOMS_RO;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_COMMAND_ROOMS_RO;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.resireg.testutil.TypicalCommandWordAliases.ROOMS_RO;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.AddAliasCommand;
import seedu.resireg.model.alias.Alias;
import seedu.resireg.model.alias.CommandWord;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.testutil.CommandWordAliasBuilder;

public class AddAliasCommandParserTest {
    private AddAliasCommandParser parser = new AddAliasCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CommandWordAlias expectedAlias = new CommandWordAliasBuilder(ROOMS_RO).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMMAND_DESC_ROOMS_RO
            + ALIAS_DESC_ROOMS_RO, new AddAliasCommand(expectedAlias));

        // multiple command words - last name accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMMAND_DESC_STUDENTS_STU + COMMAND_DESC_ROOMS_RO
            + ALIAS_DESC_ROOMS_RO, new AddAliasCommand(expectedAlias));

    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.HELP.getFullMessage());

        // missing command word prefix
        assertParseFailure(parser, VALID_COMMAND_ROOMS_RO + ALIAS_DESC_ROOMS_RO,
            expectedMessage);

        // missing alias prefix
        assertParseFailure(parser, COMMAND_DESC_ROOMS_RO + VALID_ALIAS_ROOMS_RO,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_COMMAND_ROOMS_RO + VALID_ALIAS_ROOMS_RO, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid command word
        assertParseFailure(parser, INVALID_COMMAND_DESC + ALIAS_DESC_ROOMS_RO, CommandWord.MESSAGE_CONSTRAINTS);

        // invalid alias
        assertParseFailure(parser, COMMAND_DESC_ROOMS_RO + INVALID_ALIAS_DESC, Alias.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COMMAND_DESC + INVALID_ALIAS_DESC, CommandWord.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COMMAND_DESC_ROOMS_RO + ALIAS_DESC_ROOMS_RO,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.HELP.getFullMessage()));
    }
}

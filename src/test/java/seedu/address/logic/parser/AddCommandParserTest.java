package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MOD_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULAR_CREDIT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MOD_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.MOD_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CORRECT_SEMESTER_OF_MOD_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULAR_CREDIT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MOD_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.module.Grade;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.ModuleBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(MOD_B)
                .withModularCredit(VALID_MODULAR_CREDIT)
                .withSemester(VALID_CORRECT_SEMESTER_OF_MOD_NAME_B.toString()).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MOD_NAME_DESC_B
                + GRADE_DESC_B + MODULAR_CREDIT_DESC, new AddCommand(expectedModule));

        // multiple names - last name accepted
        assertParseSuccess(parser, MOD_NAME_DESC_A + MOD_NAME_DESC_B
                + GRADE_DESC_B + MODULAR_CREDIT_DESC, new AddCommand(expectedModule));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, MOD_NAME_DESC_B + GRADE_DESC_A
                + GRADE_DESC_B + MODULAR_CREDIT_DESC, new AddCommand(expectedModule));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        //Module expectedModule = new ModuleBuilder(MOD_A)
                //.withModularCredit(VALID_MODULAR_CREDIT)
                //.withSemester(VALID_CORRECT_SEMESTER_OF_MOD_NAME_B.toString()).build();
        //assertParseSuccess(parser, MOD_NAME_DESC_A + GRADE_DESC_A + MODULAR_CREDIT_DESC,
            //new AddCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_MOD_NAME_B + GRADE_DESC_B,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_MOD_NAME_DESC + GRADE_DESC_B, ModuleName.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, MOD_NAME_DESC_B + INVALID_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_MOD_NAME_DESC + INVALID_GRADE_DESC,
                ModuleName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MOD_NAME_DESC_B
                        + GRADE_DESC_B,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

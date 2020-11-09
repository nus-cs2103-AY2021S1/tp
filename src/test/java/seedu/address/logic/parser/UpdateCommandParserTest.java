package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.GRADE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MOD_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MOD_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.NO_GRADE;
import static seedu.address.logic.commands.CommandTestUtil.SEMESTER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CORRECT_SEMESTER_OF_MOD_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_A;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.COM_ORG;
import static seedu.address.testutil.TypicalModules.SWE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.model.module.Grade;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.UpdateModNameDescriptorBuilder;

public class UpdateCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        String moduleName = COM_ORG.getModuleName().fullModName;
        assertParseFailure(parser, INVALID_MOD_NAME_DESC, ModuleName.MESSAGE_CONSTRAINTS); // invalid module name
        assertParseFailure(parser, " " + PREFIX_MOD_NAME + moduleName + INVALID_GRADE_DESC,
                Grade.MESSAGE_CONSTRAINTS); // invalid grade

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_MOD_NAME_DESC + INVALID_GRADE_DESC, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        ModuleName nameThirdModule = SWE.getModuleName();
        String userInput = PREFIX_MOD_NAME + nameThirdModule.fullModName
                + GRADE_DESC_A + MOD_NAME_DESC_A;
        userInput = " " + userInput.toLowerCase();
        UpdateCommand.UpdateModNameDescriptor descriptor = new UpdateModNameDescriptorBuilder()
                .withName(VALID_MOD_NAME_A)
                .withGrade(VALID_GRADE_A).build();
        UpdateCommand expectedCommand = new UpdateCommand(nameThirdModule, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        ModuleName nameThirdModule = SWE.getModuleName();
        String userInput = MOD_NAME_DESC_A;
        UpdateCommand.UpdateModNameDescriptor descriptor =
                new UpdateModNameDescriptorBuilder().withName(VALID_MOD_NAME_A).withGrade(NO_GRADE).build();
        UpdateCommand expectedCommand = new UpdateCommand(nameThirdModule, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // grade
        userInput = MOD_NAME_DESC_A + GRADE_DESC_A;
        descriptor = new UpdateModNameDescriptorBuilder().withName(VALID_MOD_NAME_A).withGrade(VALID_GRADE_A).build();
        expectedCommand = new UpdateCommand(nameThirdModule, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // semester
        userInput = MOD_NAME_DESC_A + SEMESTER_DESC;
        descriptor = new UpdateModNameDescriptorBuilder().withName(VALID_MOD_NAME_A)
                .withGrade(NO_GRADE).withSemester(VALID_CORRECT_SEMESTER_OF_MOD_NAME_B).build();
        expectedCommand = new UpdateCommand(nameThirdModule, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        ModuleName nameFirstModule = COM_ORG.getModuleName();
        String userInput = " " + PREFIX_MOD_NAME + nameFirstModule.fullModName + GRADE_DESC_A
                + GRADE_DESC_A
                + GRADE_DESC_B;

        UpdateCommand.UpdateModNameDescriptor descriptor = new UpdateModNameDescriptorBuilder()
                .withName(nameFirstModule.fullModName)
                .withGrade(VALID_GRADE_B)
                .build();
        UpdateCommand expectedCommand = new UpdateCommand(nameFirstModule, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        //Original first assert has only phone in userInput, now deleted - kunnan 5/10/2020
        // no other valid values specified
        ModuleName nameFirstModule = COM_ORG.getModuleName();
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput;
        UpdateCommand.UpdateModNameDescriptor descriptor;
        UpdateCommand expectedCommand;
        //assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = " " + PREFIX_MOD_NAME + nameFirstModule.fullModName + GRADE_DESC_B;
        descriptor = new UpdateModNameDescriptorBuilder()
                .withName(nameFirstModule.fullModName).withGrade(VALID_GRADE_B).build();
        expectedCommand = new UpdateCommand(nameFirstModule, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

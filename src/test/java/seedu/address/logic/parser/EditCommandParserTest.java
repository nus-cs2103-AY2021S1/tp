package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EDIT_USAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_CODE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAG;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2103T_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS3216_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CODE_DESC_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CORE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_NON_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDITS_CS3216;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NON_CORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS3216;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_SECOND_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_THIRD_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.ModuleInfoSearcherTest;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EDIT_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no module code to edit specified
        assertParseFailure(parser, CODE_DESC_CS2103T_WITH_PREFIX, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, CODE_DESC_CS2103T, MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // module code invalid format
        assertParseFailure(parser, "c2222" + CODE_DESC_CS2103T_WITH_PREFIX,
            String.format(MESSAGE_INVALID_MODULE_CODE, "C2222"));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "cs2100 some random string",
            String.format(MESSAGE_INVALID_MODULE_CODE, "CS2100 SOME RANDOM STRING"));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "cs2100 i/ string",
            String.format(MESSAGE_INVALID_MODULE_CODE, "CS2100 I/ STRING"));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "cs2100" + INVALID_CODE_DESC_WITH_PREFIX,
            String.format(MESSAGE_INVALID_MODULE_CODE, INVALID_CODE)); // invalid code
        assertParseFailure(parser, "cs2100" + INVALID_TAG_DESC,
            String.format(MESSAGE_INVALID_TAG, INVALID_TAG)); // invalid tag

        // valid code followed by invalid code. The test case for invalid code followed by valid code
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "cs2100" + CODE_DESC_CS2103T_WITH_PREFIX + INVALID_CODE_DESC_WITH_PREFIX,
            String.format(MESSAGE_INVALID_MODULE_CODE, INVALID_CODE));

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Module} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "cs2100" + TAG_DESC_CORE + TAG_DESC_NON_CORE + TAG_EMPTY,
            String.format(MESSAGE_INVALID_TAG, ""));
        assertParseFailure(parser, "cs2100" + TAG_DESC_CORE + TAG_EMPTY + TAG_DESC_NON_CORE,
            String.format(MESSAGE_INVALID_TAG, ""));
        assertParseFailure(parser, "cs2100" + TAG_EMPTY + TAG_DESC_CORE + TAG_DESC_NON_CORE,
            String.format(MESSAGE_INVALID_TAG, ""));

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "cs2100" + INVALID_CODE_DESC_WITH_PREFIX + INVALID_TAG_DESC,
            String.format(MESSAGE_INVALID_MODULE_CODE, INVALID_CODE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        ModuleCode targetCode = CODE_SECOND_MODULE;
        String userInput = targetCode + TAG_DESC_NON_CORE
                + CODE_DESC_CS2103T_WITH_PREFIX;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_CODE_CS2103T)
            .withModuleTitle(VALID_TITLE_CS2103T).withModularCredits(VALID_CREDITS_CS2103T)
            .withTags(VALID_TAG_NON_CORE).build();
        EditCommand expectedCommand = new EditCommand(targetCode, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyModuleCodeSpecified_success() {
        ModuleCode targetCode = CODE_FIRST_MODULE;
        String userInput = targetCode + CODE_DESC_CS2103T_WITH_PREFIX;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
            .withModuleCode(VALID_CODE_CS2103T).withModuleTitle(VALID_TITLE_CS2103T)
            .withModularCredits(VALID_CREDITS_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetCode, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onlyTagsSpecified_success() {
        // one tag
        ModuleCode targetCode = CODE_THIRD_MODULE;
        String userInput = targetCode + TAG_DESC_CORE;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CORE).build();
        EditCommand expectedCommand = new EditCommand(targetCode, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // two tags
        userInput = targetCode + TAG_DESC_CORE + TAG_DESC_NON_CORE;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE).build();
        expectedCommand = new EditCommand(targetCode, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        ModuleCode targetCode = CODE_FIRST_MODULE;
        String userInput = targetCode + CODE_DESC_CS2103T_WITH_PREFIX + TAG_DESC_CORE
            + TAG_DESC_CORE + CODE_DESC_CS3216_WITH_PREFIX + TAG_DESC_NON_CORE;

        EditModuleDescriptor descriptor =
            new EditModuleDescriptorBuilder().withModuleCode(VALID_CODE_CS3216)
                .withModuleTitle(VALID_TITLE_CS3216)
                .withModularCredits(VALID_CREDITS_CS3216)
                .withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetCode, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        ModuleCode targetCode = CODE_FIRST_MODULE;
        String userInput = targetCode + INVALID_CODE_DESC_WITH_PREFIX + CODE_DESC_CS2103T_WITH_PREFIX;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
            .withModuleCode(VALID_CODE_CS2103T).withModuleTitle(VALID_TITLE_CS2103T)
            .withModularCredits(VALID_CREDITS_CS2103T).build();
        EditCommand expectedCommand = new EditCommand(targetCode, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput =
            targetCode + INVALID_CODE_DESC_WITH_PREFIX + CODE_DESC_CS2103T_WITH_PREFIX
                + TAG_DESC_CORE + TAG_DESC_NON_CORE;
        descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_CODE_CS2103T)
            .withModuleTitle(VALID_TITLE_CS2103T).withModularCredits(VALID_CREDITS_CS2103T)
            .withTags(VALID_TAG_CORE, VALID_TAG_NON_CORE).build();
        expectedCommand = new EditCommand(targetCode, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        ModuleCode targetCode = CODE_THIRD_MODULE;
        String userInput = targetCode + TAG_EMPTY;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetCode, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_moduleSearchFailure_throwsCommandException() {
        parser = new EditCommandParser(new ModuleInfoSearcherTest.ModuleInfoSearcherStub());

        // still provide valid user input to enforce at-most-one-invalid-input heuristic
        String userInput = CODE_FIRST_MODULE + CODE_DESC_CS2103T_WITH_PREFIX;
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}

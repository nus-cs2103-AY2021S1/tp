package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.modulelistparsers.AddModuleParser;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleParserTest {
    private AddModuleParser parser = new AddModuleParser();
    private static final double MC_4 = 4.0;
    private final static String validInput = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).withMC(MC_4).build();
        AddModuleCommand test2 = new AddModuleCommand(expectedModule);
        assertParseSuccess(parser, validInput, new AddModuleCommand(expectedModule));

        // assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + EMAIL_DESC_BOB
        //         + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        // assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + EMAIL_DESC_BOB
        //         + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        // assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
        //         + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        // assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
        //         + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
//        Contact expectedPersonMultipleTags = new ContactBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
//                .build();
        // assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //         + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
//        Contact expectedPerson = new ContactBuilder(AMY).withTags().build();

        // assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
        //         new AddCommand(expectedPerson));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing name prefix
        // assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB,
        //         expectedMessage);

        // missing email prefix
        //assertParseFailure(parser, NAME_DESC_BOB + VALID_EMAIL_BOB,
        //        expectedMessage);

        // all prefixes missing
        // assertParseFailure(parser, VALID_NAME_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
        //         expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        //assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB
        //        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid email
        //assertParseFailure(parser, NAME_DESC_BOB + INVALID_EMAIL_DESC
        //        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        //assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB
        //        + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        //assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB,
        //        Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        //assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + EMAIL_DESC_BOB
        //        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
        //       String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

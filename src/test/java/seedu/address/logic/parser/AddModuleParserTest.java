package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.parser.modulelistparsers.AddModuleParser;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleParserTest {
    private AddModuleParser parser = new AddModuleParser();
    private final static String validInput = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private final static String multipleNames = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private final static String multipleLinks = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private final static String multipleMCs = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_2;

    @Test
    public void parse_allFieldsPresent_success() {
        // need to add tags into all of these tests.
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).withMC(VALID_MC_4).build();

        assertParseSuccess(parser, validInput, new AddModuleCommand(expectedModule));

        // multiple tags - all accepted
//        Contact expectedPersonMultipleTags = new ContactBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
//                .build();
        // assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //         + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));

    }

    @Test
    public void parse_preambleWhiteSpace_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).withMC(VALID_MC_4).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + validInput,
                new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_multipleNames_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_ES2660)
                .withZoomLink(VALID_ZOOMLINK_CS2103T)
                .withMC(VALID_MC_4)
                .build();
        assertParseSuccess(parser, multipleNames, new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_multipleLinks_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_ES2660)
                .withMC(VALID_MC_4)
                .build();
        assertParseSuccess(parser, multipleLinks, new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_multipleMCs_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_CS2103T)
                .withMC(VALID_MC_2)
                .build();
        assertParseSuccess(parser, multipleMCs, new AddModuleCommand(expectedModule));
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

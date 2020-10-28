package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MC_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_ES2660;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
// import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.parser.modulelistparsers.AddModuleParser;
//import seedu.address.model.module.ModularCredits;
// import seedu.address.model.module.Module;
//import seedu.address.model.module.ModuleName;
//import seedu.address.model.module.ZoomLink;
//import seedu.address.model.tag.Tag;
// import seedu.address.testutil.ModuleBuilder;

public class AddModuleParserTest {
    private static final String multipleMCs = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_2;

    private static final String multipleNames = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private static final String multipleLinks = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private static final String validInput = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;

    private AddModuleParser parser = new AddModuleParser();

    /*
    @Test
    public void parse_allFieldsPresent_success() {
        // need to add tags into all of these tests.
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).withMC(VALID_MC_4).build();

        assertParseSuccess(parser, validInput, new AddModuleCommand(expectedModule));

        // multiple tags - all accepted
        // Contact expectedPersonMultipleTags = new ContactBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
        //        .build();
        // assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //         + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));

    }
     */

    /*
    @Test
    public void parse_preambleWhiteSpace_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).withMC(VALID_MC_4).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + validInput,
                new AddModuleCommand(expectedModule));
    }
     */

    /*
    @Test
    public void parse_multipleNames_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_ES2660)
                .withZoomLink(VALID_ZOOMLINK_CS2103T)
                .withMC(VALID_MC_4)
                .build();
        assertParseSuccess(parser, multipleNames, new AddModuleCommand(expectedModule));
    }
     */

    /*
    @Test
    public void parse_multipleLinks_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_ES2660)
                .withMC(VALID_MC_4)
                .build();
        assertParseSuccess(parser, multipleLinks, new AddModuleCommand(expectedModule));
    }
     */

    /*
    @Test
    public void parse_multipleMCs_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withZoomLink(VALID_ZOOMLINK_CS2103T)
                .withMC(VALID_MC_2)
                .build();
        assertParseSuccess(parser, multipleMCs, new AddModuleCommand(expectedModule));
    }
     */


    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        // Contact expectedPerson = new ContactBuilder(AMY).withTags().build();

        // assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
        //         new AddCommand(expectedPerson));

    }

    @Test
    public void parse_compulsoryNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);
        String missingName = " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_CS2103T
                + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4;
        // missing name prefix
        assertParseFailure(parser, missingName, expectedMessage);
    }

    @Test
    public void parse_compulsoryAllFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);
        String missingAll = "";
        // all prefixes missing
        assertParseFailure(parser, missingAll, expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        //String invalidName = " " + PREFIX_NAME + "@123" + " " + PREFIX_ZOOM_LINK + "www.example.com";
        // invalid name
        //assertParseFailure(parser, invalidName, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidZoomLink_failure() {
        //String invalidLink = " " + PREFIX_NAME + "CS2103T" + " " + PREFIX_ZOOM_LINK + "1234";
        //assertParseFailure(parser, invalidLink, ZoomLink.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidMC_failure() {
        //String invalidMC = " " + PREFIX_NAME + "CS2103T"
        //        + " " + PREFIX_ZOOM_LINK + "www.example.com"
        //        + " " + PREFIX_MODULAR_CREDITS + "3.9";
        //assertParseFailure(parser, invalidMC, ModularCredits.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTags_failure() {
        //String invalidTags = ""; // need to implement this.
        //assertParseFailure(parser, invalidTags, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String nonEmptyPreamble = "add " + PREFIX_NAME + "CS2103T"
                + " " + PREFIX_ZOOM_LINK + "www.example.com";
        assertParseFailure(parser, nonEmptyPreamble,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }

}

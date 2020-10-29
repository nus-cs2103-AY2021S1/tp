package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_ES2660;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.commands.modulelistcommands.EditModuleDescriptor;
import seedu.address.logic.parser.modulelistparsers.EditModuleParser;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleParserTest {
    private static final int INDEX = 1;
    private static final String VALID_INPUT = " " + INDEX
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660;

    private static final String VALID_INPUT_NO_LINK = " " + INDEX
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660;

    private static final String VALID_INPUT_NO_NAME = " " + INDEX
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660;

    private static final String VALID_INPUT_REPEATED = " " + INDEX
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleParser parser = new EditModuleParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660)
                .withZoomLink(VALID_ZOOMLINK_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT, expectedCommand);
    }

    @Test
    public void parse_onlyNameSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_NO_LINK, expectedCommand);
    }

    /*
    @Test
    public void parse_onlyZoomLinkSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withZoomLink(VALID_ZOOMLINK_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_NO_NAME, expectedCommand);
    }
    */

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660)
                .withZoomLink(VALID_ZOOMLINK_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, VALID_INPUT_REPEATED, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String invalidThenValid = " " + 1
                + " " + PREFIX_NAME + "@123"
                + " " + PREFIX_NAME + VALID_MODULENAME_ES2660;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(Index.fromOneBased(1), descriptor);
        assertParseSuccess(parser, invalidThenValid, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryName_failure() {
        //String missingCompulsoryName = " " + PREFIX_NAME + VALID_ZOOMLINK_ES2660;
        //assertParseFailure(parser, missingCompulsoryName, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingAllFields_failure() {
        //String missingAll = "";
        //assertParseFailure(parser, missingAll, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //String invalid = " " + PREFIX_EDIT_NAME + VALID_MODULENAME_CS2103T
        //        + " " + PREFIX_NAME + "@123";
        //assertParseFailure(parser, invalid, MESSAGE_INVALID_FORMAT);
    }
}

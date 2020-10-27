package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.logic.commands.modulelistcommands.EditModuleCommand.EditModuleDescriptor;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.EditModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.modulelistparsers.EditModuleParser;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.tag.Tag;

public class EditModuleParserTest {

    private static final String VALID_INPUT = " " + PREFIX_EDIT_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660;

    private static final String VALID_INPUT_NO_LINK = " " + PREFIX_EDIT_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660;

    private static final String VALID_INPUT_NO_NAME = " " + PREFIX_EDIT_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660;

    private static final String VALID_INPUT_REPEATED = " " + PREFIX_EDIT_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_EDIT_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ZOOM_LINK + VALID_ZOOMLINK_ES2660;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleParser parser = new EditModuleParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660)
                .withZoomLink(VALID_ZOOMLINK_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(VALID_MODULENAME_CS2103T, descriptor);
        assertParseSuccess(parser, VALID_INPUT, expectedCommand);
    }

    @Test
    public void parse_onlyNameSpecified_success() {
       EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660).build();
       EditModuleCommand expectedCommand = new EditModuleCommand(VALID_MODULENAME_CS2103T, descriptor);
       assertParseSuccess(parser, VALID_INPUT_NO_LINK, expectedCommand);
    }

    @Test
    public void parse_onlyZoomLinkSpecified_success() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withZoomLink(VALID_ZOOMLINK_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(VALID_MODULENAME_CS2103T, descriptor);
        assertParseSuccess(parser, VALID_INPUT_NO_NAME, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULENAME_ES2660)
                .withZoomLink(VALID_ZOOMLINK_ES2660).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(VALID_MODULENAME_CS2103T, descriptor);
        assertParseSuccess(parser, VALID_INPUT_REPEATED, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        // String userInput = targetIndex.getOneBased();
        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder().build();
        //EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        // userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB;
        //descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_BOB)
        //.build();
        //expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryName_failure() {
        String missingCompulsory_Name = " " + PREFIX_NAME + VALID_ZOOMLINK_ES2660;
        assertParseFailure(parser, missingCompulsory_Name, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingAllFields_failure() {
        String missingAll = "";
        assertParseFailure(parser, missingAll, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        String invalid = " " + PREFIX_EDIT_NAME + VALID_MODULENAME_CS2103T
                + " " + PREFIX_NAME + "@123";
        assertParseFailure(parser, invalid, MESSAGE_INVALID_FORMAT);
    }
}

package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LESSON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ZOOM_LINK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_LESSON_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_LESSON_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.ZOOM_LINK_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.ZOOM_LINK_DESC_ES2660;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.AddZoomLinkCommand;
import seedu.address.logic.commands.modulelistcommands.ZoomDescriptor;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;
import seedu.address.testutil.ZoomDescriptorBuilder;

public class AddZoomLinkParserTest {

    private AddZoomLinkParser parser = new AddZoomLinkParser();
    private ZoomDescriptor zoomDescriptor = new ZoomDescriptorBuilder()
            .withZoomLink(VALID_ZOOM_LINK_CS2103T).withModuleLesson(VALID_MODULE_LESSON_LECTURE).build();

    @Test
    public void parse_compulsoryFieldsMissing_parseFailure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddZoomLinkCommand.MESSAGE_USAGE);

        // no index specified
        assertParseFailure(parser, ZOOM_LINK_DESC_CS2103T + MODULE_LESSON_DESC_LECTURE, expectedMessage);

        // missing zoom link prefix
        assertParseFailure(parser, INDEX_FIRST_MODULE
                + VALID_ZOOM_LINK_CS2030 + MODULE_LESSON_DESC_LECTURE, expectedMessage);

        // missing module lesson name prefix
        assertParseFailure(parser, INDEX_FIRST_MODULE
                + ZOOM_LINK_DESC_CS2103T + VALID_MODULE_LESSON_LECTURE, expectedMessage);

        // index and all prefixes missing
        assertParseFailure(parser, VALID_ZOOM_LINK_CS2030 + VALID_MODULE_LESSON_LECTURE, expectedMessage);
    }

    @Test
    public void parse_allFieldsPresent_parseSuccess() {

        // only one of each prefix argument specified
        assertParseSuccess(parser, INDEX_FIRST_MODULE.getOneBased() + ZOOM_LINK_DESC_CS2103T
                + MODULE_LESSON_DESC_LECTURE, new AddZoomLinkCommand(INDEX_FIRST_MODULE, zoomDescriptor));

        // multiple zoom links - last zoom link accepted
        assertParseSuccess(parser, INDEX_FIRST_MODULE.getOneBased() + ZOOM_LINK_DESC_ES2660
                + ZOOM_LINK_DESC_CS2103T + MODULE_LESSON_DESC_LECTURE,
                new AddZoomLinkCommand(INDEX_FIRST_MODULE, zoomDescriptor));

        // multiple module lessons - last module lesson accepted
        assertParseSuccess(parser, INDEX_FIRST_MODULE.getOneBased() + ZOOM_LINK_DESC_CS2103T
                + MODULE_LESSON_DESC_TUTORIAL + MODULE_LESSON_DESC_LECTURE,
                new AddZoomLinkCommand(INDEX_FIRST_MODULE, zoomDescriptor));
    }

    @Test
    public void parse_invalidArguments_parseFailure() {
        // invalid index
        assertParseFailure(parser, "-1" + ZOOM_LINK_DESC_CS2103T + MODULE_LESSON_DESC_LECTURE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddZoomLinkCommand.MESSAGE_USAGE));

        // invalid zoom link
        assertParseFailure(parser, INDEX_FIRST_MODULE.getOneBased() + INVALID_ZOOM_LINK_DESC
                + MODULE_LESSON_DESC_LECTURE, ZoomLink.MESSAGE_CONSTRAINTS);

        // invalid module lesson
        assertParseFailure(parser, INDEX_FIRST_MODULE.getOneBased() + ZOOM_LINK_DESC_CS2103T
                + INVALID_MODULE_LESSON_DESC, ModuleLesson.MESSAGE_CONSTRAINTS);

        // invalid zoom link and module lesson, only invalid zoom link reported
        assertParseFailure(parser, INDEX_FIRST_MODULE.getOneBased() + INVALID_ZOOM_LINK_DESC
                + INVALID_MODULE_LESSON_DESC, ZoomLink.MESSAGE_CONSTRAINTS);
    }
}

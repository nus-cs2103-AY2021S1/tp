package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LESSON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_LESSON_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_LESSON_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_LECTURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.DeleteZoomLinkCommand;
import seedu.address.model.module.ModuleLesson;

public class DeleteZoomLinkParserTest {

    private DeleteZoomLinkParser parser = new DeleteZoomLinkParser();

    @Test
    public void parse_allFieldsPresent_parseSuccess() {

        // only one module lesson argument specified
        assertParseSuccess(parser, INDEX_FIRST_MODULE.getOneBased() + MODULE_LESSON_DESC_LECTURE,
                new DeleteZoomLinkCommand(INDEX_FIRST_MODULE, new ModuleLesson(VALID_MODULE_LESSON_LECTURE)));

        // multiple module lessons - last module lesson accepted
        assertParseSuccess(parser, INDEX_FIRST_MODULE.getOneBased()
                + MODULE_LESSON_DESC_TUTORIAL + MODULE_LESSON_DESC_LECTURE,
                new DeleteZoomLinkCommand(INDEX_FIRST_MODULE, new ModuleLesson(VALID_MODULE_LESSON_LECTURE)));
    }

    @Test
    public void parse_compulsoryFieldsMissing_parseFailure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteZoomLinkCommand.MESSAGE_USAGE);

        // no index specified
        assertParseFailure(parser, MODULE_LESSON_DESC_LECTURE,
                expectedMessage);

        // missing module lesson name prefix
        assertParseFailure(parser, INDEX_FIRST_MODULE + VALID_MODULE_LESSON_LECTURE, expectedMessage);

        // missing index and module lesson name prefix
        assertParseFailure(parser, VALID_MODULE_LESSON_LECTURE, expectedMessage);
    }

    @Test
    public void parse_invalidArguments_parseFailure() {

        // invalid index
        assertParseFailure(parser, "-1" + MODULE_LESSON_DESC_LECTURE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteZoomLinkCommand.MESSAGE_USAGE));

        // invalid module lesson
        assertParseFailure(parser, INDEX_FIRST_MODULE.getOneBased() + INVALID_MODULE_LESSON_DESC,
                ModuleLesson.MESSAGE_CONSTRAINTS);

        // invalid index and module lesson, only invalid index reported
        assertParseFailure(parser, "-1" + INVALID_MODULE_LESSON_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteZoomLinkCommand.MESSAGE_USAGE));
    }

}

package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTags.CS2101;

import java.awt.Desktop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TagBuilder;

public class OpenCommandIntegrationTest {

    private static final Model model = new ModelManager();
    private static Tag validTag;
    private static Tag invalidTag;

    // Change to true to enable testing of opening files
    private final boolean isTestingOpenFile = false;

    @BeforeAll
    static void prepareModel() {
        validTag = new TagBuilder(CS2101).build();
        invalidTag = new TagBuilder().withFileAddress(".\\somewhereOverTheRainbow").build();
        model.addTag(validTag);
        model.addTag(invalidTag);
    }

    @Test
    public void execute_openValidTag_success() {
        if (Desktop.isDesktopSupported()) {
            assertCommandSuccess(new OpenCommand(validTag.getTagName()), model,
                    String.format(OpenCommand.MESSAGE_SUCCESS, validTag), model);
        }
    }

    @Test
    public void execute_openTagNameNotInModel_throwCommandException() {
        TagName tagNameNotInModel = new TagName("somethingNotInModel");
        OpenCommand openCommand = new OpenCommand(tagNameNotInModel);
        assertCommandFailure(openCommand, model,
                String.format(OpenCommand.MESSAGE_TAG_NOT_FOUND, tagNameNotInModel.tagName));
    }

    @Test
    public void execute_openInvalidTag_throwCommandException() {
        if (Desktop.isDesktopSupported()) {
            OpenCommand openCommand = new OpenCommand(invalidTag.getTagName());
            assertCommandFailure(openCommand, model,
                    String.format(OpenCommand.MESSAGE_FILE_NOT_FOUND, invalidTag.getFileAddress()));
        }
    }
}

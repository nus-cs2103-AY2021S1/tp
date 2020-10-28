package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAC_FILE_ADDRESS_TESTFILE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import java.awt.Desktop;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.label.Label;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TagBuilder;

class OpenCommandTest {

    private Model typicalModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagName tagName1 = new TagName(VALID_TAG_NAME_CS2103);
        TagName tagName2 = new TagName(VALID_TAG_NAME_CS2101);

        OpenCommand openCommand1 = new OpenCommand(tagName1);
        OpenCommand openCommand2 = new OpenCommand(tagName2);

        // same object -> returns true
        assertTrue(openCommand1.equals(openCommand1));

        // same values -> returns true
        OpenCommand openCommand1Copy = new OpenCommand(tagName1);
        assertTrue(openCommand1.equals(openCommand1Copy));

        // different types -> returns false
        assertFalse(openCommand1.equals(1));

        // null -> returns false
        assertFalse(openCommand1.equals(null));

        // different tagName -> returns false
        assertFalse(openCommand1.equals(openCommand2));
    }

    @Test
    public void execute_tagNotInModel_throwCommandException() {
        TagName tagName = new TagName("haHaImWrong");
        OpenCommand openCommand = new OpenCommand(tagName);
        assertThrows(CommandException.class, String.format(OpenCommand.MESSAGE_TAG_NOT_FOUND,
                tagName.tagName), () -> openCommand.execute(typicalModel));
    }

    @Test
    public void execute_tagNameInModel_success() {
        if (Desktop.isDesktopSupported()) {
            Tag correctTag = new TagBuilder().build();
            String os = System.getProperty("os.name").toLowerCase();
            if (!os.startsWith("windows")) {
                correctTag = new TagBuilder()
                        .withFileAddress(VALID_MAC_FILE_ADDRESS_TESTFILE).build();
            }
            OpenCommand openCommand = new OpenCommand(correctTag.getTagName());
            Model modelStubWithTag = new ModelStubWithTag(new TagBuilder().build());
            modelStubWithTag.addTag(correctTag);

            String expectedMessage = String.format(OpenCommand.MESSAGE_SUCCESS, correctTag);

            assertCommandSuccess(openCommand, modelStubWithTag, expectedMessage, modelStubWithTag);
        }
    }

    @Test
    public void execute_tagWithLabelInModel_success() {
        if (Desktop.isDesktopSupported()) {
            Tag correctTag = new TagBuilder().build();
            Tag correctTag2 = new TagBuilder().withTagName("anotherTag").build();
            OpenCommand openCommand = new OpenCommand(new Label(VALID_LABEL));
            Model modelStub = new ModelStubWithTagAndTaglist();
            modelStub.addTag(correctTag);
            modelStub.addTag(correctTag2);

            String expectedMessage = String.format(OpenCommand.MESSAGE_SUCCESS, correctTag)
                    + "\n" + String.format(OpenCommand.MESSAGE_SUCCESS, correctTag2);

            assertCommandSuccess(openCommand, modelStub, expectedMessage, modelStub);
        }
    }

    @Test
    public void execute_tagNameInModelFileNotFound_throwCommandException() {
        if (Desktop.isDesktopSupported()) {
            Tag correctTag = new TagBuilder().withTagName("test")
                    .withFileAddress(".\\src\\test\\java\\seedu\\address\\testutil\\testFileNotHere.bat").build();
            OpenCommand openCommand = new OpenCommand(correctTag.getTagName());
            Model modelStubWithTag = new ModelStubWithTag(new TagBuilder().build());
            modelStubWithTag.addTag(correctTag);

            assertThrows(CommandException.class, String.format(OpenCommand.MESSAGE_ERROR, correctTag,
                    String.format(OpenCommand.MESSAGE_FILE_NOT_FOUND,
                    correctTag.getFileAddress().value)), () -> openCommand.execute(modelStubWithTag));
        }
    }

}

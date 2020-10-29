package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;

public class TagCommandTest {

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagCommand(null));
    }

    @Test
    void execute_absoluteFilePath_tagSuccess() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();

        // Get absolute filepath
        Path validPath = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        Tag tagValidAddress = new TagBuilder().withFileAddress(validPath.toString()).build();

        CommandResult commandResult = new TagCommand(tagValidAddress).execute(modelStub);

        assertEquals(String.format(TagCommand.MESSAGE_SUCCESS, tagValidAddress), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(tagValidAddress), modelStub.tagsAdded);
    }

    @Test
    void execute_invalidFilePath_throwsCommandException() {
        Tag validTag = new TagBuilder().withTagName("valid tag name").build();
        Path validPath = FileSystems.getDefault().getPath(new String()).toAbsolutePath();
        String path = validPath.toString() + "somewhereOverTheRainbow";

        Tag tagInvalidAddress = new TagBuilder().withFileAddress(path).build();

        TagCommand tagCommand = new TagCommand(tagInvalidAddress);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class,
                String.format(TagCommand.MESSAGE_FILE_NOT_FOUND, path), () -> tagCommand.execute(modelStub));
    }

    @Test
    public void execute_tagAddressFileNotFound_throwsCommandException() {
        Tag tagInvalidAddress = new TagBuilder().withFileAddress("./somewhereOverTheRainbow").build();
        Tag validTag = new TagBuilder().build();
        TagCommand tagCommand = new TagCommand(tagInvalidAddress);
        ModelStub modelStub = new ModelStubWithTag(validTag);
        assertThrows(CommandException.class, () -> tagCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateTagName_throwsCommandException() {
        Tag validTag = new TagBuilder().build();
        TagCommand tagCommand = new TagCommand(validTag);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class, TagCommand.MESSAGE_DUPLICATE_TAG, () -> tagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag t1 = new TagBuilder().withTagName(VALID_TAG_NAME_CS2101).build();
        Tag t2 = new TagBuilder().withTagName(VALID_TAG_NAME_CS2103).build();
        TagCommand tagT1Command = new TagCommand(t1);
        TagCommand tagT2Command = new TagCommand(t2);

        // same object -> returns true
        assertTrue(tagT1Command.equals(tagT1Command));

        // same values -> returns true
        TagCommand tagT1CommandCopy = new TagCommand(t1);
        assertTrue(tagT1Command.equals(tagT1CommandCopy));

        // different types -> returns false
        assertFalse(tagT1Command.equals(1));

        // null -> returns false
        assertFalse(tagT1Command.equals(null));
        // different person -> returns false
        assertFalse(tagT1Command.equals(tagT2Command));
    }
}

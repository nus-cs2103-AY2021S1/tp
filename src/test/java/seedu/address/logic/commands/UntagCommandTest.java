package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TagBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code UntagCommand}.
 */
public class UntagCommandTest {
    private ModelStub modelStub;

    @BeforeEach
    public void setUp() {
        Tag validTag = new TagBuilder().build();
        ArrayList<Tag> tagList = new ArrayList<>();
        tagList.add(validTag);

        modelStub = new ModelStubDeleteTag(tagList);
    }

    @Test
    public void execute_validTagName_success() {
        Tag tagToDelete = new TagBuilder().build();
        UntagCommand untagCommand = new UntagCommand(tagToDelete.getTagName());

        String expectedMessage = String.format(UntagCommand.MESSAGE_UNTAG_TAG_SUCCESS, tagToDelete.getTagName());

        ModelStub expectedModel = new ModelStubDeleteTag(new ArrayList<Tag>());
        expectedModel.commitAddressBook();

        assertCommandSuccess(untagCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagNotInModel_throwsCommandException() {
        TagName nonExistTagName = new TagName("jalksdjlfkjsdlakjflsjlj");
        UntagCommand untagCommand = new UntagCommand(nonExistTagName);

        assertThrows(CommandException.class, String.format(UntagCommand.MESSAGE_TAG_NOT_FOUND,
                nonExistTagName), () -> untagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        TagName validTag1 = new TagName(VALID_TAG_NAME_CS2101);
        TagName validTag2 = new TagName(VALID_TAG_NAME_CS2103);
        UntagCommand deleteFirstCommand = new UntagCommand(validTag1);
        UntagCommand deleteSecondCommand = new UntagCommand(validTag2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        UntagCommand deleteFirstCommandCopy = new UntagCommand(new TagName(VALID_TAG_NAME_CS2101));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(new TagName(VALID_TAG_NAME_CS2101)));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.testutil.TagBuilder;

public class RetagCommandTest {

    private ModelStubWithTagAndTaglist modelStub;

    @BeforeEach
    public void setUp() {
        Tag validTag = new TagBuilder().build();

        modelStub = new ModelStubWithTagAndTaglist();
        modelStub.addTag(validTag);
    }

    @Test
    public void execute_duplicateNewTagName_retagSuccess() {
        TagName oldTagName = new TagBuilder().build().getTagName();
        TagName newTagName = new TagBuilder().build().getTagName();

        Tag newTag = new TagBuilder().withTagName(newTagName.toString()).build();

        ModelStubWithTagAndTaglist expectedModelStub = new ModelStubWithTagAndTaglist();
        expectedModelStub.addTag(newTag);
        expectedModelStub.commitAddressBook();

        RetagCommand retagCommand = new RetagCommand(oldTagName, newTagName);

        assertCommandSuccess(retagCommand, modelStub,
                String.format(RetagCommand.MESSAGE_RETAG_TAG_SUCCESS, oldTagName, newTagName), expectedModelStub);
    }

    @Test
    public void execute_newTagNameInTagList_throwCommandException() {
        // Retag a tag with a Tag name that is already in model
        Tag duplicateTag = new TagBuilder().withTagName("CS1231").build();
        Tag tagToRetag = new TagBuilder().build();
        TagName oldTagName = tagToRetag.getTagName();
        TagName newTagName = new TagName("CS1231");

        ModelStubWithTagAndTaglist expectedModelStub = new ModelStubWithTagAndTaglist();
        expectedModelStub.addTag(tagToRetag);
        expectedModelStub.commitAddressBook();
        expectedModelStub.addTag(duplicateTag);
        expectedModelStub.commitAddressBook();

        RetagCommand retagCommand = new RetagCommand(oldTagName, newTagName);

        assertThrows(CommandException.class,
                RetagCommand.MESSAGE_DUPLICATE_TAG, () -> retagCommand.execute(expectedModelStub));
    }

    @Test
    public void execute_oldTagNotInModel_throwCommandException() {
        TagName oldTagName = new TagName("noExist");
        TagName newTagName = new TagName("noMatter");

        RetagCommand retagCommand = new RetagCommand(oldTagName, newTagName);

        assertThrows(CommandException.class, String.format(RetagCommand.MESSAGE_OLD_TAG_NOT_FOUND,
                oldTagName.tagName), () -> retagCommand.execute(modelStub));
    }

    @Test
    public void execute_validInput_retagSuccess() {
        Tag newTag = new TagBuilder().withTagName("new").build();

        TagName oldTagName = new TagBuilder().build().getTagName();
        TagName newTagName = newTag.getTagName();

        RetagCommand retagCommand = new RetagCommand(oldTagName, newTagName);

        ModelStubWithTagAndTaglist expectedModelStub = new ModelStubWithTagAndTaglist();
        expectedModelStub.addTag(newTag);
        expectedModelStub.commitAddressBook();

        assertCommandSuccess(retagCommand, modelStub,
                String.format(RetagCommand.MESSAGE_RETAG_TAG_SUCCESS, oldTagName, newTagName), expectedModelStub);
    }

    @Test
    public void equals() {
        TagName oldTagName = new TagName("oldTag");
        TagName newTagName = new TagName("newTag");

        RetagCommand retagCommand1 = new RetagCommand(oldTagName, newTagName);
        RetagCommand retagCommand2 = new RetagCommand(newTagName, oldTagName);
        RetagCommand retagCommand3 = new RetagCommand(oldTagName, oldTagName);

        // same object -> returns true
        assertTrue(retagCommand1.equals(retagCommand1));

        // same values -> returns true
        RetagCommand retagCommand1Copy = new RetagCommand(oldTagName, newTagName);
        assertTrue(retagCommand1.equals(retagCommand1Copy));

        // different types -> returns false
        assertFalse(retagCommand1.equals(1));

        // null -> returns false
        assertFalse(retagCommand1.equals(null));

        // different tagName -> returns false
        assertFalse(retagCommand1.equals(retagCommand2));
        assertFalse(retagCommand1.equals(retagCommand3));
        assertFalse(retagCommand2.equals(retagCommand3));

    }
}

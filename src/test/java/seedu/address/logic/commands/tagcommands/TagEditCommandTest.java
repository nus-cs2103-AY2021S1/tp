package seedu.address.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HANDBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_HANDBALL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTagAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TAG;
import static seedu.address.testutil.TypicalProjact.getTypicalProjact;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.tagcommands.TagEditCommand.EditTagDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Projact;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditTagDescriptorBuilder;
import seedu.address.testutil.TagBuilder;

public class TagEditCommandTest {
    private Model model = new ModelManager(getTypicalProjact(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Tag tagToEdit = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        Tag editedTag = new TagBuilder(tagToEdit).withTagName(VALID_TAG_NAME_HANDBALL).build();

        EditTagDescriptor descriptor = new EditTagDescriptorBuilder().withTagName(VALID_TAG_NAME_HANDBALL).build();
        TagEditCommand tagEditCommand = new TagEditCommand(INDEX_FIRST_TAG, descriptor);

        String expectedMessage = String.format(TagEditCommand.MESSAGE_EDIT_TAG_SUCCESS, tagToEdit, editedTag);

        Model expectedModel = new ModelManager(new Projact(model.getProjact()), new UserPrefs());
        expectedModel.setTag(tagToEdit, editedTag);
        assertCommandSuccess(tagEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        TagEditCommand tagEditCommand = new TagEditCommand(INDEX_FIRST_TAG, new EditTagDescriptor());
        Tag editedTag = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());

        String expectedMessage = String.format(TagEditCommand.MESSAGE_EDIT_TAG_SUCCESS, editedTag, editedTag);

        Model expectedModel = new ModelManager(new Projact(model.getProjact()), new UserPrefs());

        assertCommandSuccess(tagEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTagUnfilteredList_failure() {
        Tag firstTag = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder(firstTag).build();
        TagEditCommand tagEditCommand = new TagEditCommand(INDEX_SECOND_TAG, descriptor);

        assertCommandFailure(tagEditCommand, model, TagEditCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_duplicateTagFilteredList_failure() {
        showTagAtIndex(model, INDEX_FIRST_TAG);

        // edit tag in filtered list into a duplicate in Projact
        Tag tagInList = model.getProjact().getTagList().get(INDEX_SECOND_TAG.getZeroBased());
        TagEditCommand tagEditCommand = new TagEditCommand(INDEX_FIRST_TAG,
                new EditTagDescriptorBuilder(tagInList).build());

        assertCommandFailure(tagEditCommand, model, TagEditCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_invalidTagIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTagList().size() + 1);
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder().withTagName(VALID_TAG_NAME_HANDBALL).build();
        TagEditCommand tagEditCommand = new TagEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(tagEditCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showTagAtIndex(model, INDEX_FIRST_TAG);

        Tag tagInFilteredList = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        Tag editedTag = new TagBuilder(tagInFilteredList).withTagName(VALID_TAG_NAME_HANDBALL).build();
        TagEditCommand tagEditCommand = new TagEditCommand(INDEX_FIRST_TAG,
                new EditTagDescriptorBuilder().withTagName(VALID_TAG_NAME_HANDBALL).build());

        String expectedMessage = String.format(TagEditCommand.MESSAGE_EDIT_TAG_SUCCESS, tagInFilteredList, editedTag);

        Model expectedModel = new ModelManager(new Projact(model.getProjact()), new UserPrefs());
        expectedModel.setTag(model.getFilteredTagList().get(0), editedTag);

        assertCommandSuccess(tagEditCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of Projact
     */
    @Test
    public void execute_invalidTagIndexFilteredList_failure() {
        showTagAtIndex(model, INDEX_FIRST_TAG);
        Index outOfBoundIndex = INDEX_SECOND_TAG;
        // ensures that outOfBoundIndex is still in bounds of Projact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjact().getTagList().size());

        TagEditCommand tagEditCommand = new TagEditCommand(outOfBoundIndex,
                new EditTagDescriptorBuilder().withTagName(VALID_TAG_NAME_HANDBALL).build());

        assertCommandFailure(tagEditCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final TagEditCommand standardCommand = new TagEditCommand(INDEX_FIRST_TAG, TAG_DESC_COLLEAGUE);

        // same values -> returns true
        EditTagDescriptor copyDescriptor = new EditTagDescriptor(TAG_DESC_COLLEAGUE);
        TagEditCommand commandWithSameValues = new TagEditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagEditCommand(INDEX_SECOND_TAG, TAG_DESC_COLLEAGUE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new TagEditCommand(INDEX_FIRST_TAG, TAG_DESC_HANDBALL)));
    }
}

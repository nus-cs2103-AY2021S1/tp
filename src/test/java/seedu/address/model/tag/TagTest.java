package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.COLLEAGUE;
import static seedu.address.testutil.TypicalTags.GROUPMATE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TagBuilder;

public class TagTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Tag tag = new TagBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> tag.getPersons().remove(0));
    }

    @Test
    void isSameTag() {
        // same object -> returns true
        assertTrue(GROUPMATE.isSameTag(GROUPMATE));

        // null -> returns false
        assertFalse(GROUPMATE.isSameTag(null));

        // different name -> returns false
        Tag editedGroupmate = new TagBuilder(GROUPMATE).withName(VALID_TAG_HUSBAND).build();
        assertFalse(GROUPMATE.isSameTag(editedGroupmate));

        // same name, different attributes -> returns true
        editedGroupmate = new TagBuilder(GROUPMATE).withPersons(VALID_NAME_AMY).build();
        assertTrue(GROUPMATE.isSameTag(editedGroupmate));
    }

    @Test
    void testEquals() {
        // same values -> returns true
        Tag groupmateCopy = new TagBuilder(GROUPMATE).build();
        assertTrue(GROUPMATE.equals(groupmateCopy));

        // same object -> returns true
        assertTrue(GROUPMATE.equals(GROUPMATE));

        // null -> returns false
        assertFalse(GROUPMATE.equals(null));

        // different type -> returns false
        assertFalse(GROUPMATE.equals(5));

        // different tag -> returns false
        assertFalse(GROUPMATE.equals(COLLEAGUE));

        // different name -> returns false
        Tag editedGroupmate = new TagBuilder(GROUPMATE).withName(VALID_TAG_HUSBAND).build();
        assertFalse(GROUPMATE.equals(editedGroupmate));

        // different persons -> returns false
        editedGroupmate = new TagBuilder(GROUPMATE).withPersons(VALID_NAME_AMY).build();
        assertFalse(GROUPMATE.equals(editedGroupmate));
    }
}

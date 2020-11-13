package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_ADDRESS_CS2103;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.CS2101;
import static seedu.address.testutil.TypicalTags.CS2103;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.TagBuilder;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(CS2103));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(CS2103);
        assertTrue(uniqueTagList.contains(CS2103));
    }

    @Test
    public void contains_tagWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTagList.add(CS2103);
        Tag editedCS2103 = new TagBuilder(CS2103).build();
        assertTrue(uniqueTagList.contains(editedCS2103));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(CS2103);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(CS2103));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(null, CS2103));
    }

    @Test
    public void setTag_nullEditedTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(CS2103, null));
    }

    @Test
    public void setTag_targetTagNotInList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.setTag(CS2103, CS2103));
    }

    @Test
    public void setTag_editedTagIsSameTag_success() {
        uniqueTagList.add(CS2103);
        uniqueTagList.setTag(CS2103, CS2103);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CS2103);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasSameIdentity_success() {
        uniqueTagList.add(CS2103);
        Tag editedCS2103 = new TagBuilder(CS2103).withFileAddress(VALID_FILE_ADDRESS_CS2103).build();
        uniqueTagList.setTag(CS2103, editedCS2103);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(editedCS2103);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasDifferentIdentity_success() {
        uniqueTagList.add(CS2103);
        uniqueTagList.setTag(CS2103, CS2101);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CS2101);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasNonUniqueIdentity_throwsDuplicateTagException() {
        uniqueTagList.add(CS2103);
        uniqueTagList.add(CS2101);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTag(CS2103, CS2101));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(CS2103));
    }

    @Test
    public void remove_existingTag_removesTag() {
        uniqueTagList.add(CS2103);
        uniqueTagList.remove(CS2103);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTag_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(CS2103);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CS2101);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(CS2103);
        List<Tag> tagList = Collections.singletonList(CS2101);
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CS2101);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(CS2103, CS2103);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTagList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator_tagInObservableList_success() {
        Tag tag = new TagBuilder().withTagName("CS2103T").build();
        uniqueTagList.add(tag);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(tag);
        assertFalse(uniqueTagList.iterator().equals(expectedUniqueTagList.iterator()));
    }
}

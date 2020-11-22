package seedu.forgetfulnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.DESC_FORGETFULNESS;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.DESC_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_DIFFICULTY_TAG_HARD;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_ENGLISH_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GENDER_TAG_F;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_GERMAN_PHRASE_TABLE;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.EditCommand.EditFlashCardDescriptor;
import seedu.forgetfulnus.testutil.EditFlashCardDescriptorBuilder;

public class EditFlashCardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFlashCardDescriptor descriptorWithSameValues = new EditFlashCardDescriptor(DESC_FORGETFULNESS);
        assertTrue(DESC_FORGETFULNESS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FORGETFULNESS.equals(DESC_FORGETFULNESS));

        // null -> returns false
        assertFalse(DESC_FORGETFULNESS.equals(null));

        // different types -> returns false
        assertFalse(DESC_FORGETFULNESS.equals(5));

        // different values -> returns false
        assertFalse(DESC_FORGETFULNESS.equals(DESC_TABLE));

        // different German phrase -> returns false
        EditFlashCardDescriptor editedForgetfulnessPhrase = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS)
                .withGermanPhrase(VALID_GERMAN_PHRASE_TABLE).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedForgetfulnessPhrase));

        // different English phrase -> returns false
        editedForgetfulnessPhrase = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS)
                .withEnglishPhrase(VALID_ENGLISH_PHRASE_TABLE).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedForgetfulnessPhrase));

        // different difficulty tag -> returns false
        editedForgetfulnessPhrase = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS)
                .withDifficultyTag(VALID_DIFFICULTY_TAG_HARD).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedForgetfulnessPhrase));

        // different gender tag -> returns false
        editedForgetfulnessPhrase = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS)
                .withGenderTag(VALID_GENDER_TAG_F).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedForgetfulnessPhrase));

        // different tags -> returns false
        editedForgetfulnessPhrase = new EditFlashCardDescriptorBuilder(DESC_FORGETFULNESS)
                .withTags(VALID_TAG_CHAPTER_ONE).build();
        assertFalse(DESC_FORGETFULNESS.equals(editedForgetfulnessPhrase));
    }
}

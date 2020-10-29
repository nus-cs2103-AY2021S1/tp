package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;
import static seedu.address.testutil.TypicalClients.ALICE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;

class TagNoteMapTest {

    private static final Note TAGGED_NOTE = new Note("jurong hill was a nice place");
    private static final Tag TEST_TAG = new Tag("tagName");
    private TagNoteMap tagNoteMap;
    private Client client;
    private Set<Tag> tags;

    @BeforeEach
    public void setUp() {
        tagNoteMap = new TagNoteMap();
        client = new ClientBuilder(ALICE).build();
        tags = new HashSet<>();
        tags.add(TEST_TAG);
    }

    @Test
    public void initTagNoteMapFromClients_addClientWithTaggedNotes_doesNotThrowException() {
        TAGGED_NOTE.setTags(tags);
        client.addClientNote(TAGGED_NOTE);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        assertDoesNotThrow(() -> tagNoteMap.initTagNoteMapFromClients(clients));
    }

    @Test
    public void initTagNoteMapFromCountryNotes_validSetOfTaggedCountryNotes_doesNotThrowException() {
        Set<Note> inputSet = new HashSet<>();
        Tag tag = new Tag("tagName");
        Note countryNote1 = new Note("this country note will be tagged");
        countryNote1.setTags(tags);
        inputSet.add(countryNote1);
        assertDoesNotThrow(() -> this.tagNoteMap.initTagNoteMapFromCountryNotes(inputSet));
    }

    @Test
    public void getNotesForTag_usesInitialisedMap_returnsTrue() {
        TAGGED_NOTE.setTags(tags);
        client.addClientNote(TAGGED_NOTE);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        Set<Note> expectedNotes = new HashSet<>();
        expectedNotes.add(TAGGED_NOTE);
        assertEquals(tagNoteMap.getNotesForTag(TEST_TAG), expectedNotes);
    }

    @Test
    public void getUniqueTags_verifyWithNewTag_doesNotThrowExceptionReturnsTrue() throws ParseException {
        Set<Tag> expectedResult = new HashSet<>();
        expectedResult.add(new Tag("unprecedentedTag"));
        List<String> tagNameStrings = new ArrayList<>();
        tagNameStrings.add("unprecedentedTag");
        assertDoesNotThrow(() -> this.tagNoteMap.getUniqueTags(tagNameStrings));
        Set<Tag> actualResult = this.tagNoteMap.getUniqueTags(tagNameStrings);
        assertEquals(expectedResult, actualResult);
    }

    // also tests that when a tag doesn't have associated notes then it is removed from tagToNotesMap and uniqueTagsMap
    @Test
    public void deleteNote_deleteSoleNoteWithSoleTag_clearsTagToNotesMapAndUniqueTagEntriesReturnsTrue() {
        TAGGED_NOTE.setTags(tags);
        Set<Note> expectedNotesSet = new HashSet<>();
        expectedNotesSet.add(TAGGED_NOTE);
        client.addClientNote(TAGGED_NOTE);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        assertTrue(tagNoteMap.getTagsForNote(TAGGED_NOTE).equals(tags));
        assertTrue(tagNoteMap.getNotesForTag(TEST_TAG).equals(expectedNotesSet));
        tagNoteMap.deleteNote(TAGGED_NOTE);
        assertFalse(tagNoteMap.getNotesForTag(TEST_TAG).equals(expectedNotesSet));
    }

    @Test
    public void editNote_validInputs_replacesExistingNoteWithNewNote() {
        TAGGED_NOTE.setTags(tags);
        Note newNote = new Note("new content");
        Set<Tag> newTagSet = new HashSet<>();
        Tag newTag = new Tag("unprecedentedTag");
        newTagSet.add(newTag);
        newNote.setTags(newTagSet);
        Set<Note> expectedNotesSet = new HashSet<>();
        expectedNotesSet.add(newNote);
        client.addClientNote(TAGGED_NOTE);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        assertTrue(tagNoteMap.getTagsForNote(TAGGED_NOTE).equals(tags));
        tagNoteMap.editNote(TAGGED_NOTE, newNote);
        assertFalse(tagNoteMap.getNotesForTag(TEST_TAG).equals(expectedNotesSet));
        assertTrue(tagNoteMap.getNotesForTag(newTag).equals(expectedNotesSet));
    }

    @Test
    public void getTagsForNote_useNoteWithTwoTags_returnsTrue() {
        Tag tag2 = new Tag("tag2");
        tags.add(tag2);
        TAGGED_NOTE.setTags(tags);
        client.addClientNote(TAGGED_NOTE);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        tagNoteMap.initTagNoteMapFromClients(clients);
        Set<Tag> expectedTags = new HashSet<>();
        expectedTags.add(tag2);
        expectedTags.add(TEST_TAG);
        Set<Tag> actualTags = tagNoteMap.getTagsForNote(TAGGED_NOTE);
        assertTrue(expectedTags.equals(actualTags));
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(tagNoteMap);

        // same state -> returns true
        TagNoteMap newTagNoteMap = new TagNoteMap();
        assertTrue(newTagNoteMap.equals(this.tagNoteMap));

        // after adding a note -> returns false
        newTagNoteMap.addTagsForNote(tags, new Note("note"));
        assertFalse(tagNoteMap.equals(newTagNoteMap));
    }

}

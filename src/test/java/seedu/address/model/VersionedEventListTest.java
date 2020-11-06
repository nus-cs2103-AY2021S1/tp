package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.EventUtil.DEFAULT_EVENT;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.VersionedListException;
import seedu.address.testutil.event.EventListBuilder;

public class VersionedEventListTest {
    private static final EventList VALID_EVENT_LIST = new EventListBuilder().withEvent(VALID_EVENT)
            .withEvent(DEFAULT_EVENT).build();
    private final VersionedEventList versionedEventList = new VersionedEventList(VALID_EVENT_LIST);


    @Test
    public void constructor() {
        assertEquals(VALID_EVENT_LIST, versionedEventList);
    }
    @Test
    public void execute_commit_success() {
        //Initiate empty VersionedEventList
        VersionedEventList initialVersionedEventList = new VersionedEventList();
        initialVersionedEventList.commit(VALID_EVENT_LIST);
        assertEquals(initialVersionedEventList, VALID_EVENT_LIST);
    }

    @Test
    public void undoData_withNoHistory_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedEventList.undo());
    }

    @Test
    public void execute_undoWithHistory_success() {
        //Initiate empty VersionedEventList
        VersionedEventList initialVersionedEventList = new VersionedEventList();
        initialVersionedEventList.commit(VALID_EVENT_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedEventList, VALID_EVENT_LIST);
        try {
            initialVersionedEventList.undo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedEventList, new EventList());
    }

    @Test
    public void redoData_withNoFuture_throwsVersionedListException() {
        assertThrows(VersionedListException.class, () -> versionedEventList.redo());
    }

    @Test
    public void execute_redoWithFuture_success() {
        //Initiate empty VersionedEventList
        VersionedEventList initialVersionedEventList = new VersionedEventList();
        initialVersionedEventList.commit(VALID_EVENT_LIST);
        //Make sure that commit was successful
        assertEquals(initialVersionedEventList, VALID_EVENT_LIST);
        try {
            initialVersionedEventList.undo();
            //make sure that undo was successful
            assertEquals(initialVersionedEventList, new EventList());
            initialVersionedEventList.redo();
        } catch (VersionedListException ve) {
            throw new AssertionError("Execution of method should not fail.", ve);
        }
        assertEquals(initialVersionedEventList, VALID_EVENT_LIST);
    }
}

package seedu.resireg.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListPtrTest {
    private static final String FIRST = "first";
    private static final String SECOND = "second";
    private static final String THIRD = "third";
    private List<String> ptrList;
    private ListPtr ptr;

    @BeforeEach
    public void setUp() {
        ptrList = new ArrayList<>();
        ptrList.add(FIRST);
        ptrList.add(SECOND);
        ptrList.add(THIRD);
    }

    @Test
    public void constructor_defensiveCopying_backingListUnmodified() {
        List<String> list = new ArrayList<>();
        ptr = new ListPtr(list);
        list.add(FIRST);

        ListPtr emptyPtr = new ListPtr(Collections.emptyList());
        assertEquals(emptyPtr, ptr);
    }

    @Test
    public void emptyList() {
        ptr = new ListPtr(new ArrayList<>());
        assertCurrFailure();
        assertPrevFailure();
        assertNextFailure();

        ptr.add(FIRST);
        assertNextSuccess(FIRST);
    }

    @Test
    public void singletonList() {
        List<String> list = new ArrayList<>();
        list.add(FIRST);
        ptr = new ListPtr(list);

        assertCurrSuccess(FIRST);
        assertPrevFailure();
        assertCurrSuccess(FIRST);
        assertNextFailure();
        assertCurrSuccess(FIRST);

        ptr.add(SECOND);
        assertNextSuccess(SECOND);
    }

    @Test
    public void multipleItemsList() {
        ptr = new ListPtr(ptrList);
        String fourth = "fourth";
        ptr.add(fourth);

        assertCurrSuccess(THIRD);

        assertNextSuccess(fourth);
        assertNextFailure();

        assertPrevSuccess(THIRD);
        assertPrevSuccess(SECOND);
        assertPrevSuccess(FIRST);
        assertPrevFailure();
    }

    @Test
    public void equals() {
        ListPtr firstPtr = new ListPtr(ptrList);

        // same object -> returns true
        assertTrue(firstPtr.equals(firstPtr));

        // same values -> returns true
        ListPtr copyOfFirstPtr = new ListPtr(ptrList);
        assertTrue(firstPtr.equals(copyOfFirstPtr));

        // different types -> returns false
        assertFalse(firstPtr.equals("Jet"));

        // null -> returns false
        assertFalse(firstPtr.equals(null));

        // different items -> returns false
        ListPtr diffPtr = new ListPtr(Collections.singletonList(THIRD));
        assertFalse(firstPtr.equals(diffPtr));

        // different index -> returns false
        copyOfFirstPtr.previous();
        assertFalse(firstPtr.equals(copyOfFirstPtr));

    }

    /**
     * Asserts that {@code ptr#hasNext()} returns true and the return value of
     * {@code ptr#next()} equals to {@code item}
     */
    private void assertNextSuccess(String item) {
        assertTrue(ptr.hasNext());
        assertEquals(item, ptr.next());
    }

    /**
     * Asserts that {@code ptr#hasPrevious()} returns true and the return value of
     * {@code ptr#previous()} equals to {@code item}
     */
    private void assertPrevSuccess(String item) {
        assertTrue(ptr.hasPrevious());
        assertEquals(item, ptr.previous());
    }

    /**
     * Asserts that {@code ptr#hasCurrent()} returns true and the return value of
     * {@code ptr#current()} equals to {@code item}
     */
    private void assertCurrSuccess(String item) {
        assertTrue(ptr.hasCurrent());
        assertEquals(item, ptr.current());
    }

    /**
     * Asserts that {@code ptr#hasNext()} returns false and the following
     * {@code ptr#next()} call throws {@code NoSuchElementException}.
     */
    private void assertNextFailure() {
        assertFalse(ptr.hasNext());
        try {
            ptr.next();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected exception thrown
        }
    }

    /**
     * Asserts that {@code ptr#hasPrevious()} returns false and the following
     * {@code ptr#previous()} call throws {@code NoSuchElementException}.
     */
    private void assertPrevFailure() {
        assertFalse(ptr.hasPrevious());
        try {
            ptr.previous();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected exception thrown
        }
    }

    /**
     * Asserts that {@code ptr#hasCurrent()} returns false and the following
     * {@code ptr#current()} call throws {@code NoSuchElementException}.
     */
    private void assertCurrFailure() {
        assertFalse(ptr.hasCurrent());
        try {
            ptr.current();
            throw new AssertionError("The expected NoSuchElementException was not thrown.");
        } catch (NoSuchElementException e) {
            // expected exception thrown
        }
    }
}

package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalLogs.LOG_A;
import static seedu.fma.testutil.TypicalLogs.LOG_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.fma.model.log.exceptions.DuplicateLogException;
import seedu.fma.model.log.exceptions.LogNotFoundException;
import seedu.fma.testutil.LogBuilder;

public class UniqueLogListTest {

    private final UniqueLogList uniqueLogList = new UniqueLogList();

    @Test
    public void contains_nullLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLogList.contains(null));
    }

    @Test
    public void contains_logNotInList_returnsFalse() {
        assertFalse(uniqueLogList.contains(LOG_A));
    }

    @Test
    public void contains_logInList_returnsTrue() {
        uniqueLogList.add(LOG_A);
        assertTrue(uniqueLogList.contains(LOG_A));
    }

    @Test
    public void contains_logWithSameIdentityFieldsInList_returnsTrue() {
        uniqueLogList.add(LOG_A);
        Log editedLogA = new LogBuilder(LOG_A).build();
        assertTrue(uniqueLogList.contains(editedLogA));
    }

    @Test
    public void add_nullLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLogList.add(null));
    }

    @Test
    public void add_duplicateLog_throwsDuplicateLogException() {
        uniqueLogList.add(LOG_A);
        assertThrows(DuplicateLogException.class, () -> uniqueLogList.add(LOG_A));
    }

    @Test
    public void setLog_nullTargetLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLogList.setLog(null, LOG_A));
    }

    @Test
    public void setLog_nullEditedLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLogList.setLog(LOG_A, null));
    }

    @Test
    public void setLog_targetLogNotInList_throwsLogNotFoundException() {
        assertThrows(LogNotFoundException.class, () -> uniqueLogList.setLog(LOG_A, LOG_A));
    }

    @Test
    public void setLog_editedLogIsSameLog_success() {
        uniqueLogList.add(LOG_A);
        uniqueLogList.setLog(LOG_A, LOG_A);
        UniqueLogList expectedUniqueLogList = new UniqueLogList();
        expectedUniqueLogList.add(LOG_A);
        assertEquals(expectedUniqueLogList, uniqueLogList);
    }

    @Test
    public void setLog_editedLogHasSameIdentity_success() {
        uniqueLogList.add(LOG_A);
        Log editedLogA = new LogBuilder(LOG_A).withComment("test comment")
                .build();
        uniqueLogList.setLog(LOG_A, editedLogA);
        UniqueLogList expectedUniqueLogList = new UniqueLogList();
        expectedUniqueLogList.add(editedLogA);
        assertEquals(expectedUniqueLogList, uniqueLogList);
    }

    @Test
    public void setLog_editedLogHasDifferentIdentity_success() {
        uniqueLogList.add(LOG_A);
        uniqueLogList.setLog(LOG_A, LOG_B);
        UniqueLogList expectedUniqueLogList = new UniqueLogList();
        expectedUniqueLogList.add(LOG_B);
        assertEquals(expectedUniqueLogList, uniqueLogList);
    }

    @Test
    public void setLog_editedLogHasNonUniqueIdentity_throwsDuplicateLogException() {
        uniqueLogList.add(LOG_A);
        uniqueLogList.add(LOG_B);
        assertThrows(DuplicateLogException.class, () -> uniqueLogList.setLog(LOG_A, LOG_B));
    }

    @Test
    public void remove_nullLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLogList.remove(null));
    }

    @Test
    public void remove_logDoesNotExist_throwsLogNotFoundException() {
        assertThrows(LogNotFoundException.class, () -> uniqueLogList.remove(LOG_A));
    }

    @Test
    public void remove_existingLog_removesLog() {
        uniqueLogList.add(LOG_A);
        uniqueLogList.remove(LOG_A);
        UniqueLogList expectedUniqueLogList = new UniqueLogList();
        assertEquals(expectedUniqueLogList, uniqueLogList);
    }

    @Test
    public void setLogs_nullUniqueLogList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLogList.setLogs((UniqueLogList) null));
    }

    @Test
    public void setLogs_uniqueLogList_replacesOwnListWithProvidedUniqueLogList() {
        uniqueLogList.add(LOG_A);
        UniqueLogList expectedUniqueLogList = new UniqueLogList();
        expectedUniqueLogList.add(LOG_B);
        uniqueLogList.setLogs(expectedUniqueLogList);
        assertEquals(expectedUniqueLogList, uniqueLogList);
    }

    @Test
    public void setLogs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueLogList.setLogs((List<Log>) null));
    }

    @Test
    public void setLogs_list_replacesOwnListWithProvidedList() {
        uniqueLogList.add(LOG_A);
        List<Log> logList = Collections.singletonList(LOG_B);
        uniqueLogList.setLogs(logList);
        UniqueLogList expectedUniqueLogList = new UniqueLogList();
        expectedUniqueLogList.add(LOG_B);
        assertEquals(expectedUniqueLogList, uniqueLogList);
    }

    @Test
    public void setLogs_listWithDuplicateLogs_throwsDuplicateLogException() {
        List<Log> listWithDuplicateLogs = Arrays.asList(LOG_A, LOG_A);
        assertThrows(DuplicateLogException.class, () -> uniqueLogList.setLogs(listWithDuplicateLogs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueLogList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    void iterator() {
        uniqueLogList.add(LOG_A);
        assertEquals(LOG_A, uniqueLogList.iterator().next());
    }

    @Test
    void testHashCode() {
        uniqueLogList.add(LOG_A);
        uniqueLogList.add(LOG_B);
        assertTrue(uniqueLogList.hashCode() == uniqueLogList.hashCode());
    }

}

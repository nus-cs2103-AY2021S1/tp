package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.GOOGLE;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.information.Job;
import seedu.address.model.information.exceptions.DuplicateJobException;
import seedu.address.testutil.JobBuilder;

public class JobAddressBookTest {

    private final JobAddressBook jobAddressBook = new JobAddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), jobAddressBook.getJobList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobAddressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        JobAddressBook newData = getTypicalJobAddressBook();
        jobAddressBook.resetData(newData);
        assertEquals(newData, jobAddressBook);
    }

    @Test
    public void resetData_withDuplicateJobs_throwsDuplicateJobException() {
        // Two jobs with the same identity fields
        Job editedGoogle = new JobBuilder(GOOGLE).withAddress(VALID_ADDRESS_IRAS).withTags(VALID_TAG_MAYBANK)
                .build();
        List<Job> newJobs = Arrays.asList(GOOGLE, editedGoogle);
        JobAddressBookStub newData = new JobAddressBookStub(newJobs);

        assertThrows(DuplicateJobException.class, () -> jobAddressBook.resetData(newData));
    }

    @Test
    public void hasJob_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jobAddressBook.hasJob(null));
    }

    @Test
    public void hasJob_jobNotInAddressBook_returnsFalse() {
        assertFalse(jobAddressBook.hasJob(GOOGLE));
    }

    @Test
    public void hasJob_jobInAddressBook_returnsTrue() {
        jobAddressBook.addJob(GOOGLE);
        assertTrue(jobAddressBook.hasJob(GOOGLE));
    }

    @Test
    public void hasJob_jobWithSameIdentityFieldsInAddressBook_returnsTrue() {
        jobAddressBook.addJob(GOOGLE);
        Job editedGoogle = new JobBuilder(GOOGLE).withAddress(VALID_ADDRESS_IRAS).withTags(VALID_TAG_MAYBANK)
                .build();
        assertTrue(jobAddressBook.hasJob(editedGoogle));
    }

    @Test
    public void getJobList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> jobAddressBook.getJobList().remove(0));
    }

    /**
     * A stub ReadOnlyJobAddressBook whose jobs list can violate interface constraints.
     */
    private static class JobAddressBookStub implements ReadOnlyJobAddressBook {
        private final ObservableList<Job> jobs = FXCollections.observableArrayList();

        JobAddressBookStub(Collection<Job> jobs) {
            this.jobs.setAll(jobs);
        }

        @Override
        public ObservableList<Job> getJobList() {
            return jobs;
        }
    }

}

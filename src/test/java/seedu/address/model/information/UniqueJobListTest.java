package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.FACEBOOK;
import static seedu.address.testutil.TypicalJobs.GOOGLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.exceptions.DuplicateJobException;
import seedu.address.model.information.exceptions.JobNotFoundException;
import seedu.address.testutil.JobBuilder;

public class UniqueJobListTest {

    private final UniqueJobList uniqueJobList = new UniqueJobList();

    @Test
    public void contains_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.contains(null));
    }

    @Test
    public void contains_jobNotInList_returnsFalse() {
        assertFalse(uniqueJobList.contains(GOOGLE));
    }

    @Test
    public void contains_jobInList_returnsTrue() {
        uniqueJobList.add(GOOGLE);
        assertTrue(uniqueJobList.contains(GOOGLE));
    }

    @Test
    public void contains_jobWithSameIdentityFieldsInList_returnsTrue() { //?
        uniqueJobList.add(GOOGLE);
        Job editedGoogle = new JobBuilder(GOOGLE).withAddress(VALID_ADDRESS_IRAS).withTags(VALID_TAG_MAYBANK)
                .build();
        assertTrue(uniqueJobList.contains(editedGoogle));
    }

    @Test
    public void add_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.add(null));
    }

    @Test
    public void add_duplicateJob_throwsDuplicateJobException() {
        uniqueJobList.add(GOOGLE);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.add(GOOGLE));
    }

    @Test
    public void setJob_nullTargetJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(null, GOOGLE));
    }

    @Test
    public void setJob_nullEditedJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(GOOGLE, null));
    }

    @Test
    public void setJob_targetJobNotInList_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.setJob(GOOGLE, GOOGLE));
    }

    @Test
    public void setJob_editedJobIsSameJob_success() {
        uniqueJobList.add(GOOGLE);
        uniqueJobList.setJob(GOOGLE, GOOGLE);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(GOOGLE);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasSameIdentity_success() {
        uniqueJobList.add(GOOGLE);
        Job editedGoogle = new JobBuilder(GOOGLE).withAddress(VALID_ADDRESS_IRAS).withTags(VALID_TAG_MAYBANK)
                .build();
        uniqueJobList.setJob(GOOGLE, editedGoogle);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(editedGoogle);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasDifferentIdentity_success() {
        uniqueJobList.add(GOOGLE);
        uniqueJobList.setJob(GOOGLE, FACEBOOK);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(FACEBOOK);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasNonUniqueIdentity_throwsDuplicateJobException() {
        uniqueJobList.add(GOOGLE);
        uniqueJobList.add(FACEBOOK);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.setJob(GOOGLE, FACEBOOK));
    }

    @Test
    public void remove_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.remove(null));
    }

    @Test
    public void remove_jobDoesNotExist_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.remove(GOOGLE));
    }

    @Test
    public void remove_existingJob_removesJob() {
        uniqueJobList.add(GOOGLE);
        uniqueJobList.remove(GOOGLE);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullUniqueJobList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((UniqueJobList) null));
    }

    @Test
    public void setJobs_uniqueJobList_replacesOwnListWithProvidedUniqueJobList() {
        uniqueJobList.add(GOOGLE);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(FACEBOOK);
        uniqueJobList.setJobs(expectedUniqueJobList);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((List<Job>) null));
    }

    @Test
    public void setJobs_list_replacesOwnListWithProvidedList() {
        uniqueJobList.add(GOOGLE);
        List<Job> jobList = Collections.singletonList(FACEBOOK);
        uniqueJobList.setJobs(jobList);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(FACEBOOK);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_listWithDuplicateJobs_throwsDuplicateJobException() {
        List<Job> listWithDuplicateJobs = Arrays.asList(GOOGLE, GOOGLE);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.setJobs(listWithDuplicateJobs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueJobList.asUnmodifiableObservableList().remove(0));
    }
}

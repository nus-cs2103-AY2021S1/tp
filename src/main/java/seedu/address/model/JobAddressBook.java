package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.information.Job;
import seedu.address.model.information.UniqueJobList;

/**
 * Wraps all job data at the address-book level
 * Duplicates are not allowed (by .isSameJob comparison)
 */
public class JobAddressBook implements ReadOnlyJobAddressBook {

    private final UniqueJobList jobs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        jobs = new UniqueJobList();
    }

    public JobAddressBook() {}

    /**
     * Creates a JobAddressBook using the Jobs in the {@code toBeCopied}
     */
    public JobAddressBook(ReadOnlyJobAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the job list with {@code jobs}.
     * {@code jobs} must not contain duplicate persons.
     */
    public void setJobs(List<Job> jobs) {
        this.jobs.setJobs(jobs);
    }

    /**
     * Resets the existing job data of this {@code JobAddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyJobAddressBook newData) {
        requireNonNull(newData);

        setJobs(newData.getJobList());
    }

    //// job-level operations

    /**
     * Returns true if a job with the same identity as {@code job} exists in the job address book.
     */
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * Adds a job to the job address book.
     * The job must not already exist in the address book.
     */
    public void addJob(Job j) {
        jobs.add(j);
    }


    /**
     * Replaces the given job {@code target} in the list with {@code editedJob}.
     * {@code target} must exist in the address book.
     * The job identity of {@code editedJob} must not be the same as another existing job in the address book.
     */
    public void setJob(Job target, Job editedJob) {
        requireNonNull(editedJob);

        jobs.setJob(target, editedJob);
    }

    /**
     * Removes {@code key} from this {@code JobAddressBook}.
     * {@code key} must exist in the job address book.
     */
    public void removeJob(Job key) {
        jobs.remove(key);
    }


    //// util methods

    @Override
    public String toString() {
        return jobs.asUnmodifiableObservableList().size() + " jobs";
        // TODO: refine later
    }

    @Override
    public ObservableList<Job> getJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobAddressBook // instanceof handles nulls
                && jobs.equals(((JobAddressBook) other).jobs));
    }

    @Override
    public int hashCode() {
        return jobs.hashCode();
    }
}

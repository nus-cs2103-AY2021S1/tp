package seedu.address.testutil;

import seedu.address.model.JobAddressBook;
import seedu.address.model.information.Job;

/**
 * A utility class to help with building JobAddressBook objects.
 * Example usage: <br>
 *     {@code JobAddressBook jab = new JobAddressBookBuilder().withJob("IRAS", "MAYBANK").build();}
 */
public class JobAddressBookBuilder {

    private JobAddressBook jobAddressBook;

    public JobAddressBookBuilder() {
        jobAddressBook = new JobAddressBook();
    }

    public JobAddressBookBuilder(JobAddressBook jobAddressBook) {
        this.jobAddressBook = jobAddressBook;
    }

    /**
     * Adds a new {@code Job} to the {@code JobAddressBook} that we are building.
     */
    public JobAddressBookBuilder withJob(Job job) {
        jobAddressBook.addJob(job);
        return this;
    }

    public JobAddressBook build() {
        return jobAddressBook;
    }
}

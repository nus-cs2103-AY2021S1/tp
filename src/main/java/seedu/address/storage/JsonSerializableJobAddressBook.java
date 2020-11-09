package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.JobAddressBook;
import seedu.address.model.ReadOnlyJobAddressBook;
import seedu.address.model.information.Job;

/**
 * An Immutable JobAddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableJobAddressBook {

    public static final String MESSAGE_DUPLICATE_JOB = "Jobs list contains duplicate job(s).";

    private final List<JsonAdaptedJob> jobs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableJobAddressBook} with the given jobs.
     */
    @JsonCreator
    public JsonSerializableJobAddressBook(@JsonProperty("jobs") List<JsonAdaptedJob> jobs) {
        this.jobs.addAll(jobs);
    }

    /**
     * Converts a given {@code ReadOnlyJobAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableJobAddressBook}.
     */
    public JsonSerializableJobAddressBook(ReadOnlyJobAddressBook source) {
        jobs.addAll(source.getJobList().stream().map(JsonAdaptedJob::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code JobAddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public JobAddressBook toModelType() throws IllegalValueException {
        JobAddressBook addressBook = new JobAddressBook();
        for (JsonAdaptedJob jsonAdaptedJob : jobs) {
            Job job = jsonAdaptedJob.toModelType();
            if (addressBook.hasJob(job)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_JOB);
            }
            addressBook.addJob(job);
        }
        return addressBook;
    }

}

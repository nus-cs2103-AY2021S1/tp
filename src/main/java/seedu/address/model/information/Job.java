package seedu.address.model.information;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Job in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    public static final String TAB_NAME = "Jobs";

    // Identity fields
    private final Name jobTitle;
    private final CompanyName companyName;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Priority priority;
    private final Vacancy vacancy;

    /**
     * Every field must be present and not null.
     */
    public Job(Name jobTitle, CompanyName companyName, Phone phone, Email email, Address address,
               Set<Tag> tags, Priority priority, Vacancy vacancy) {
        requireAllNonNull(jobTitle, companyName, phone, email, address, tags, priority, vacancy);
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.priority = priority;
        this.vacancy = vacancy;
    }

    public Name getJobTitle() {
        return jobTitle;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Priority getPriority() {
        return priority;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    /**
     * Returns true if both jobs of the same job title are from the same company.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && otherJob.getJobTitle().equals(getJobTitle())
                && (otherJob.getCompanyName().equals(getCompanyName()));
    }

    /**
     * Returns true if both jobs have the same identity and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return otherJob.getJobTitle().equals(getJobTitle())
                && otherJob.getCompanyName().equals(getCompanyName())
                && otherJob.getPhone().equals(getPhone())
                && otherJob.getEmail().equals(getEmail())
                && otherJob.getAddress().equals(getAddress())
                && otherJob.getTags().equals(getTags())
                && otherJob.getPriority().equals(getPriority())
                && otherJob.getVacancy().equals(getVacancy());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(jobTitle, companyName, phone, email, address, tags, priority, vacancy);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getJobTitle())
                .append(" Company: ")
                .append(getCompanyName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Priority: ")
                .append(getPriority())
                .append(" Vacancy: ")
                .append(getVacancy())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}

package com.eva.model.applicant;

import java.util.Optional;
import java.util.Set;

import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.tag.Tag;

/**
 * Represents the Applicant to be stored by Eva.
 */
public class Applicant extends Person {
    protected Optional<InterviewDate> interviewDate;
    protected ApplicationStatus applicationStatus;

    /**
     * Creates of an applicant object with a fixed interview date.
     */
    public Applicant(Name name,
                     Phone phone,
                     Email email,
                     Address address,
                     Set<Tag> tags,
                     InterviewDate interviewDate,
                     ApplicationStatus status) {
        super(name, phone, email, address, tags);
        this.interviewDate = Optional.ofNullable(interviewDate);
        this.applicationStatus = status;
    }

    /**
     * Creates of an applicant object without a fixed interview date.
     */
    public Applicant(Name name,
                     Phone phone,
                     Email email,
                     Address address,
                     Set<Tag> tags,
                     ApplicationStatus status) {
        super(name, phone, email, address, tags);
        this.interviewDate = Optional.empty();
        this.applicationStatus = status;
    }

    /**
     * Sets the application status of the specific applicant to be accepted.
     */
    public void setApplicantAccepted() {
        this.applicationStatus.setAccepted();
    }

    /**
     * Sets the application status of the specific applicant to be rejected.
     */
    public void setApplicantRejected() {
        this.applicationStatus.setRejected();
    }

    public Optional<InterviewDate> getInterviewDate() {
        return interviewDate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Interview Date: ")
                .append(getInterviewDate().isEmpty() ? "Interview date not set yet" : getInterviewDate())
                .append(" Application Status: ")
                .append(getApplicationStatus());
        return builder.toString();
    }
}

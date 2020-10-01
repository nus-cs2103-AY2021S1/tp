package seedu.address.model.applicant;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Optional;
import java.util.Set;

public class Applicant extends Person {
    protected Optional<InterviewDate> interviewDate;
    protected ApplicationStatus applicationStatus;


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

    public void setApplicantAccepted() {
        this.applicationStatus.setAccepted();
    }

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

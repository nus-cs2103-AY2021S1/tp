package com.eva.model.person.applicant;

import java.util.Optional;
import java.util.Set;

import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.application.Application;
import com.eva.model.tag.Tag;


/**
 * Represents the Applicant to be stored by Eva.
 */
public class Applicant extends Person {
    private Optional<InterviewDate> interviewDate;
    private ApplicationStatus applicationStatus;
    private Application application;

    /**
     * Creates of an applicant object with a fixed interview date.
     */
    public Applicant(Name name,
                     Phone phone,
                     Email email,
                     Address address,
                     Set<Tag> tags,
                     Set<Comment> comments,
                     Optional<InterviewDate> interviewDate,
                     ApplicationStatus status) {
        super(name, phone, email, address, tags, comments);
        this.interviewDate = interviewDate;
        this.applicationStatus = status;
        this.application = new Application();
    }

    /**
     * Creates of an applicant object without a fixed interview date.
     */
    public Applicant(Name name,
                     Phone phone,
                     Email email,
                     Address address,
                     Set<Tag> tags,
                     Set<Comment> comments,
                     ApplicationStatus status) {
        super(name, phone, email, address, tags, comments);
        this.interviewDate = Optional.empty();
        this.applicationStatus = status;
        this.application = new Application();
    }

    /**
     * Creates an Applicant object from a Person Object.
     * @param person
     * @param interviewDate
     * @param status
     */
    public Applicant(Person person, InterviewDate interviewDate, ApplicationStatus status) {
        this(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
                person.getTags(), person.getComments(), Optional.ofNullable(interviewDate), status);
        this.application = new Application();
    }

    /**
     * Sets the application status of the specific applicant to be accepted.
     */
    public void setApplicantAccepted() {
        this.applicationStatus.setAccepted();
    }

    /**
     * Sets the application status of the specific applicant to be processing.
     */
    public void setApplicantProcessing() {
        this.applicationStatus.setProcessing();
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

    /**
     * Sets the interview date of the specific applicant.
     */
    public void setInterviewDate(InterviewDate interviewDate) {
        this.interviewDate = Optional.ofNullable(interviewDate);
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplication(Application application) {
        assert application != null;
        this.application = application;
    }

    public Application getApplication() {
        return application;
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
                .append(getInterviewDate().isEmpty() ? "Not set yet" : getInterviewDate().get())
                .append(" Application Status: ")
                .append(getApplicationStatus());
        return builder.toString();
    }

    public void setApplicationStatus(ApplicationStatus status) {
        this.applicationStatus = status;
    }
}

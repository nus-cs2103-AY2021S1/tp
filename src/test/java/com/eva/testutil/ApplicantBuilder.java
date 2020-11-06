package com.eva.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.tag.Tag;
import com.eva.model.util.SampleDataUtil;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    public static final String DEFAULT_APPLICATION_STATUS = "received";
    public static final String DEFAULT_INTERVIEW_DATE = "30/12/2020";

    protected Name name;
    protected Phone phone;
    protected Email email;
    protected Address address;
    protected Set<Tag> tags;
    protected Set<Comment> comments;
    private Optional<InterviewDate> interviewDate;
    private ApplicationStatus applicationStatus;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        comments = new HashSet<>();
        applicationStatus = new ApplicationStatus(DEFAULT_APPLICATION_STATUS);
        interviewDate = Optional.empty();
    }

    /**
     * Creates a {@code ApplicantBuilder} with the details of the provided applicant.
     * @param applicantToCopy
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        address = applicantToCopy.getAddress();
        tags = new HashSet<>(applicantToCopy.getTags());
        comments = new HashSet<>(applicantToCopy.getComments());
        applicationStatus = applicantToCopy.getApplicationStatus();
        interviewDate = applicantToCopy.getInterviewDate();
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code comments} into a {@code Set<Comment>} and set it to the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withComments(String... comments) {
        this.comments = SampleDataUtil.getCommentSet(comments);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code InterviewDate} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withInterviewDate(String interviewDate) {
        this.interviewDate = Optional.ofNullable(new InterviewDate(interviewDate));
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withApplicationStatus(String applicationStatus) {
        this.applicationStatus = new ApplicationStatus(applicationStatus);
        return this;
    }

    /**
     * Builds an applicant with the relevant attributes.
     * @return applicant containing the attributes.
     */
    public Applicant build() {
        return new Applicant(name, phone, email, address, tags, comments,
                interviewDate, applicationStatus);
    }
}

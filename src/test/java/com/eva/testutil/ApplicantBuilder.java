package com.eva.testutil;

import java.util.Optional;

import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;


public class ApplicantBuilder extends PersonBuilder {
    public static final String DEFAULT_APPLICATION_STATUS = "received";
    public static final String DEFAULT_INTERVIEW_DATE = "30/12/2020";

    private Optional<InterviewDate> interviewDate;
    private ApplicationStatus applicationStatus;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        super();
        this.applicationStatus = new ApplicationStatus(DEFAULT_APPLICATION_STATUS);
        this.interviewDate = Optional.empty();
    }

    public void withInterviewDate(String interviewDate) {
        this.interviewDate = Optional.ofNullable(new InterviewDate(interviewDate));
    }

    public void withApplicationStatus (String applicationStatus) {
        this.applicationStatus = new ApplicationStatus(applicationStatus);
    }

    @Override
    public Applicant build() {
        return new Applicant(name, phone, email, address, tags, comments,
                interviewDate, applicationStatus);
    }
}

package com.eva.model.person.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.eva.testutil.ApplicantBuilder;

public class ApplicantTest {

    @Test
    public void setApplicantAcceptedSuccessful() {
        Applicant applicant = new ApplicantBuilder().build();
        assertEquals(applicant.getApplicationStatus().toString(), "received");
        applicant.setApplicantProcessing();
        assertEquals(applicant.getApplicationStatus().toString(), "processing");
        applicant.setApplicantAccepted();
        assertEquals(applicant.getApplicationStatus().toString(), "accepted");
        applicant.setApplicantRejected();
        assertEquals(applicant.getApplicationStatus().toString(), "rejected");
    }
}

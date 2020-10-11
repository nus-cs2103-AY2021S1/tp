package com.eva.model.person.applicant;

import com.eva.testutil.ApplicantBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

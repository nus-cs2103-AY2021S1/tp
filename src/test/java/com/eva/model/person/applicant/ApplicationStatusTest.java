package com.eva.model.person.applicant;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class ApplicationStatusTest {

    @Test
    public void isValidApplicationStatusSuccessful() {
        assertTrue(ApplicationStatus.isValidApplicationStatus("received"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("processing"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("accepted"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("rejected"));
    }

    @Test
    public void isValidApplicationStatusFail() {
        assertFalse(ApplicationStatus.isValidApplicationStatus("receivedd"));
        assertFalse(ApplicationStatus.isValidApplicationStatus("processsing"));
        assertFalse(ApplicationStatus.isValidApplicationStatus("accepttedd"));
        assertFalse(ApplicationStatus.isValidApplicationStatus("rejectted "));
    }
}

package com.eva.model.person.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ApplicationStatusTest {

    @Test
    public void isValidApplicationStatusSuccessful() {
        assertTrue(ApplicationStatus.isValidApplicationStatus("received"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("processing"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("accepted"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("rejected"));
    }

}

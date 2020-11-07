package com.eva.storage;

import static com.eva.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.commons.util.DateUtil;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.applicant.application.Application;


public class JsonAdaptedApplicantTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_COMMENT = "";
    private static final String INVALID_INTERVIEW_DATE = "80-50-9999";
    private static final String INVALID_APPLICATION_STATUS = "not received";

    private static final Applicant APPLICANT_BENSON = new Applicant(BENSON,
            new InterviewDate("01/02/2000"), new ApplicationStatus("received"));

    private static final String VALID_NAME = APPLICANT_BENSON.getName().toString();
    private static final String VALID_PHONE = APPLICANT_BENSON.getPhone().toString();
    private static final String VALID_EMAIL = APPLICANT_BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = APPLICANT_BENSON.getAddress().toString();
    private static final String VALID_INTERVIEW_DATE = APPLICANT_BENSON.getInterviewDate().get().toString();
    private static final String VALID_APPLICATION_STATUS = APPLICANT_BENSON.getApplicationStatus().toString();
    private static final JsonAdaptedApplication VALID_APPLICATION =
            new JsonAdaptedApplication(APPLICANT_BENSON.getApplication());
    private static final List<JsonAdaptedTag> VALID_TAGS =
            APPLICANT_BENSON.getTags().stream()
                    .map(JsonAdaptedTag::new)
                    .collect(Collectors.toList());
    private static final List<JsonAdaptedComment> VALID_COMMENT =
            APPLICANT_BENSON.getComments().stream()
                    .map(JsonAdaptedComment::new)
                    .collect(Collectors.toList());

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidInterviewDate_throwsIllegalArgumentException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = String.format(DateUtil.MESSAGE_CONSTRAINTS, INVALID_INTERVIEW_DATE);
        assertThrows(IllegalArgumentException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullInterviewDate_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        null, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_INTERVIEW_DATE, INVALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = ApplicationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_INTERVIEW_DATE, null, VALID_TAGS, VALID_COMMENT, VALID_APPLICATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ApplicationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullApplication_throwsIllegalValueException() {
        JsonAdaptedApplicant person = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, VALID_TAGS, VALID_COMMENT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Application.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedApplicant person =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_INTERVIEW_DATE, VALID_APPLICATION_STATUS, invalidTags, VALID_COMMENT, VALID_APPLICATION);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}

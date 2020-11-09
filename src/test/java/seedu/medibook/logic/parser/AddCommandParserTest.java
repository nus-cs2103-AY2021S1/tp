package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.ALLERGY_DESC_PENICILLIN;
import static seedu.medibook.logic.commands.CommandTestUtil.ALLERGY_DESC_SHELLFISH;
import static seedu.medibook.logic.commands.CommandTestUtil.BLOOD_TYPE_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.BLOOD_TYPE_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.CONDITION_DESC_BACK;
import static seedu.medibook.logic.commands.CommandTestUtil.CONDITION_DESC_DIABETES;
import static seedu.medibook.logic.commands.CommandTestUtil.DOB_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.DOB_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.HEIGHT_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.HEIGHT_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.IC_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.IC_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_ALLERGY_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_BLOOD_TYPE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_CONDITION_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_DOB_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_HEIGHT_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_IC_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_TREATMENT_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.medibook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.medibook.logic.commands.CommandTestUtil.TREATMENT_DESC_PARACETAMOL;
import static seedu.medibook.logic.commands.CommandTestUtil.TREATMENT_DESC_PHYSIOTHERAPY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_SHELLFISH;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_BACK;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_DIABETES;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PARACETAMOL;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PHYSIOTHERAPY;
import static seedu.medibook.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.medibook.testutil.TypicalPatients.AMY;
import static seedu.medibook.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.commands.AddCommand;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Tag;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.testutil.PatientBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withAllergies(VALID_ALLERGY_PENICILLIN)
                .withConditions(VALID_CONDITION_BACK).withTreatments(VALID_TREATMENT_PARACETAMOL).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple ics - last ic accepted
        assertParseSuccess(parser, IC_DESC_AMY + IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple names - last name accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple dates of birth - last date of birth accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_AMY + DOB_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_AMY
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple heights - last height accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_AMY + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple weights - last weight accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_AMY + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple blood type - last blood type accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_AMY + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // multiple allergies - all accepted
        Patient expectedPatientMultipleAllergies = new PatientBuilder(BOB)
            .withAllergies(VALID_ALLERGY_PENICILLIN, VALID_ALLERGY_SHELLFISH)
            .withConditions(VALID_CONDITION_BACK)
            .withTreatments(VALID_TREATMENT_PARACETAMOL).build();
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + TREATMENT_DESC_PARACETAMOL,
            new AddCommand(expectedPatientMultipleAllergies));

        // multiple conditions - all accepted
        Patient expectedPatientMultipleConditions = new PatientBuilder(BOB)
                .withAllergies(VALID_ALLERGY_PENICILLIN)
                .withConditions(VALID_CONDITION_BACK, VALID_CONDITION_DIABETES)
                .withTreatments(VALID_TREATMENT_PARACETAMOL).build();
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK + CONDITION_DESC_DIABETES
                + TREATMENT_DESC_PARACETAMOL,
            new AddCommand(expectedPatientMultipleConditions));

        // multiple treatments - all accepted
        Patient expectedPatientMultipleTreatments = new PatientBuilder(BOB)
                .withAllergies(VALID_ALLERGY_PENICILLIN)
                .withConditions(VALID_CONDITION_BACK)
                .withTreatments(VALID_TREATMENT_PARACETAMOL, VALID_TREATMENT_PHYSIOTHERAPY).build();
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + CONDITION_DESC_BACK
                        + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
            new AddCommand(expectedPatientMultipleTreatments));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero allergies
        Patient expectedPatient = new PatientBuilder(AMY).withAllergies().build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + CONDITION_DESC_DIABETES
                + TREATMENT_DESC_PARACETAMOL,
            new AddCommand(expectedPatient));

        // zero conditions
        expectedPatient = new PatientBuilder(AMY).withConditions().build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + TREATMENT_DESC_PARACETAMOL,
            new AddCommand(expectedPatient));

        // zero treatments
        expectedPatient = new PatientBuilder(AMY).withTreatments().build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + CONDITION_DESC_DIABETES,
                new AddCommand(expectedPatient));

        // missing email
        expectedPatient = new PatientBuilder(AMY).withEmail("").build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // missing address
        expectedPatient = new PatientBuilder(AMY).withAddress("").build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // missing height
        expectedPatient = new PatientBuilder(AMY).withHeight("").build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // missing weight
        expectedPatient = new PatientBuilder(AMY).withWeight("").build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));

        // missing bloodType
        expectedPatient = new PatientBuilder(AMY).withBloodType("").build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PARACETAMOL,
                new AddCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing ic prefix
        assertParseFailure(parser, VALID_IC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, IC_DESC_BOB + VALID_NAME_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing date of birth prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + VALID_DOB_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid ic
        assertParseFailure(parser, INVALID_IC_DESC + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Ic.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, IC_DESC_BOB + INVALID_NAME_DESC + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Name.MESSAGE_CONSTRAINTS);

        // invalid date of birth
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + INVALID_DOB_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Address.MESSAGE_CONSTRAINTS);

        // invalid height
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_HEIGHT_DESC + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Height.MESSAGE_CONSTRAINTS);

        // invalid weight
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + INVALID_WEIGHT_DESC
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Weight.MESSAGE_CONSTRAINTS);

        // invalid blood type
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + INVALID_BLOOD_TYPE_DESC + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                BloodType.MESSAGE_CONSTRAINTS);

        // invalid allergy
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + INVALID_ALLERGY_DESC + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid condition
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + INVALID_CONDITION_DESC
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid treatment
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + INVALID_TREATMENT_DESC + TREATMENT_DESC_PARACETAMOL,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, IC_DESC_BOB + INVALID_NAME_DESC + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PHYSIOTHERAPY + TREATMENT_DESC_PARACETAMOL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

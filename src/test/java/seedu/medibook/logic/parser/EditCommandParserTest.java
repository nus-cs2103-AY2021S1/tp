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
import static seedu.medibook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.TREATMENT_DESC_PARACETAMOL;
import static seedu.medibook.logic.commands.CommandTestUtil.TREATMENT_DESC_PHYSIOTHERAPY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_PENICILLIN;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_SHELLFISH;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_BACK;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_DIABETES;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_IC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PARACETAMOL;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PHYSIOTHERAPY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.EditCommand;
import seedu.medibook.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicaldetail.Tag;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.testutil.EditPatientDescriptorBuilder;

public class EditCommandParserTest {

    private static final String ALLERGY_EMPTY = " " + PREFIX_ALLERGY;
    private static final String CONDITION_EMPTY = " " + PREFIX_CONDITION;
    private static final String TREATMENT_EMPTY = " " + PREFIX_TREATMENT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 l/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_IC_DESC, Ic.MESSAGE_CONSTRAINTS); // invalid ic
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        // invalid date of birth
        assertParseFailure(parser, "1" + INVALID_DOB_DESC, DateOfBirth.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_HEIGHT_DESC, Height.MESSAGE_CONSTRAINTS); // invalid height
        assertParseFailure(parser, "1" + INVALID_WEIGHT_DESC, Weight.MESSAGE_CONSTRAINTS); // invalid weight
        // invalid blood type
        assertParseFailure(parser, "1" + INVALID_BLOOD_TYPE_DESC, BloodType.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ALLERGY_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid allergy
        assertParseFailure(parser, "1" + INVALID_CONDITION_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid condition
        assertParseFailure(parser, "1" + INVALID_TREATMENT_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid treatment

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_ALLERGY} alone will reset the allergies of the {@code Patient} being edited,
        // parsing it together with a valid allergy results in error
        assertParseFailure(parser, "1" + ALLERGY_DESC_PENICILLIN + ALLERGY_DESC_SHELLFISH
                + ALLERGY_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ALLERGY_DESC_PENICILLIN + ALLERGY_EMPTY
                + ALLERGY_DESC_SHELLFISH, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ALLERGY_EMPTY + ALLERGY_DESC_PENICILLIN
                + ALLERGY_DESC_SHELLFISH, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_CONDITION} alone will reset the conditions of the {@code Patient} being edited,
        // parsing it together with a valid condition results in error
        assertParseFailure(parser, "1" + CONDITION_DESC_BACK + CONDITION_DESC_DIABETES
                + CONDITION_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CONDITION_DESC_BACK + CONDITION_EMPTY
                + CONDITION_DESC_DIABETES, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + CONDITION_EMPTY + CONDITION_DESC_BACK
                + CONDITION_DESC_DIABETES, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TREATMENT} alone will reset the treatments of the {@code Patient} being edited,
        // parsing it together with a valid treatment results in error
        assertParseFailure(parser, "1" + TREATMENT_DESC_PARACETAMOL + TREATMENT_DESC_PHYSIOTHERAPY
                + TREATMENT_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TREATMENT_DESC_PARACETAMOL + TREATMENT_EMPTY
                + TREATMENT_DESC_PHYSIOTHERAPY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TREATMENT_EMPTY + TREATMENT_DESC_PARACETAMOL
                + TREATMENT_DESC_PHYSIOTHERAPY, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY
                        + VALID_HEIGHT_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + IC_DESC_AMY + PHONE_DESC_BOB + TREATMENT_DESC_PHYSIOTHERAPY
                + DOB_DESC_AMY + EMAIL_DESC_AMY + ALLERGY_DESC_SHELLFISH + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY
                + WEIGHT_DESC_AMY + CONDITION_DESC_BACK + BLOOD_TYPE_DESC_AMY + NAME_DESC_AMY + ALLERGY_DESC_PENICILLIN
                + CONDITION_DESC_DIABETES + TREATMENT_DESC_PARACETAMOL;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withIc(VALID_IC_AMY)
                .withName(VALID_NAME_AMY).withDateOfBirth(VALID_DOB_AMY).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withHeight(VALID_HEIGHT_AMY)
                .withWeight(VALID_WEIGHT_AMY).withBloodType(VALID_BLOOD_TYPE_AMY)
                .withAllergies(VALID_ALLERGY_SHELLFISH, VALID_ALLERGY_PENICILLIN)
                .withConditions(VALID_CONDITION_BACK, VALID_CONDITION_DIABETES)
                .withTreatments(VALID_TREATMENT_PHYSIOTHERAPY, VALID_TREATMENT_PARACETAMOL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // ic
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + IC_DESC_AMY;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withIc(VALID_IC_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name
        userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date of birth
        userInput = targetIndex.getOneBased() + DOB_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withDateOfBirth(VALID_DOB_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // height
        userInput = targetIndex.getOneBased() + HEIGHT_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withHeight(VALID_HEIGHT_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // weight
        userInput = targetIndex.getOneBased() + WEIGHT_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withWeight(VALID_WEIGHT_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // blood type
        userInput = targetIndex.getOneBased() + BLOOD_TYPE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withBloodType(VALID_BLOOD_TYPE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // allergies
        userInput = targetIndex.getOneBased() + ALLERGY_DESC_PENICILLIN;
        descriptor = new EditPatientDescriptorBuilder().withAllergies(VALID_ALLERGY_PENICILLIN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // conditions
        userInput = targetIndex.getOneBased() + CONDITION_DESC_BACK;
        descriptor = new EditPatientDescriptorBuilder().withConditions(VALID_CONDITION_BACK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // treatments
        userInput = targetIndex.getOneBased() + TREATMENT_DESC_PARACETAMOL;
        descriptor = new EditPatientDescriptorBuilder().withTreatments(VALID_TREATMENT_PARACETAMOL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + IC_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY + TREATMENT_DESC_PARACETAMOL
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TREATMENT_DESC_PARACETAMOL + IC_DESC_BOB
                + PHONE_DESC_BOB + CONDITION_DESC_BACK + ALLERGY_DESC_SHELLFISH + CONDITION_DESC_DIABETES
                + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + DOB_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + ALLERGY_DESC_SHELLFISH + TREATMENT_DESC_PHYSIOTHERAPY + ALLERGY_DESC_PENICILLIN;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withIc(VALID_IC_BOB)
                .withDateOfBirth(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
                .withBloodType(VALID_BLOOD_TYPE_BOB).withAllergies(VALID_ALLERGY_PENICILLIN, VALID_ALLERGY_SHELLFISH)
                .withConditions(VALID_CONDITION_BACK, VALID_CONDITION_DIABETES)
                .withTreatments(VALID_TREATMENT_PARACETAMOL, VALID_TREATMENT_PHYSIOTHERAPY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DOB_DESC_BOB + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB + BLOOD_TYPE_DESC_BOB;
        descriptor = new EditPatientDescriptorBuilder().withDateOfBirth(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB)
                .withWeight(VALID_WEIGHT_BOB).withBloodType(VALID_BLOOD_TYPE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetMedicalDetails_success() {
        Index targetIndex = INDEX_THIRD;

        // allergies
        String userInput = targetIndex.getOneBased() + ALLERGY_EMPTY;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withAllergies().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // conditions
        userInput = targetIndex.getOneBased() + CONDITION_EMPTY;
        descriptor = new EditPatientDescriptorBuilder().withConditions().build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // treatments
        userInput = targetIndex.getOneBased() + TREATMENT_EMPTY;
        descriptor = new EditPatientDescriptorBuilder().withTreatments().build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

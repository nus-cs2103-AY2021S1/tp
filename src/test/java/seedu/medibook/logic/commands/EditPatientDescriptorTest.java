package seedu.medibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.medibook.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_ALLERGY_SHELLFISH;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONDITION_BACK;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_TREATMENT_PHYSIOTHERAPY;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.medibook.testutil.EditPatientDescriptorBuilder;

public class EditPatientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPatientDescriptor descriptorWithSameValues = new EditCommand.EditPatientDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different ic -> returns false
        EditCommand.EditPatientDescriptor editedAmy = new EditPatientDescriptorBuilder(DESC_AMY)
                .withIc(VALID_IC_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different name -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different date of birth -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withDateOfBirth(VALID_DOB_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different height -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withHeight(VALID_HEIGHT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different weight -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withWeight(VALID_WEIGHT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different blood type -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withBloodType(VALID_BLOOD_TYPE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different allergies -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withAllergies(VALID_ALLERGY_SHELLFISH).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different conditions -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withConditions(VALID_CONDITION_BACK).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different treatments -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withTreatments(VALID_TREATMENT_PHYSIOTHERAPY).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}

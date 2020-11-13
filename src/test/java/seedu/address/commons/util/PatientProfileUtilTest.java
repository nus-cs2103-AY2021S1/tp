package seedu.address.commons.util;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Sex;
import seedu.address.testutil.Assert;

/**
 * Tests methods for PatientProfileUtil.
 */
public class PatientProfileUtilTest {

    /*
     * Tests methods that converts patient's parameters into String.
     * We have a total of 3 equivalence partitions.
     * We partitioned null values as one invalid equivalence partition (EP1).
     * Valid parameters as one valid equivalence partition (EP2).
     * Empty (but not null) values as one valid equivalence partition (EP3).
     * EP3 is only designed for parameters that can be empty.
     */

    //-------------------- Tests for convertNameToString --------------------

    // EP 1 - Null values
    @Test
    public void convertNameToString_nullName_throwsNullPointerException() {
        Name patientName = null;
        Assert.assertThrows(NullPointerException.class, "Patient's name cannot be null.", ()
            -> PatientProfileUtil.convertNameToString(patientName));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertNameToString_validNameInput_correctResult() {
        Name name = new Name("Bob the Builder");
        Assertions.assertEquals("Bob the Builder", PatientProfileUtil.convertNameToString(name));
    }

    //-------------------- Tests for convertAllergiesToString --------------------

    // EP 1 - Null values
    @Test
    public void convertAllergiesToString_nullAllergySet_throwsNullPointerException() {
        Set<Allergy> allergySet = null;
        Assert.assertThrows(NullPointerException.class, "Patient's allergies set cannot be null.", ()
            -> PatientProfileUtil.convertAllergiesToString(allergySet));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertAllergiesToString_validAllergySet_correctResult() {
        Set<Allergy> allergySet = new HashSet<>();
        allergySet.add(new Allergy("Paracetamol"));
        Assertions.assertEquals("Paracetamol; ", PatientProfileUtil.convertAllergiesToString(allergySet));
    }

    // EP 3 - Valid parameters that are empty
    @Test
    public void convertAllergiesToString_validEmptyAllergySet_correctResult() {
        Set<Allergy> allergySet = new HashSet<>();
        Assertions.assertEquals("-", PatientProfileUtil.convertAllergiesToString(allergySet));
    }

    //-------------------- Tests for convertPhoneToString --------------------

    // EP 1 - Null values
    @Test
    public void convertPhoneToString_nullPhone_throwsNullPointerException() {
        Phone patientPhone = null;
        Assert.assertThrows(NullPointerException.class, "Patient's phone cannot be null.", ()
            -> PatientProfileUtil.convertPhoneToString(patientPhone));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertPhoneToString_validPhoneInput_correctResult() {
        Phone patientPhone = new Phone("94214567");
        Assertions.assertEquals("94214567", PatientProfileUtil.convertPhoneToString(patientPhone));
    }

    //-------------------- Tests for convertEmailToString --------------------

    // EP 1 - Null values
    @Test
    public void convertEmailToString_nullEmail_throwsNullPointerException() {
        Email patientEmail = null;
        Assert.assertThrows(NullPointerException.class, "Patient's email cannot be null.", ()
            -> PatientProfileUtil.convertEmailToString(patientEmail));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertEmailToString_validEmailInput_correctResult() {
        Email patientEmail = new Email("bobthebuilder@hotmail.com");
        Assertions.assertEquals("bobthebuilder@hotmail.com",
                                PatientProfileUtil.convertEmailToString(patientEmail));
    }

    //-------------------- Tests for convertAddressToString --------------------

    // EP 1 - Null values
    @Test
    public void convertAddressToString_nullAddress_throwsNullPointerException() {
        Address patientAddress = null;
        Assert.assertThrows(NullPointerException.class, "Patient's address cannot be null.", ()
            -> PatientProfileUtil.convertAddressToString(patientAddress));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertAddressToString_validAddressInput_correctResult() {
        Address patientAddress = new Address("123 High Lane");
        Assertions.assertEquals("123 High Lane", PatientProfileUtil.convertAddressToString(patientAddress));
    }

    //-------------------- Tests for convertIcToString --------------------

    // EP 1 - Null values
    @Test
    public void convertIcToString_nullIc_throwsNullPointerException() {
        IcNumber patientIc = null;
        Assert.assertThrows(NullPointerException.class, "Patient's IC number cannot be null.", ()
            -> PatientProfileUtil.convertIcToString(patientIc));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertIcToString_validIcInput_correctResult() {
        IcNumber patientIc = new IcNumber("S9822413E");
        Assertions.assertEquals("S9822413E", PatientProfileUtil.convertIcToString(patientIc));
    }

    //-------------------- Tests for convertBloodTypeToString --------------------

    // EP 1 - Null values
    @Test
    public void convertBloodTypeToString_nullBloodType_throwsNullPointerException() {
        BloodType patientBloodType = null;
        Assert.assertThrows(NullPointerException.class, "Patient's blood type cannot be null.", ()
            -> PatientProfileUtil.convertBloodTypeToString(patientBloodType));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertBloodTypeToString_validBloodTypeInput_correctResult() {
        BloodType patientBloodType = new BloodType("A+");
        Assertions.assertEquals("A+", PatientProfileUtil.convertBloodTypeToString(patientBloodType));
    }

    //-------------------- Tests for convertSexToString --------------------

    // EP 1 - Null values
    @Test
    public void convertSexToString_nullSex_throwsNullPointerException() {
        Sex patientSex = null;
        Assert.assertThrows(NullPointerException.class, "Patient's sex cannot be null.", ()
            -> PatientProfileUtil.convertSexToString(patientSex));
    }

    // EP 2 - Valid parameters
    @Test
    public void convertSexToString_validSexInput_correctResult() {
        Sex patientSex = new Sex("F");
        Assertions.assertEquals("F", PatientProfileUtil.convertSexToString(patientSex));
    }
}


package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.patient.Sex;
import seedu.address.model.tag.ColorTag;
import seedu.address.model.visit.VisitHistory;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditCommand.EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditCommand.EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditCommand.EditPatientDescriptor descriptor) {
        this.descriptor = new EditCommand.EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setPhone(patient.getPhone());
        descriptor.setIcNumber(patient.getIcNumber());
        descriptor.setAddress(patient.getAddress());
        descriptor.setEmail(patient.getEmail());
        descriptor.setSex(patient.getSex());
        descriptor.setBloodType(patient.getBloodType());
        descriptor.setAllergies(patient.getAllergies());
        descriptor.setVisitHistory(patient.getVisitHistory());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code IcNumber} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withIcNumber(String icNumber) {
        descriptor.setIcNumber(new IcNumber(icNumber));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Profile Picture} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withProfilePicture(String profilePic) {
        ProfilePicture profilePicture = new ProfilePicture(profilePic);
        descriptor.setProfilePicture(profilePicture);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withSex(String sex) {
        descriptor.setSex(new Sex(sex));
        return this;
    }

    /**
     * Sets the {@code BloodType} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withBloodType(String bloodtype) {
        descriptor.setBloodType(new BloodType(bloodtype));
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Allergy>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withAllergies(String... allergies) {
        Set<Allergy> allergySet = Stream.of(allergies).map(Allergy::new).collect(Collectors.toSet());
        descriptor.setAllergies(allergySet);
        return this;
    }

    /**
     * Sets the {@code ColorTag} of the {@code EditPatientDescriptor} that we're building.
     */
    public EditPatientDescriptorBuilder withColorTag(String colorName) {
        ColorTag colorTag = new ColorTag(colorName);
        descriptor.setColorTag(colorTag);
        return this;
    }

    /**
     * Parses the {@code visithistory} into a {@code VisitHistory} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withVisitHistory(VisitHistory visitHistory) {
        VisitHistory visitHistory1 = new VisitHistory(visitHistory.getVisits());
        descriptor.setVisitHistory(visitHistory1);
        return this;
    }

    public EditCommand.EditPatientDescriptor build() {
        return descriptor;
    }
}

package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditCommand.EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code person}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setNric(patient.getNric());
        descriptor.setPhone(patient.getPhone());
        descriptor.setEmail(patient.getEmail());
        descriptor.setAddress(patient.getAddress());
        descriptor.setAllergies(patient.getAllergies());
        descriptor.setMedicalRecord(patient.getMedicalRecord());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withNric(String nric) {
        descriptor.setNric(new Nric(nric));
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
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Allergy> allergySet = Stream.of(tags).map(Allergy::new).collect(Collectors.toSet());
        descriptor.setAllergies(allergySet);
        return this;
    }

    /**
     * Sets the {@code MedicalRecord} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withMedicalRecord(String url) {
        descriptor.setMedicalRecord(new MedicalRecord(url));
        return this;
    }

    public EditCommand.EditPatientDescriptor build() {
        return descriptor;
    }
}

package seedu.address.testutil;

import seedu.address.logic.commands.patient.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setPhone(patient.getPhone());
        descriptor.setAge(patient.getAge());
        descriptor.setPeriodOfStay(patient.getPeriodOfStay());
        descriptor.setComment(patient.getComment());
        descriptor.setTemperature(patient.getTemperature());
    }

    /**
     * Sets the {@code name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code phone} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code age} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withAge(String age) {
        descriptor.setAge(new Age(age));
        return this;
    }

    /**
     * Sets the {@code periodOfStay} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPeriodOfStay(String periodOfStay) {
        descriptor.setPeriodOfStay(new PeriodOfStay(periodOfStay));
        return this;
    }

    /**
     * Sets the {@code comment} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withComment(String comment) {
        descriptor.setComment(new Comment(comment));
        return this;
    }

    /**
     * Sets the {@code temperature} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withTemperature(String temperature) {
        descriptor.setTemperature(new Temperature(temperature));
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }
}

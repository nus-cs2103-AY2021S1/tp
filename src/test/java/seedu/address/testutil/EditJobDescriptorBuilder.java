package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditJobCommand;
import seedu.address.logic.commands.EditJobCommand.EditJobDescriptor;
import seedu.address.model.information.Address;
import seedu.address.model.information.CompanyName;
import seedu.address.model.information.Email;
import seedu.address.model.information.Job;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Vacancy;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditJobDescriptor objects.
 */
public class EditJobDescriptorBuilder {

    private EditJobCommand.EditJobDescriptor descriptor;

    public EditJobDescriptorBuilder() {
        descriptor = new EditJobDescriptor();
    }

    public EditJobDescriptorBuilder(EditJobDescriptor descriptor) {
        this.descriptor = new EditJobDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditJobDescriptor} with fields containing {@code job}'s details
     */
    public EditJobDescriptorBuilder(Job job) {
        descriptor = new EditJobDescriptor();
        descriptor.setJobTitle(job.getJobTitle());
        descriptor.setCompanyName(job.getCompanyName());
        descriptor.setPhone(job.getPhone());
        descriptor.setEmail(job.getEmail());
        descriptor.setAddress(job.getAddress());
        descriptor.setPriority(job.getPriority());
        descriptor.setVacancy(job.getVacancy());
        descriptor.setTags(job.getTags());
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withJobTitle(String jobTitle) {
        descriptor.setJobTitle(new Name(jobTitle));
        return this;
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Sets the {@code Vacancy} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withVacancy(String vacancy) {
        descriptor.setVacancy(new Vacancy(vacancy));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditJobDescriptor}
     * that we are building.
     */
    public EditJobDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditJobDescriptor build() {
        return descriptor;
    }
}

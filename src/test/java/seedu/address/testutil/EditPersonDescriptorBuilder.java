package seedu.address.testutil;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Person;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setExperience(person.getExperience());
        descriptor.setDateOfApplication(person.getDateOfApplication());
        descriptor.setBlackListStatus(person.getBlacklistStatus());
        descriptor.setAddressOptional(person.getAddressOptional());
        descriptor.setUrlLinkOptional(person.getUrlLinkOptional());
        descriptor.setSalaryOptional(person.getSalaryOptional());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Experience} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withExperience(String experience) {
        descriptor.setExperience(new Experience(experience));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDateOfApplication(String dateOfApplication) {
        descriptor.setDateOfApplication(new Date(dateOfApplication));
        return this;
    }

    /**
     * Sets the {@code BlacklistStatus} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withBlacklistStatus(String isBlacklisted) {
        descriptor.setBlackListStatus(new BlacklistStatus(isBlacklisted));
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddressOptional(Optional.of(new Address(address)));
        return this;
    }

    /**
     * Sets the {@code urlLink} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withUrlLink(String link) {
        descriptor.setUrlLinkOptional(Optional.of(new UrlLink(link)));
        return this;
    }

    /**
     * Sets the {@code salary} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSalary(String salary) {
        descriptor.setSalaryOptional(Optional.of(new Salary(salary)));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}

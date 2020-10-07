package seedu.address.testutil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditCaseDescriptor;
import seedu.address.model.investigationcase.Address;
import seedu.address.model.investigationcase.Case;
import seedu.address.model.investigationcase.Email;
import seedu.address.model.investigationcase.Name;
import seedu.address.model.investigationcase.Phone;
import seedu.address.model.investigationcase.Status;
import seedu.address.model.investigationcase.Suspect;
import seedu.address.model.investigationcase.Victim;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditCaseDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCaseDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCommand.EditCaseDescriptor descriptor) {
        this.descriptor = new EditCommand.EditCaseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Case investigationCase) {
        descriptor = new EditCaseDescriptor();
        descriptor.setName(investigationCase.getName());
        descriptor.setPhone(investigationCase.getPhone());
        descriptor.setEmail(investigationCase.getEmail());
        descriptor.setStatus(investigationCase.getStatus());
        descriptor.setAddress(investigationCase.getAddress());
        descriptor.setSuspects(investigationCase.getSuspects());
        descriptor.setVictims(investigationCase.getVictims());
        descriptor.setTags(investigationCase.getTags());
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
     * Sets the {@code Status} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(Status.createStatus(status));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
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

    /**
     * Parses the {@code suspects} into a {@code List<Suspect>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withSuspects(String... suspect) {
        List<Suspect> suspects = Stream.of(suspect).map(string -> new Suspect(new Name(string)))
                .collect(Collectors.toList());
        descriptor.setSuspects(suspects);
        return this;
    }

    /**
     * Parses the {@code victims} into a {@code List<Victim>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withVictims(String... victims) {
        List<Victim> victim = Stream.of(victims).map(string -> new Victim(new Name(string)))
                .collect(Collectors.toList());
        descriptor.setVictims(victim);
        return this;
    }

    public EditCaseDescriptor build() {
        return descriptor;
    }
}

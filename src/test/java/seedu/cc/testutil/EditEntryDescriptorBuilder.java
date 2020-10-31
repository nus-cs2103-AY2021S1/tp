package seedu.cc.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.cc.commons.core.category.Category;
import seedu.cc.logic.commands.entrylevel.EditCommand.EditEntryDescriptor;
import seedu.cc.model.account.entry.Amount;
import seedu.cc.model.account.entry.Description;
import seedu.cc.model.account.entry.Entry;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;
import seedu.cc.model.tag.Tag;

/**
 * A utility class to help with building EditEntryDescriptor objects.
 */
public class EditEntryDescriptorBuilder {

    private EditEntryDescriptor descriptor;

    public EditEntryDescriptorBuilder() {
        descriptor = new EditEntryDescriptor();
    }

    public EditEntryDescriptorBuilder(EditEntryDescriptor descriptor) {
        this.descriptor = new EditEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEntryDescriptor} with fields containing {@code entry}'s details
     */
    public EditEntryDescriptorBuilder(Entry entry) {
        descriptor = new EditEntryDescriptor();
        descriptor.setDescription(entry.getDescription());
        descriptor.setAmount(entry.getAmount());
        if (!entry.getTags().isEmpty()) {
            descriptor.setTags(entry.getTags());
        }

        if (entry instanceof Expense) {
            descriptor.setCategory(new Category("expense"));
        } else {
            assert(entry instanceof Revenue);
            descriptor.setCategory(new Category("revenue"));
        }
    }

    /**
     * Sets the {@code category} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditEntryDescriptor} that we are building.
     */
    public EditEntryDescriptorBuilder withAmount(String amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEntryDescriptor}
     * that we are building.
     */
    public EditEntryDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEntryDescriptor build() {
        return descriptor;
    }
}

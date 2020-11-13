package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.budget.EditExpenditureCommand.EditExpenditureDescriptor;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Name;
import seedu.address.model.expenditure.Price;
import seedu.address.model.tag.Tag;

public class EditExpenditureDescriptorBuilder {

    private EditExpenditureDescriptor descriptor;

    public EditExpenditureDescriptorBuilder() {
        this.descriptor = new EditExpenditureDescriptor();
    }

    public EditExpenditureDescriptorBuilder(EditExpenditureDescriptor descriptor) {
        this.descriptor = new EditExpenditureDescriptor(descriptor);
    }

    /**
     *
     * @param expenditure
     */
    public EditExpenditureDescriptorBuilder(Expenditure expenditure) {
        this.descriptor = new EditExpenditureDescriptor();
        this.descriptor.setName(expenditure.getName());
        this.descriptor.setPrice(expenditure.getPrice());
        this.descriptor.setDate(expenditure.getCreatedOn());
        this.descriptor.setTags(expenditure.getTags());
    }

    /**
     *
     * @param name
     * @return
     */
    public EditExpenditureDescriptorBuilder withName(String name) {
        this.descriptor.setName(new Name(name));
        return this;
    }

    /**
     *
     * @param price
     * @return
     */
    public EditExpenditureDescriptorBuilder withPrice(String price) {
        this.descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     *
     * @param date
     * @return
     */
    public EditExpenditureDescriptorBuilder withDate(String date) {
        this.descriptor.setDate(new Date(date));
        return this;
    }

    /**
     *
     * @param tags
     * @return
     */
    public EditExpenditureDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        this.descriptor.setTags(tagSet);
        return this;
    }

    public EditExpenditureDescriptor build() {
        return descriptor;
    }
}

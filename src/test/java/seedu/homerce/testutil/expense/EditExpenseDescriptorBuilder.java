package seedu.homerce.testutil.expense;

import seedu.homerce.logic.commands.expense.EditExpenseCommand;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.IsFixed;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Description;
import seedu.homerce.model.util.attributes.Tag;

/**
 * A utility class to help with building EditExpenseDescriptor objects.
 */
public class EditExpenseDescriptorBuilder {

    private EditExpenseCommand.EditExpenseDescriptor descriptor;

    public EditExpenseDescriptorBuilder() {
        descriptor = new EditExpenseCommand.EditExpenseDescriptor();
    }

    public EditExpenseDescriptorBuilder(EditExpenseCommand.EditExpenseDescriptor descriptor) {
        this.descriptor = new EditExpenseCommand.EditExpenseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExpenseDescriptor} with fields containing {@code expense}'s details
     */
    public EditExpenseDescriptorBuilder(Expense expense) {
        descriptor = new EditExpenseCommand.EditExpenseDescriptor();
        descriptor.setDescription(expense.getDescription());
        descriptor.setIsFixed(expense.getIsFixed());
        descriptor.setAmount(expense.getValue());
        descriptor.setDate(expense.getDate());
        descriptor.setTag(expense.getTag());
    }

    /**
     * Sets the {@code Description} of the {@Code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withDescription(String description) {
        descriptor.setDescription((new Description(description)));
        return this;
    }

    /**
     * Sets the {@code IsFixed} of the {@Code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withIsFixed(String isFixed) {
        descriptor.setIsFixed((new IsFixed(isFixed)));
        return this;
    }

    /**
     * Sets the {@code Value} of the {@Code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withAmount(double value) {
        descriptor.setAmount((new Amount(value)));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@Code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withDate(String date) {
        descriptor.setDate((new Date(date)));
        return this;
    }

    /**
     * Sets the {@code Tag} of the {@Code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withTag(String tag) {
        descriptor.setTag((new Tag(tag)));
        return this;
    }

    public EditExpenseCommand.EditExpenseDescriptor build() {
        return descriptor;
    }
}


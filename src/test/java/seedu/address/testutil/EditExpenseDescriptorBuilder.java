package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Category;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;

public class EditExpenseDescriptorBuilder {

    private EditExpenseDescriptor descriptor;

    public EditExpenseDescriptorBuilder() {
        descriptor = new EditExpenseDescriptor();
    }

    public EditExpenseDescriptorBuilder(EditExpenseDescriptor descriptor) {
        this.descriptor = new EditExpenseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditExpenseDescriptorBuilder(Expense expense) {
        descriptor = new EditExpenseDescriptor();
        descriptor.setAmount(expense.getAmount());
        descriptor.setCategory(expense.getCategory());
        descriptor.setDate(expense.getDate());
        descriptor.setDescription(expense.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withAmount(Double amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditExpenseDescriptor build() {
        return descriptor;
    }
}

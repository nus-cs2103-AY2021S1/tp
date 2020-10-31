package seedu.address.testutil;

import seedu.address.logic.commands.ingredient.EditIngredientCommand;
import seedu.address.model.ingredient.Ingredient;

public class EditIngredientDescriptorBuilder {
    private EditIngredientCommand.EditIngredientDescriptor descriptor;

    public EditIngredientDescriptorBuilder() {
        descriptor = new EditIngredientCommand.EditIngredientDescriptor();
    }

    public EditIngredientDescriptorBuilder(EditIngredientCommand.EditIngredientDescriptor descriptor) {
        this.descriptor = new EditIngredientCommand.EditIngredientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditIngredientDescriptor} with fields containing {@code Ingredient}'s details
     */
    public EditIngredientDescriptorBuilder(Ingredient ingredient) {
        descriptor = new EditIngredientCommand.EditIngredientDescriptor();
        descriptor.setIngredient(ingredient);
    }

    /**
     * Sets the {@code Name} of the {@code EditIngredientDescriptor} that we are building.
     */
    public EditIngredientDescriptorBuilder withIngredient(Ingredient ingredient) {
        descriptor.setIngredient(ingredient);
        return this;
    }

    public EditIngredientCommand.EditIngredientDescriptor build() {
        return descriptor;
    }
}

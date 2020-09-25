package seedu.address.testutil;

/*import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;*/

import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
//import seedu.address.model.recipe.Address;
//import seedu.address.model.recipe.Email;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
//import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditRecipeDescriptor objects.
 */
public class EditRecipeDescriptorBuilder {

    private EditRecipeDescriptor descriptor;

    public EditRecipeDescriptorBuilder() {
        descriptor = new EditRecipeDescriptor();
    }

    public EditRecipeDescriptorBuilder(EditRecipeDescriptor descriptor) {
        this.descriptor = new EditRecipeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecipeDescriptor} with fields containing {@code recipe}'s details
     */
    public EditRecipeDescriptorBuilder(Recipe recipe) {
        descriptor = new EditRecipeDescriptor();
        descriptor.setName(recipe.getName());
        descriptor.setIngredient(recipe.getIngredient());
    }

    /**
     * Sets the {@code Name} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Ingredient} of the {@code EditRecipeDescriptor} that we are building.
     */
    public EditRecipeDescriptorBuilder withIngredient(String ingredients) {
        descriptor.setIngredient(new Ingredient[]{new Ingredient(ingredients)});
        return this;
    }



    public EditRecipeDescriptor build() {
        return descriptor;
    }
}

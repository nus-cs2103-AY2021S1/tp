// RecipeEditDescriptor.java

package chopchop.logic.edit;

import java.util.List;

public class RecipeEditDescriptor {

    private final List<IngredientEditDescriptor> ingredientEdits;
    private final List<StepEditDescriptor> stepEdits;
    private final List<TagEditDescriptor> tagEdits;

    /**
     * Creates a RecipeEditDescriptor to edit a recipe.
     *
     * @param ingredientEdits   the list of edit descriptors for ingredients
     * @param stepEdits         the list of edit descriptors for steps
     * @param tagEdits          the list of edit descriptors for tags
     */
    public RecipeEditDescriptor(List<IngredientEditDescriptor> ingredientEdits,
        List<StepEditDescriptor> stepEdits, List<TagEditDescriptor> tagEdits) {

        this.ingredientEdits = ingredientEdits;
        this.stepEdits = stepEdits;
        this.tagEdits = tagEdits;
    }

    public List<IngredientEditDescriptor> getIngredientEdits() {
        return this.ingredientEdits;
    }

    public List<StepEditDescriptor> getStepEdits() {
        return this.stepEdits;
    }

    public List<TagEditDescriptor> getTagEdits() {
        return this.tagEdits;
    }
}

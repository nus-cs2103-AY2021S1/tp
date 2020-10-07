package seedu.address.model.recipe;

public class PrintableRecipe extends Recipe{

    public String printableIngredientList;
    /**
     * Every field must be present and not null.
     *
     * @param id
     * @param ingredients
     * @param productId
     * @param productName
     * @param productQuantity
     * @param description
     * @param isDeleted
     */
    public PrintableRecipe(int id, IngredientList ingredients, int productId, String productName, ProductQuantity productQuantity, String description, boolean isDeleted) {
        super(id, ingredients, productId, productName, productQuantity, description, isDeleted);


    }
}

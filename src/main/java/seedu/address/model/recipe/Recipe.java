package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.recipe.EditRecipeCommand;
import seedu.address.model.ingredient.Ingredient;


/**
 * Represents a Recipe in the Wishful Shrinking.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {

    // Identity fields
    private final Name name;
    private final ArrayList<Instruction> instructions;
    private RecipeImage recipeImage;

    // Data fields
    private final ArrayList<Ingredient> ingredients;
    private final Calories calories;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Recipe(Name name, ArrayList<Instruction> instructions, RecipeImage recipeImage,
                  ArrayList<Ingredient> ingredients, Calories calories,
                  Set<Tag> tags) {
        requireAllNonNull(name, ingredients, calories, instructions, tags);
        this.name = name;
        this.instructions = instructions;
        this.recipeImage = recipeImage;
        this.ingredients = ingredients;
        this.calories = calories;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public ArrayList<Instruction> getInstruction() {
        return instructions;
    }

    public RecipeImage getRecipeImage() {
        return recipeImage;
    }

    public ArrayList<Ingredient> getIngredient() {
        return ingredients;
    }

    public Calories getCalories() {
        return calories;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Set recipe image to default image.
     */
    public void setDefaultImage() {
        this.recipeImage = new RecipeImage("images/default.jpg");
    }

    /**
     * Returns true if both recipes of the same name have the same ingredient names.
     * This defines a weaker notion of equality between two recipes.
     * This method is used to check for adding duplicate recipes in the recipe list.
     */
    public boolean isSameRecipeNameAndIngredientName(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().toString().toLowerCase().equals(getName().toString().toLowerCase())
                && isSameIngredientNames(otherRecipe.getIngredient());
    }

    private boolean isSameIngredientNames(ArrayList<Ingredient> otherIngredients) {
        ArrayList<Ingredient> ingredients = getIngredient();
        if (ingredients.size() != otherIngredients.size()) {
            return false;
        }
        ArrayList<String> ingredientsCopy = new ArrayList<String>(new ArrayList<Ingredient>(ingredients)
                                            .stream().map(ingredient -> ingredient.getValue().toLowerCase())
                                            .collect(Collectors.toList()));
        ArrayList<String> otherIngredientsCopy = new ArrayList<String>(new ArrayList<Ingredient>(otherIngredients)
                                            .stream().map(ingredient -> ingredient.getValue().toLowerCase())
                                            .collect(Collectors.toList()));

        Collections.sort(ingredientsCopy);
        Collections.sort(otherIngredientsCopy);
        return ingredientsCopy.equals(otherIngredientsCopy);
    }

    /**
     * Returns true if both recipes have the same fields.
     * This defines a weaker notion of equality between two recipes.
     * This method is used to check for editing duplicate recipes in the recipe list.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName())
                && otherRecipe.getInstruction().equals(getInstruction())
                && otherRecipe.getRecipeImage().equals(getRecipeImage())
                && isSameIngredients(otherRecipe.getIngredient())
                && otherRecipe.getCalories().equals(getCalories())
                && otherRecipe.getTags().equals(getTags());
    }

    /**
     * Returns true if both ingredient lists are the same.
     * This defines a weaker notion of equality between two ingredient lists.
     * This method is used to check for adding duplicate ingredients.
     * @return boolean
     */
    public boolean isSameIngredients(ArrayList<Ingredient> otherIngredients) {
        ArrayList<Ingredient> ingredients = getIngredient();
        if (ingredients.size() != otherIngredients.size()) {
            return false;
        }
        ArrayList<String> ingredientsCopy = new ArrayList<String>(new ArrayList<Ingredient>(ingredients)
                .stream().map(ingredient -> ingredient.toString())
                .collect(Collectors.toList()));
        ArrayList<String> otherIngredientsCopy = new ArrayList<String>(new ArrayList<Ingredient>(otherIngredients)
                .stream().map(ingredient -> ingredient.toString())
                .collect(Collectors.toList()));

        Collections.sort(ingredientsCopy);
        Collections.sort(otherIngredientsCopy);
        return ingredientsCopy.equals(otherIngredientsCopy);
    }

    /**
     * Converts a recipe to its command string.
     * @return String
     */
    public String stringify(int position) {
        String commandWord = EditRecipeCommand.COMMAND_WORD;
        String recipeName = PREFIX_NAME.toString() + name;
        String ingredients = PREFIX_INGREDIENT.toString() + stringifyIngredients(this.ingredients);
        String calories = PREFIX_CALORIES.toString() + this.calories.getValue();
        String instructions = stringifyInstructions(this.instructions);
        String image = PREFIX_RECIPE_IMAGE + this.recipeImage.getValue();
        String tags = stringifyTags(this.tags);
        return commandWord + " " + position + " " + recipeName + " " + ingredients + " " + calories
                + " " + instructions + " " + image + " " + tags;
    }

    /**
     * Converts the list of ingredients into a string, with each ingredient separated by a comma.
     * @param ingredients the list of ingredients
     * @return a string of the ingredienst
     */
    public String stringifyIngredients(ArrayList<Ingredient> ingredients) {
        int len = ingredients.size();
        String ingredientsResult = "";
        for (int i = 0; i < len; i++) {
            Ingredient ingt = ingredients.get(i);
            if (i == len - 1) {
                ingredientsResult += ingt.parseToString();
            } else {
                ingredientsResult += ingt.parseToString() + ", ";
            }
        }
        return ingredientsResult;
    }

    private String stringifyTags(Set<Tag> set) {
        int size = set.size();
        if (size == 0) {
            return "";
        } else {
            String tags = PREFIX_TAG.toString();
            for (Tag tag: set) {
                if (size == 1) {
                    tags += tag.getTagName();
                } else {
                    tags += tag.getTagName() + " " + PREFIX_TAG;
                }
                size--;
            }
            return tags;
        }
    }

    private String stringifyInstructions(ArrayList<Instruction> instructions) {
        int len = instructions.size();
        if (len == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(PREFIX_INSTRUCTION.toString());
            for (int i = 0; i < len; i++) {
                Instruction instruction = instructions.get(i);
                String instrStr = instruction.toString();
                sb.append(instrStr + ". ");
            }
            return sb.toString();
        }
    }

    /**
     * Returns true if both recipes have the same identity and data fields.
     * This defines a stronger notion of equality between two recipes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return otherRecipe.getName().toString().toLowerCase().equals(getName().toString().toLowerCase())
                && isSameIngredients(otherRecipe.getIngredient());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, instructions, recipeImage, ingredients, calories, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Ingredient: ")
                .append(ingredients.stream()
                .map(item -> item.getQuantity() + " " + item.getValue())
                .reduce("", (a, b) -> b.equals("") ? a : a.trim().equals("") ? b : b + ", " + a))
                .append(" Calories: ")
                .append(getCalories() + " cal")
                .append(" Instructions: ")
                .append(getInstruction().stream()
                        .map(item -> item.toString() + ". ")
                        .reduce("", (a, b) -> a + " " + b).trim())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}

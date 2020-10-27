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

import seedu.address.logic.commands.EditRecipeCommand;
import seedu.address.model.commons.Calories;
import seedu.address.model.tag.Tag;


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


    public void setDefaultImage() {
        this.recipeImage = new RecipeImage("images/default.jpg");
    }
    /**
     * Returns true if both recipes of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getName().equals(getName())
                && otherRecipe.getInstruction().equals(getInstruction())
                && otherRecipe.getRecipeImage().equals(getRecipeImage())
                && otherRecipe.getIngredient().equals(getIngredient())
                && otherRecipe.getCalories().equals(getCalories())
                && otherRecipe.getTags().equals(getTags());
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
        String instructions = PREFIX_INSTRUCTION.toString() + stringifyInstructions(this.instructions);
        String image = PREFIX_RECIPE_IMAGE + this.recipeImage.getValue();
        String tags = stringifyTags(this.tags);
        return commandWord + " " + position + " " + recipeName + " " + ingredients + " " + calories
                + " " + instructions + " " + image + " " + tags;
    }

    private String stringifyIngredients(ArrayList<Ingredient> ingredients) {
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

    private String stringifyInstructions(ArrayList<Instruction> instructions) {
        int len = instructions.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Instruction instruction = instructions.get(i);
            if (i == len - 1) {
                sb.append(instruction.toString());
            } else {
                sb.append(instruction.toString() + ". ");
            }
        }
        return sb.toString();
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
        return otherRecipe.getName().equals(getName())
                && otherRecipe.getInstruction().equals(getInstruction())
                && otherRecipe.getRecipeImage().equals(getRecipeImage())
                && otherRecipe.getIngredient().equals(getIngredient())
                && otherRecipe.getCalories().equals(getCalories())
                && otherRecipe.getTags().equals(getTags());
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
                        .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a))
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

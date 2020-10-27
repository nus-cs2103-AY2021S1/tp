package seedu.address.model.util;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Instruction;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeImage;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code WishfulShrinking} with sample data.
 */
public class SampleDataUtil {

    /**
     * For each recipe in openrecipes.txt:
     * index 0: name, index 1: ingredients, index 2: instructions, index 5: image, index 7: calories
     * The method will parse the sample recipes txt file and into an array of Recipe objects.
     * @return Recipe[]
     */
    public static Recipe[] getSampleRecipes() {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        Recipe[] recipes = new Recipe[]{};
        InputStream file = SampleDataUtil.class.getResourceAsStream("/recipes/openrecipes.txt");
        Scanner sc = new Scanner(file);
        int index = 0;
        Name recipeName = null;
        ArrayList<Instruction> recipeInstructions = new ArrayList<>();
        RecipeImage recipeImage = null;
        HashSet<Tag> tag = new HashSet<>();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Calories calories = null;
        while (sc.hasNextLine() && index < 11) {
            String field = sc.nextLine();
            if (index == 0) {
                recipeName = new Name(getRecipeName(field));
            } else if (index == 1) {
                ingredients = getRecipeIngredients(field);
            } else if (index == 2) {
                recipeInstructions = getRecipeInstructions(field);
            } else if (index == 3) {
                tag.add(new Tag(getTag(field)));
            } else if (index == 5) {
                recipeImage = new RecipeImage(getRecipeImage(field));
            } else if (index == 7) {
                calories = new Calories(getCalories(field));
            }
            index++;
            if (index == 11) { //end of a recipe object
                index = 0;
                requireAllNonNull(recipeName, recipeInstructions, recipeImage, ingredients,
                        calories, tag);
                Recipe toAdd = new Recipe(recipeName, recipeInstructions, recipeImage, ingredients,
                        calories, tag);
                recipeList.add(toAdd);
            }
        }
        if (recipeList.size() == 0) {
            recipeList.add(getFallbackRecipe());
        }
        recipes = recipeList.toArray(new Recipe[]{});
        return recipes;
    }

    private static String getRecipeName(String str) {
        return str.substring(10, str.length() - 2);
    }

    private static ArrayList<Instruction> getRecipeInstructions(String instruction) {
        String instrString = instruction.substring(17, instruction.length() - 2);
        String[] instructions = instrString.split("\\.");
        ArrayList<Instruction> instructionList = new ArrayList<>();
        int index = 1;
        for (String instr : instructions) {
            instructionList.add(new Instruction((index) + ") " + instr.trim()));
            index = index + 1;
        }
        return instructionList;
    }

    private static String getTag(String str) {
        return str.substring(8, str.length() - 3);
    }

    private static ArrayList<Ingredient> getRecipeIngredients(String str) {
        String ingts = str.substring(16, str.length() - 2);
        String[] ingredients = ingts.split(", ");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        for (String ingredient: ingredients) {
            ingredientList.add(getIngredientObject(ingredient));
        }
        return ingredientList;
    }

    private static Ingredient getIngredientObject(String ingredient) {
        String[] ingtComponents = ingredient.split(" ");
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(ingtComponents[0] + " ");
            sb.append(ingtComponents[1] + " ");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("For sample recipes, all ingredients should have quantity field " + e.toString());
        }
        String quantity = sb.toString();
        sb = new StringBuilder();
        for (int i = 2; i < ingtComponents.length; i++) {
            sb.append(ingtComponents[i] + " ");
        }
        String ingredientValue = sb.toString();
        return new Ingredient(ingredientValue, quantity);
    }

    private static String getRecipeImage(String str) {
        return str.substring(10, str.length() - 2);
    }

    private static int getCalories(String str) {
        String calorie = str.substring(13, str.length() - 3);
        try {
            return Integer.parseInt(calorie);
        } catch (NumberFormatException e) {
            System.out.println("Calorie string cannot be converted to int " + e.toString());
        }
        return 0;
    }

    private static Recipe getFallbackRecipe() {
        Name name = new Name("Tahini cake");
        Instruction instruction1 = new Instruction("1) Heat oven");
        Instruction instruction2 = new Instruction("2) Add flour");
        ArrayList<Instruction> instructionList = new ArrayList<>();
        instructionList.add(instruction1);
        instructionList.add(instruction2);
        RecipeImage recipeImage =
                new RecipeImage("https://i.guim.co.uk/img/media/0a07b58d3e8a5c67901c90c7b3b25885095597e6"
                + "/84_2248_5678_6000/master/5678.jpg?width=620&quality=85&auto=format&fit=max&s=b20e33f"
                + "7054827278dbd2b9d8a2e7616");
        Ingredient ingredient1 = new Ingredient("unsalted butter", "210g");
        Ingredient ingredient2 = new Ingredient("flour", "2 cups");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        Calories calories = new Calories(100);
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("healthy"));
        return new Recipe(name, instructionList, recipeImage, ingredientList, calories, tags);
    }

    public static ReadOnlyWishfulShrinking getSampleWishfulShrinking() {
        WishfulShrinking sampleAb = new WishfulShrinking();
        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleAb.addRecipe(sampleRecipe);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}

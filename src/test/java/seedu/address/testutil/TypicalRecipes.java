package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUCTION_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUCTION_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_IMAGE_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_IMAGE_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MARGARITAS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Recipe;

/**
 * A utility class containing a list of {@code Recipe} objects to be used in tests.
 */
public class TypicalRecipes {

    public static final Recipe SANDWICH = new RecipeBuilder().withName("Sandwich")
            .withIngredient("Kaiser Rolls Or Other Bread", "2 whole")
            .withCalories(70)
            .withInstruction(
                    "Make egg salad by chopping the hard boiled eggs and mixing in a bowl with mayonnaise, Dijon. "
                    + "Halve the rolls and spread one half with Dijon, the other half with mayonnaise."
                    + "Sprinkle the mayonnaise-spread half with salt and pepper. "
                    + "Lay cheese and ham on the mustard half; "
                    + "lay avocado, onion slices, tomato slices, egg salad, and lettuce on the other half.")
            .withRecipeImage("images/sandwich.jpeg")
            .withTags("healthy")
            .build();

    public static final Recipe PASTA = new RecipeBuilder().withName("Pasta")
            .withIngredient("Pasta", "12 ounces")
            .withCalories(80)
            .withInstruction(
                    "Cook pasta until al dente. "
                    + "Add basil leaves, 1/2 cup Parmesan, pine nuts, and salt and pepper to food "
                    + "processor or blender."
                    + " Turn machine on, then drizzle in olive oil while it mixes. "
                    + "Continue blending until combined, adding additional olive oil if needed. Set aside. "
                    + "Heat cream and butter in a small saucepan over medium-low heat. Add pesto and stir. "
                    + "Drain pasta and place in a serving bowl. Pour pesto cream over the top. "
                    + "Toss to combine, add diced tomatoes and toss quickly. "
                    + "Serve immediately.")
            .withRecipeImage("images/pesto1.jpg")
            .withTags("healthy")
            .build();

    public static final Recipe PORK = new RecipeBuilder().withName("Roasted Pork")
            .withIngredient("Pork Tenderloins", "2 whole")
            .withCalories(102)
            .withInstruction(
                    "Preheat oven to 350 degrees. "
                    + "In a small bowl, combine garlic, basil, oregano, thyme, parsley, and sage."
                    + "Generously season meat with salt and pepper. "
                    + "Add to pan, and cook on all sides until dark golden brown. "
                    + "Wrap in foil, bake until meat is 150 degrees internally at the widest,"
                    + " thickest part of the tenderloin (about 25 minutes). "
                    + "When pork has come to temperature, remove and let rest, "
                    + "tented with foil, for at least five minutes to lock in juices.")
            .withRecipeImage("images/pork.jpeg")
            .withTags("healthy")
            .build();

    public static final Recipe FLORENTINE = new RecipeBuilder().withName("Florentine Pasta")
            .withIngredient("Penne", "1 pound ")
            .withCalories(100)
            .withInstruction(
                    "Cook pasta according to package directions in lightly salted water. "
                    + "Cut chicken breasts into chunks and sprinkle on salt and pepper."
                    + "Heat butter and olive oil over high heat in a large skillet. "
                    + "Add chicken chunks in a single layer and do not stir for a minute or "
                    + "two in order to allow the chicken to brown on the first side. "
                    + "Turn the chicken and brown on the other side. "
                    + "Cook until done, then remove chicken from the skillet. "
                    + "Add garlic and quickly stir to avoid burning. "
                    + "After about 30 seconds, pour in wine and broth, stirring to deglaze the pan. "
                    + "Allow the liquid to bubble up, then continue cooking until it's reduced by at least half "
                    + "(most of the surface of the liquid should be bubbling at this point). "
                    + "Turn off the heat. "
                    + "Add spinach, tomatoes, chicken, and cooked pasta to the skillet. "
                    + "Toss to combine; the spinach will wilt as you toss everything. "
                    + "Add plenty of Parmesan shavings and toss to combine.")
            .withRecipeImage("images/florentine1.jpeg")
            .withTags("healthy")
            .build();

    public static final Recipe ENCHILADAS = new RecipeBuilder().withName("Enchiladas")
            .withIngredient("Green Enchilada Sauce", "15 Ounce")
            .withCalories(40)
            .withInstruction(
                    "In a small skillet, saute onions with the butter over medium-low heat until "
                    + "the onions are nice and golden brown."
                    + "In a small saucepan, combine green enchilada sauce with green chilies "
                    + "and heat until very warm."
                    + "In the oven (on a baking sheet) or microwave, melt cheese all over "
                    + "the top of each tortilla so that it covers most of the surface area.")
            .withRecipeImage("images/enchilada1.jpeg")
            .withTags("healthy", "low calories")
            .build();

    public static final Recipe EGGS = new RecipeBuilder().withName("Easter Eggs")
            .withIngredient("Small Chocolate Easter Eggs", "20 pieces")
            .withCalories(102)
            .withInstruction(
                    "In a large saucepan, melt butter over low heat. " 
                            + "Add marshmallows and stir until melted."
                            + "Remove from heat, then add rice cereal and stir until well coated. "
                            + "Lightly spray interior of the plastic eggs with non-stick cooking spray. "
                            + "If mixture is too sticky, you can also spray your hands. "
                            + "Fill both sides of the plastic egg with rice cereal mixture, "
                            + "slightly over-filling one side. "
                            + "Press chocolate egg in the center on one side of the egg, "
                            + "then close the plastic egg to shape it. "
                            + "(It should be full enough to meet with a little resistance as you close it.) "
                            + "Gently release the rice cereal egg from the mold, decorate with your choice of sprinkles"
                            + " and set aside in egg crate until set.")
            .withRecipeImage("images/easter.jpeg")
            .withTags("healthy")
            .build();

    public static final Recipe PATTY = new RecipeBuilder().withName("Patty Melts")
            .withIngredient("Rye Bread", "8 slices")
            .withCalories(40)
            .withInstruction(
                    "In a medium skillet, melt 2 tablespoons of butter over medium-low heat. "
                    + "Throw in the sliced onions and cook slowly for 20 to 25 minutes, stirring occasionally,"
                            + " until the onions are golden brown and soft."
                            + "In a medium bowl, mix together the ground beef, salt & pepper, and Worcestershire. "
                            + "Form into 4 patties. Melt 2 tablespoons butter in a separate skillet over medium heat. "
                            + "Cook the patties on both sides until totally done in the middle. "
                            + "Assemble patty melts.")
            .withRecipeImage("images/party.jpeg")
            .withTags("healthy", "low calories")
            .build();

    // Manually added
    public static final Recipe BURGER = new RecipeBuilder().withName("Burger")
            .withIngredient("Everything Bagels", "2 whole")
            .withCalories(80)
            .withInstruction(
                    "Melt butter in a skillet over medium heat and "
                            + "grill the cut side of the bagels until golden brown and slightly crisp. "
                            + "Set aside. Stir together the goat cheese (or cream cheese) and the pesto until smooth. "
                            + "Add more pesto if you'd like! Combine  turkey, salt, pepper, Worcestershire sauce, "
                                    + "hot sauce, and egg yolk (if using) in a large bowl. "
                            + "Stir or  knead together with your hands, then form into patties. "
                            + "Heat canola oil and 1 tablespoon of butter in a skillet over medium-high heat. "
                            + "Cook the patties on both sides until totally done, with no sign of pink in the middle, "
                                    + "at least 4-5 minutes per side. "
                            + "To serve, spread a good amount of the pesto spread on each bagel half. "
                            + "Place the burgers on the bottom half, then top with tomato slices and basil leaves."
                            + " Lay avocado slices on the top half, using the spread to make them stay put. "
                            + "Sprinkle a little salt and pepper on the avocados, "
                                    + "then place the top half on the bottom half, smush it together...and dig in!")
            .withRecipeImage("images/burger1.jpg")
            .withTags("healthy")
            .build();

    public static final Recipe SOUP = new RecipeBuilder().withName("Soup")
            .withIngredient("Low Sodium Chicken Broth", "6 cups")
            .withCalories(102)
            .withInstruction(
                    "Heat olive oil in a large pot over medium high heat. "
                            + "Add onions and garlic and stir to cook for 3 to 4 minutes. "
                            + "Add tomato paste and stir to cook about 2 minutes, "
                            + "getting lots of flavor in the bottom of the pan. "
                            + "Stir in red pepper flakes and dried oregano; stir and cook for another 30 seconds. "
                            + "Pour in wine and whisk, scraping the bottom of the pan a bit as you go. "
                            + "Allow this to bubble up and reduce,  cooking until the wine is reduced by half. "
                            + "Add canned tomatoes. Drain and rinse beans, then add to pot. Pour in chicken broth. "
                            + "Stir to combine and allow it to simmer for 20-25 minutes. "
                            + "Sprinkle in salt and pepper to taste. "
                            + "While the soup is simmering, "
                                    + "cook the shrimp: melt butter in a small skillet over medium-high (or high) heat."
                            + " Throw in the shrimp and chopped parsley and toss to cook until no longer opaque,"
                                    + "about 4 minutes. "
                            + "Season with salt. "
                            + "Set aside. Rinse the kale and tear it into chunks. Stir into soup. "
                            + "(It'll shrink quite a bit, so don't be afraid when you first add it!) "
                            + "Add a bunch of torn basil and stir. "
                            + "Allow the soup to simmer for another 5 minutes, "
                                    + "tasting and adding salt and pepper (or even more red pepper flakes) as needed. "
                            + "Transfer soup to a large serving bowl and top with the shrimp, "
                                    + "or ladle into individual bowls and top with 3 or 4 shrimp.")
            .withRecipeImage("images/tuscan1")
            .withTags("healthy")
            .build();

    // Manually added - Recipe's details found in {@code CommandTestUtil}
    public static final Recipe NOODLE = new RecipeBuilder().withName(VALID_NAME_NOODLE)
            .withIngredient(VALID_INGREDIENT_NOODLE, VALID_QUANTITY_NOODLE)
           .withCalories(VALID_CALORIES_NOODLE).withInstruction(VALID_INSTRUCTION_NOODLE)
            .withRecipeImage(VALID_RECIPE_IMAGE_NOODLE).build();
    public static final Recipe MARGARITAS = new RecipeBuilder().withName(VALID_NAME_MARGARITAS)
            .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
            .withCalories(VALID_CALORIES_MARGARITAS).withInstruction(VALID_INSTRUCTION_MARGARITAS)
            .withRecipeImage(VALID_RECIPE_IMAGE_MARGARITAS)
            .withTags(VALID_TAG_MARGARITAS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRecipes() {} // prevents instantiation

    /**
     * Returns an {@code WishfulShrinking} with all the typical recipes.
     */
    public static WishfulShrinking getTypicalWishfulShrinking() {
        WishfulShrinking ab = new WishfulShrinking();
        for (Recipe recipe : getTypicalRecipes()) {
            ab.addRecipe(recipe);
        }
        return ab;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(SANDWICH, PASTA, PORK, FLORENTINE, ENCHILADAS, EGGS, PATTY));
    }
}

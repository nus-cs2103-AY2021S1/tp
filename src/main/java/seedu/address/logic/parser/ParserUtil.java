package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ingredient.EditIngredientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Calories;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.RecipeImage;
import seedu.address.model.recipe.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String VALIDATION_REGEX = "[\\w\\s-]+"
            + "(,\\s*[\\w\\s-]*)*";
    public static final String VALIDATION_REGEX_QUANTITY = "[\\w\\s\\/\\.]*";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String ingredient} into a {@code Ingredient}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ingredients} is invalid.
     */
    public static String parseIngredient(String ingredients) throws ParseException {
        requireNonNull(ingredients);
        String trimmedIngredient = ingredients.trim();
        String ingName = trimmedIngredient;
        if (trimmedIngredient.equals("")) {
            throw new ParseException(Ingredient.MESSAGE_CONSTRAINTS);
        }
        if (trimmedIngredient.indexOf(",") != -1) {
            throw new ParseException(EditIngredientCommand.MESSAGE_ONE_INGREDIENT);
        }
        String ingQuantity = "";
        int indexOfDash = trimmedIngredient.indexOf("-");
        if (indexOfDash != -1) {
            ingName = trimmedIngredient.substring(0, indexOfDash).trim();
            ingQuantity = trimmedIngredient.substring(indexOfDash + 1).trim();
        }
        if ((ingName != "" && !ingName.matches(VALIDATION_REGEX))) {
            throw new ParseException(Ingredient.MESSAGE_CONSTRAINTS);
        }

        if (ingQuantity != "") {
            parseQuantity(ingQuantity);
        }
        return trimmedIngredient;
    }

    /**
     * Parses a {@code String ingredients} into a {@code Ingredient}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ingredients} is invalid.
     */
    public static String parseIngredients(String ingredients) throws ParseException {
        requireNonNull(ingredients);
        String[] ingredientsArr = ingredients.split(",");
        if (ingredientsArr.length == 0) {
            throw new ParseException(Ingredient.MESSAGE_CONSTRAINTS);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ingredientsArr.length; i++) {
            String trimmedIngredient = parseIngredient(ingredientsArr[i]);
            if (i != ingredientsArr.length - 1) {
                sb.append(trimmedIngredient + ", ");
            } else {
                sb.append(trimmedIngredient);
            }
        }
        return sb.toString();
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static String parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!trimmedQuantity.matches(VALIDATION_REGEX_QUANTITY)) {
            throw new ParseException(Ingredient.MESSAGE_CONSTRAINTS);
        }
        if (!Ingredient.isValidQuantity(quantity)) {
            throw new ParseException(Ingredient.QUANTITY_CONSTRAINTS);
        }
        trimmedQuantity = Ingredient.removeLeadingZeroesFromNumber(trimmedQuantity);
        return trimmedQuantity;
    }

    /**
     * Parses a {@code String calories} into an {@code Calories}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code calories} is invalid.
     */
    public static Calories parseCalories(String calories) throws ParseException {
        requireNonNull(calories);
        String trimmedCalories = calories.trim();
        if (!Calories.isValidCalories(trimmedCalories)) {
            throw new ParseException(Calories.MESSAGE_CONSTRAINTS);
        }
        return new Calories(Integer.parseInt(trimmedCalories));
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            if (!tagName.equals("")) {
                tagSet.add(parseTag(tagName));
            }
        }
        return tagSet;
    }

    /**
     *  Parses a {@code String imgPath} into a {@code RecipeImage}.
     *  Leading and trailing whitespaces will be trimmed.
     *
     *  @throws ParseException if the given {@code RecipeImage} is invalid.
     */
    public static RecipeImage parseImage(String imgPath) throws ParseException {
        requireNonNull(imgPath);
        String trimmedPath = imgPath.trim();
        if (!RecipeImage.isValidImage(trimmedPath)) {
            throw new ParseException(RecipeImage.MESSAGE_CONSTRAINTS);
        }
        return new RecipeImage(trimmedPath);
    }

}

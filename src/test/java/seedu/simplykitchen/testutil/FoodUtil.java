package seedu.simplykitchen.testutil;

import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;

import java.util.Set;

import seedu.simplykitchen.logic.commands.AddCommand;
import seedu.simplykitchen.logic.commands.ChangeQuantityCommand;
import seedu.simplykitchen.logic.commands.EditCommand;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.tag.Tag;

/**
 * A utility class for Food.
 */
public class FoodUtil {

    /**
     * Returns an add command string for adding the {@code food}.
     */
    public static String getAddCommand(Food food) {
        return AddCommand.COMMAND_WORD + " " + getFoodDetails(food);
    }

    /**
     * Returns a change quantity command for changing the quantity of a food item by {@code amount} unit.
     */
    public static String getChangeQuantityCommand(double amount) {
        StringBuilder sb = new StringBuilder();
        sb.append(ChangeQuantityCommand.COMMAND_WORD + " ");
        sb.append(INDEX_FIRST_FOOD.getOneBased() + " ");
        sb.append(PREFIX_AMOUNT + "+1.00");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code food}'s details.
     */
    public static String getFoodDetails(Food food) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCRIPTION + food.getDescription().fullDescription + " ");
        sb.append(PREFIX_PRIORITY + food.getPriority().toString() + " ");
        sb.append(PREFIX_EXPIRY_DATE + food.getExpiryDate().value + " ");
        sb.append(PREFIX_QUANTITY + food.getQuantity().toString() + " ");
        food.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFoodDescriptor}'s details.
     */
    public static String getEditFoodDescriptorDetails(EditCommand.EditFoodDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.fullDescription).append(" "));
        descriptor.getPriority().ifPresent(priority -> sb.append(PREFIX_PRIORITY)
                .append(priority.toString()).append(" "));
        descriptor.getExpiryDate().ifPresent(expiryDate -> sb.append(PREFIX_EXPIRY_DATE)
                .append(expiryDate.value).append(" "));
        descriptor.getQuantity().ifPresent(quantity -> sb.append(PREFIX_QUANTITY)
                .append(quantity.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

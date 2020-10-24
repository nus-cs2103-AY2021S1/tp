package seedu.address.model.food;

import java.util.function.Predicate;

import seedu.address.logic.commands.enums.Inequality;

public class PriceWithinRangePredicate implements Predicate<Food> {
    private final double price;
    private final Inequality inequality;

    /**
     * Creates a predicate, taking in an inequality and a price. Used to compare the price of a food item to the price
     * with the specified inequality.
     */
    public PriceWithinRangePredicate(Inequality inequality, double price) {
        this.inequality = inequality;
        this.price = price;
    }

    @Override
    public boolean test(Food food) {
        switch (inequality) {
        case LESSER_THAN:
            return food.getPrice() < price;
        case LESSER_THAN_OR_EQUAL_TO:
            return food.getPrice() <= price;
        case GREATER_THAN:
            return food.getPrice() > price;
        case GREATER_THAN_OR_EQUAL_TO:
            return food.getPrice() >= price;
        default:
            assert (false);
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", inequality.toString(), price);
    }
}

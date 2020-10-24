package seedu.address.model.food;

import seedu.address.logic.commands.enums.Inequality;

import java.util.function.Predicate;

public class PriceWithinRangePredicate implements Predicate<Food> {
    private final double price;
    private final Inequality inequality;

    public PriceWithinRangePredicate(Inequality inequality, double price) {
        this.inequality = inequality;
        this.price = price;
    }

    @Override
    public boolean test(Food food) {
        switch (inequality){
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
}

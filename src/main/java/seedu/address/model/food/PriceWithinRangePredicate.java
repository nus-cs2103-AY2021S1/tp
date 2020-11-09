package seedu.address.model.food;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.exceptions.ParseException;

public class PriceWithinRangePredicate implements Predicate<MenuItem> {
    private final double price;
    private final Inequality inequality;

    /**
     * Creates a predicate, taking in an inequality and a price. Used to compare the price of a food item to the price
     * with the specified inequality.
     */
    public PriceWithinRangePredicate(Inequality inequality, double price) throws ParseException {
        this.inequality = inequality;
        if (price < 0) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_PRICE, price));
        }
        if (price > 1000) {
            throw new ParseException(String.format(Messages.MESSAGE_PRICE_GREATER_THAN_LIMIT, price));
        }
        this.price = price;
    }

    @Override
    public boolean test(MenuItem item) {
        switch (inequality) {
        case LESSER_THAN:
            return item.getPrice() < price;
        case LESSER_THAN_OR_EQUAL_TO:
            return item.getPrice() <= price;
        case GREATER_THAN:
            return item.getPrice() > price;
        case GREATER_THAN_OR_EQUAL_TO:
            return item.getPrice() >= price;
        default:
            assert (false);
            return false;
        }
    }

    @Override
    public boolean equals (Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceWithinRangePredicate // instanceof handles nulls
                && price == ((PriceWithinRangePredicate) other).price
                && inequality.equals(((PriceWithinRangePredicate) other).inequality)); // state check
    }
    @Override
    public String toString() {
        return String.format("%s $%.2f", inequality.toString(), price);
    }
}

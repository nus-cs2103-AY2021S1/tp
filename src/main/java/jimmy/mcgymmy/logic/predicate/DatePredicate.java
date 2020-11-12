package jimmy.mcgymmy.logic.predicate;

import java.util.function.Predicate;
import java.util.logging.Logger;

import jimmy.mcgymmy.commons.core.LogsCenter;
import jimmy.mcgymmy.logic.parser.ParserUtil;
import jimmy.mcgymmy.logic.parser.exceptions.ParseException;
import jimmy.mcgymmy.model.date.Date;
import jimmy.mcgymmy.model.food.Food;

/**
 * Tests that a {@code Food}'s {@code Date} matches the {@code String} date given.
 */
public class DatePredicate implements Predicate<Food> {
    private static final Logger logger = LogsCenter.getLogger(DatePredicate.class);
    private final Date date;

    /**
     * Initialises {@code DatePredicate} object.
     */
    public DatePredicate(String date) throws ParseException {
        this.date = ParserUtil.parseDate(date);
    }

    /**
     * Checks if the food contains the same date as the input (format insensitive)
     *
     * @param food The food to be checked
     * @return True if Food contains the date equal to the input date, false otherwise
     */
    @Override
    public boolean test(Food food) {
        return this.date.equals(food.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DatePredicate // instanceof handles nulls
                && date.equals(((DatePredicate) other).date)); // state check
    }

}

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
    private final Date date;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Initialises {@code DatePredicate} object.
     */
    public DatePredicate(String date) {
        Date date1 = null;
        try {
            Date parsedDate = ParserUtil.parseDate(date);
            date1 = parsedDate;
        } catch (ParseException e) {
            logger.info("Invalid date: " + date);
        }
        this.date = date1;
    }

    /**
     * Check if the food contains the same date as the input (format insensitive)
     * @param food The food to be checked
     * @return True if Food contains the date equal to the input date, false otherwise
     */
    @Override
    public boolean test(Food food) {
        return this.date == food.getDate();
    }
}

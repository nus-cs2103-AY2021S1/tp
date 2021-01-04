package seedu.homerce.model.revenue.predicate;

import java.time.Month;
import java.time.Year;
import java.util.function.Predicate;

import seedu.homerce.model.revenue.Revenue;

/**
 * Tests that a {@code Revenue}'s {@code Date} matches the given month and year.
 */
public class RevenueMonthYearPredicate implements Predicate<Revenue> {
    private final Month month;
    private final Year year;

    /**
     * Constructs a Revenue Month Year Predicate.
     * @param month month to search.
     * @param year year to search.
     */
    public RevenueMonthYearPredicate(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean test(Revenue revenue) {
        Month revenueMonth = revenue.getDate().getMonth();
        int revenueYear = revenue.getDate().getYear();
        return month.equals(revenueMonth) && year.getValue() == revenueYear;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RevenueMonthYearPredicate // instanceof handles nulls
                && month.equals(((RevenueMonthYearPredicate) other).month)
                && year.equals(((RevenueMonthYearPredicate) other).year)); // state check
    }
}

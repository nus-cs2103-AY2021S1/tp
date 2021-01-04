package seedu.homerce.testutil.revenue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;

/**
 * A utility class containing a list of {@code Revenue} objects to be used in tests.
 */
public class TypicalRevenues {

    public static final Revenue LASH_LIFT = new RevenueBuilder()
        .withService(new Service(new Title("Lash Lift"), new Duration(5.0), new Amount(5.00)), "SC999")
        .withDate("25-10-2020").build();

    public static final Revenue HAIR_TREATMENT = new RevenueBuilder()
        .withService(new Service(new Title("Hair Treatment"), new Duration(6.0), new Amount(6.00)), "SC998")
        .withDate("26-10-2020").build();

    public static final Revenue MANICURE = new RevenueBuilder()
        .withService(new Service(new Title("Manicure"), new Duration(7.0), new Amount(7.00)), "SC997")
        .withDate("27-10-2020").build();

    private TypicalRevenues() {
    } // prevents instantiation

    /**
     * Returns an {@code RevenueTracker} with all the typical revenues.
     */
    public static RevenueTracker getTypicalRevenueTracker() {
        RevenueTracker revenueTracker = new RevenueTracker();
        for (Revenue revenue : getTypicalRevenues()) {
            revenueTracker.addRevenue(revenue);
        }
        return revenueTracker;
    }

    public static List<Revenue> getTypicalRevenues() {
        return new ArrayList<>(Arrays.asList(LASH_LIFT, HAIR_TREATMENT, MANICURE));
    }

}

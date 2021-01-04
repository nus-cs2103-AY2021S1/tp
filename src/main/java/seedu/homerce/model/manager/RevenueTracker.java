package seedu.homerce.model.manager;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.revenue.RevenueComparator;
import seedu.homerce.model.revenue.RevenueDefaultComparator;
import seedu.homerce.model.util.nonuniquelist.NonUniqueList;

/**
 * Wraps all data at the RevenueTracker level
 */
public class RevenueTracker implements ReadOnlyRevenueTracker {

    private final NonUniqueList<Revenue> revenues;
    private final RevenueDefaultComparator revenueDefaultComparator = new RevenueDefaultComparator();
    private final Logger logger;

    /**
     * Revenue Tracker Constructor
     */
    public RevenueTracker() {
        this.revenues = new NonUniqueList<>();
        this.logger = Logger.getLogger("Revenue Tracker");
    }

    /**
     * Creates an RevenueTracker using the Revenue in the {@code toBeCopied}
     */
    public RevenueTracker(ReadOnlyRevenueTracker toBeCopied) {
        this.revenues = new NonUniqueList<>();
        this.logger = Logger.getLogger("Revenue Tracker");
        resetData(toBeCopied);
    }

    /**
     * Sorts the revenue list by value.
     *
     * @param isAscending
     */
    @Override
    public void sortRevenueList(boolean isAscending) {
        if (isAscending) {
            revenues.sort(new RevenueComparator());
        } else {
            revenues.sort(new RevenueComparator().reversed());
        }
        logger.info("Revenue List is sorted in ascending order by value: " + isAscending);
    }

    /**
     * Sorts the revenue list by date (default).
     */
    @Override
    public void sortDefaultRevenueList() {
        revenues.sort(new RevenueDefaultComparator());
        logger.info("Revenue List is sorted in descending chronological order");
    }

    //// list overwrite operations

    /**
     * Resets the existing data of this {@code RevenueTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyRevenueTracker newData) {
        requireNonNull(newData);
        setRevenues(newData.getRevenueList());
    }

    /**
     * Replaces the contents of the revenue list with {@code revenues}.
     * {@code services} can contain duplicate revenues.
     */
    public void setRevenues(List<Revenue> revenues) {
        this.revenues.setItems(revenues);
        logger.info("Revenue List is updated to new list");
    }

    //// service-level operations

    /**
     * Adds a revenues to the Homerce.
     */
    public void addRevenue(Revenue r) {
        revenues.add(r);
        logger.info("Added revenue entry to Revenue List");
        FXCollections.sort(revenues.getInternalList(), revenueDefaultComparator);
    }


    /**
     * Removes {@code key} from this {@code ServiceManager}.
     * {@code key} must exist in the Homerce.
     */
    public void removeRevenue(Revenue key) {
        assert key.getClass().equals(Revenue.class);
        revenues.remove(key);
        logger.info("Remove revenue entry tin Revenue List");
    }

    //// util methods

    @Override
    public String toString() {
        return "Revenue Tracker:\n"
                + revenues.stream().map(Revenue::toString).collect(Collectors.joining("\n"))
                + "\n Total number of revenues: " + revenues.size();

    }

    @Override
    public ObservableList<Revenue> getRevenueList() {
        return revenues.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RevenueTracker // instanceof handles nulls
                && revenues.equals(((RevenueTracker) other).revenues));
    }

    @Override
    public int hashCode() {
        return Objects.hash(revenues);
    }

    /**
     * Creates a deep copy of all the revenues in the list of revenues.
     *
     * @return a list of revenues.
     */
    public RevenueTracker deepCopy() {
        List<Revenue> internalListCopy = revenues.deepCopy();
        RevenueTracker revenueTrackerCopy = new RevenueTracker();
        revenueTrackerCopy.setRevenues(internalListCopy);
        return revenueTrackerCopy;
    }
}

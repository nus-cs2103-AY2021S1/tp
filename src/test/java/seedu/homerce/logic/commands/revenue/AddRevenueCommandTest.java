package seedu.homerce.logic.commands.revenue;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;
import seedu.homerce.testutil.ModelStub;
import seedu.homerce.testutil.revenue.RevenueBuilder;

public class AddRevenueCommandTest {

    @Test
    public void constructor_nullRevenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRevenueCommand(null));
    }

    @Test
    public void constructor_nullModel_throwsNullPointerException() {
        Revenue revenue = new RevenueBuilder()
            .withService(new Service(new Title("Cut Hair"), new Duration(8.0), new Amount(8.00)), "SC995")
            .withDate("28-10-2020").build();
        AddRevenueCommand addRevenueCommand = new AddRevenueCommand(revenue);
        assertThrows(NullPointerException.class, () -> addRevenueCommand.execute(null, null));
    }

    @Test
    public void execute_revenueAcceptedByModel_addSuccessful() {
        ModelStubAcceptingRevenueAdded modelStub = new ModelStubAcceptingRevenueAdded();
        Revenue validRevenue = new RevenueBuilder()
            .withService(new Service(new Title("Cut Hair"), new Duration(8.0), new Amount(8.00)), "SC995")
            .withDate("28-10-2020").build();

        CommandResult commandResult = new AddRevenueCommand(validRevenue).execute(modelStub,
            HistoryManager.getInstance());

        assertEquals(String.format(AddRevenueCommand.MESSAGE_ADD_REVENUE_SUCCESS, validRevenue), commandResult
            .getFeedbackToUser());
        assertEquals(Arrays.asList(validRevenue), modelStub.revenuesAdded);
    }

    @Test
    public void equals() {
        Revenue firstRevenue = new RevenueBuilder()
            .withService(new Service(new Title("Cut Hair"), new Duration(8.0), new Amount(8.00)), "SC995")
            .withDate("28-10-2020").build();

        Revenue secondRevenue = new RevenueBuilder()
            .withService(new Service(new Title("Shave Browns"), new Duration(9.0), new Amount(9.00)), "SC995")
            .withDate("29-10-2020").build();

        AddRevenueCommand addRevenueCommandFirst = new AddRevenueCommand(firstRevenue);
        AddRevenueCommand addRevenueCommandSecond = new AddRevenueCommand(secondRevenue);

        // same object -> returns true
        assertTrue(addRevenueCommandFirst.equals(addRevenueCommandFirst));

        // different types -> return false
        assertFalse(addRevenueCommandFirst.equals(1));

        // null -> returns false
        assertFalse(addRevenueCommandFirst.equals(null));

        // different expense -> returns false
        assertFalse(addRevenueCommandFirst.equals(addRevenueCommandSecond));
    }

    /**
     * A Model stub that always accepts the revenue being added.
     */
    private static class ModelStubAcceptingRevenueAdded extends ModelStub {

        final ArrayList<Revenue> revenuesAdded = new ArrayList<>();

        @Override
        public void addRevenue(Revenue revenue) {
            requireNonNull(revenue);
            revenuesAdded.add(revenue);
        }

        @Override
        public ReadOnlyRevenueTracker getRevenueTracker() {
            return new RevenueTracker();
        }
    }
}

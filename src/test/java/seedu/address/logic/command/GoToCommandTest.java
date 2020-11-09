package seedu.address.logic.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX;
import static seedu.address.logic.command.GoToCommand.TRAVEL_PLAN;
import static seedu.address.logic.command.GoToCommand.WISHLIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalTravelPlans.AUSTRALIA_TRIP;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.add.ModelStub;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.travelplan.TravelPlan;
import seedu.address.testutil.typicals.TypicalIndexes;

public class GoToCommandTest {
    @Test
    public void execute_goToTravelPlan_success() throws Exception {
        Index index = TypicalIndexes.INDEX_FIRST_TRAVELPLAN;
        int zeroBasedIndex = index.getZeroBased();
        ModelStubGoTo modelStub = new ModelStubGoTo();
        TravelPlan travelPlan = modelStub.getFilteredTravelPlanList().get(zeroBasedIndex);
        CommandResult commandResult = new GoToCommand(index, true).execute(modelStub);
        assertEquals(String.format(GoToCommand.MESSAGE_GOTO_SUCCESS, TRAVEL_PLAN + " " + travelPlan.getName()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_goToWishlist_success() throws Exception {
        ModelStubGoTo modelStub = new ModelStubGoTo();
        CommandResult commandResult = new GoToCommand(false).execute(modelStub);
        assertEquals(String.format(GoToCommand.MESSAGE_GOTO_SUCCESS, WISHLIST), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_goToTravelPlan_failure() {
        Index index = TypicalIndexes.INDEX_SECOND_TRAVELPLAN;
        ModelStubGoTo modelStub = new ModelStubGoTo();
        GoToCommand goToCommand = new GoToCommand(index, true);
        assertThrows(CommandException.class, MESSAGE_INVALID_TRAVELPLAN_DISPLAYED_INDEX, () ->
                goToCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Index index = TypicalIndexes.INDEX_FIRST_TRAVELPLAN;
        GoToCommand goToTravelPlanCommand = new GoToCommand(index, true);
        GoToCommand goToWishlistCommand = new GoToCommand(false);

        // same object -> returns true
        assertTrue(goToTravelPlanCommand.equals(goToTravelPlanCommand));

        // same value -> returns true
        GoToCommand goToTravelPlanCommandCopy = new GoToCommand(index, true);
        assertTrue(goToTravelPlanCommand.equals(goToTravelPlanCommandCopy));

        // different type -> returns false
        assertFalse(goToTravelPlanCommand.equals(1));

        // null -> returns false
        assertFalse(goToTravelPlanCommand.equals(null));

        // different GoToCommand -> returns false
        assertFalse(goToTravelPlanCommand.equals(goToWishlistCommand));
    }

    /**
     * A Model stub for GoTo Command.
     */
    private class ModelStubGoTo extends ModelStub {
        private FilteredList<TravelPlan> filteredTravelPlanList;

        ModelStubGoTo() {
            List<TravelPlan> list = Arrays.asList(AUSTRALIA_TRIP);
            filteredTravelPlanList = new FilteredList<>(FXCollections.observableList(list));
        }

        @Override
        public ObservableList<TravelPlan> getFilteredTravelPlanList() {
            return filteredTravelPlanList;
        }

        @Override
        public void setDirectory(int index) {
        }
    }
}

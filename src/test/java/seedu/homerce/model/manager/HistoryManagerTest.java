package seedu.homerce.model.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.service.TypicalServices.MANICURE;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.service.AddServiceCommand;
import seedu.homerce.logic.commands.service.DeleteServiceCommand;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;

public class HistoryManagerTest {

    @Test
    public void addToHistory_stateChange_success() {
        Model modelTest = new ModelManager();
        HistoryManager historyTest = HistoryManager.getInstance();
        AddServiceCommand addServiceCommandTest = new AddServiceCommand(MANICURE);

        historyTest.addToHistory(modelTest, addServiceCommandTest);
        assertTrue(historyTest.getPreviousHistory().getModel().getServiceManager().getServiceList().isEmpty());

        historyTest.addToHistory(modelTest, addServiceCommandTest);
        assertTrue(historyTest.getPreviousHistory().getModel().getServiceManager().getServiceList().isEmpty());
    }

    @Test
    public void getPreviousHistory_stateChange_returnsPreviousHistory() {
        Model modelTest = new ModelManager();
        HistoryManager historyTest = HistoryManager.getInstance();
        AddServiceCommand commandTest = new AddServiceCommand(MANICURE);

        historyTest.addToHistory(modelTest, commandTest);
        assertTrue(historyTest.getPreviousHistory().getCommand().equals(commandTest));

        DeleteServiceCommand deleteServiceCommandTest = new DeleteServiceCommand(Index.fromZeroBased(1));
        historyTest.addToHistory(modelTest, deleteServiceCommandTest);
        assertTrue(historyTest.getPreviousHistory().getCommand().equals(deleteServiceCommandTest));
    }

}

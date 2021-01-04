package seedu.homerce.model.manager;

import java.util.LinkedList;

import seedu.homerce.logic.commands.BreakdownFinanceCommand;
import seedu.homerce.logic.commands.Command;
import seedu.homerce.logic.commands.HelpCommand;
import seedu.homerce.logic.commands.UndoCommand;
import seedu.homerce.logic.commands.appointment.FindAppointmentCommand;
import seedu.homerce.logic.commands.appointment.ListAppointmentCommand;
import seedu.homerce.logic.commands.client.FindClientCommand;
import seedu.homerce.logic.commands.client.ListClientCommand;
import seedu.homerce.logic.commands.expense.FindExpenseCommand;
import seedu.homerce.logic.commands.expense.ListExpenseCommand;
import seedu.homerce.logic.commands.revenue.FindRevenueCommand;
import seedu.homerce.logic.commands.revenue.ListRevenueCommand;
import seedu.homerce.logic.commands.schedule.AbstractWeekCommand;
import seedu.homerce.logic.commands.schedule.NextWeekCommand;
import seedu.homerce.logic.commands.schedule.PreviousWeekCommand;
import seedu.homerce.logic.commands.service.FindServiceCommand;
import seedu.homerce.logic.commands.service.ListServiceCommand;
import seedu.homerce.model.Model;
import seedu.homerce.model.undo.History;


/**
 * Holds all the previous states of Homerce's storage.
 *
 * A new HistoryManager is initialized upon each start up of Homerce.
 */
public class HistoryManager {
    private static HistoryManager historyManager = null;
    private LinkedList<History> histories;

    private HistoryManager() {
        this.histories = new LinkedList<>();
    }

    /**
     * Factory method to return the one and only instance of HistoryManager if it exists, otherwise a new
     * HistoryManager will be created.
     *
     * @return the sole instance of HistoryManager.
     */
    public static HistoryManager getInstance() {
        if (historyManager == null) {
            historyManager = new HistoryManager();
        }
        return historyManager;
    }

    /**
     * Adds a model to the history of model states.
     * The model input will not be added if the command given does not change the state of the model.
     *
     * @param model the new model to be added to the history of model states.
     * @param command the latest command given by the user.
     */
    public void addToHistory(Model model, Command command) {
        if (willCommandChangeState(command)) {
            Model modelDeepCopy = model.deepCopy();
            History history = new History(modelDeepCopy, command);
            histories.addLast(history);
        }
    }

    /**
     * Checks if the user command will change the storage state of Homerce.
     *
     * @param command the command given by the user.
     * @return true if the command will change the state of Homerce.
     */
    private static boolean willCommandChangeState(Command command) {
        return !(command instanceof UndoCommand) && !(command instanceof HelpCommand)
            && !(command instanceof FindClientCommand) && !(command instanceof ListClientCommand)
            && !(command instanceof FindExpenseCommand) && !(command instanceof ListExpenseCommand)
            && !(command instanceof FindServiceCommand) && !(command instanceof ListServiceCommand)
            && !(command instanceof FindRevenueCommand) && !(command instanceof ListRevenueCommand)
            && !(command instanceof FindAppointmentCommand) && !(command instanceof ListAppointmentCommand)
            && !(command instanceof AbstractWeekCommand) && !(command instanceof BreakdownFinanceCommand)
            && !(command instanceof PreviousWeekCommand) && !(command instanceof NextWeekCommand);
    }

    public History getPreviousHistory() {
        return histories.pollLast();
    }
}

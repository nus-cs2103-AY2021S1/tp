package seedu.resireg.logic.commands;

import seedu.resireg.ui.MainWindow;

public class ToggleMainPanelCommandResult extends CommandResult {

    /**
     * Constructs a {@code ToggleCommandResult} with the specified {@code feedbackToUser},
     * that will toggle the UI layout between a tabbed layout and a side-by-side split view.
     */
    public ToggleMainPanelCommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }

    @Override
    public void displayResult(MainWindow mainWindow) {
        mainWindow.toggleMainPanelLayout();
    }
}

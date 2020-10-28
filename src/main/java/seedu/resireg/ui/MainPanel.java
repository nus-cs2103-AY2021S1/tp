package seedu.resireg.ui;


import javafx.scene.layout.Region;
import seedu.resireg.logic.Logic;
import seedu.resireg.logic.commands.TabView;

/**
 * Represents the main panel for the {@code MainWindow}.
 */
public abstract class MainPanel extends UiPart<Region> {

    public MainPanel(String fxml) {
        super(fxml);
    }

    /**
     * Update the contents of all the lists in the UI based on the given {@code logic}.
     */
    abstract void updatePanels(Logic logic);

    /**
     * Changes the tab being shown (if applicable).
     */
    abstract void handleToggle(TabView toggleView);

    /**
     * Toggles between showing students and rooms in two separate tabs, and showing students and rooms in one tab
     * (if applicable).
     */
    abstract void toggleStudentsRoomsTabSplit();
}

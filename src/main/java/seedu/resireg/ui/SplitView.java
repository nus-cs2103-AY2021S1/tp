package seedu.resireg.ui;

import seedu.resireg.logic.commands.TabView;

public class SplitView extends MainPanel {
    private static final String FXML = "SplitView.fxml";

    public SplitView() {
        super(FXML);
    }

    @Override
    void handleToggle(TabView toggleView) {
        // do nothing, since all tabs are already displayed
    }
}

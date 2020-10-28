package seedu.resireg.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

public class PiechartsOnlyTab extends UiPart<Tab> implements PiechartsTab {
    private static final String FXML = "PiechartsOnlyTab.fxml";

    @FXML
    private StackPane piechartPanelPlaceholder;

    public PiechartsOnlyTab() {
        super(FXML);
    }

    @Override
    public void setPiechartPanel(PiechartPanel piechartPanel) {
        piechartPanelPlaceholder.getChildren().clear();
        piechartPanelPlaceholder.getChildren().add(piechartPanel.getRoot());
    }

    @Override
    public Tab getTab() {
        return getRoot();
    }
}

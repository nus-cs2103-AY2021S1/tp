package seedu.jarvis.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.model.consultation.Consultation;


public class ConsultationListPanel extends UiPart<Region> {

    private static final String FXML = "ConsultationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ConsultationListPanel.class);

    @javafx.fxml.FXML
    private ListView<Consultation> consultationListView;

    /**
     * Creates a {@code ConsultationListPanel} with the given {@code ObservableList}.
     */
    public ConsultationListPanel(ObservableList<Consultation> consultationList) {
        super(FXML);

        Label emptyListLabel = new Label("No Consultations");
        emptyListLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        consultationListView.setPlaceholder(emptyListLabel);
        consultationListView.setItems(consultationList);
        consultationListView.setCellFactory(listView -> new ConsultationListPanel.ConsultationListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Consultation} using a {@code ConsultationCard}.
     */
    class ConsultationListViewCell extends ListCell<Consultation> {
        @Override
        protected void updateItem(Consultation consultation, boolean empty) {
            super.updateItem(consultation, empty);

            if (empty || consultation == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ConsultationCard(consultation, getIndex() + 1).getRoot());
            }
        }
    }

}

package seedu.pivot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.model.investigationcase.caseperson.CasePerson;

public class CasePersonListPanel extends UiPart<Region> {
    private static final String FXML = "CasePersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CasePersonListPanel.class);

    @FXML
    private ListView<CasePerson> casePersonListView;

    /**
     * Creates a {@code CasePersonListPanel} with the given {@code ObservableList}.
     */
    public CasePersonListPanel(ObservableList<CasePerson> casePersonList) {
        super(FXML);
        casePersonListView.setItems(casePersonList);
        casePersonListView.setCellFactory(listView -> new CasePersonViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code CasePerson} using a {@code CasePersonCard}.
     */
    class CasePersonViewCell extends ListCell<CasePerson> {
        @Override
        protected void updateItem(CasePerson casePerson, boolean empty) {
            super.updateItem(casePerson, empty);

            if (empty || casePerson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CasePersonCard(casePerson, getIndex() + 1).getRoot());
            }
        }
    }
}

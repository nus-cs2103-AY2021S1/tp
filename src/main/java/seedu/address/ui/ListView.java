package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Renderable;
import seedu.address.model.budget.Budget;
import seedu.address.model.expenditure.Expenditure;

/**
 * Panel containing the list of persons.
 */
public class ListView extends UiPart<Region> {
    private static final String FXML = "RenderableListView.fxml";
    private final Logger logger = LogsCenter.getLogger(ListView.class);

    @FXML
    private javafx.scene.control.ListView<Renderable> renderableListView;

    /**
     * Creates a {@code RenderableListPanel} with the given {@code ObservableList}.
     */
    public ListView(ObservableList<Renderable> renderableList) {
        super(FXML);
        renderableListView.setItems(renderableList);
        renderableListView.setCellFactory(listView -> new RenderableListViewCell());
    }

    class RenderableListViewCell extends ListCell<Renderable> {
        @Override
        protected void updateItem(Renderable renderable, boolean empty) {
            super.updateItem(renderable, empty);

            if (empty || renderable == null) {
                setGraphic(null);
                setText(null);
            } else if (renderable instanceof Budget) {
                setGraphic(new BudgetCard((Budget) renderable, getIndex() + 1).getRoot());
            } else if (renderable instanceof Expenditure) {
                setGraphic(new ExpenditureCard((Expenditure) renderable, getIndex() + 1).getRoot());
            }
        }
    }
}

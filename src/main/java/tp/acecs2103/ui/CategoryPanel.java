package tp.acecs2103.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.model.task.Task;

/**
 * Encapsulates a panel containing the list of tasks for a specific category.
 */
public class CategoryPanel extends UiPart<Region> {
    private static final String FXML = "CategoryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CategoryPanel.class);

    @FXML
    private ListView<Task> categoryView;
    @FXML
    private Label categoryLabel;

    private String category;

    /**
     * Creates a {@code CategoryPanel} with the given {@code ObservableList} and {@code categoryString}.
     */
    public CategoryPanel(ObservableList<Task> taskList, String categoryString) {
        super(FXML);
        category = categoryString;

        categoryLabel.setText(categoryString);
        categoryLabel.textAlignmentProperty();
        setStyle(categoryString);

        categoryView.setItems(taskList);
        categoryView.setCellFactory(listView -> new CategoryCell());
    }

    public void setText(ArrayList<Integer> weekRange) {
        if (weekRange != null) {
            String startWeek = String.valueOf(weekRange.get(0));
            String endWeek = String.valueOf(weekRange.get(1));
            String res = category + " (Week " + startWeek + " to Week " + endWeek + ")";
            categoryLabel.setText(res);
        } else {
            categoryLabel.setText(category);
        }
    }

    /**
     * Custom {@code CategoryCell} that displays the graphics of a {@code Task} using a {@code TaskBox}.
     */
    class CategoryCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);
            if (task != null) {
                switch (task.getCategory().toString()) {
                case "ADMIN":
                    setStyle("-fx-background-color: #FFF0F5");
                    break;
                case "TOPIC":
                    setStyle("-fx-background-color: #E1FFFF");
                    break;
                case "IP":
                    setStyle("-fx-background-color: #FFFFE0");
                    break;
                case "TP":
                    setStyle("-fx-background-color: #F0FFF0");
                    break;
                default:
                    assert(false);
                    break;
                }
            }
            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskBox(task).getRoot());
            }
        }
    }

    /**
     * Set different background color for different category panel.
     * @param categoryString a string represents the category.
     */
    public void setStyle(String categoryString) {
        switch (categoryString) {
        case "Admin":
            categoryView.getStyleClass().add("color1");
            break;
        case "Topic":
            categoryView.getStyleClass().add("color2");
            break;
        case "Ip":
            categoryView.getStyleClass().add("color3");
            break;
        case "Tp":
            categoryView.getStyleClass().add("color4");
            break;
        default:
            categoryView.getStyleClass().add("");

        }

    }
}

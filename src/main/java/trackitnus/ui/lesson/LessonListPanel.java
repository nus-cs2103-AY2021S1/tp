package trackitnus.ui.lesson;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.model.lesson.Lesson;
import trackitnus.ui.UiPart;

/**
 * Panel containing the list of lessons.
 */
public class LessonListPanel extends UiPart<Region> {
    private static final String FXML = "Lesson/LessonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LessonListPanel.class);

    @FXML
    private ListView<Lesson> lessonListView;

    /**
     * Creates a {@code LessonListPanel} with the given {@code ObservableList}.
     */
    public LessonListPanel(ObservableList<Lesson> lessonList) {
        super(FXML);
        lessonListView.setItems(lessonList);
        lessonListView.setCellFactory(listView -> new LessonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Lesson} using a {@code LessonCard}.
     */
    class LessonListViewCell extends ListCell<Lesson> {
        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }

}

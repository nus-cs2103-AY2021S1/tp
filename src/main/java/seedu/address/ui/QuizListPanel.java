package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.quiz.Question;

/**
 * Panel containing the list of persons.
 */
public class QuizListPanel extends UiPart<Region> {
    private static final String FXML = "QuizListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Question> quizListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public QuizListPanel(ObservableList<Question> quizList) {
        super(FXML);
        quizListView.setItems(quizList);
        quizListView.setCellFactory(listView -> new QuizListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Question} using a {@code QuizCard}.
     */
    class QuizListViewCell extends ListCell<Question> {
        @Override
        protected void updateItem(Question question, boolean empty) {
            super.updateItem(question, empty);

            if (empty || question == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new QuizCard(question, getIndex() + 1).getRoot());
            }
        }
    }

}

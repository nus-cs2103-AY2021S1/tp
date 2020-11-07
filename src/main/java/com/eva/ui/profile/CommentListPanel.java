package com.eva.ui.profile;

import java.util.logging.Logger;

import com.eva.commons.core.LogsCenter;
import com.eva.model.comment.Comment;
import com.eva.ui.UiPart;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * A UI component that displays the list of comments.
 */
public class CommentListPanel extends UiPart<Region> {
    private static final String FXML = "CommentListPanel.fxml";
    private static final String DISPLAY_NAME = "Comments";

    private final Logger logger = LogsCenter.getLogger(CommentListPanel.class);

    @FXML
    private ListView<Comment> commentListView;
    @FXML
    private Label display;

    /**
     * Creates a {@code CommentListPanel} with the given {@code ObservableList}.
     */
    public CommentListPanel(ObservableList<Comment> commentList) {
        super(FXML);
        commentListView.setItems(commentList);
        commentListView.setCellFactory(listView -> new CommentListViewCell());
        display.setText(DISPLAY_NAME);
    }

    /**
     * Custom {@code CommentListCell} that displays the graphics of a {@code Comment} using a {@code CommentListCard}.
     */
    class CommentListViewCell extends ListCell<Comment> {
        @Override
        protected void updateItem(Comment comment, boolean empty) {
            super.updateItem(comment, empty);

            if (empty || comment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CommentListCard(comment, getIndex() + 1).getRoot());
            }
        }
    }

}

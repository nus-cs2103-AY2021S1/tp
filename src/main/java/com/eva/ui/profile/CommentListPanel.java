package com.eva.ui.profile;

import java.util.logging.Logger;

import com.eva.commons.core.LogsCenter;
import com.eva.model.comment.Comment;
import com.eva.ui.UiPart;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;


public class CommentListPanel extends UiPart<Region> {
    private static final String FXML = "CommentListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(CommentListPanel.class);

    @FXML
    private ListView<Comment> commentListView;

    /**
     * Creates a {@code StaffListPanel} with the given {@code ObservableList}.
     */
    public CommentListPanel(ObservableList<Comment> commentList) {
        super(FXML);
        commentListView.setItems(commentList);
        commentListView.setCellFactory(listView -> new CommentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Staff} using a {@code StaffListCard}.
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

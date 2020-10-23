package com.eva.ui.profile;

import static com.eva.commons.util.DateUtil.dateToString;

import com.eva.model.comment.Comment;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class CommentListCard extends UiPart<Region> {
    private static final String FXML = "CommentListCard.fxml";

    public final Comment comment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label date;
    @FXML
    private Label description; //rename to body

    /**
     * Creates a {@code CommentListCard} with the given {@code Comment} and index to display.
     */
    public CommentListCard(Comment comment, int displayedIndex) {
        super(FXML);
        this.comment = comment;
        id.setText(displayedIndex + ". ");
        title.setText(comment.getTitle().toString());
        date.setText(dateToString(comment.getDate()));
        description.setText(comment.getDescription());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommentListCard)) {
            return false;
        }

        // state check
        CommentListCard card = (CommentListCard) other;
        return id.getText().equals(card.id.getText())
                && comment.equals(card.comment);
    }
}

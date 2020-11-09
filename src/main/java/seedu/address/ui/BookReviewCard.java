package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.review.Review;

/**
 * An UI component that displays a single review of a {@code Review} of a book.
 */
public class BookReviewCard extends UiPart<Region> {

    private static final String FXML = "BookReviewCard.fxml";
    @FXML
    private Label id;
    @FXML
    private Label rating;
    @FXML
    private Label content;
    @FXML
    private FlowPane time;

    /**
     * Creates a {@code BookReviewCard} with the given {@code Review} and index to display.
     */
    public BookReviewCard(Review review, int displayedIndex) {
        super(FXML);
        id.setText("(" + displayedIndex + ")");
        String ratingStar = review.getRating().ratingNumber + " star";
        if (review.getRating().ratingNumber > 1) {
            ratingStar = ratingStar + "s";
        }
        ratingStar = ratingStar + " ";
        for (int i = 0; i < review.getRating().ratingNumber; i = i + 1) {
            ratingStar = ratingStar + "*";
        }
        rating.setText(ratingStar);
        content.setText(review.getContent().content);
        time.getChildren().add(new Label("created: " + review.getCreatedTimeRepresentation()));
        if (review.getEditedTimeRepresentation().isPresent()) {
            time.getChildren().add(new Label("last edited: "
                    + review.getEditedTimeRepresentation().get()));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookReviewCard)) {
            return false;
        }

        // state check
        BookReviewCard card = (BookReviewCard) other;
        return rating.getText().equals(card.rating.getText())
                && content.getText().equals(card.content.getText());
    }
}

package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a Book's review in the application.
 */
public class Review {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private final Rating rating;
    private final ReviewContent reviewContent;
    private LocalDateTime createdTime;
    private LocalDateTime editedTime;

    /**
     * Constructs an {@code Review}.
     *
     * @param rating A valid rating.
     * @param content The review content.
     */
    public Review(Rating rating, ReviewContent content) {
        requireNonNull(rating);
        requireNonNull(content);
        this.rating = rating;
        this.reviewContent = content;
    }


    public Rating getRating() {
        return rating;
    }

    public ReviewContent getContent() {
        return reviewContent;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setEditedTime(LocalDateTime editedTime) {
        this.editedTime = editedTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getCreatedTimeRepresentation() {
        return createdTime.format(DATE_TIME_FORMATTER);
    }

    public Optional<String> getEditedTimeRepresentation() {
        if (editedTime == null) {
            return Optional.empty();
        } else {
            return Optional.of(editedTime.format(DATE_TIME_FORMATTER));
        }
    }

    @Override
    public String toString() {
        return "rating: " + this.rating.ratingNumber + " content: " + this.reviewContent.content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Review // instanceof handles nulls
                && rating.equals(((Review) other).rating))
                && reviewContent.equals(((Review) other).reviewContent); // state check
    }

    @Override
    public int hashCode() {
        return rating.hashCode();
    }
}

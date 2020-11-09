package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Library;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Language;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;
import seedu.address.model.problem.Description;
import seedu.address.model.problem.Problem;
import seedu.address.model.problem.Severity;
import seedu.address.model.review.Rating;
import seedu.address.model.review.Review;
import seedu.address.model.review.ReviewContent;

/**
 * Contains utility methods for populating {@code Library} with sample data.
 */
public class SampleDataUtil {
    public static Problem[] getSampleProblems() {
        return new Problem[] {
            new Problem(new Severity("high"), new Description("Harry Potter is lost")),
            new Problem(new Severity("medium"), new Description("Linear Algebra book cover is damaged")),
            new Problem(new Severity("low"), new Description("level 1 floor is dirty"))
        };
    }

    public static Book[] getSampleBooks() {
        HashMap<String, Integer> storage = new HashMap<>();
        storage.put("centralLb", 10);
        storage.put("scienceLb", 8);
        storage.put("HSSMLb", 16);
        Stocking stocking = new Stocking(storage);

        List<Review> reviews = new ArrayList<>();
        Rating rating = new Rating(5);
        ReviewContent reviewContent = new ReviewContent("The book is interesting");
        Rating newRating = new Rating(4);
        ReviewContent newReviewContent = new ReviewContent("The book is inspiring");
        Review review = new Review(rating, reviewContent);
        Review newReview = new Review(newRating, newReviewContent);
        review.setCreatedTime(LocalDateTime.parse("2018/10/24 17:30", Review.DATE_TIME_FORMATTER));
        newReview.setCreatedTime(LocalDateTime.parse("2019/11/29 18:30", Review.DATE_TIME_FORMATTER));
        newReview.setEditedTime(LocalDateTime.parse("2019/12/15 17:45", Review.DATE_TIME_FORMATTER));
        reviews.add(review);
        reviews.add(newReview);

        return new Book[] {
            new Book(new Name("Pride and Prejudice"), new Isbn("9780141439518"),
                    new Email("pride&prejudice@example.com"), new Language("English"), new Times("195"),
                    getCategorySet("Novel"), stocking, reviews, new Author("Jane Austen"),
                    new Publisher("Penguin Publishing Group")),
            new Book(new Name("A Brief History Of Time From Big Bang To Black Holes"), new Isbn("9780553175219"),
                    new Email("abriefhistoryoftime@example.com"), new Language("English"), new Times("20278"),
                    getCategorySet("Science"), stocking, reviews,
                    new Author("Stephen Hawking"), new Publisher("Bantam")),
            new Book(new Name("The Great Gatsby"), new Isbn("9780743273565"), new Email("thegreatgatsby@example.com"),
                    new Language("English"), new Times("6529"), getCategorySet("Novel"), stocking, reviews,
                    new Author("Scott Fitzgerald"), new Publisher("Scribner")),
            new Book(new Name("Introduction to Linear Algebra"), new Isbn("9780980232776"),
                    new Email("introtolinearalgebra@example.com"), new Language("English"), new Times("243"),
                    getCategorySet("Math"), stocking, reviews,
                    new Author("Gilbert Strang"), new Publisher("Wellesley Cambridge Press")),
            new Book(new Name("Chemistry The Molecular Nature of Matter and Change"), new Isbn("9781259631757"),
                    new Email("chemistrytextbook@example.com"), new Language("English"), new Times("936"),
                    getCategorySet("Chemistry", "Science"), stocking, reviews,
                    new Author("Patricia Amateis"), new Publisher("McGraw Hill Education")),
            new Book(new Name("University Physics"), new Isbn("9780805387681"),
                    new Email("universityphysics@example.com"), new Language("English"), new Times("2038"),
                    getCategorySet("Physics", "Science"), stocking, reviews,
                    new Author("Hugh D Young"), new Publisher("Addison Wesley Publishing Company")),
            new Book(new Name("The Rape of Nanking The Forgotten Holocaust of World War II"),
                    new Isbn("9780805387682"), new Email("nanjing@example.com"), new Language("English"),
                    new Times("236"), getCategorySet("ModernWar", "ModernHistory", "History"), stocking,
                    reviews, new Author("Iris Chang"), new Publisher("Basic Books")),
            new Book(new Name("Ancient China A Captivating Guide to the Ancient History of the Chinese Civilization"),
                    new Isbn("9780805383745"), new Email("chinahistory@example.com"),
                    new Language("English"), new Times("666"), getCategorySet("AncientHistory", "History"),
                    stocking, reviews, new Author("Captivating History"),
                    new Publisher("Captivating History")),
        };
    }

    public static ReadOnlyLibrary getSampleLibrary() {
        Library sampleLib = new Library();
        for (Book sampleBook : getSampleBooks()) {
            sampleLib.addBook(sampleBook);
        }
        for (Problem sampleProblem : getSampleProblems()) {
            sampleLib.addProblem(sampleProblem);
        }
        return sampleLib;
    }

    /**
     * Returns a category set containing the list of strings given.
     */
    public static Set<Category> getCategorySet(String... strings) {
        return Arrays.stream(strings)
                .map(Category::new)
                .collect(Collectors.toSet());
    }

    public static List<Review> getReviews(Review ... reviews) {
        return new ArrayList<>(Arrays.asList(reviews));
    }
}

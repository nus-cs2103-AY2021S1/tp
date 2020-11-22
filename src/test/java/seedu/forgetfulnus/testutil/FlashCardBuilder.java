package seedu.forgetfulnus.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.flashcard.Order;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.Tag;
import seedu.forgetfulnus.model.util.SampleDataUtil;

/**
 * A utility class to help with building FlashCard objects.
 */
public class FlashCardBuilder {

    public static final String DEFAULT_GERMAN_PHRASE = "Vergesslichkeit";
    public static final String DEFAULT_ENGLISH_PHRASE = "Forgetfulness";
    public static final String DEFAULT_ORDER = "8";

    private GermanPhrase germanPhrase;
    private EnglishPhrase englishPhrase;
    private DifficultyTag difficultyTag;
    private GenderTag genderTag;
    private Set<Tag> tags;
    private Order order;

    /**
     * Creates a {@code FlashCardBuilder} with the default details.
     */
    public FlashCardBuilder() {
        germanPhrase = new GermanPhrase(DEFAULT_GERMAN_PHRASE);
        englishPhrase = new EnglishPhrase(DEFAULT_ENGLISH_PHRASE);
        difficultyTag = new DifficultyTag(DifficultyTag.MEDIUM_TAG);
        genderTag = new GenderTag(GenderTag.NEUTRAL_GENDER_TAG);
        tags = new HashSet<>();
        order = new Order(Integer.parseInt(DEFAULT_ORDER));
    }

    /**
     * Initializes the FlashCardBuilder with the data of {@code flashCardToCopy}.
     */
    public FlashCardBuilder(FlashCard flashCardToCopy) {
        germanPhrase = flashCardToCopy.getGermanPhrase();
        englishPhrase = flashCardToCopy.getEnglishPhrase();
        difficultyTag = flashCardToCopy.getDifficultyTag();
        genderTag = flashCardToCopy.getGenderTag();
        assert GenderTag.isValidGenderTag(genderTag.toString());
        assert DifficultyTag.isValidDifficultyTag(difficultyTag.toString());
        tags = new HashSet<>(flashCardToCopy.getTags());
        order = flashCardToCopy.getOrder();
    }

    /**
     * Sets the {@code germanPhrase} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withGermanPhrase(String germanPhrase) {
        this.germanPhrase = new GermanPhrase(germanPhrase);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ephrase} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withEnglishPhrase(String englishPhrase) {
        this.englishPhrase = new EnglishPhrase(englishPhrase);
        return this;
    }

    /**
     * Sets the {@code difficultyTag} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withDifficultyTag(String difficultyTag) {
        this.difficultyTag = new DifficultyTag(difficultyTag);
        return this;
    }

    /**
     * Sets the {@code genderTag} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withGenderTag(String genderTag) {
        this.genderTag = new GenderTag(genderTag);
        return this;
    }

    /**
     * Sets the {@code Order} of the {@code FlashCard} that we are building.
     */
    public FlashCardBuilder withOrder(int value) {
        this.order = new Order(value);
        return this;
    }

    public FlashCard build() {
        return new FlashCard(germanPhrase, englishPhrase, difficultyTag, genderTag, tags, order);
    }
}

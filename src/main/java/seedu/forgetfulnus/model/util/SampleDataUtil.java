package seedu.forgetfulnus.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.flashcard.Order;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Glossary} with sample data.
 */
public class SampleDataUtil {

    public static FlashCard[] getSampleFlashCards() {
        return new FlashCard[] {
            new FlashCard(new GermanPhrase("Start"), new EnglishPhrase("start"),
                    new DifficultyTag("EASY"), new GenderTag("M"),
                    getTagSet("chapter1"), new Order(1)),
            new FlashCard(new GermanPhrase("auf"), new EnglishPhrase("in"),
                    new DifficultyTag("EASY"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(2)),
            new FlashCard(new GermanPhrase("Deutsch"), new EnglishPhrase("German"),
                    new DifficultyTag("EASY"), new GenderTag("NEUTRAL"),
                    getTagSet("chapter1"), new Order(3)),
            new FlashCard(new GermanPhrase("hier"), new EnglishPhrase("here"),
                    new DifficultyTag("EASY"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(4)),
            new FlashCard(new GermanPhrase("lernen"), new EnglishPhrase("learn"),
                    new DifficultyTag("MEDIUM"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(5)),
            new FlashCard(new GermanPhrase("Sie"), new EnglishPhrase("you"),
                    new DifficultyTag("HARD"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(6)),
            new FlashCard(new GermanPhrase("international"), new EnglishPhrase("international"),
                    new DifficultyTag("EASY"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(7)),
            new FlashCard(new GermanPhrase("Wort"), new EnglishPhrase("word"),
                    new DifficultyTag("EASY"), new GenderTag("NEUTRAL"),
                    getTagSet("chapter1"), new Order(8)),
            new FlashCard(new GermanPhrase("verstehen"), new EnglishPhrase("understand"),
                    new DifficultyTag("MEDIUM"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(9)),
            new FlashCard(new GermanPhrase("jemand"), new EnglishPhrase("somebody"),
                    new DifficultyTag("MEDIUM"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(10)),
            new FlashCard(new GermanPhrase("begrüßen"), new EnglishPhrase("greet"),
                    new DifficultyTag("HARD"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(11)),
            new FlashCard(new GermanPhrase("sich"), new EnglishPhrase("oneself"),
                    new DifficultyTag("MEDIUM"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(12)),
            new FlashCard(new GermanPhrase("und"), new EnglishPhrase("and"),
                    new DifficultyTag("EASY"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(13)),
            new FlashCard(new GermanPhrase("andere"), new EnglishPhrase("another"),
                    new DifficultyTag("HARD"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(14)),
            new FlashCard(new GermanPhrase("vorstellen"), new EnglishPhrase("introduce"),
                    new DifficultyTag("MEDIUM"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(15)),
            new FlashCard(new GermanPhrase("nach"), new EnglishPhrase("about"),
                    new DifficultyTag("EASY"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(16)),
            new FlashCard(new GermanPhrase("Name"), new EnglishPhrase("name"),
                    new DifficultyTag("EASY"), new GenderTag("M"),
                    getTagSet("chapter1"), new Order(17)),
            new FlashCard(new GermanPhrase("Herkunft"), new EnglishPhrase("origin"),
                    new DifficultyTag("MEDIUM"), new GenderTag("F"),
                    getTagSet("chapter1"), new Order(18)),
            new FlashCard(new GermanPhrase("fragen"), new EnglishPhrase("ask"),
                    new DifficultyTag("EASY"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(19)),
            new FlashCard(new GermanPhrase("Vorname"), new EnglishPhrase("firstname"),
                    new DifficultyTag("MEDIUM"), new GenderTag("M"),
                    getTagSet("chapter1"), new Order(20)),
            new FlashCard(new GermanPhrase("Nachname"), new EnglishPhrase("lastname"),
                    new DifficultyTag("MEDIUM"), new GenderTag("M"),
                    getTagSet("chapter1"), new Order(21)),
            new FlashCard(new GermanPhrase("buchstabieren"), new EnglishPhrase("spell"),
                    new DifficultyTag("HARD"), new GenderTag("NONE"),
                    getTagSet("chapter1"), new Order(22))
        };
    }

    public static ReadOnlyGlossary getSampleGlossary() {
        Glossary sampleG = new Glossary();
        for (FlashCard sampleFlashCard : getSampleFlashCards()) {
            sampleG.addFlashCard(sampleFlashCard);
        }
        return sampleG;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

package seedu.bagel.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.bagel.model.Bagel;
import seedu.bagel.model.ReadOnlyBagel;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;
import seedu.bagel.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Bagel} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[] {
            new Flashcard(new Title("Types of Observational Studies"),
                new Description("Prospective, Retrospective, Cross-sectional"),
                    new Link("https://en.wikipedia.org/wiki/Observational_study"),
                    new FlashcardSet("1"),
                    getTagSet("Types", "ObservationalStudies")),
            new Flashcard(new Title("Types of Sampling Plan"),
                new Description("Probability Sampling: simple random, systematic, stratified, cluster | "
                        + "Non-probability sampling: volunteer/self-selected, convenience, judgemental, quota"),
                    new Link("file:///c:/GER1000/Tutorial%203%20Slides.pdf"), new FlashcardSet("1"),
                    getTagSet("Types", "SamplingPlan")),
            new Flashcard(new Title("Ecological Fallacy"),
                new Description("Ecological fallacy is thinking that relationships observed for groups "
                        + "will hold for individuals. E.g. if countries with more fat in the diet have higher rates of"
                        + "breast cancer, then women who eat fatty foods must be more likely to get breast cancer."),
                    new Link("https://en.wikipedia.org/wiki/Ecological_fallacy"),
                    new FlashcardSet("2"),
                    getTagSet("Definition", "EcologicalFallacy")),
            new Flashcard(new Title("Atomistic Fallacy"),
                new Description("Atomistic fallacy is thinking that relation observed for individuals"
                        + "will hold for groups. The atomistic fallacy arises because associations between two "
                        + "variables at the individual level may differ from associations between analogous "
                        + "variables measured at the group level. "),
                    new Link(""),
                    new FlashcardSet("1"),
                    getTagSet("Definition", "AtomisticFallacy")),
            new Flashcard(new Title("Types of association"),
                new Description("r = 0: no linear association, "
                        + "r > 0: positive association, "
                        + "r < 0: negative association, "
                        + "r = 1: perfect positive association, "
                        + "r = -1: perfect negative association"),
                    new Link(""), new FlashcardSet("2"),
                    getTagSet("Identification", "Types", "Association")),
            new Flashcard(new Title("Odds Ratio(OR) and Risk Ratio(RR)"),
                new Description("OR: odds(exp)/odds(unexp), "
                        + "RR: risk(exp)/risk(unexp)"),
                    new Link("file:///c:/GER1000/Tutorial%204%20Slides.pdf"),
                    new FlashcardSet("3"),
                    getTagSet("OddsRatio", "RiskRatio"))
        };
    }

    public static ReadOnlyBagel getSampleBagel() {
        Bagel sampleBagel = new Bagel();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleBagel.addFlashcard(sampleFlashcard);
        }
        return sampleBagel;
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

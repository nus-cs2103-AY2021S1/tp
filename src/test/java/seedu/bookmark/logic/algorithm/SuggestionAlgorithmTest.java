package seedu.bookmark.logic.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalWords.HARRY;
import static seedu.bookmark.testutil.TypicalWords.MISSPELT_HARRY;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.WordBank;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.wordstore.Word;
import seedu.bookmark.model.wordstore.exceptions.WordStoreNotFoundException;
import seedu.bookmark.testutil.BookBuilder;
import seedu.bookmark.testutil.TypicalWords;

public class SuggestionAlgorithmTest {
    private WordBank wordBank = TypicalWords.getEmptyWordBank();
    private SuggestionAlgorithm suggestionAlgorithm = new SuggestionAlgorithm(wordBank);

    //------------------------- instantiate Object ---------------------//

    @Test
    public void create_suggestionAlgo_nullPointerException() {
        assertThrows(NullPointerException.class, () -> new SuggestionAlgorithm(null));
    }

    @Test
    public void create_suggestionAlgo_correctValue() {
        WordBank wordBank = TypicalWords.getTypicalWordBank();
        SuggestionAlgorithm suggestionAlgorithm = new SuggestionAlgorithm(wordBank);

        assertEquals(wordBank, suggestionAlgorithm.getWordBank());

    }

    //------------------------- findSuggestion() ----------------------//

    @Test
    public void findSuggestion_sourceWordNull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .findSuggestion(null, PREFIX_NAME));
    }

    @Test
    public void findSuggestion_prefixNull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .findSuggestion(HARRY, null));
    }


    @Test
    public void findSuggestion_prefixInvalid() {
        assertThrows(WordStoreNotFoundException.class, () -> suggestionAlgorithm
                                                                .findSuggestion(MISSPELT_HARRY, PREFIX_BOOKMARK));

    }

    @Test
    public void findSuggestion_correctParameters() {
        String validWord = "Harry";
        Book testBook = new BookBuilder().withName(validWord).withGenre("Fiction").withTotalPages("100").build();
        wordBank.addToWordBank(testBook);

        ArrayList<Word> suggestions = suggestionAlgorithm.findSuggestion("Haary", PREFIX_NAME);
        int distance = suggestionAlgorithm.calculateDistance("Haary", "Harry"); //distance = 1
        Word harry = new Word("Harry", distance);
        assertTrue(suggestions.contains(harry));
    }

    @Test
    public void findSuggestion_distanceLimit() {
        int distanceTolerance = SuggestionAlgorithm.DISTANCE_TOLERANCE;
        String validWord = "Harry";
        Book testBook = new BookBuilder().withName(validWord).withGenre("Fiction").withTotalPages("100").build();
        wordBank.addToWordBank(testBook);

        //word found when within distance limit
        int wordDistanceWithinLimit = suggestionAlgorithm.calculateDistance(validWord, validWord);
        Word wordObjectWithinLimit = new Word(validWord, wordDistanceWithinLimit);
        Word validWordObject = new Word(validWord, wordDistanceWithinLimit);
        ArrayList<Word> suggestions = suggestionAlgorithm.findSuggestion(wordObjectWithinLimit.getWord(), PREFIX_NAME);
        System.out.println(suggestions.size());
        assertTrue(suggestions.contains(validWordObject));

        //word found when at distance limit
        String wordAtDistanceLimit = "Ha" + "a".repeat(distanceTolerance) + "rry";
        int wordDistanceAtLimit = suggestionAlgorithm.calculateDistance(wordAtDistanceLimit, validWord);
        Word wordObjectAtLimit = new Word(wordAtDistanceLimit, wordDistanceAtLimit);
        validWordObject = new Word(validWord, wordDistanceAtLimit);
        suggestions = suggestionAlgorithm.findSuggestion(wordObjectAtLimit.getWord(), PREFIX_NAME);
        assertTrue(suggestions.contains(validWordObject));


        //word not found when out of distance limit
        String wordOutOfDistanceLimit = "Ha" + "a".repeat(distanceTolerance + 1) + "rry";
        int wordDistanceOutOfLimit = suggestionAlgorithm.calculateDistance(wordOutOfDistanceLimit, validWord);
        Word wordObjectOutOfLimit = new Word(wordOutOfDistanceLimit, wordDistanceOutOfLimit);
        validWordObject = new Word(validWord, wordDistanceOutOfLimit);
        suggestions = suggestionAlgorithm.findSuggestion(wordObjectOutOfLimit.getWord(), PREFIX_NAME);
        assertFalse(suggestions.contains(validWordObject));
    }

    //------------------------ calculateDistance ---------------------//

    @Test
    public void calculateDistance_sourceWordNull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .calculateDistance(HARRY, null));
    }

    @Test
    public void calculateDistance_targetWordNull() {
        assertThrows(NullPointerException.class, () -> suggestionAlgorithm
                                                            .calculateDistance(null, HARRY));
    }

    @Test
    public void calculateDistance_correctInput() {
        int substitutionDistance = suggestionAlgorithm
                                    .calculateDistance("Hbrry", "Harry"); //substitution (cost 1)
        assertEquals(1, substitutionDistance);

        int positionSwapDistance = suggestionAlgorithm
                                    .calculateDistance("Hrary", "Harry"); //adjacent swapping (cost 1)
        assertEquals(1, positionSwapDistance);

        int missingLetterDistance = suggestionAlgorithm
                                    .calculateDistance("Hrry", "Harry"); //insertion (cost 1)
        assertEquals(1, missingLetterDistance);

        int extraLetterDistance = suggestionAlgorithm
                                    .calculateDistance("Haarry", "Harry"); //deletion (cost 1)
        assertEquals(1, extraLetterDistance);

        int substitutionDistanceBig = suggestionAlgorithm
                                        .calculateDistance("Hbbby", "Harry"); // (cost 3)
        assertEquals(3, substitutionDistanceBig);

        int positionSwapDistanceBig = suggestionAlgorithm
                .calculateDistance("Hyrra", "Harry"); //adjacent swapping (cost 2) (becomes insert&delete)
        assertEquals(2, positionSwapDistanceBig);

        int missingLetterDistanceBig = suggestionAlgorithm
                .calculateDistance("Hy", "Harry"); //insertion (cost 3)
        assertEquals(3, missingLetterDistanceBig);

        int extraLetterDistanceBig = suggestionAlgorithm
                .calculateDistance("Haaaarry", "Harry"); //deletion (cost 3)
        assertEquals(3, extraLetterDistanceBig);

    }

}

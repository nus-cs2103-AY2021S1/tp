package seedu.bookmark.model.wordstore;

import java.util.Comparator;

public class WordComparator implements Comparator<Word> {

    @Override
    public int compare(Word firstWord, Word secondWord) {
        int firstWordDistance = firstWord.getDistance();
        int secondWordDistance = secondWord.getDistance();

        if (firstWordDistance < secondWordDistance) {
            return -1;
        } else if (firstWordDistance > secondWordDistance) {
            return 1;
        } else {
            return firstWord.getWord().charAt(0) < secondWord.getWord().charAt(0) ? -1 : 1;
        }
    }
}

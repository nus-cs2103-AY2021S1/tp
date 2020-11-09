package seedu.address.model;

import java.util.Comparator;

import seedu.address.model.person.Flashcard;

public class FlashcardComparator implements Comparator<Flashcard> {

    public int compare(Flashcard f1, Flashcard f2) {
        return f1.compareTo(f2);
    }
}

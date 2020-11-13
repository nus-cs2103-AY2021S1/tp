package seedu.bagel.logic.commands.sort;

import java.util.Comparator;

import seedu.bagel.model.flashcard.Flashcard;

public class SortByAscTitle implements Comparator<Flashcard> {

    @Override
    public int compare(Flashcard one, Flashcard two) {
        return one.getTitle().toString().compareTo(two.getTitle().toString());
    }
}

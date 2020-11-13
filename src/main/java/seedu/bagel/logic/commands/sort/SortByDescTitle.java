package seedu.bagel.logic.commands.sort;

import java.util.Comparator;

import seedu.bagel.model.flashcard.Flashcard;

public class SortByDescTitle implements Comparator<Flashcard> {

    @Override
    public int compare(Flashcard one, Flashcard two) {
        return two.getTitle().toString().compareTo(one.getTitle().toString());
    }
}

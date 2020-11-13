package seedu.bagel.logic.commands.sort;

import java.util.Comparator;

import seedu.bagel.model.flashcard.Flashcard;

public class SortByTag implements Comparator<Flashcard> {

    @Override
    public int compare(Flashcard one, Flashcard two) {
        if (one.getTags().isEmpty()) {
            if (two.getTags().isEmpty()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (two.getTags().isEmpty()) {
                return 1;
            } else {
                return one.getTags().stream()
                            .sorted(Comparator.comparing(tag -> tag.tagName))
                            .findFirst()
                            .toString()
                        .compareToIgnoreCase(two.getTags().stream()
                                .sorted(Comparator.comparing(tag -> tag.tagName))
                                .findFirst()
                                .toString());
            }
        }
    }
}

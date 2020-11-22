package seedu.forgetfulnus.model;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.model.score.Score;

public interface ReadOnlyScoreList {
    ObservableList<Score> getScoreList();
}

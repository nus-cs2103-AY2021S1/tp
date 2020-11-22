package seedu.forgetfulnus.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.model.ReadOnlyScoreList;
import seedu.forgetfulnus.model.ScoreList;
import seedu.forgetfulnus.model.score.Score;

@JsonRootName(value = "scores")
public class JsonScoreList {

    private final List<JsonAdaptedScore> scores = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableScore} with the given scores.
     */
    @JsonCreator
    public JsonScoreList(@JsonProperty("scores") List<JsonAdaptedScore> scores) {
        this.scores.addAll(scores);
    }

    public JsonScoreList(ReadOnlyScoreList source) {
        scores.addAll(source.getScoreList().stream().map(JsonAdaptedScore::new).collect(Collectors.toList()));
    }

    /**
     * Converts this score list into the model's {@code ScoreList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ScoreList toModelType() throws IllegalValueException {
        ScoreList scoreList = new ScoreList();
        for (JsonAdaptedScore jsonAdaptedScore : scores) {
            Score score = jsonAdaptedScore.toModelType();
            scoreList.addScore(score);
        }
        return scoreList;
    }
}


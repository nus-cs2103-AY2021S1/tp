package seedu.address.model.play.scoring;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.play.Score;

public class QuizAttempt implements Comparable<QuizAttempt> {

    private Score score;
    private int duration; // time in seconds
    private LocalDateTime takenAt;
    private List<QuestionAttempt> questionAttempts;
    private Scoring scoringMethod;

    /**
     * This is the default constructor mostly used for testing purposes.
     */
    public QuizAttempt() {
        questionAttempts = new ArrayList<>();
    }

    /**
     * This is the constructor that we will pass the scoring method to so it knows how to score a
     * new entry when a new entry (questionAttemptt) comes in. Here, scoring means computing the
     * score received based on the difference between what the given answer is and what the correct
     * answer is.
     *
     * @param scoringMethod
     */
    public QuizAttempt(Scoring scoringMethod) {
        this.duration = -1; // to denote quiz in progress
        this.takenAt = LocalDateTime.now();
        this.scoringMethod = scoringMethod;
        questionAttempts = new ArrayList<>();
    }

    public void answerQuestion(String answer, String guess) {
        questionAttempts.add(new QuestionAttempt(answer, guess, scoringMethod));
    }

    /**
     * @param quizLength
     */
    public void endQuiz(int quizLength) {
        this.score = new Score(
            quizLength,
            questionAttempts.stream().mapToDouble(QuestionAttempt::getScore).sum()
        );
        this.duration = (int) this.takenAt.until(LocalDateTime.now(), ChronoUnit.SECONDS);
    }

    public Score getScore() {
        return score;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getTakenAt() {
        return takenAt;
    }

    public List<QuestionAttempt> getQuestionAttempts() {
        return questionAttempts;
    }

    public Scoring getScoringMethod() {
        return scoringMethod;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuizAttempt)) {
            return false;
        }
        QuizAttempt that = (QuizAttempt) o;
        return getDuration() == that.getDuration()
            && Objects.equals(getScore(), that.getScore())
            && Objects.equals(getTakenAt(), that.getTakenAt())
            && Objects.equals(getQuestionAttempts(), that.getQuestionAttempts())
            && Objects.equals(getScoringMethod(), that.getScoringMethod());
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(getScore(), getDuration(), getTakenAt(), getQuestionAttempts(),
                getScoringMethod());
    }

    public void setScoringMethod(Scoring scoringMethod) {
        this.scoringMethod = scoringMethod;
    }

    public void setTakenAt(LocalDateTime takenAt) {
        this.takenAt = takenAt;
    }

    public void setQuestionAttempts(List<QuestionAttempt> questionAttempts) {
        this.questionAttempts = questionAttempts;
    }

    public String getTakenAtAndScore() {
        return takenAt.toString() + ", " + score.toString();
    }

    @Override
    public int compareTo(QuizAttempt other) {
        if (takenAt.compareTo(other.getTakenAt()) == 0) {
            return Double
                .compare(score.getScoreInPercentage(), other.getScore().getScoreInPercentage());
        }

        return takenAt.compareTo((other.getTakenAt()));
    }

    public String getTakenAtAndScoreInPercentage() {
        return takenAt.toString() + ", " + score.getScoreInPercentage();
    }
}


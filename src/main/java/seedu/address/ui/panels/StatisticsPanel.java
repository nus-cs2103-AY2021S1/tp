package seedu.address.ui.panels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.NumberAxis.DefaultFormatter;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.logic.statistics.StatisticsManager;
import seedu.address.model.deck.Deck;
import seedu.address.model.play.scoring.QuizAttempt;
import seedu.address.ui.UiPart;

public class StatisticsPanel extends UiPart<Region> {

    private static final String FXML = "StatisticsPanel.fxml";

    @FXML
    private Label lastLoginLabel;
    @FXML
    private LineChart<String, Number> statisticsLineChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    private String chartTitle;

    private static class DataPoint {

        private double scoreInPercentage;
        private LocalDateTime takenAt;

        DataPoint(LocalDateTime takenAt, double scoreInPercentage) {
            this.scoreInPercentage = scoreInPercentage;
            this.takenAt = takenAt;
        }

        public double getScoreInPercentage() {
            return scoreInPercentage;
        }

        public LocalDateTime getTakenAt() {
            return takenAt;
        }

        public String getTakenAtString() {
            return takenAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd\nHH:mm:ss"));
        }

        @Override
        public String toString() {
            return "DataPoint{" + "scoreInPercentage="
                + scoreInPercentage + ", takenAt=" + takenAt + '}';

        }
    }

    // helper class
    private static class Pair<S extends Comparable<S>, T extends Comparable<T>> implements
        Comparable<Pair<S, T>> {

        private S first;
        private T second;

        Pair(S first, T second) {
            this.first = first;
            this.second = second;
        }

        public S getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }

        public void setSecond(T second) {
            this.second = second;
        }

        public void setFirst(S first) {
            this.first = first;
        }

        @Override
        public int compareTo(Pair<S, T> other) {
            return this.first.compareTo(other.getFirst());
        }
    }

    /**
     * Constructor for statistics panel
     */
    public StatisticsPanel(Logic logic, int indexOfSelectedDeck) {
        super(FXML);

        // System.out.println("deck selected = " + indexOfSelectedDeck);

        ObservableList<Deck> originalDecks = logic.getFilteredDeckList();
        // decide which deck to get, or get all of them if indexOfSelectedDeck==-1
        List<Deck> decks = IntStream.range(0, originalDecks.size())
            .filter(idx -> indexOfSelectedDeck == -1 || idx == indexOfSelectedDeck)
            .mapToObj(originalDecks::get).collect(Collectors.toList());

        if (indexOfSelectedDeck == -1 || decks.size() == 0) {
            chartTitle = "Recent performance over all decks.";
        } else {
            chartTitle = "Recent performance over [" + decks.get(0).getDeckName() + "]";
        }

        StatisticsManager statisticsManager = logic.getStatisticsManager();

        initialize();
        plotDataPoints(getDeckPlottingPoints(decks));

        String lastLoginString = statisticsManager.getLastLoginString();

        //lastLoginLabel.setText(String.format("Last Login: %s", lastLoginString));
    }

    private void initialize() {
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(101);
        yAxis.setTickLabelFormatter(new DefaultFormatter(yAxis, null, "%"));

        statisticsLineChart.setTitle(chartTitle);
    }

    private void plotDataPoints(List<DataPoint> dataPoints) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Score (in %)");
        for (DataPoint dataPoint : dataPoints) {
            series.getData()
                .add(new XYChart.Data<>(dataPoint.getTakenAtString(),
                    dataPoint.getScoreInPercentage()));
        }
        statisticsLineChart.getData().add(series);
    }

    /**
     * Helper function to compute the statistics for plotting/displaying
     */
    private List<DataPoint> getDeckPlottingPoints(List<Deck> decks) {

        List<List<QuizAttempt>> listsToMerge = new ArrayList<>();

        for (Deck deck : decks) {
            listsToMerge.add(deck.getQuizAttempts());
        }

        List<QuizAttempt> merged = mergeSortedListsAndRetrieveFirstK(listsToMerge, 10);
        Collections.reverse(merged);
        List<DataPoint> ret = new ArrayList<>();

        for (var attempt : merged) {
            LocalDateTime takenAt = attempt.getTakenAt();
            double scoreInPercentage = attempt.getScore().getScoreInPercentage();
            ret.add(new DataPoint(takenAt, scoreInPercentage));
        }

        return ret;
    }

    /**
     * Helper function to get the first k latest QuizAttempts by takenAt, amongst a list of lists of
     * QuizAttempts. We assume the lists of QuizAttempts in listsToMerge are sorted ascending order
     * by takenAt so we need to process each list from the end to start. Time complexity: O(k *
     * log(numLists)) as we poll from a PQ of size numLists at most k times to form the return list
     * of size k.
     *
     * @param listsToMerge
     */
    public static List<QuizAttempt> mergeSortedListsAndRetrieveFirstK(
        List<List<QuizAttempt>> listsToMerge, int k) {
        int numLists = listsToMerge.size();
        if (numLists == 0) {
            return new ArrayList<>();
        }
        PriorityQueue<Pair<QuizAttempt, Integer>> pq = new PriorityQueue<>(numLists,
            Collections.reverseOrder());

        int[] ptrs = new int[numLists]; // tracks current pos in each list

        // make the initial pq of size numLists
        // by adding the biggest element (last element, if it exists) of each list
        // to the pq. We also track which list this element is from.
        for (int i = 0; i < numLists; ++i) {
            int idx = listsToMerge.get(i).size() - 1;

            if (idx >= 0) {
                ptrs[i] = idx - 1;
                pq.add(new Pair<>(listsToMerge.get(i).get(idx), i));
            }
        }

        List<QuizAttempt> ret = new ArrayList<>();
        while (!pq.isEmpty()) {
            Pair<QuizAttempt, Integer> pair = pq.poll();
            ret.add(pair.getFirst());
            if (ret.size() == k) {
                // we are done building a list of size k, let's return it
                return ret;
            }

            int listIdx = pair.getSecond(); // this is the list the quiz attempt was taken from

            if (ptrs[listIdx] >= 0) {
                // if we still have elements from listIdx to consider i.e. our pointer has not
                // gone past the start of the list, then we take the element at ptrs[listIdx]
                pq.add(new Pair<>(listsToMerge.get(listIdx).get(ptrs[listIdx]), listIdx));

                // and decrement the pointer position
                ptrs[listIdx]--;
            }
        }

        return ret;
    }
}

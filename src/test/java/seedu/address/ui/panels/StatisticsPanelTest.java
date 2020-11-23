package seedu.address.ui.panels;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.play.Score;
import seedu.address.model.play.scoring.QuizAttempt;

class StatisticsPanelTest {

    private static List<List<QuizAttempt>> listsToMerge;
    private static List<QuizAttempt> correctMostRecent;
    private int maxTotalScore = 100;
    private int minTotalScore = 10;
    private int attempts = 500;
    private int maxLists = 100;
    private int minLists = 10;
    private long startDate = LocalDateTime.of(2020, 5, 1, 0, 0).atZone(ZoneId.systemDefault())
        .toEpochSecond();
    private long endDate = LocalDateTime.of(2020, 12, 1, 0, 0).atZone(ZoneId.systemDefault())
        .toEpochSecond();

    @BeforeEach
    void setUp() {
        listsToMerge = new ArrayList<>();
        Random rand = new Random();
        int numberOfLists = rand.nextInt(maxLists - minLists + 1) + minLists;

        // generate random number of buckets
        for (int i = 0; i < numberOfLists; ++i) {
            listsToMerge.add(new ArrayList<>());
        }

        // generate many random quiz attempts
        // each with randomized scores (between 1 and 100) and takenAts
        // and place each quiz attempt in a random bucket
        for (int i = 0; i < attempts; ++i) {
            QuizAttempt quizAttempt = new QuizAttempt();
            double totalScore =
                rand.nextInt((maxTotalScore - minTotalScore) + 1) + minTotalScore;
            double yourScore = rand.nextDouble() * totalScore;
            Score score = new Score(totalScore, yourScore);
            quizAttempt.setScore(score);
            LocalDateTime takenAt = LocalDateTime
                .ofInstant(Instant.ofEpochSecond(ThreadLocalRandom.current().nextLong(
                    startDate,
                    endDate
                )), ZoneId.systemDefault());
            quizAttempt.setTakenAt(takenAt);
            // System.out.println(quizAttempt.getTakenAtAndScoreInPercentage());
            int listIdx = rand.nextInt(numberOfLists);
            listsToMerge.get(listIdx).add(quizAttempt);
        }

        //for(int i=0;i<numberOfLists;++i){
        //    System.out.println(listsToMerge.get(i).size());
        //}

        for (var list : listsToMerge) {
            list.sort(Comparator.comparing(QuizAttempt::getTakenAt));
        }
        List<QuizAttempt> temp = new ArrayList<>();
        for (var list : listsToMerge) {
            //            try {
            //                System.out.println("==============");
            //                System.out.println(list.get(list.size() - 1).getTakenAtAndScoreInPercentage());
            //                System.out.println(list.get(list.size() - 2).getTakenAtAndScoreInPercentage());
            //                System.out.println(list.get(list.size() - 3).getTakenAtAndScoreInPercentage());
            //                System.out.println("==============");
            //            } catch (Exception ignored) {
            //            }
            temp.addAll(list);
        }
        temp.sort(Comparator.comparing(QuizAttempt::getTakenAt).reversed());
        correctMostRecent = temp;
        //        correctMostRecent = temp.subList(0, 10); // this is our correct 10 most recent elements

    }

    @Test
    void testMergeSortedListsAndRetrieveFirstK_forKEquals10_shouldGiveCorrectResult() {
        int k = 10;
        List<QuizAttempt> ans = StatisticsPanel.mergeSortedListsAndRetrieveFirstK(listsToMerge, k);
        //        for (int i = 0; i < k; ++i) {
        //            System.out.println(correctMostRecent.get(i).getTakenAtAndScoreInPercentage());
        //        }
        //        System.out.println("================");
        //        for (var i : ans) {
        //            System.out.println(i.getTakenAtAndScoreInPercentage());
        //        }
        for (int i = 0; i < Math.min(ans.size(), k); ++i) {
            //            System.out.println(
            //                "-----------" + i + ", " + ans.get(i).getTakenAtAndScoreInPercentage() + " | "
            //                    + correctMostRecent.get(i).getTakenAtAndScoreInPercentage());
            assertEquals(
                correctMostRecent.get(i).getTakenAtAndScoreInPercentage(), ans.get(i)
                    .getTakenAtAndScoreInPercentage());
        }
    }

    @Test
    void testMergeSortedListsAndRetrieveFirstK_forKEquals20_shouldGiveCorrectResult() {
        int k = 20;
        List<QuizAttempt> ans = StatisticsPanel.mergeSortedListsAndRetrieveFirstK(listsToMerge, k);
        //        for (int i = 0; i < k; ++i) {
        //            System.out.println(correctMostRecent.get(i).getTakenAtAndScoreInPercentage());
        //        }
        //        System.out.println("================");
        //        for (var i : ans) {
        //            System.out.println(i.getTakenAtAndScoreInPercentage());
        //        }
        for (int i = 0; i < Math.min(ans.size(), k); ++i) {
            //            System.out
            //                .println("===========" + i + ", " + ans.get(i).getTakenAtAndScoreInPercentage());
            assertEquals(
                correctMostRecent.get(i).getTakenAtAndScoreInPercentage(), ans.get(i)
                    .getTakenAtAndScoreInPercentage());
        }
    }

    @Test
    void testMergeSortedListsAndRetrieveFirstK_forKEquals30_shouldGiveCorrectResult() {
        int k = 30;
        List<QuizAttempt> ans = StatisticsPanel.mergeSortedListsAndRetrieveFirstK(listsToMerge, k);
        //        for (int i = 0; i < k; ++i) {
        //            System.out.println(correctMostRecent.get(i).getTakenAtAndScoreInPercentage());
        //        }
        //        System.out.println("================");
        //        for (var i : ans) {
        //            System.out.println(i.getTakenAtAndScoreInPercentage());
        //        }
        for (int i = 0; i < Math.min(ans.size(), k); ++i) {
            assertEquals(
                correctMostRecent.get(i).getTakenAtAndScoreInPercentage(), ans.get(i)
                    .getTakenAtAndScoreInPercentage());
        }
    }

    @Test
    void testMergeSortedListsAndRetrieveFirstK_forKEquals50_shouldGiveCorrectResult() {
        int k = 50;
        List<QuizAttempt> ans = StatisticsPanel.mergeSortedListsAndRetrieveFirstK(listsToMerge, k);
        //        for (int i = 0; i < k; ++i) {
        //            System.out.println(correctMostRecent.get(i).getTakenAtAndScoreInPercentage());
        //        }
        //        System.out.println("================");
        //        for (var i : ans) {
        //            System.out.println(i.getTakenAtAndScoreInPercentage());
        //        }
        for (int i = 0; i < Math.min(ans.size(), k); ++i) {
            assertEquals(
                correctMostRecent.get(i).getTakenAtAndScoreInPercentage(), ans.get(i)
                    .getTakenAtAndScoreInPercentage());
        }
    }

    @Test
    void testMergeSortedListsAndRetrieveFirstK_forKEquals100_shouldGiveCorrectResult() {
        int k = 100;
        List<QuizAttempt> ans = StatisticsPanel.mergeSortedListsAndRetrieveFirstK(listsToMerge, k);
        //        for (int i = 0; i < k; ++i) {
        //            System.out.println(correctMostRecent.get(i).getTakenAtAndScoreInPercentage());
        //        }
        //        System.out.println("================");
        //        for (var i : ans) {
        //            System.out.println(i.getTakenAtAndScoreInPercentage());
        //        }
        for (int i = 0; i < Math.min(ans.size(), k); ++i) {
            assertEquals(
                correctMostRecent.get(i).getTakenAtAndScoreInPercentage(), ans.get(i)
                    .getTakenAtAndScoreInPercentage());
        }
    }

    @AfterEach
    void tearDown() {
    }
}

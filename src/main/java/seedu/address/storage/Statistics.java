package seedu.address.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Planus;
import seedu.address.model.StatisticsData;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.event.Event;


/**
 * This class handles all computation related to time spent on tasks and lessons.
 */
public class Statistics {
    private static final Logger logger = LogsCenter.getLogger(JsonPlanusStorage.class);
    private static final Path filePath = Paths.get("data", "planus.json");
    /**
     * Computes total duration of time spent on all tasks and lessons for the specified time period and
     * stores it in a data structure.
     */
    public static StatisticsData generateStatistics(LocalDate startDate, LocalDate endDate) {
        Planus planus = null;
        StatisticsData stats = new StatisticsData();
        try {
            JsonSerializablePlanus dataFromFile = JsonUtil.readJsonFile(filePath,
                    JsonSerializablePlanus.class).get();
            planus = dataFromFile.toModelType();
            System.out.println(planus);
        } catch (Exception e) {
            logger.info("Failed to read PlaNus storage file in " + filePath + ": " + e.getMessage());
        }
        if (planus == null) {
            logger.info("Unable to get statistics summary as storage file is not found.");
            return null;
        }
        logger.info("Generating summary for statistics.");
        //TODO: update this after implementing filtering
        updateTimeTakenForTasks(planus.getTaskList(), stats);
        updateTimeTakenForLessons(planus.getLessonList(), stats);
        logger.info("Generated statistics \n" + stats.toString());
        System.out.println(stats.toString());
        return stats;
    }
//    private static FilteredList<Task> filterTasksWithinPeriod(ObservableList<Task> tasks, LocalDate start,
//                                                              LocalTime end) {
//        return tasks.filtered(task -> {
//            if (task instanceof Event) {
//                return ((Event)task).getStartDateTime().(start)
//                        && ;
//            } else {
//                
//            }
//        })
//    }
    
    public static void updateTimeTakenForTasks(ObservableList<Task> tasks, StatisticsData stats) {
        for (Task task: tasks) {
            Tag currentTag = task.getTag();
            if (!stats.contains(currentTag)) {
                stats.addTag(currentTag);
            }
            stats.addTaskTime(currentTag, task.getTimeTaken());
        }
    }

    public static void updateTimeTakenForLessons(ObservableList<Lesson> lessons, StatisticsData stats) {
        for (Lesson lesson: lessons) {
            Tag currentTag = lesson.getTag();
            if (!stats.contains(currentTag)) {
                stats.addTag(currentTag);
            }
            stats.addLessonTime(currentTag, lesson.getTimeTaken());
        }
    }

    public static void main(String args[]) {
        //test
        try {
            generateStatistics(ParserUtil.parseDate("11-11-1111"),
                    ParserUtil.parseDate("01-05-2020"));
        } catch (Exception e) {
            System.out.println(e);
        }
            
    }

}

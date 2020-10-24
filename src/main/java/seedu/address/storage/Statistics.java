package seedu.address.storage;

import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Planus;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * This class handles all computation related to time spent on tasks and lessons.
 */
public class Statistics {

    /**
     * Computes total duration of time spent on all tasks and lessons for the specified time period.
     */
    public static Integer tabulateTotalDuration(LocalDate startDate, LocalDate endDate) {
//        JsonSerializablePlanus dataFromFile = JsonUtil.readJsonFile(,
//                JsonSerializablePlanus.class).get();
//        Planus planusFromFile = dataFromFile.toModelType();
        return null;
    }
    /**
     * Computes total duration of time spent on all tasks and lessons under each module tag for the specified
     * time period.
     */
    public static HashMap<Tag, Integer> tabulateTimeSpentOnEachModule(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    public static void main(String args[]) {
        Path filePath = Paths.get("data", "planus.json");
        try {
            JsonSerializablePlanus dataFromFile = JsonUtil.readJsonFile(filePath,
                    JsonSerializablePlanus.class).get();
            Planus planus = dataFromFile.toModelType();
            System.out.println(planus);
            HashMap<Tag, Integer[]> stats = new HashMap<>();
            for (Task task: planus.getTaskList()) {
                Tag currentTag = task.getTag();
                int currentDuration = task.getDuration();
                if(stats.containsKey(currentTag)) {
                    Integer[] res = stats.get(currentTag);
//                    res[0] = currentDuration.durationInMinutes;
                } else {
//                    stats.put()
                }
            }
            for (Lesson lesson: planus.getLessonList()) {
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

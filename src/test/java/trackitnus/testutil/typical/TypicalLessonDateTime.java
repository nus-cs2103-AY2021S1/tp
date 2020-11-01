package trackitnus.testutil.typical;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import trackitnus.model.lesson.DayOfWeek;
import trackitnus.model.lesson.LessonDateTime;

public class TypicalLessonDateTime {
    private static final List<LessonDateTime> times = new ArrayList<>();

    public static List<LessonDateTime> get() {
        if (!times.isEmpty()) {
            return times;
        }
        times.add(new LessonDateTime(DayOfWeek.Mon, LocalTime.of(8, 0), LocalTime.of(10, 0)));
        times.add(new LessonDateTime(DayOfWeek.Mon, LocalTime.of(10, 0), LocalTime.of(12, 0)));
        times.add(new LessonDateTime(DayOfWeek.Mon, LocalTime.of(12, 0), LocalTime.of(15, 0)));
        return times;
    }
}

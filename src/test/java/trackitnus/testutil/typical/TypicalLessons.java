package trackitnus.testutil.typical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import trackitnus.model.lesson.Lesson;
import trackitnus.model.lesson.LessonDateTime;
import trackitnus.model.lesson.Type;
import trackitnus.model.module.Module;

public class TypicalLessons {
    private static final List<Lesson> lessons = new ArrayList<>();
    private static final int NUM_LESSON = 10;

    public static List<Lesson> get() {
        if (!lessons.isEmpty()) {
            return lessons;
        }
        List<Module> modules = TypicalModule.get();
        Type[] types = Type.values();
        List<LessonDateTime> times = TypicalLessonDateTime.get();
        int cnt = 0;
        for (Module module : modules) {
            for (Type type : types) {
                for (LessonDateTime time : times) {
                    lessons.add(new Lesson(module.getCode(), type, time));
                }
            }
        }
        Collections.shuffle(lessons, new Random(0));
        while (lessons.size() > NUM_LESSON) {
            lessons.remove(lessons.size() - 1);
        }
        return lessons;
    }
}

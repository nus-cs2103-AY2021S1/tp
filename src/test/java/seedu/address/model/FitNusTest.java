package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.GES1028;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.exceptions.DuplicateLessonException;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Weight;
import seedu.address.model.routine.Routine;
import seedu.address.model.slot.Slot;
import seedu.address.testutil.LessonBuilder;

public class FitNusTest {

    private final FitNus fitNus = new FitNus();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fitNus.getLessonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitNus.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFitNus_replacesData() {
        FitNus newData = getTypicalFitNus();
        fitNus.resetData(newData);
        assertEquals(newData, fitNus);
    }

    @Test
    public void resetData_withDuplicateLessons_throwsDuplicateLessonException() {
        // Two lessons with the same identity fields
        Lesson editedGes1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_EASY).build();
        List<Lesson> newLessons = Arrays.asList(GES1028, editedGes1028);
        FitNusStub newData = new FitNusStub(newLessons);

        assertThrows(DuplicateLessonException.class, () -> fitNus.resetData(newData));
    }

    @Test
    public void hasLesson_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitNus.hasLesson(null));
    }

    @Test
    public void hasLesson_lessonNotInFitNus_returnsFalse() {
        assertFalse(fitNus.hasLesson(GES1028));
    }

    @Test
    public void hasLesson_lessonInFitNus_returnsTrue() {
        fitNus.addLesson(GES1028);
        assertTrue(fitNus.hasLesson(GES1028));
    }

    @Test
    public void hasLesson_lessonWithSameIdentityFieldsInFitNus_returnsTrue() {
        fitNus.addLesson(GES1028);
        Lesson editedGes1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_EASY)
                .build();
        assertTrue(fitNus.hasLesson(editedGes1028));
    }



    @Test
    public void getLessonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fitNus.getLessonList().remove(0));
    }


    //Height, Weight and BMI testing

    @Test
    public void bmiTesting() {

        //This is using fitNUS values when it first starts up
        Double bmi = (45 / Math.pow((160 / 100.0), 2));
        assertEquals(fitNus.getBmi(), bmi);
    }

    @Test
    public void heightTesting() {
        assertEquals(fitNus.getHeight(), new Height(160));

        fitNus.addHeight(new Height(170));

        assertEquals(fitNus.getHeight(), new Height(170));
    }

    @Test
    public void weightTesting() {
        assertEquals(fitNus.getWeight(), new Weight(45.0));

        fitNus.addWeight(new Weight(80));

        assertEquals(fitNus.getWeight(),new Weight(80));
    }

    //Calorie testing

    @Test
    public void calorieTest() {
        FitNus modelFitNus = new FitNus();

        //Before adding any calorie
        assertEquals(modelFitNus.getCalories(), 0);
        assertEquals(modelFitNus.getDailyCalorieList().size(), 0);

        //After adding calorie
        modelFitNus.addCalories(1000);
        assertEquals(modelFitNus.getCalories(), 1000);
        assertEquals(modelFitNus.getDailyCalorieList().size(), 1);

        //After deducting calories, calorie count should change but entry must remain
        modelFitNus.minusCalories(1000);
        assertEquals(modelFitNus.getCalories(), 0);
        assertEquals(modelFitNus.getDailyCalorieList().size(), 1);
    }

    @Test
    public void settingCalorieLogTest() {

        FitNus modelFitNus = new FitNus();

        DailyCalorie modelDailyCalorie = new DailyCalorie(LocalDate.of(2020,7,3));
        DailyCalorie modelDailyCalorie1 = new DailyCalorie(LocalDate.of(2020,7,4));
        DailyCalorie modelDailyCalorie2 = new DailyCalorie(LocalDate.of(2020,7,5));
        DailyCalorie modelDailyCalorie3 = new DailyCalorie(LocalDate.of(2020,7,6));
        DailyCalorie modelDailyCalorie4 = new DailyCalorie(LocalDate.of(2020,7,7));
        DailyCalorie modelDailyCalorie5 = new DailyCalorie(LocalDate.of(2020,7,8));
        DailyCalorie modelDailyCalorie6 = new DailyCalorie(LocalDate.of(2020,7,9));

        List<DailyCalorie> modelCalorieLog = new ArrayList<>();
        modelCalorieLog.add(modelDailyCalorie);
        modelCalorieLog.add(modelDailyCalorie1);
        modelCalorieLog.add(modelDailyCalorie2);
        modelCalorieLog.add(modelDailyCalorie3);
        modelCalorieLog.add(modelDailyCalorie4);
        modelCalorieLog.add(modelDailyCalorie5);
        modelCalorieLog.add(modelDailyCalorie6);

        modelFitNus.addCalorieEntries(modelCalorieLog);
        assertEquals(modelFitNus.getDailyCalorieList().size(), 7);

        //Adding a new calorie entry will delete oldest one and maintain a 7 day long log.
        assertTrue(modelFitNus.hasDailyCalorie(modelDailyCalorie));
        modelFitNus.addCalories(1000);
        assertEquals(modelFitNus.getCalories(), 1000);
        assertEquals(modelFitNus.getDailyCalorieList().size(), 7);
        assertFalse(modelFitNus.hasDailyCalorie(modelDailyCalorie));
    }

    /**
     * A stub ReadOnlyFitNus whose persons list can violate interface constraints.
     */
    private static class FitNusStub implements ReadOnlyFitNus {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        private final ObservableList<Routine> routines = FXCollections.observableArrayList();
        private final ObservableList<Slot> slots = FXCollections.observableArrayList();
        private final ObservableList<DailyCalorie> calorieLog = FXCollections.observableArrayList();
        private final ObservableList<Body> body = FXCollections.observableArrayList();

        FitNusStub(Collection<Lesson> lessons) {
            this.lessons.setAll(lessons);
        }


        @Override
        public ObservableList<Exercise> getExerciseList() {
            return exercises;
        }

        @Override
        public ObservableList<Routine> getRoutineList() {
            return routines;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }

        @Override
        public ObservableList<Slot> getSlotList() {
            return slots;
        }

        @Override
        public ObservableList<DailyCalorie> getDailyCalorieList() {
            return calorieLog;
        }

        @Override
        public ObservableList<Body> getBody() {
            return body;
        }
    }
}

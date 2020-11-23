package seedu.tr4cker.model.planner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PlannerDayTest {

    private final LocalDate localDate1 = LocalDate.of(2021, 1, 1);
    private final PlannerDay plannerDay1 = new PlannerDay(localDate1);
    private final LocalDate localDate2 = LocalDate.of(2021, 2, 14);
    private final PlannerDay plannerDay2 = new PlannerDay(localDate2);
    private final LocalDate localDate3 = LocalDate.of(2021, 12, 6);
    private final PlannerDay plannerDay3 = new PlannerDay(localDate3);
    private final PlannerDay plannerDay4 = new PlannerDay(localDate3);

    @Test
    public void getLocalDate_success() {
        assertEquals(localDate1, plannerDay1.getLocalDate());
        assertEquals(localDate2, plannerDay2.getLocalDate());
        assertEquals(localDate3, plannerDay3.getLocalDate());
    }

    @Test
    public void getDay_success() {
        assertEquals(plannerDay1.getDay(), 1);
        assertEquals(plannerDay2.getDay(), 14);
        assertEquals(plannerDay3.getDay(), 6);
    }

    @Test
    public void getDayOfWeek_success() {
        assertEquals(plannerDay1.getDayOfWeek(), 5);
        assertEquals(plannerDay2.getDayOfWeek(), 7);
        assertEquals(plannerDay3.getDayOfWeek(), 1);
    }

    @Test
    public void getMonth_success() {
        assertEquals(plannerDay1.getMonth(), 1);
        assertEquals(plannerDay2.getMonth(), 2);
        assertEquals(plannerDay3.getMonth(), 12);
    }

    @Test
    public void getMonthName_success() {
        assertEquals(plannerDay1.getMonthName(), "JANUARY");
        assertEquals(plannerDay2.getMonthName(), "FEBRUARY");
        assertEquals(plannerDay3.getMonthName(), "DECEMBER");
    }

    @Test
    public void getYear_success() {
        assertEquals(plannerDay1.getYear(), 2021);
        assertEquals(plannerDay2.getYear(), 2021);
        assertEquals(plannerDay3.getYear(), 2021);
    }

    @Test
    public void createFirstDayOfMonth_success() {
        LocalDate test1 = LocalDate.of(2021, 1, 1);
        LocalDate test2 = LocalDate.of(2021, 2, 1);
        LocalDate test3 = LocalDate.of(2021, 12, 1);
        assertEquals(plannerDay1.createFirstDayOfMonth(), new PlannerDay(test1));
        assertEquals(plannerDay2.createFirstDayOfMonth(), new PlannerDay(test2));
        assertEquals(plannerDay3.createFirstDayOfMonth(), new PlannerDay(test3));
    }

    @Test
    public void getCurrDay_success() {
        PlannerDay day1 = PlannerDay.getCurrDay();
        PlannerDay day2 = PlannerDay.getCurrDay();
        assertEquals(day1, day2);
    }

    @Test
    public void getPrevDay_success() {
        LocalDate test1 = LocalDate.of(2020, 12, 31);
        LocalDate test2 = LocalDate.of(2021, 2, 13);
        LocalDate test3 = LocalDate.of(2021, 12, 5);
        assertEquals(plannerDay1.getPrevDay(), new PlannerDay(test1));
        assertEquals(plannerDay2.getPrevDay(), new PlannerDay(test2));
        assertEquals(plannerDay3.getPrevDay(), new PlannerDay(test3));
    }

    @Test
    public void getNextDay_success() {
        LocalDate test1 = LocalDate.of(2021, 1, 2);
        LocalDate test2 = LocalDate.of(2021, 2, 15);
        LocalDate test3 = LocalDate.of(2021, 12, 7);
        assertEquals(plannerDay1.getNextDay(), new PlannerDay(test1));
        assertEquals(plannerDay2.getNextDay(), new PlannerDay(test2));
        assertEquals(plannerDay3.getNextDay(), new PlannerDay(test3));
    }

    @Test
    public void equalsTest() {
        assertEquals(plannerDay3, plannerDay4);
        assertNotEquals(plannerDay1, null);
        assertNotEquals(plannerDay3, 5);
    }

    @Test
    public void stringTest() {
        assertEquals(plannerDay3.toString(), plannerDay4.toString());
    }

}

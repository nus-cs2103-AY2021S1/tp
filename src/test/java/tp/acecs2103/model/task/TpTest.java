package tp.acecs2103.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TpTest {
    private TP task1 = new TP(new Index("0101"), new WeekNumber("1"), new Description("Test Task One"),
            new OfficialDeadline("2020-09-10", LocalDate.of(2020, 9, 10)),
            null, new Remark("no remark"), false, false);

    @Test
    void getCategory_null_correctTaskCategory() {
        assertEquals(TaskCategory.TP, task1.getCategory());
    }
}

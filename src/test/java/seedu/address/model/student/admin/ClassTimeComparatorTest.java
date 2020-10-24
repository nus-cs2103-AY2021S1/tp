package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class ClassTimeComparatorTest {

    private ClassTimeComparator cmp = new ClassTimeComparator();

    private Student wedEight = new StudentBuilder().withName("Eve").withSchool("Bishan Primary School")
            .withClassTime("3 0800-1000").build();
    private Student thursTen = new StudentBuilder().withName("Alice").withSchool("SST")
            .withClassTime("4 1000-1200").build();
    private Student friTwelve = new StudentBuilder().withName("aloysius").withSchool("SST")
            .withClassTime("5 1200-1400").build();
    private Student monTen = new StudentBuilder().withName("dominic").withSchool("SOTA")
            .withClassTime("1 1000-1300").build();
    private Student tuesNine = new StudentBuilder().withName("pikachu").withSchool("Trainers School")
            .withClassTime("2 0900-1700").build();
    private Student monThirteen = new StudentBuilder().withName("Bob").withSchool("Innova JC")
            .withClassTime("1 1300-1400").build();

    @Test
    public void test_smallerThan_lesThanZero() {
        assertTrue(cmp.compare(tuesNine, wedEight) < 0);
        assertTrue(cmp.compare(tuesNine, thursTen) < 0);
        assertTrue(cmp.compare(monTen, monThirteen) < 0);
        assertTrue(cmp.compare(thursTen, friTwelve) < 0);
    }

    @Test
    public void test_largerThan_greaterThanZero() {
        assertTrue(cmp.compare(wedEight, tuesNine) > 0);
        assertTrue(cmp.compare(thursTen, monThirteen) > 0);
    }
}

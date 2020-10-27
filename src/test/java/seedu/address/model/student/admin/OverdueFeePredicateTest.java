package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class OverdueFeePredicateTest {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d/M/yy");

    @Test
    public void test_studentHasNotPaid_returnsTrue() {
        LocalDate overDue = LocalDate.now().minusMonths(1).minusDays(1);
        Student unpaid = new StudentBuilder().withPaymentDate(overDue.format(FORMAT)).build();
        OverdueFeePredicate predicate = new OverdueFeePredicate();
        assertTrue(predicate.test(unpaid));

        overDue = LocalDate.now().minusYears(1);
        unpaid = new StudentBuilder().withPaymentDate(overDue.format(FORMAT)).build();
        assertTrue(predicate.test(unpaid));

        overDue = LocalDate.now().minusDays(32);
        unpaid = new StudentBuilder().withPaymentDate(overDue.format(FORMAT)).build();
        assertTrue(predicate.test(unpaid));

        overDue = LocalDate.now().minusWeeks(5);
        unpaid = new StudentBuilder().withPaymentDate(overDue.format(FORMAT)).build();
        assertTrue(predicate.test(unpaid));
    }

    @Test
    public void test_studentHasPaid_returnFalse() {
        LocalDate paidDate = LocalDate.now();
        Student paid = new StudentBuilder().withPaymentDate(paidDate.format(FORMAT)).build();
        OverdueFeePredicate predicate = new OverdueFeePredicate();
        assertFalse(predicate.test(paid));

        paidDate = LocalDate.now().minusDays(10);
        paid = new StudentBuilder().withPaymentDate(paidDate.format(FORMAT)).build();
        assertFalse(predicate.test(paid));

        paidDate = LocalDate.now().minusMonths(1).plusDays(1);
        paid = new StudentBuilder().withPaymentDate(paidDate.format(FORMAT)).build();
        assertFalse(predicate.test(paid));
    }

}

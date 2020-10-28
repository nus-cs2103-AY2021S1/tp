package seedu.address.model.util;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventRecurrence;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.ClassTime;

import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ClassTimeToVEventConverter {

    public static List<VEvent> convertClassTimeToVEvent(List<Student> students) {
        requireNonNull(students);
        EventRecurrence defaultRecur = EventRecurrence.WEEKLY;
        List<VEvent> convertedVEvents = new ArrayList<>();
        for(int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            ClassTime classTime = student.getAdmin().getClassTime();
            VEvent vEvent = new VEvent();
            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        }

        return null;

    }

}

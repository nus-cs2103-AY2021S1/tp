package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.Assignment;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ZoomDescriptorBuilder;
import seedu.address.testutil.gradetracker.AssignmentBuilder;
import seedu.address.ui.DisplayZoomLink;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ViewCommandResultTest {

    @Test
    public void setNullModule_false() {
        ViewCommandResult commandResult = new ViewCommandResult("feedback");
        assertThrows(NullPointerException.class, () -> commandResult.setModule(null));
    }

    @Test
    public void setNullAssignments_false() {
        ViewCommandResult commandResult = new ViewCommandResult("feedback");
        assertThrows(NullPointerException.class, () -> commandResult.setAssignments(null));
    }
    @Test
    public void setNullDisplayZoomLinks_false() {
        ViewCommandResult commandResult = new ViewCommandResult("feedback");
        assertThrows(NullPointerException.class, () -> commandResult.setDisplayZoomLinks(null));
    }

    @Test
    public void setDetails_true() {
        ViewCommandResult commandResult = new ViewCommandResult("feedback");
        Module module = new ModuleBuilder().build();

        ZoomLink zoomLink = new ZoomDescriptorBuilder().build().getZoomLink();
        ModuleLesson moduleLesson = new ModuleLesson("Lecture");
        DisplayZoomLink displayZoomLink = new DisplayZoomLink(moduleLesson, zoomLink);
        List<DisplayZoomLink> displayZoomLinkList = new ArrayList<>();
        displayZoomLinkList.add(displayZoomLink);

        Assignment assignment = new AssignmentBuilder().build();
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList.add(assignment);

        commandResult.setModule(module);
        commandResult.setDisplayZoomLinks(displayZoomLinkList);
        commandResult.setAssignments(assignmentList);
        commandResult.setTextArea("feedback");

        assertEquals(module, commandResult.getModule());
        assertEquals(displayZoomLinkList, commandResult.getDisplayZoomLinks());
        assertEquals(assignmentList, commandResult.getAssignments());
        assertEquals("feedback", commandResult.getTextArea());
    }

    @Test
    public void equals() {
        ViewCommandResult commandResult = new ViewCommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new ViewCommandResult("feedback")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new ViewCommandResult("different")));
    }

    @Test
    public void hashcode() {
        ViewCommandResult commandResult = new ViewCommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new ViewCommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new ViewCommandResult("different").hashCode());
    }
}

package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.applicant.TypicalApplicants.getTypicalApplicantDatabase;
import static com.eva.testutil.staff.TypicalStaffs.getTypicalStaffDatabase;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.comment.Comment;
import com.eva.model.current.view.CurrentViewApplicant;
import com.eva.model.current.view.CurrentViewStaff;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.staff.Staff;
import com.eva.testutil.ApplicantBuilder;
import com.eva.testutil.CommentPersonDescriptorBuilder;
import com.eva.testutil.staff.StaffBuilder;





public class DeleteCommentCommandTest {

    public static final Index INDEX = Index.fromZeroBased(1);
    public static final Staff STAFF = new StaffBuilder().build();
    public static final CommentCommand.CommentPersonDescriptor DESCRIPTOR =
            new CommentPersonDescriptorBuilder(STAFF).build();
    public static final List<Comment> COMMENT_LIST = new ArrayList<>(STAFF.getComments());
    public static final Applicant APPLICANT = new ApplicantBuilder().build();
    public static final CommentCommand.CommentPersonDescriptor APPLICANT_DESCRIPTOR =
            new CommentPersonDescriptorBuilder(APPLICANT).build();
    private Model model;

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommentCommand(null, DESCRIPTOR));
    }

    @Test
    public void constructor_nullLeaveList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLeaveCommand(INDEX, null));
    }

    @Test
    public void execute_commentDeletedByModelOnStaffList_deleteSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());

        Person staffToDeleteComment = expectedModel.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person description = new Staff(staffToDeleteComment.getName(),
                staffToDeleteComment.getPhone(),
                staffToDeleteComment.getEmail(),
                staffToDeleteComment.getAddress(),
                staffToDeleteComment.getTags(),
                staffToDeleteComment.getComments());
        CommentCommand.CommentPersonDescriptor editedperson = new CommentPersonDescriptorBuilder(description).build();
        Person staff = DeleteCommentCommand.createDeleteEditedPerson(staffToDeleteComment, editedperson);
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(INDEX_FIRST_PERSON, editedperson);

        expectedModel.setStaff((Staff) staffToDeleteComment, (Staff) staff);
        String expectedMessage = String.format(DeleteCommentCommand.MESSAGE_DELETE_COMMENT_SUCCESS_STAFF,
                editedperson.getCommentTitle(), staff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        assertCommandSuccess(deleteCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_commentDeletedByModelOnStaffProfile_deleteSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setPanelState(PanelState.STAFF_PROFILE);

        Person staffToDeleteComment = expectedModel.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person description = new Staff(staffToDeleteComment.getName(),
                staffToDeleteComment.getPhone(),
                staffToDeleteComment.getEmail(),
                staffToDeleteComment.getAddress(),
                staffToDeleteComment.getTags(),
                staffToDeleteComment.getComments());
        CommentCommand.CommentPersonDescriptor editedperson = new CommentPersonDescriptorBuilder(description).build();
        Person staff = DeleteCommentCommand.createDeleteEditedPerson(staffToDeleteComment, editedperson);
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(INDEX_FIRST_PERSON, editedperson);

        expectedModel.setStaff((Staff) staffToDeleteComment, (Staff) staff);
        expectedModel.setCurrentViewStaff(new CurrentViewStaff((Staff) staff, INDEX_FIRST_PERSON));

        String expectedMessage = String.format(DeleteCommentCommand.MESSAGE_DELETE_COMMENT_SUCCESS_STAFF,
                editedperson.getCommentTitle(), staff);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.STAFF_PROFILE);
        assertCommandSuccess(deleteCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_commentDeletedByModelOnApplicantList_deleteSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());

        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        Person applicantToDeleteComment =
                expectedModel.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person description = new Applicant(applicantToDeleteComment.getName(),
                applicantToDeleteComment.getPhone(),
                applicantToDeleteComment.getEmail(),
                applicantToDeleteComment.getAddress(),
                applicantToDeleteComment.getTags(),
                applicantToDeleteComment.getComments(),
                new ApplicationStatus("received"));
        CommentCommand.CommentPersonDescriptor editedperson = new CommentPersonDescriptorBuilder(description).build();
        Person applicant = DeleteCommentCommand.createDeleteEditedPerson(applicantToDeleteComment, editedperson);
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(INDEX_FIRST_PERSON, editedperson);

        expectedModel.setApplicant((Applicant) applicantToDeleteComment, (Applicant) applicant);
        String expectedMessage = String.format(DeleteCommentCommand.MESSAGE_DELETE_COMMENT_SUCCESS_APPLICANT,
                editedperson.getCommentTitle(), applicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandSuccess(deleteCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_commentDeletedByModelOnApplicantProfile_deleteSuccessful() throws Exception {
        ModelManager expectedModel = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        expectedModel.setPanelState(PanelState.APPLICANT_PROFILE);

        Person applicantToDeleteComment =
                expectedModel.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person description = new Applicant(applicantToDeleteComment.getName(),
                applicantToDeleteComment.getPhone(),
                applicantToDeleteComment.getEmail(),
                applicantToDeleteComment.getAddress(),
                applicantToDeleteComment.getTags(),
                applicantToDeleteComment.getComments(),
                new ApplicationStatus("received"));
        CommentCommand.CommentPersonDescriptor editedperson = new CommentPersonDescriptorBuilder(description).build();
        Person applicant = DeleteCommentCommand.createDeleteEditedPerson(applicantToDeleteComment, editedperson);
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(INDEX_FIRST_PERSON, editedperson);

        expectedModel.setApplicant((Applicant) applicantToDeleteComment, (Applicant) applicant);
        expectedModel.setCurrentViewApplicant(new CurrentViewApplicant((Applicant) applicant, INDEX_FIRST_PERSON));

        String expectedMessage = String.format(DeleteCommentCommand.MESSAGE_DELETE_COMMENT_SUCCESS_APPLICANT,
                editedperson.getCommentTitle(), applicant);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        model.setPanelState(PanelState.APPLICANT_PROFILE);
        assertCommandSuccess(deleteCommentCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredStaffList_throwsCommandException() {
        model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
                getTypicalApplicantDatabase(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStaffList().size() + 1);
        DeleteCommentCommand deleteCommentCommand = new DeleteCommentCommand(outOfBoundIndex, DESCRIPTOR);
        assertCommandFailure(deleteCommentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}

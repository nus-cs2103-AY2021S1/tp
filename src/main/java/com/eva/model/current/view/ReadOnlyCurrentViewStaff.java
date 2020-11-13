package com.eva.model.current.view;

import java.util.Optional;

import com.eva.commons.core.index.Index;
import com.eva.model.comment.Comment;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a currentViewStaff.
 */
public interface ReadOnlyCurrentViewStaff {
    /**
     * Returns the current view staff encapsulated in an Optional wrapper class.
     */
    Optional<Staff> getCurrentView();

    /**
     * Returns an unmodifiable view of the Comment list.
     * This list will not contain any duplicate comments.
     */
    ObservableList<Comment> getCommentList();

    /**
     * Returns an unmodifiable view of the Leave list.
     * This list will not contain any duplicate leaves.
     */
    ObservableList<Leave> getLeaveList();

    Index getIndex();
}

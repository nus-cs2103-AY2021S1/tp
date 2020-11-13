package com.eva.model.current.view;

import java.util.Optional;

import com.eva.commons.core.index.Index;
import com.eva.model.comment.Comment;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.application.Application;

import javafx.collections.ObservableList;


/**
 * Unmodifiable view of a currentViewApplicant.
 */
public interface ReadOnlyCurrentViewApplicant {
    /**
     * Returns the current view applicant encapsulated in an Optional wrapper class.
     */
    Optional<Applicant> getCurrentView();

    /**
     * Returns an unmodifiable view of the Comment list.
     * This list will not contain any duplicate comments.
     */
    ObservableList<Comment> getCommentList();

    /**
     * Returns the application of the current view applicant.
     */
    Application getApplication();

    Index getIndex();
}

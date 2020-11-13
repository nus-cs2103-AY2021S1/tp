package com.eva.model.current.view;

import java.util.Objects;
import java.util.Optional;

import com.eva.commons.core.index.Index;
import com.eva.model.comment.Comment;
import com.eva.model.comment.exceptions.CommentNotFoundException;
import com.eva.model.comment.view.UniqueCommentsList;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.application.Application;

import javafx.collections.ObservableList;

public class CurrentViewApplicant implements ReadOnlyCurrentViewApplicant {

    private final Applicant currentView;
    private final Application application;
    private final UniqueCommentsList comments;
    private final Index index;

    /**
     * Creates an empty currentViewApplicant.
     */
    public CurrentViewApplicant() {
        this.currentView = null;
        this.application = new Application();
        this.comments = new UniqueCommentsList();
        this.index = Index.fromZeroBased(0);
    }

    /**
     * Creates a currentView with the current viewing applicant.
     */
    public CurrentViewApplicant(Applicant currentView, Index index) {
        this.currentView = currentView;
        this.application = currentView.getApplication();
        this.comments = new UniqueCommentsList();
        this.comments.fill(currentView.getComments());
        this.index = index;
    }

    public Optional<Applicant> getCurrentView() {
        return Optional.ofNullable(currentView);
    }

    public Index getIndex() {
        return this.index;
    }

    public Application getApplication() {
        return application;
    }

    public ObservableList<Comment> getCommentList() throws CommentNotFoundException {
        if (currentView == null) {
            throw new CommentNotFoundException();
        }
        return comments.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return comments.asUnmodifiableObservableList().size() + " leaves";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CurrentViewApplicant // instanceof handles nulls
                && application.equals(((CurrentViewApplicant) other).application)
                && comments.equals(((CurrentViewApplicant) other).comments)
                && currentView.equals(((CurrentViewApplicant) other).currentView));
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentView, application, comments);
    }
}

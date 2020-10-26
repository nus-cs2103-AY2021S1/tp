package com.eva.model.comment.exceptions;

public class DuplicateCommentException extends RuntimeException {
    public DuplicateCommentException() {
        super("Operation would result in duplicate comments");
    }
}

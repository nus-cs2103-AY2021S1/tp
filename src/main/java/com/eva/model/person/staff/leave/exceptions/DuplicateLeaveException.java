package com.eva.model.person.staff.leave.exceptions;

public class DuplicateLeaveException extends RuntimeException {
    public DuplicateLeaveException() {
        super("Operation would result in duplicate leaves");
    }
}

package com.eva.model.person.comment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Comment {

    public final LocalDate date;
    public final String description;
    private String instruction;
    private int identity;
    private static int identifier = 0;

    public Comment(LocalDate date, String description, String instruction) {
        this.date = date;
        this.description = description;
        this.instruction = instruction;
    }

    public Comment(LocalDate date, String description) {
        this.date = date;
        this.description = description;
        this.instruction = "Present";
        this.identity = identifier;
        identifier++;
    }

    public Comment(LocalDate date, String description, int identity) {
        this.date = date;
        this.description = description;
        this.instruction = "Present";
        this.identity = identity;
        if (identity + 1 > identifier) {
            identifier = identity + 1;
        }
    }

    public Comment(int identity, String instruction) {
        this.date = null;
        this.description = null;
        this.identity = identity;
        this.instruction = instruction;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && description.equals(((Comment) other).description)
                && date.equals(((Comment) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, date);
    }

    public static boolean isValidComment(String comment) {
        return true;
    }

    public String getInstruction() {
        if (this.instruction == "Present") {
            return "Present";
        }
        return this.instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public int getIdentity() {
        return this.identity;
    }

    public String toString() {
        return this.identity + ": " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}

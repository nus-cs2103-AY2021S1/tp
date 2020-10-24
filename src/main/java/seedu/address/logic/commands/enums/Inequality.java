package seedu.address.logic.commands.enums;

public enum Inequality {
    LESSER_THAN("<"),
    LESSER_THAN_OR_EQUAL_TO("<="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL_TO(">=");

    private String s;
    Inequality(String s) {
        this.s = s;
    }

    public boolean matches(String s) {
        return this.s.equals(s);
    }

    public static Inequality get(String s) {
        for (Inequality ineq: Inequality.values()) {
            if (ineq.matches(s)) {
                return ineq;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return s;
    }
}

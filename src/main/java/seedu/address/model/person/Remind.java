package seedu.address.model.person;

public class Remind {
    private boolean isReminded;

    public Remind() {
        this.isReminded = false;
    }

    public Remind setReminder() {
        Remind reminded = new Remind();
        reminded.isReminded = true;
        return reminded;
    }

    public boolean isReminded() {
        return isReminded;
    }
}

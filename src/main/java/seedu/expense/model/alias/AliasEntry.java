package seedu.expense.model.alias;

public class AliasEntry {
    private final String key;
    private final String value;

    public AliasEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}

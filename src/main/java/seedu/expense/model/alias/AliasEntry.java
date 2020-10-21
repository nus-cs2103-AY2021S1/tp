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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AliasEntry)) {
            return false;
        }
        return this.key.equals(((AliasEntry) o).key)
                && this.value.equals(((AliasEntry) o).value);
    }
}

package nustorage.model.record;

import java.util.List;
import java.util.function.Predicate;

import nustorage.commons.util.StringUtil;

public class InventoryRecordNameContainsKeywordsPredicate implements Predicate<InventoryRecord> {

    private final List<String> keywords;

    public InventoryRecordNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(InventoryRecord inventoryRecord) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(inventoryRecord.getItemName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryRecordNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InventoryRecordNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}

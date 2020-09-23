package nustorage.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nustorage.model.record.FinanceRecord;

public class FinanceAccount {

    private final List<FinanceRecord> financeRecords;

    public FinanceAccount() {
        financeRecords = new ArrayList<>();
    }

    public FinanceAccount(List<FinanceRecord> financeRecords) {
        this.financeRecords = financeRecords;
    }

    public void addRecord(FinanceRecord record) {
        financeRecords.add(record);
    }

    public FinanceRecord removeRecord(int index) {
        return financeRecords.remove(index);
    }

    public int count() {
        return financeRecords.size();
    }

    /**
     * Returns the net transaction amount of all finance records
     */
    public double netProfit() {
        return financeRecords.stream()
                .mapToDouble(FinanceRecord::getAmount)
                .sum();
    }

    public List<FinanceRecord> filterRecords (Predicate<FinanceRecord> filter) {
        return financeRecords.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return financeRecords.stream()
                .map(FinanceRecord::toString)
                .collect(Collectors.joining("\n"));
    }
}

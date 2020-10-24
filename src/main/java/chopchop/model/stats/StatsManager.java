package chopchop.model.stats;

import java.time.LocalDateTime;
import java.util.List;

import chopchop.commons.util.Pair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatsManager {
    private ObservableList<Pair<String, LocalDateTime>> records = FXCollections.observableArrayList();

    public StatsManager(List<Pair<String, LocalDateTime>> records) {
        this.records.addAll(records);
    }

    public ObservableList<Pair<String, LocalDateTime>> getRecords() {
        return this.records;
    }

    public void pop() {
        assert records.size() > 0;
        this.records.remove(records.remove(records.size() - 1));
    }

    public void add() {
        this.records.add(new Pair("NEW", LocalDateTime.now()));
    }

}

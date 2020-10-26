package chopchop.ui;


import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import chopchop.commons.util.Pair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for displaying pinned information.
 * Displays results of statistics.
 */
public class StatsBox extends UiPart<Region> {
    private static final String EMPTY_PROMPT = "You haven't cooked anything.";
    private static final String FXML = "PinBox.fxml";

    @FXML
    private TextArea pins;

    @FXML
    private TextArea header1;

    @FXML
    private ListView<String> listView;

    /**
     * Creates a {@code PinBox}.
     */
    public StatsBox() {
        super(FXML);
        pins.setText("Statistics\n");
        ArrayList<String> testList = new ArrayList<>(
            Arrays.asList("recipe 1 2020-02-13", "recipe 2 2020-02-12"));
        renderList(testList);

    }

    public void setContent() {
        final ObservableList<String> names = FXCollections.observableArrayList();
        final ObservableList<String> data = FXCollections.observableArrayList();
        this.listView = new ListView(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);
        names.addAll(
            "Adam", "Alex", "Alfred", "Albert",
            "Brenda", "Connie", "Derek", "Donny",
            "Lynne", "Myrtle", "Rose", "Rudolph",
            "Tony", "Trudy", "Williams", "Zach"
        );
        for (int i = 0; i < 18; i++) {
            data.add("anonym");
        }
        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));
    }

    private String formatRecords(ObservableList<Pair<String, LocalDateTime>> records) {
        List<Pair<String, LocalDateTime>> outputList = new ArrayList<>();
        int i = 0;
        while (i < 3 && records.size() != 0) {
            outputList.add(records.remove(records.size() - 1));
            i++;
        }
        String output = outputList.stream()
            .map(x -> String.format("%s, %s", x.fst(),
                x.snd().format(DateTimeFormatter.ofPattern("dd-MMM-yy hh:mm a"))))
            .collect(Collectors.joining("\n"));
        return output.toString();
    }

    /**
     * A vertical scrollable list. Useful for showing a bunch of Strings wrapped in each
     * panel.
     */
    private void renderList(List<String> inputList) {
        ObservableList<String> items = FXCollections.observableArrayList(inputList);
        listView.setItems(items);


        //-------------------------------style-----------------------------------------
        listView.setPrefWidth(100);
        listView.setPrefHeight(120);
        /*
        final ObservableList names =
            FXCollections.observableArrayList();
        final ObservableList data =
            FXCollections.observableArrayList();
        listView.setPrefSize(200, 250);
        listView.setEditable(true);
        names.addAll(
            "Adam", "Alex", "Alfred", "Albert",
            "Brenda", "Connie", "Derek", "Donny",
            "Lynne", "Myrtle", "Rose", "Rudolph",
            "Tony", "Trudy", "Williams", "Zach"
        );
        for (int i = 0; i < 18; i++) {
            data.add("anonym");
        }
        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));
         */
    }

    /**
     * A bar graph for showing the quantities.
     */
    private XYChart renderChart(List<Pair<String, Integer>> inputList, String xLabel,
                                String yLabel) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(
            inputList.stream().map(Pair::fst).collect(Collectors.toList())
        ));
        xAxis.setLabel(xLabel);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);
        LineChart lineChart = new LineChart(xAxis, yAxis);
        return lineChart;
    }

    public void setBoxContent(String boxContent) {
        requireNonNull(boxContent);
    }

}

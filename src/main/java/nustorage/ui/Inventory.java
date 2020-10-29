package nustorage.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import nustorage.logic.Logic;
import nustorage.model.record.InventoryRecord;
import nustorage.ui.uilogic.UiLogic;

public class Inventory extends UiPart<Region> {

    private static final String FXML = "Inventory.fxml";

    @FXML
    private TableView<InventoryRecord> tableView;
    @FXML
    private TableColumn<InventoryRecord, String> idCol;
    @FXML
    private TableColumn<InventoryRecord, String> itemNameCol;
    @FXML
    private TableColumn<InventoryRecord, String> costCol;
    @FXML
    private TableColumn<InventoryRecord, Integer> quantityCol;
    @FXML
    private TableColumn<InventoryRecord, String> financeIdCol;

    /**
     * Sets the display for the Inventory tab in the user interface.
     */
    public Inventory(Logic logic, UiLogic uiLogic) {
        super(FXML);
        final String[] financeId = {""};
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // to prevent side-scrolling
        tableView.getItems().setAll(parseInventoryList(logic));
        idCol.setCellValueFactory(new PropertyValueFactory<>("UiUsableIndex"));
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        costCol.setCellValueFactory(item -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(String.valueOf(item.getValue()));
            return property;
        });
        financeIdCol.setCellValueFactory(item -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(String.valueOf(item.getValue().getFinanceId()));
            financeId[0] = String.valueOf(item.getValue().getFinanceId());
            return property;
        });
        Callback<TableColumn<InventoryRecord, String>,
                TableCell<InventoryRecord, String>> cellFactory = new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<InventoryRecord, String> param) {
                        final TableCell<InventoryRecord, String> cell = new TableCell<>() {

                            final Button button = new Button(financeId[0]);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else if (item.equals("0")) {
                                    button.setDisable(true);
                                    setText(financeId[0]);
                                } else {
                                    button.setOnAction(event -> {
                                        try {
                                            uiLogic.execute("goto_finance");
                                            logic.execute(String.format("find_finance %s", financeId[0]));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    });

                                    setGraphic(button);
                                }

                            }
                        };
                        return cell;
                    }
                };
        financeIdCol.setCellFactory(cellFactory);

    }

    /**
     * Parses the filtered list in model to update the indexes and put it into a list.
     * @param logic Logic
     * @return List of Items.
     */
    private List<InventoryRecord> parseInventoryList(Logic logic) {
        List<InventoryRecord> list = new ArrayList<>();
        for (int i = 0; i < logic.getFilteredInventory().size(); i++) {
            logic.getFilteredInventory().get(i).setUiUsableIndex(i + 1);
            list.add(logic.getFilteredInventory().get(i));
        }
        return list;
    }

}

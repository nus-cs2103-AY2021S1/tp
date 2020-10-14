//package seedu.address.ui;
//
//import java.util.logging.Logger;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javax.swing.plaf.synth.Region;
//import seedu.address.commons.core.LogsCenter;
//import seedu.address.model.account.entry.Entry;
//import seedu.address.model.account.entry.Expense;
//
//public class EntryListPanel extends UiPart<Region> {
//    protected static String suffixFXML = "ListPanel.FXML";
//    private final Logger logger = LogsCenter.getLogger(EntryListPanel.class);
//
//    @FXML
//    private ListView<Entry> entryListView;
//
//    /**
//     * Creates a {@code EntryListPanel} with the given {@code ObservableList}.
//     */
//    public EntryListPanel() {
//        super(suffixFXML);
//    }
//
//    class EntryListView extends ListCell<Entry> {
//        @Override
//        protected void updateItem(Entry entry, boolean isEmpty) {
//            super.updateItem(entry, isEmpty);
//
//            if (isEmpty || entry == null) {
//                setGraphic(null);
//                setText(null);
//            } else {
//                setGraphic(new EntryCard(entry, getIndex() + 1).getRoot());
//            }
//        }
//    }
//}

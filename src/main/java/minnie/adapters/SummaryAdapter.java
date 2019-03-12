package minnie.adapters;

import javafx.scene.control.ListCell;
import minnie.controller.SummaryController;
import minnie.model.Earnings;

public class SummaryAdapter extends ListCell<Earnings> {

    @Override
    protected void updateItem(Earnings earnings, boolean empty) {
        super.updateItem(earnings, empty);

        if (empty || earnings == null) {

            setGraphic(null);
        } else {

            SummaryController controller = new SummaryController(earnings);
            setGraphic(controller.getItemSummaryParent());
        }
    }
}

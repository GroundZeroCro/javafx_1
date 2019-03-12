package minnie.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import minnie.model.Earnings;

import java.io.IOException;

public class SummaryController {

    @FXML
    private Label summaryTime, summaryEarnings;
    @FXML
    private AnchorPane itemSummaryParent;

    private FXMLLoader mLoader;

    public SummaryController(Earnings earnings) {

        mLoader = new FXMLLoader(this.getClass().getResource("/fxml/item_monthly_earnings_summary.fxml"));
        mLoader.setController(this);
        try {
            mLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        summaryTime.setText(earnings.getDate());
        summaryEarnings.setText(String.format("%.2f", earnings.getMonthlyEarnings()*1.25));
    }

    public AnchorPane getItemSummaryParent() {
        return itemSummaryParent;
    }
}

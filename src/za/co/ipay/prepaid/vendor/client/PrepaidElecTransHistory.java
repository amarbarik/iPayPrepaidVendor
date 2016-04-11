package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by F4742443 on 2016/03/11.
 */
public class PrepaidElecTransHistory extends Composite {

    @UiTemplate("PrepaidElecTransHistory.ui.xml")
    interface PrepaidElecTransHistoryUiBinder extends UiBinder<Widget, PrepaidElecTransHistory> {
    }

    private static PrepaidElecTransHistoryUiBinder ourUiBinder = GWT.create(PrepaidElecTransHistoryUiBinder.class);

    @UiField
    FlexTable transTable;

    public PrepaidElecTransHistory() {
        initWidget(ourUiBinder.createAndBindUi(this));
        FlexTable.FlexCellFormatter cellFormatter = transTable.getFlexCellFormatter();
        transTable.insertRow(0);
        transTable.setText(0, 0, "Transaction ID");
        transTable.setText(0, 1, "Reference Number");
        transTable.setText(0, 2, "Response Code");
        transTable.setText(0, 3, "Meter Number");
        transTable.setText(0, 4, "Transaction Number");
        transTable.setCellSpacing(5);
        transTable.setCellPadding(3);
        transTable.getRowFormatter().addStyleName(0,"watchListHeader");
        transTable.addStyleName("watchList");
    }

    @UiHandler("goBack")
    void showPreviousTransaction(ClickEvent e) {
        RootPanel.get().clear();
        PrepaidElectricityForm prepaidElectricityForm
                = new PrepaidElectricityForm();
        RootPanel.get().add(prepaidElectricityForm);
    }
}
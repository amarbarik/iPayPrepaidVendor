package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.util.Util;

import java.util.List;

/**
 * Created by F4742443 on 2016/03/11.
 */
public class PrepaidElecTransHistory extends Composite {


    private ApplicationServiceAsync applicationService = GWT.create(ApplicationService.class);
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
        transTable.setText(0, 2, "Response");
        transTable.setText(0, 3, "Customer Message");
        transTable.setText(0, 4, "Meter Number");
        transTable.setText(0, 5, "Transaction Date");
        transTable.setCellSpacing(5);
        transTable.setCellPadding(3);
        transTable.getRowFormatter().addStyleName(0,"transHistoryHeader");
        transTable.addStyleName("transList");


        applicationService.getPrvsTransactions(new AsyncCallback<List<ElecTransactionDTO>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<ElecTransactionDTO> result) {
                if(result == null){
                    return;
                }

                for (int i = transTable.getRowCount() - 1; i > 0; i--) {
                    if (transTable.isCellPresent(i, 0)) {
                        transTable.clearCell(i, 0);
                        transTable.clearCell(i, 1);
                        transTable.clearCell(i, 2);
                        transTable.clearCell(i, 3);
                        transTable.clearCell(i, 4);
                        transTable.clearCell(i, 5);
                    } else {
                        transTable.clearCell(i, 2);
                    }
                }
                int index = 1;
                for(ElecTransactionDTO elecTransactionDTO : result) {

                    transTable.insertRow(index);
                    transTable.getRowFormatter().addStyleName(index, "transHistoryRow");

                    transTable.setText(index, 0, String.valueOf(elecTransactionDTO.getId()));
                    transTable.setText(index, 1, elecTransactionDTO.getReference());
                    transTable.setText(index, 2, (elecTransactionDTO.getResponseCode() + "-" + elecTransactionDTO.getResponse()));
                    transTable.setText(index, 3, elecTransactionDTO.getCustomerMsg());
                    transTable.setText(index, 4, elecTransactionDTO.getMeter().getMeterNumber());
                    transTable.setText(index, 5, (elecTransactionDTO.formatTransTime()));

                }

            }
        });
    }

    @UiHandler("goBack")
    void showPreviousTransaction(ClickEvent e) {
        RootPanel.get().clear();
        PrepaidElectricityForm prepaidElectricityForm
                = new PrepaidElectricityForm();
        RootPanel.get().add(prepaidElectricityForm);
    }
}
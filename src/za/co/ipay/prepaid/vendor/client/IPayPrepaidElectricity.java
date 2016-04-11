package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import za.co.ipay.prepaid.vendor.client.dto.MeterDTO;
import za.co.ipay.prepaid.vendor.client.dto.PayTypeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class IPayPrepaidElectricity implements EntryPoint {

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final ApplicationServiceAsync applicationService = GWT.create(ApplicationService.class);

    @UiField
    ListBox meterBox;

    @UiField
    ListBox payTypeBox;

    @UiField
    Button submit;

    @UiField
    TextBox amount;

    @UiField
    TextBox noOfToken;


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {




/*
        applicationService.getMeterDetails(new AsyncCallback<List<MeterDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                //TODO: Will have think about it
            }

            @Override
            public void onSuccess(List<MeterDTO> result) {
                for(MeterDTO meter :result) {
                    meterBox.addItem(meter.getMeterNumber());
                }
            }
        });

        applicationService.getPayTypeDetails(new AsyncCallback<List<PayTypeDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                //TODO: Will have think about it
            }

            @Override
            public void onSuccess(List<PayTypeDTO> result) {
                for(PayTypeDTO payType : result) {
                    payTypeBox.addItem(payType.getDescription(), payType.getName());
                }
            }
        });*/

        PrepaidElectricityForm prepaidElectricityForm
                = new PrepaidElectricityForm();
        RootPanel.get().add(prepaidElectricityForm);
    }
}

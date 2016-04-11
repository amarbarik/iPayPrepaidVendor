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
     * This is the entry point method.
     */
    public void onModuleLoad() {
        PrepaidElectricityForm prepaidElectricityForm
                = new PrepaidElectricityForm();
        RootPanel.get().add(prepaidElectricityForm);
    }
}

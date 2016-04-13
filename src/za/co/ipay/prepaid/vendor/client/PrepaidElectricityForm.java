package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.client.dto.MeterDTO;
import za.co.ipay.prepaid.vendor.client.dto.PayTypeDTO;

import java.util.List;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class PrepaidElectricityForm extends Composite {

    public static final String SELECT_A_METER_NO = "Select a Meter No";
    private ApplicationServiceAsync applicationService = GWT.create(ApplicationService.class);
    private MessageServiceAsync messageService = (MessageServiceAsync) GWT.create(MessageService.class);

    private static PrepaidElectricityFormUiBinder ourUiBinder = GWT.create(PrepaidElectricityFormUiBinder.class);

    private String result = "No Meter";

    private Integer lastTransNumber;

    private String responseMsg = "";

    // Annotation not needed as we use the default but this allows to change the path
    @UiTemplate("PrepaidElectricityForm.ui.xml")
    interface PrepaidElectricityFormUiBinder extends UiBinder<Widget, PrepaidElectricityForm> {
    }


    public PrepaidElectricityForm() {

        applicationService.getMeterDetails(new AsyncCallback<List<MeterDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                //TODO: Will have think about it
            }

            @Override
            public void onSuccess(List<MeterDTO> result) {
                meterBox.addItem(SELECT_A_METER_NO);
                for (MeterDTO meter : result) {
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
                for (PayTypeDTO payType : result) {
                    payTypeBox.addItem(payType.getDescription(), payType.getName());
                }
                payTypeBox.setEnabled(false);
                amount.setEnabled(false);
                noOfToken.setEnabled(false);
            }
        });

        initWidget(ourUiBinder.createAndBindUi(this));
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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

    @UiField
    Label displayResponse;

    @UiHandler("meterBox")
    void onSelectHandler(final ChangeEvent e) {
        if (meterBox.getSelectedValue().equals(PrepaidElectricityForm.SELECT_A_METER_NO)) {
            payTypeBox.setEnabled(false);
            amount.setEnabled(false);
            noOfToken.setEnabled(false);
        } else {
            payTypeBox.setEnabled(true);
            amount.setEnabled(true);
            noOfToken.setEnabled(true);
        }

    }

    @UiHandler("amount")
    void handleKeyPressForAmount(final KeyPressEvent event) {
        if (!Character.isDigit(event.getCharCode())) {
            ((TextBox) event.getSource()).cancelKey();
        }

    }

    @UiHandler("noOfToken")
    void handleKeyPressForToken(final KeyPressEvent event) {
        if (!Character.isDigit(event.getCharCode())) {
            ((TextBox) event.getSource()).cancelKey();
        }
    }


    @UiHandler("submit")
    void handleClick(final ClickEvent e) {

        if (meterBox.getSelectedValue().equals(SELECT_A_METER_NO)) {
            PrepaidElectricityForm.alertWidget("Error while submitting request.",
                    "Please select the meter number and then Submit!.").center();
            return;
        }


        applicationService.getLastTransNumber(new AsyncCallback<Integer>() {
            @Override
            public void onFailure(Throwable caught) {
                //TODO
                Window.alert("Something Went Wrong! Unable to fetch last tran number.");
            }

            @Override
            public void onSuccess(Integer result) {
                if (result != null)
                    lastTransNumber = result;
                else {
                    lastTransNumber = 1;
                }
                messageService.sendMessageAndGetResponse(meterBox.getSelectedValue(), amount.getValue(), noOfToken.getValue(),
                        payTypeBox.getSelectedValue(), lastTransNumber, new AsyncCallback<String>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                //TODO
                                Window.alert("Something Went Wrong! Unable to send message.");
                            }

                            @Override
                            public void onSuccess(String result) {
                                responseMsg = result;
                                messageService.transferResponse(responseMsg, new AsyncCallback<ElecTransactionDTO>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                        Window.alert("Failed to transform message: " + caught.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(final ElecTransactionDTO elecTransactionDTO) {
                                        elecTransactionDTO.setTranNumber(lastTransNumber);
                                        applicationService.getMeterByNumber(meterBox.getSelectedValue(), new AsyncCallback<MeterDTO>() {
                                            @Override
                                            public void onFailure(Throwable caught) {
                                                Window.alert("Error while getting meter by number!! " + caught.getMessage());

                                            }

                                            @Override
                                            public void onSuccess(MeterDTO result) {
                                                elecTransactionDTO.setMeter(result);
                                                applicationService.getPayTypeByName(payTypeBox.getSelectedValue(), new AsyncCallback<PayTypeDTO>() {
                                                    @Override
                                                    public void onFailure(Throwable caught) {
                                                        Window.alert("Error while getting payType by name!! " + caught.getMessage());

                                                    }

                                                    @Override
                                                    public void onSuccess(PayTypeDTO result) {
                                                        elecTransactionDTO.setPayType(result);
                                                        applicationService.saveElecTransaction(elecTransactionDTO, new AsyncCallback<Long>() {
                                                            @Override
                                                            public void onFailure(Throwable caught) {
                                                                Window.alert("Error while saving the transaction " + caught.getMessage());

                                                            }

                                                            @Override
                                                            public void onSuccess(Long result) {
                                                                System.out.println("Msg Saved Successfully");

                                                            }
                                                        });

                                                    }
                                                });

                                            }
                                        });


                                        displayResponse.setText("Response: " + (elecTransactionDTO.getResponse().equals("OK") ?
                                                elecTransactionDTO.getCustomerMsg() + "\n and here's the token Number: " +
                                                        elecTransactionDTO.getTokenDTOs().get(0).getNumber()
                                        : elecTransactionDTO.getResponse()));

                                    }
                                });
                            }
                        });
            }
        });
    }

    public static DialogBox alertWidget(final String header, final String content) {
        final DialogBox box = new DialogBox();
        final VerticalPanel panel = new VerticalPanel();
        // few empty labels to make widget larger
        final Label emptyLabel = new Label("");
        emptyLabel.setSize("auto","25px");
        panel.add(emptyLabel);

        box.setText(header);
        final Label label = new Label(content);
        label.setStyleName("errorBox");
        panel.add(label);
        final Button buttonClose = new Button("Close",new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                box.hide();
            }
        });

        panel.add(emptyLabel);
        buttonClose.setWidth("90px");
        panel.add(buttonClose);
        panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_CENTER);
        box.add(panel);
        return box;
    }

    @UiHandler("prvTrans")
    void showPreviousTransaction(ClickEvent e) {
        RootPanel.get().clear();
        PrepaidElecTransHistory prepaidElecTransHistory = new PrepaidElecTransHistory();
        RootPanel.get().add(prepaidElecTransHistory);
    }
}
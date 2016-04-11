package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.jdom.Document;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;

/**
 * Created by F4742443 on 2016/03/11.
 */
public interface MessageServiceAsync {
    void sendMessageAndGetResponse(String meterNumber, String amount, String noOfToken,
                                   String payType, Integer lastTransNo, AsyncCallback<String> async);


    void transferResponse(String response, AsyncCallback<ElecTransactionDTO> async);
}

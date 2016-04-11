package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import org.jdom.Document;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;

/**
 * Created by F4742443 on 2016/03/11.
 */
@RemoteServiceRelativePath("messageService")
public interface MessageService extends RemoteService {
    String sendMessageAndGetResponse(String meterNumber, String amount, String noOfToken,
                                     String payType, Integer lastTransNo);

    ElecTransactionDTO transferResponse(String response);

}

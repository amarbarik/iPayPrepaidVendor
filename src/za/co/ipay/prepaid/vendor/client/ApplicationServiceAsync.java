package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.client.dto.MeterDTO;
import za.co.ipay.prepaid.vendor.client.dto.PayTypeDTO;
import za.co.ipay.prepaid.vendor.domain.ElecTransaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * The async counterpart of <code>ApplicationService</code>.
 */
public interface ApplicationServiceAsync {
    void greetServer(String input, AsyncCallback<String> callback)
            throws IllegalArgumentException;

    void getMeterDetails(AsyncCallback<List<MeterDTO>> callback);

    void getPayTypeDetails(AsyncCallback<List<PayTypeDTO>> callback);

    void getLastTransNumber(AsyncCallback<Integer> callback);

    void saveElecTransaction(ElecTransactionDTO elecTransaction, AsyncCallback<Long> async);

    void getMeterByNumber(String number, AsyncCallback<MeterDTO> async);

    void getPayTypeByName(String name, AsyncCallback<PayTypeDTO> async);

    void getPrvsTransactions(AsyncCallback<List<ElecTransactionDTO>> async);
}

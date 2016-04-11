package za.co.ipay.prepaid.vendor.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.client.dto.MeterDTO;
import za.co.ipay.prepaid.vendor.client.dto.PayTypeDTO;
import za.co.ipay.prepaid.vendor.domain.ElecTransaction;

import java.util.List;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("appservice")
public interface ApplicationService extends RemoteService {
    String greetServer(String name) throws IllegalArgumentException;

    List<MeterDTO> getMeterDetails();

    List<PayTypeDTO> getPayTypeDetails();

    Integer getLastTransNumber();

    Long saveElecTransaction(ElecTransactionDTO elecTransaction);

    MeterDTO getMeterByNumber(String number);

    PayTypeDTO getPayTypeByName(String name);

    List<ElecTransactionDTO> getPrvsTransactions();

}

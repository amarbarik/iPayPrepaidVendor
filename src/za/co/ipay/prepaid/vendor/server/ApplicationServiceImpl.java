package za.co.ipay.prepaid.vendor.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.hibernate.Session;
import za.co.ipay.prepaid.vendor.client.ApplicationService;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.client.dto.MeterDTO;
import za.co.ipay.prepaid.vendor.client.dto.PayTypeDTO;
import za.co.ipay.prepaid.vendor.domain.ElecTransaction;
import za.co.ipay.prepaid.vendor.domain.Meter;
import za.co.ipay.prepaid.vendor.domain.PayType;
import za.co.ipay.prepaid.vendor.shared.FieldVerifier;
import za.co.ipay.prepaid.vendor.util.DataBaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ApplicationServiceImpl extends RemoteServiceServlet implements
        ApplicationService {

    public String greetServer(String input) throws IllegalArgumentException {
        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back to
            // the client.
            throw new IllegalArgumentException(
                    "Name must be at least 4 characters long");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script vulnerabilities.
        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + input + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent;
    }

    public List<MeterDTO> getMeterDetails() {

        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Meter> meters = new ArrayList<>(session.createQuery("from meter ").list());
        List<MeterDTO> meterDTOs = new ArrayList<>(meters.size());
        for(Meter meter : meters) {
            meterDTOs.add(createMeterDTO(meter));
        }
        session.getTransaction().commit();
        return meterDTOs;
    }

    private MeterDTO createMeterDTO(Meter meter) {
        return new MeterDTO(meter.getId(), meter.getMeterNumber(), meter.getAddress(), meter.isEnabled());
    }

    public List<PayTypeDTO> getPayTypeDetails() {
        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<PayType> payTypes = new ArrayList<>(session.createQuery("from pay_type ").list());
        List<PayTypeDTO> meterDTOs = new ArrayList<>(payTypes.size());
        for(PayType payType : payTypes) {
            meterDTOs.add(createPayTypeDTO(payType));
        }
        session.getTransaction().commit();
        return meterDTOs;
    }

    @Override
    public Integer getLastTransNumber() {
        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ElecTransaction> list = session.createQuery("select max (tr.transactionNumber) from elec_trans tr ").list();
        session.getTransaction().commit();
        if(list.size() > 0 ) {
            ElecTransaction elecTransaction = list.get(0);
            return elecTransaction != null ? elecTransaction.getTransactionNumber() : null;
        }
        return null;
    }

    @Override
    public Long saveElecTransaction(ElecTransactionDTO elecTransaction) {
        return null;
    }

    private PayTypeDTO createPayTypeDTO(PayType payType) {
        return new PayTypeDTO(payType.getId(), payType.getName(), payType.getDescription(), payType.getEnabled());
    }


    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}

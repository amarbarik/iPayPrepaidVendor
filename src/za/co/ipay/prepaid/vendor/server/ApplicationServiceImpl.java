package za.co.ipay.prepaid.vendor.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.hibernate.Session;
import za.co.ipay.prepaid.vendor.client.ApplicationService;
import za.co.ipay.prepaid.vendor.client.dto.ElecTransactionDTO;
import za.co.ipay.prepaid.vendor.client.dto.MeterDTO;
import za.co.ipay.prepaid.vendor.client.dto.PayTypeDTO;
import za.co.ipay.prepaid.vendor.client.dto.TokenDTO;
import za.co.ipay.prepaid.vendor.domain.ElecTransaction;
import za.co.ipay.prepaid.vendor.domain.Meter;
import za.co.ipay.prepaid.vendor.domain.PayType;
import za.co.ipay.prepaid.vendor.domain.Token;
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
        List<ElecTransaction> list = session.createQuery("select tr from elec_trans tr order by tr.transactionNumber desc ").list();
        session.getTransaction().commit();
        if(list.size() > 0 ) {
            ElecTransaction elecTransaction = list.get(0);
            return elecTransaction != null ? elecTransaction.getTransactionNumber() : null;
        }
        return null;
    }

    @Override
    public Long saveElecTransaction(ElecTransactionDTO elecTransactionDTO) {

        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();

        System.out.println("------------------------SAVE");
        ElecTransaction elecTransaction = new ElecTransaction();
        elecTransaction.setCustomerMsg(elecTransactionDTO.getCustomerMsg());
        elecTransaction.setResponseCode(elecTransactionDTO.getResponseCode());
        elecTransaction.setResponse(elecTransactionDTO.getResponse());
        elecTransaction.setReference(elecTransactionDTO.getReference());
        elecTransaction.setRtlrMsg(elecTransactionDTO.getRtlrMsg());
        elecTransaction.setTransactionNumber(elecTransactionDTO.getTranNumber() + 1);

        System.out.println("Checking PayType" + elecTransactionDTO.getPayType());
        int payTypeId = elecTransactionDTO.getPayType().getId();

        System.out.println("CHECKING PAY TYPE ID" + payTypeId);
        elecTransaction.setPayType(session.load(PayType.class, payTypeId));
        System.out.println("CHECKING METER" + elecTransactionDTO.getMeter());

        elecTransaction.setMeter(session.load(Meter.class, elecTransactionDTO.getMeter().getId()));
        System.out.println("Checking Token DTOS size:" + elecTransactionDTO.getTokenDTOs().size());
        if(elecTransactionDTO.getTokenDTOs().size() > 0) {
            for (TokenDTO tokenDTO : elecTransactionDTO.getTokenDTOs()) {
                Token token = new Token(tokenDTO);
                elecTransaction.getTokens().add(token);
            }
        }
        System.out.println("Before SAVA");
        session.save(elecTransaction);
        session.getTransaction().commit();
        System.out.println("AFTER COMMIT");
        return Long.valueOf(elecTransaction.getId());
    }

    @Override
    public MeterDTO getMeterByNumber(String number) {
        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Meter> list = session.createQuery("select m from meter m where m.meterNumber like :mNumber")
                .setParameter("mNumber", number).list();
        session.getTransaction().commit();
        if(list.size() > 0 ) {
            Meter meter = list.get(0);
            return createMeterDTO(meter);
        }
        return null;
    }

    @Override
    public PayTypeDTO getPayTypeByName(String name) {

        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<PayType> list = session.createQuery("select m from pay_type m where m.name like :name")
                .setParameter("name", name).list();
        session.getTransaction().commit();
        if(list.size() > 0 ) {
            PayType payType = list.get(0);
            return createPayTypeDTO(payType);
        }
        return null;
    }

    @Override
    public List<ElecTransactionDTO> getPrvsTransactions() {

        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ElecTransaction> elecTransactions = new ArrayList<>(session.createQuery("from elec_trans ").list());
        List<ElecTransactionDTO> elecTransactionDTOs = new ArrayList<>(elecTransactions.size());
        for(ElecTransaction elecTransaction : elecTransactions) {
            elecTransactionDTOs.add(getElectTranDTO(elecTransaction));
        }
        session.getTransaction().commit();
        return elecTransactionDTOs;
    }

    private ElecTransactionDTO getElectTranDTO(ElecTransaction elecTransaction) {
        return new ElecTransactionDTO(elecTransaction.getId(),
                createPayTypeDTO(elecTransaction.getPayType()), createMeterDTO(elecTransaction.getMeter()),
                elecTransaction.getReference(), elecTransaction.getResponse(), elecTransaction.getResponseCode(),
                createTokenDTOList(elecTransaction.getTokens()),elecTransaction.getCustomerMsg(), elecTransaction.getRtlrMsg(),
                elecTransaction.getTransactionNumber());
    }

    private List<TokenDTO> createTokenDTOList(List<Token> tokens) {
        List<TokenDTO> tokenDTOs =  new ArrayList<>();
        for (Token token : tokens) {
            tokenDTOs.add(createTokenDTO(token));
        }
        return tokenDTOs;
    }

    private TokenDTO createTokenDTO(Token token) {
        return new TokenDTO(token.getTokenType(),token.getUnits(), token.getReceiptNumber(), token.getTax(),
                token.getMessage(), token.getAmount(), token.getDate(), token.getNumber());
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

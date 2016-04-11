package za.co.ipay.prepaid.vendor.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by F4742443 on 2016/03/10.
 */
@Entity(name = "elec_trans")
public class ElecTransaction  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pay_type_id")
    private PayType payType;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

    @Column(name = "ref")
    private String reference;

    @Column(name = "response")
    private String response;

    @Column(name = "res_code")
    private String responseCode;

    @Column(name = "trans_number")
    private int transactionNumber;

    @JoinColumn(name = "token_id")
    @OneToMany
    private List<Token> tokens;

    @Version
    private int version;

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}

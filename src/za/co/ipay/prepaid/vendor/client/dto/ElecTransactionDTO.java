package za.co.ipay.prepaid.vendor.client.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class ElecTransactionDTO implements Serializable {

    private int id;


    private PayTypeDTO payType;

    private MeterDTO meter;

    private String reference;

    private String response;

    private String responseCode;

    List<TokenDTO> tokenDTOs;

    public List<TokenDTO> getTokenDTOs() {
        return tokenDTOs;
    }

    public void setTokenDTOs(List<TokenDTO> tokenDTOs) {
        this.tokenDTOs = tokenDTOs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PayTypeDTO getPayType() {
        return payType;
    }

    public void setPayType(PayTypeDTO payType) {
        this.payType = payType;
    }

    public MeterDTO getMeter() {
        return meter;
    }

    public void setMeter(MeterDTO meter) {
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

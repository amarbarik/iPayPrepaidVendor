package za.co.ipay.prepaid.vendor.client.dto;

import com.google.gwt.i18n.client.DateTimeFormat;
import za.co.ipay.prepaid.vendor.domain.ElecTransaction;
import za.co.ipay.prepaid.vendor.domain.Token;
import za.co.ipay.prepaid.vendor.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

    List<TokenDTO> tokenDTOs = new ArrayList<>();
    private String customerMsg;
    private String rtlrMsg;
    private int tranNumber;
    private Date transTime;

    public ElecTransactionDTO(int id, PayTypeDTO payType, MeterDTO meter, String reference, String response,
                              String responseCode, List<TokenDTO> tokenDTOs, String customerMsg, String rtlrMsg, int tranNumber, Date transTime) {
        this.id = id;
        this.payType = payType;
        this.meter = meter;
        this.reference = reference;
        this.response = response;
        this.responseCode = responseCode;
        this.tokenDTOs = tokenDTOs;
        this.customerMsg = customerMsg;
        this.rtlrMsg = rtlrMsg;
        this.tranNumber = tranNumber;
        this.transTime = transTime;
    }

    public ElecTransactionDTO() {
    }

    public Date getTransTime() {
        return transTime;
    }

    public String formatTransTime(){

        return String.valueOf(transTime).substring(0,19);
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public String getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(String customerMsg) {
        this.customerMsg = customerMsg;
    }

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

    public void setRtlrMsg(String rtlrMsg) {
        this.rtlrMsg = rtlrMsg;
    }

    public String getRtlrMsg() {
        return rtlrMsg;
    }

    public int getTranNumber() {
        return tranNumber;
    }

    public void setTranNumber(int tranNumber) {
        this.tranNumber = tranNumber;
    }
}

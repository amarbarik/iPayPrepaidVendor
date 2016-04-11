package za.co.ipay.prepaid.vendor.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class TokenDTO  implements Serializable {

    public static final String STD_TOKEN = "Standard Token";
    public static final String BSST_TOKEN = "BSST Token";

    private int id;
    private ElecTransactionDTO elecTransaction;

    private String tokenType;
    private String units;
    private String receiptNumber;
    private String tax;
    private String message;
    private String amount;
    private Date date;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TokenDTO(int id) {
        this.id = id;
    }

    public TokenDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ElecTransactionDTO getElecTransaction() {
        return elecTransaction;
    }

    public void setElecTransaction(ElecTransactionDTO elecTransaction) {
        this.elecTransaction = elecTransaction;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package za.co.ipay.prepaid.vendor.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by F4742443 on 2016/03/10.
 */
@Entity(name = "token")
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "trans_id")
    private ElecTransaction elecTransaction;

    @Column(name = "token_type")
    private String tokenType;
    @Column(name = "units")
    private String units;
    @Column(name = "recipt_number")
    private String receiptNumber;
    @Column
    private String tax;
    @Column(name = "msg")
    private String message;
    @Column
    private String amount;
    @Column
    private Date date;
    @Column
    private String number;

    @Version
    private int version;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public ElecTransaction getElecTransaction() {
        return elecTransaction;
    }

    public void setElecTransaction(ElecTransaction elecTransaction) {
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

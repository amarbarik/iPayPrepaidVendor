package za.co.ipay.prepaid.vendor.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by F4742443 on 2016/03/08.
 */
@Entity(name = "meter")
public class Meter implements Serializable {
    public Meter() {
    }

    public Meter(String meterNumber, String address, int enabled) {
        this.meterNumber = meterNumber;
        this.address = address;
        this.enabled = enabled;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meter_id", length = 18)
    private int id;

    @Column(name = "meter_number", length = 40)
    private String meterNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "enabled", length = 1)
    private int enabled;

    @Version
    private int version;

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

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int isEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
